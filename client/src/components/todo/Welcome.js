import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import Header from './Header';

export default class Welcome extends Component {
    constructor(props) {
        super(props);
        this.state = {
            welcomeMessage: "Welcome Back!!!"
        };
    }

    render() {
        return (
            <React.Fragment>
                <Header />
                <div>
                    <h1>Welcome</h1>
                    <div className="container">
                        {this.props.match.params.name}.
                            You can manage your Cart from <Link to="/product">here</Link>
                    </div>
                    <br />
                    <div className="container">
                    </div>
                    <div className="container">
                        {this.state.welcomeMessage}
                    </div>
                </div>
            </React.Fragment>
        )
    }
}
