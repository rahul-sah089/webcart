import React, { Component } from 'react'
import Login from './Login';
import SignUp from './SignUp';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Welcome from './Welcome';
import Error from './Error';
import ProuctComponent from './ProductComponent';
import BasketComponent from './BasketComponent';
import Footer from './Footer';
import LogOut from './LogOut';
import AuthenticatedRoute from './AuthenticatedRoute';
import UpateProductComponent from './UpateProductComponent';


export default class ToDoApp extends Component {
    render() {
        return (
            <div>
                <div className="TodoApp">
                    <Router>
                        <Switch>
                            <Route path="/" exact component={Login} />
                            <Route path="/login" component={Login} />
                            <Route path="/signup" component={SignUp} />
                            <AuthenticatedRoute path="/welcome/:name" component={Welcome} />
                            <AuthenticatedRoute path="/product" component={ProuctComponent} />
                            <AuthenticatedRoute path="/logout" component={LogOut} />
                            <AuthenticatedRoute path="/basket" component={BasketComponent} />
                            <AuthenticatedRoute path="/updateproduct/:id" component={UpateProductComponent} />
                            <Route component={Error} />
                        </Switch>
                        <Footer />
                    </Router>
                </div>
            </div>
        )
    }
}
