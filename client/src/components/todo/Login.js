import React, { Component } from 'react'
import AuthenticationService from './AuthenticationService.js';
import Header from './Header';

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            hasLoginFailed: false,
            showSucessMessage: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.loginClicked = this.loginClicked.bind(this);
    }

    handleChange(event) {
        this.setState({ [event.target.name]: event.target.value });
    }

    loginClicked() {
        AuthenticationService.executeLogInService(this.state.username, this.state.password)
            .then((response) => {
                debugger;
                console.log(response)
                AuthenticationService.registerSuccesfulLoginForJWT(this.state.username, response.data.authorization);
                this.props.history.push(`/welcome/${this.state.username}`);
            })
            .catch(() => {
                console.log("Failure");
                this.setState({ showSucessMessage: false })
                this.setState({ hasLoginFailed: true })
            })
    }

    render() {
        var promtMessage = "";
        return (
            <React.Fragment>
                <Header />
                <div>
                    <h1>Login</h1>
                    {this.state.hasLoginFailed && !this.state.showSucessMessage ? <div className='alert alert-warning'>Invalid Credential</div> : null}
                    <div className="container">
                        <React.Fragment>
                            {promtMessage}
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input type="email"
                                    class="form-control"
                                    aria-describedby="emailHelp"
                                    placeholder="Enter email"
                                    name="username"
                                    value={this.state.username}
                                    onChange={this.handleChange} />
                                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input
                                    type="password"
                                    class="form-control"
                                    id="exampleInputPassword1"
                                    placeholder="Password"
                                    name="password"
                                    value={this.state.password}
                                    onChange={this.handleChange} />
                            </div>

                            <br />
                            <button
                                onClick={this.loginClicked}
                                type="submit"
                                class="btn btn-primary">Login</button>
                        </React.Fragment>
                    </div>
                </div >
            </React.Fragment>
        )
    }
}
