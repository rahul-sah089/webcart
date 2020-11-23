import React, { Component } from 'react'
import AuthenticationService from './AuthenticationService.js';
import Header from './Header';

export default class SignUp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            hasLoginFailed: false,
            showSucessMessage: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.signUpClicked = this.signUpClicked.bind(this);
    }

    handleChange(event) {
        this.setState({ [event.target.name]: event.target.value });
    }

    signUpClicked() {
        AuthenticationService.executeSignUpService(this.state.username, this.state.password)
            .then((response) => {
                AuthenticationService.registerSuccesfulLoginForJWT(this.state.username, response.data.token);
                this.props.history.push(`/login`);
            })
            .catch((err) => {
                console.log(err);
                console.log("Failure");
                this.setState({ showSucessMessage: false })
                this.setState({ hasSingUpFailed: true })
            })
    }

    render() {
        var promtMessage = "";
        return (
            <React.Fragment>
                <Header />
                <div>
                    <h1>Sign Up</h1>
                    {this.state.hasSingUpFailed && !this.state.showSucessMessage ?
                        <div className='alert alert-warning'>Email can't be duplicate</div>
                        : null}
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
                                onClick={this.signUpClicked}
                                type="submit"
                                class="btn btn-primary">Sign Up</button>
                        </React.Fragment>
                    </div>
                </div >
            </React.Fragment>
        )
    }
}
