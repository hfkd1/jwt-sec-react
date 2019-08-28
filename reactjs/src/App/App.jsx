import React, { Component } from "react";
import { BrowserRouter, Route } from "react-router-dom";
import { LoginPage } from "../LoginPage/LoginPage";
import { Main } from "../Main/Main";

class App extends Component {
  render() {
    return (
        <BrowserRouter>
          <div>
            <Route path={["/","/login"]} exact component={LoginPage} />
            <Route path="/main" component={Main} />
          </div>
        </BrowserRouter>
    );
  }
}

export default App;
