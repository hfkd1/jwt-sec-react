import "@fullcalendar/core/main.css";
import "@fullcalendar/daygrid/main.css";
import "@fullcalendar/timegrid/main.css";
import classNames from "classnames";
import "primeflex/primeflex.css";
import "primeicons/primeicons.css";
import "primereact/resources/primereact.min.css";
import "primereact/resources/themes/nova-light/theme.css";
import React, { Component, useContext } from "react";
import { Route } from "react-router-dom";
import { AppFooter } from "../Components/AppFooter";
import { AppMenu } from "../Components/AppMenu";
import { AppProfile } from "../Components/AppProfile";
import { AppTopbar } from "../Components/AppTopbar";
import { Dashboard } from "../Dashboard/Dashboard";
import { LanguageContext, languages } from "../LanguageContext/LanguageContext";
import LanguageSwitchDropdown from "../LanguageContext/LanguageSwitchDropdown";
import "../layout/layout.scss";
import axios from "axios";

export class Main extends Component {
  constructor() {
    super();

    this.state = {
      status: null,

      groups: [],

      layoutMode: "static",
      layoutColorMode: "dark",
      staticMenuInactive: false,
      overlayMenuActive: false,
      mobileMenuActive: false,
      language: languages.english,
      dashboard: languages.dashboard_en,
      topbar: languages.topbar_en,
      profile: languages.profile_en
    };

    this.onWrapperClick = this.onWrapperClick.bind(this);
    this.onToggleMenu = this.onToggleMenu.bind(this);
    this.onSidebarClick = this.onSidebarClick.bind(this);
    this.onMenuItemClick = this.onMenuItemClick.bind(this);

    this.switchLanguage = () => {
      this.setState(state => ({
        language:
          state.language === languages.english
            ? languages.turkish
            : languages.english,
        dashboard:
          state.language === languages.english
            ? languages.dashboard_tr
            : languages.dashboard_en,
        topbar:
          state.language === languages.english
            ? languages.topbar_tr
            : languages.topbar_en,
        profile:
          state.language === languages.english
            ? languages.profile_tr
            : languages.profile_en
      }));
    };
  }

  onThemeSwitchClick() {
    if (this.state.layoutColorMode === "dark") {
      this.setState({ layoutColorMode: "light" });
    } else {
      this.setState({ layoutColorMode: "dark" });
    }
  }

  onWrapperClick(event) {
    if (!this.menuClick) {
      this.setState({
        overlayMenuActive: false,
        mobileMenuActive: false
      });
    }

    this.menuClick = false;
  }

  onToggleMenu(event) {
    this.menuClick = true;

    if (this.isDesktop()) {
      if (this.state.layoutMode === "overlay") {
        this.setState({
          overlayMenuActive: !this.state.overlayMenuActive
        });
      } else if (this.state.layoutMode === "static") {
        this.setState({
          staticMenuInactive: !this.state.staticMenuInactive
        });
      }
    } else {
      const mobileMenuActive = this.state.mobileMenuActive;
      this.setState({
        mobileMenuActive: !mobileMenuActive
      });
    }

    event.preventDefault();
  }

  onSidebarClick(event) {
    this.menuClick = true;
  }

  onMenuItemClick(event) {
    if (!event.item.items) {
      this.setState({
        overlayMenuActive: false,
        mobileMenuActive: false
      });
    }
  }

  createMenu(l) {
    if (l === "EN") {
      return [
        {
          label: "Dashboard",
          icon: "pi pi-fw pi-home",
          command: () => {
            window.location = "#/";
          }
        },

        {
          label: "Template Pages",
          icon: "pi pi-fw pi-file",
          items: [
            // { label: "Login Page", icon: "pi pi-fw pi-circle-off", to: "/login" }
          ]
        },

        {
          label: "Theme Switch",
          icon: "pi pi-fw pi-spinner",
          command: () => {
            this.onThemeSwitchClick();
          }
        }
      ];
    } else if (l === "TR") {
      return [
        {
          label: "Panel",
          icon: "pi pi-fw pi-home",
          command: () => {
            window.location = "#/";
          }
        },

        {
          label: "Şablon Sayfaları",
          icon: "pi pi-fw pi-file",
          items: [
            // { label: "Login Page", icon: "pi pi-fw pi-circle-off", to: "/login" }
          ]
        },

        {
          label: "Tema Değiştir",
          icon: "pi pi-fw pi-spinner",
          command: () => {
            this.onThemeSwitchClick();
          }
        }
      ];
    }
  }

  addClass(element, className) {
    if (element.classList) element.classList.add(className);
    else element.className += " " + className;
  }

  removeClass(element, className) {
    if (element.classList) element.classList.remove(className);
    else
      element.className = element.className.replace(
        new RegExp(
          "(^|\\b)" + className.split(" ").join("|") + "(\\b|$)",
          "gi"
        ),
        " "
      );
  }

  isDesktop() {
    return window.innerWidth > 1024;
  }

  componentDidUpdate() {
    if (this.state.mobileMenuActive)
      this.addClass(document.body, "body-overflow-hidden");
    else this.removeClass(document.body, "body-overflow-hidden");
  }
  componentDidMount() {
    console.log(localStorage.getItem("Authorization"));

    axios
      .get("http://localhost:8080/api/public/admin/users", {
        headers: {
          Authorization: localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        response.data.forEach(user => {
          console.log(user.username);
        });
        this.setState({ groups: response.data });
        console.log("------");
        console.log(this.state.groups);
        console.log("------");

        if (response.status === 200) {
          this.setState({ status: response.status });

          console.log(this.state.status);
        }
      })
      .catch(err => {
        this.setState({ status: err.response.status });

        if (this.state.status === 403) {
          console.log(this.state.status);
          console.log("yetkilendirme hatası: 403 status");
        } else {
          console.log("başka bir hatamız bulunmaktadır");
        }
      });
  }

  render() {
    const logo =
      this.state.layoutColorMode === "dark"
        ? "assets/layout/images/logo-white.svg"
        : "assets/layout/images/logo.svg";

    const wrapperClass = classNames("layout-wrapper", {
      "layout-overlay": this.state.layoutMode === "overlay",
      "layout-static": this.state.layoutMode === "static",
      "layout-static-sidebar-inactive":
        this.state.staticMenuInactive && this.state.layoutMode === "static",
      "layout-overlay-sidebar-active":
        this.state.overlayMenuActive && this.state.layoutMode === "overlay",
      "layout-mobile-sidebar-active": this.state.mobileMenuActive
    });

    const sidebarClassName = classNames("layout-sidebar", {
      "layout-sidebar-dark": this.state.layoutColorMode === "dark",
      "layout-sidebar-light": this.state.layoutColorMode === "light"
    });

    const { groups } = this.state;
    if (this.state.status === 403) {
      alert("403 statu alert deneme");
      return <p>Yetkilendirme hatası...</p>;
    } else {
      return (
        <LanguageContext.Provider
          value={{
            language: this.state.language,
            switchLanguage: this.switchLanguage,
            dashboard: this.state.dashboard,
            topbar: this.state.topbar,
            profile: this.state.profile
          }}
        >
          <div className={wrapperClass} onClick={this.onWrapperClick}>
            <AppTopbar onToggleMenu={this.onToggleMenu} />

            <div
              ref={el => (this.sidebar = el)}
              className={sidebarClassName}
              onClick={this.onSidebarClick}
            >
              <div className="layout-logo">
                <img alt="Logo" src={logo} />
                <LanguageSwitchDropdown></LanguageSwitchDropdown>
              </div>
              <AppProfile />
              <AppMenu
                model={this.createMenu(this.state.language)}
                onMenuItemClick={this.onMenuItemClick}
              />
            </div>

            <div className="layout-main">
              <h1>{this.state.language}</h1>
              <Route path="/main" exact component={Dashboard} />
              <h2>usernames</h2>
              {groups.map(group => (
                <div key={group.id}>{group.username}</div>
              ))}
            </div>

            <div className="layout-mask" />
          </div>
        </LanguageContext.Provider>
      );
    }
  }
}

//export default Main;
