import React, { Component } from "react";
import { LanguageContext, languages } from "../LanguageContext/LanguageContext";
export class Dashboard extends Component {
  render() {
    return (
      <LanguageContext.Consumer>
        {({ dashboard }) => (
          <div>
            <h1>{dashboard.text1}</h1>
            <h1>{}</h1>
          </div>
        )}
      </LanguageContext.Consumer>
    );
  }
}
