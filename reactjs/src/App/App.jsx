import React, { Component } from "react";
import { BrowserRouter, Route } from "react-router-dom";
import { LoginPage } from "../LoginPage/LoginPage";
import { Main } from "../Main/Main";
import { LanguageProvider } from "../LanguageContext";

class App extends Component {
  render() {
    return (
      <LanguageProvider>
        <BrowserRouter>
          <div>
            <Route path={["/","/login"]} exact component={LoginPage} />
            <Route path="/main" component={Main} />
          </div>
        </BrowserRouter>
      </LanguageProvider>
    );
  }
}

export default App;
