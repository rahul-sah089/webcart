import React from 'react'
import Header from './Header'

export default function LogOut() {
    return (
        <React.Fragment>
            <Header />
            <div className="container">
                <h1>You are logged out</h1>
                <div className="container">
                    Thank you for Using our Application
            </div>
            </div>
        </React.Fragment>
    )
}
