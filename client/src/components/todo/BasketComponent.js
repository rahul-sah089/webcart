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
        this.refreshBasketItems();
    }

    refreshBasketItems() {
        ProductDataService.retrieveBasketItems().then(response => this.setState({ products: response.data }));
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
                                    <th>Product Name</th>
                                    <th>Product Quantity</th>
                                    <th>Product Price</th>
                                    <th>Order Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.products.map(
                                        (item) =>
                                            <tr>
                                                <td>{item.productId}</td>
                                                <td>{item.productName}</td>
                                                <td>{item.quantity}</td>
                                                <td>{item.productPrice}</td>
                                                <td>{item.orderDate}</td>
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
