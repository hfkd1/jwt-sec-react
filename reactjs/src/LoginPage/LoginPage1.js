import React, { Component } from "react";
import { InputText } from "primereact/inputtext";
import { Password } from "primereact/password";
import { Button } from "primereact/button";

export class LoginPage1 extends Component {
  // constructor(props) {
  //     super(props);

  //     this.state = {
  //       email: "",
  //       password: ""
  //     };
  //   }

  //   validateForm() {
  //     return this.state.email.length > 0 && this.state.password.length > 0;
  //   }

  //   handleChange = event => {
  //     this.setState({
  //       [event.target.id]: event.target.value
  //     });
  //   };

  //   handleSubmit = event => {
  //     event.preventDefault();
  //   };
  render() {
    return (
      <div className="p-grid p-fluid">
        <div className="p-col-12 p-md-4" />

        <div className="p-col-12 p-md-4" style={{ marginTop: "100px" }}>
          <div className="card card-w-title">
            <img
              src="assets/layout/images/avatar_5.png"
              alt=""
              width="80"
              style={{
                display: "block",
                marginLeft: "auto",
                marginRight: "auto"
              }}
            />
            <h1 style={{ marginBottom: "10px", "text-align": "center" }}>
              Login
            </h1>
            <div className="p-grid">
              <InputText
                placeholder="Email"
                style={{
                  "text-align": "center",
                  marginRight: "30px",
                  marginLeft: "30px",
                  marginTop: "10px",
                  marginBottom: "10px"
                }}
              />
              <Password
                feedback={false}
                placeholder="Password"
                style={{
                  "text-align": "center",
                  marginRight: "30px",
                  marginLeft: "30px"
                }}
              />
              <div className="p-col-12 p-md-4" />
              <div className="p-col-12 p-md-4">
                <Button
                  label="Login"
                  style={{ marginBottom: "10px", marginTop: "10px" }}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
