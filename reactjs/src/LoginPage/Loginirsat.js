import React, { Component } from "react";
import { Button } from "primereact/button";
import { InputText } from "primereact/inputtext";
import "primeicons/primeicons.css";

import { Checkbox } from "primereact/checkbox";
import { FaBeer } from "react-icons/fa";

/*function Printbeybi(props) {
    return(<div>{props.naber}</div>)
    
}
*/

export class Loginirsat extends Component {
  constructor() {
    super();
    this.state = {
      checked: false,
      selectedvalue: "Mobile",
      Username: "",
      Password: ""
    };

    this.handleClick = this.handleClick.bind(this);
    this.loginClicked = this.loginClicked.bind(this);
  }

  handleClick(e) {
    e.preventDefault();
    alert("imma alert");
  }

  loginClicked(e) {
    var url =
      "http://localhost:8080/api/authenticate?username=" +
      this.state.Username +
      "&password=" +
      this.state.Password;
    fetch(url, {
      method: "GET" // *GET, POST, PUT, DELETE, etc.
      //body: JSON.stringify(data), // body data type must match "Content-Type" header
    }).then(response => {
      console.log(response.status);
      if (response.status === 200) {
        console.log("irsat kiral");
      } else {
        console.log("irsatkiral degil");
      }
    });
  }

  render() {
    return (
      <div className="layout-main p-fluid dashboard">
        <div className="p-grid p-justify-center">
          <div className="p-grid p-align-center vertical-container">
            <div className="p-grid p-dir-col p-justify-center">
              <div className="p-col">
                <div className="p-grid p-justify-center">
                  <span className="p-float-label">
                    <img src={"assets/layout/images/avatar_6.png"} alt="" />
                  </span>
                </div>
              </div>

              <div className="p-col bosluk">
                <div className="p-grid p-justify-center">
                  <span className="p-float-label">
                    <InputText
                      id="float-input"
                      type="text"
                      size="40"
                      value={this.state.Username}
                      onChange={e =>
                        this.setState({ Username: e.target.value })
                      }
                    />
                    <label htmlFor="float-input">Username</label>
                  </span>
                </div>
              </div>

              <div className="p-col bosluk">
                <div className="p-grid p-justify-center">
                  <span className="p-float-label">
                    <InputText
                      id="float-input"
                      type="password"
                      size="40"
                      value={this.state.Password}
                      onChange={e =>
                        this.setState({ Password: e.target.value })
                      }
                    />
                    <label htmlFor="float-input">Password</label>
                  </span>
                </div>
              </div>

              <div className="p-col bosluk-5">
                <div className="p-grid">
                  <Checkbox
                    checked={this.state.checked}
                    onChange={e => this.setState({ checked: e.checked })}
                  />
                  <span className="bosluk-left">Remember Me</span>
                </div>
              </div>
              <div className="p-col">
                <div className="p-grid">
                  <a href="#/" onClick={this.handleClick}>
                    Forget Password?
                  </a>{" "}
                  <hr />
                  <a href="#/" onClick={this.handleClick}>
                    Register
                  </a>
                </div>
              </div>

              <div className="p-grid p-justify-center">
                <div className="p-col-2">
                  <Button
                    className="p-button-success"
                    icon="pi pi-tablet"
                    onClick={e => this.setState({ selectedvalue: "Mobile" })}
                  />
                </div>
                <div className="p-col-2">
                  <Button
                    className="p-button-warning"
                    icon="pi pi-cloud"
                    onClick={e => this.setState({ selectedvalue: "Web" })}
                  />
                </div>
                <div className="p-col-2">
                  <Button
                    className="p-button-danger"
                    icon="pi pi-home"
                    onClick={e => this.setState({ selectedvalue: "Desktop" })}
                  />
                </div>
              </div>

              <div className="p-grid p-justify-center">
                <p>
                  Selected: <FaBeer /> {this.state.selectedvalue}
                </p>
              </div>

              <div className="p-col">
                <div className="p-grid p-justify-center">
                  <Button label="LOGIN" onClick={this.loginClicked} />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Loginirsat;
