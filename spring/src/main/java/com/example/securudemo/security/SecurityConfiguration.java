package com.example.securudemo.security;



import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.securudemo.repository.GroupMemberRepository;
import com.example.securudemo.repository.UserRepository;





@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPrincipalDetailsService userPrincipalDetailsService;
    private GroupMemberRepository groupMemberRepository;
    
    

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, GroupMemberRepository groupMemberRepository) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.groupMemberRepository=groupMemberRepository;
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
	        // jwt kullandığımız için session ce crsf e ihtiyacımız yok
	        .csrf().disable()
	        .cors().and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .addFilter(new JwtAuthFilter(authenticationManager()))
	        .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.groupMemberRepository))
	        .authorizeRequests()
	        .antMatchers(HttpMethod.POST, "/login").permitAll()
	        .antMatchers("/api/public/management/*").hasAnyAuthority("ACCESS_TEST1")
	        .antMatchers("/api/public/admin/*").hasAnyAuthority("ACCESS_TEST2")
	        .anyRequest().authenticated();
        
        //JWT ONCESİ ROLLENDİRME!!!!
//                .antMatchers("/login").permitAll()            
//                .antMatchers("/index.html").permitAll()
//                .antMatchers("/profile/**").authenticated()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
//                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
//                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
//                .antMatchers("/api/public/users").hasRole("ADMIN");
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/signin")
//                .loginPage("/login").permitAll()
//                .usernameParameter("txtUsername")
//                .passwordParameter("txtPassword")
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
//                .and()
//                .rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe");
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
