import classNames from "classnames";
import React, { Component } from "react";
import { LanguageContext } from "../LanguageContext/LanguageContext";

export class AppProfile extends Component {
  constructor() {
    super();
    this.state = {
      expanded: false
    };
    this.onClick = this.onClick.bind(this);
  }

  onClick(event) {
    this.setState({ expanded: !this.state.expanded });
    event.preventDefault();
  }

  render() {
    return (
      <LanguageContext.Consumer>
        {({ profile }) => (
          <div className="layout-profile">
            <div>
              <img src="assets/layout/images/profile.png" alt="" />
            </div>
            <button
              className="p-link layout-profile-link"
              onClick={this.onClick}
            >
              <span className="username">Claire Williams</span>
              <i className="pi pi-fw pi-cog" />
            </button>
            <ul
              className={classNames({
                "layout-profile-expanded": this.state.expanded
              })}
            >
              <li>
                <button className="p-link">
                  <i className="pi pi-fw pi-user" />
                  <span>{profile.account}</span>
                </button>
              </li>
              <li>
                <button className="p-link">
                  <i className="pi pi-fw pi-inbox" />
                  <span>{profile.notifications}</span>
                  <span className="menuitem-badge">2</span>
                </button>
              </li>
              <li>
                <button className="p-link">
                  <i className="pi pi-fw pi-power-off" />
                  <span>{profile.logout}</span>
                </button>
              </li>
            </ul>
          </div>
        )}
      </LanguageContext.Consumer>
    );
  }
}
