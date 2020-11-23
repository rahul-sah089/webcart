import React, { Component } from 'react'
import Header from './Header';
import ProductDataService from './ProductDataService';
export default class BasketComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
            message: null
        };
        this.refreshBasketItems = this.refreshBasketItems.bind(this);
    }
    componentDidMount() {
        this.refreshTodos();
    }

    refreshBasketItems() {
        ProductDataService.retrieveBasketItems().then(response => this.setState({ items: response.data }));
    }




    render() {
        return (
            <React.Fragment>
                <Header />
                <div className="container">
                    <h1>List of Products</h1>
                    {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                    <div>
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Product Id</th>
                                    <th>Product Price</th>
                                    <th>Total Amount</th>
                                    <th>Product Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.items.map(
                                        (item) =>
                                            <tr>
                                                <td>{item.id}</td>
                                                <td>{item.amount}</td>
                                                <td>{item.price}</td>
                                                <td>{item.quantity}</td>
                                            </tr >
                                    )
                                }
                            </tbody>
                        </table>
                    </div>
                </div>
            </React.Fragment>
        )
    }
}
