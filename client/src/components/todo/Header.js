import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import AuthenticationService from './AuthenticationService.js';
export default class Header extends Component {
    constructor(props) {
        super(props);
        this.state = { isUserLoggedIn: false };
    }

    componentDidMount() {
        this.setState({ isUserLoggedIn: AuthenticationService.isUserLoggedIn() })
        console.log(this.state.isUserLoggedIn);
    }
    render() {

        return (
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div>
                        <Link className="navbar-brand" to="/">MyStrtUp</Link>
                    </div>
                    {this.state.isUserLoggedIn &&
                        <ul className="navbar-nav">
                            <li><Link className="nav-link" to="/welcome">Home</Link></li>
                            <li><Link className="nav-link" to="/product">Product</Link></li>
                        </ul>
                    }
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!this.state.isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
                        {!this.state.isUserLoggedIn && <li><Link className="nav-link" to="/signup">Sign Up</Link></li>}
                        {this.state.isUserLoggedIn && <li><Link className="nav-link" to="/basket">Basket</Link></li>}
                        {this.state.isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>LogOut</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }
}
