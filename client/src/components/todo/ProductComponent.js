import React, { Component } from 'react'
import Header from './Header';
import ProductDataService from './ProductDataService';
import AuthenticationService from './AuthenticationService';
import moment from 'moment';
export default class ProductComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
            message: null
        };

        this.deleteTodoClicked = this.deleteProductClicked.bind(this);
        this.refreshTodos = this.refreshTodos.bind(this);
        this.updateTodoClicked = this.updateProductClicked.bind(this);

    }

    componentDidMount() {
        this.refreshTodos();
    }

    refreshTodos() {
        ProductDataService.retrieveAllProducts().then(response => this.setState({ products: response.data }));
    }

    deleteProductClicked(id) {
        let userName = AuthenticationService.getLoggedInUserName();
        ProductDataService.deleteToDo(userName, id)
            .then(
                response => {
                    this.setState({ message: `Delete of todo ${id} Successful ` });
                    this.refreshTodos();
                });
    }

    updateCart(productId,quantity){
        console.log("add to cart called");
        ProductDataService.addProductToCart(productId,quantity);
    }

    updateProductClicked(id) {
        console.log('update ' + id);
        this.props.history.push(`/updateproduct/${id}`);
    }

    addToDoClicked = () => {
        alert("Clicked");
        this.props.history.push(`/updateproduct/-1`);
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
                                    <th>Is Avaiable?</th>
                                    <th>Product Name</th>
                                    <th>Proouct Price</th>
                                    <th>Date of Manufacturing</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.products.map(
                                        (product) =>
                                            <tr>
                                                <td>{product.id}</td>
                                                <td>Avaiable</td>
                                                <td>{product.name}</td>
                                                <td>{product.price}</td>
                                                <td>{moment(product.createDate).format('YYYY-MM-DD')}</td>
                                                {/*<td><button className="btn btn-success" onClick={() => this.updateProductClicked(product.id)}>Update</button></td>
                                                <td><button className="btn btn-warning" onClick={() => this.deleteProductClicked(product.id)}>Delete</button></td>
                                                */}
                                                <td><button className="btn btn-success" onClick={() => this.updateCart(product.id,1)}>Add</button></td>
                                                <td><button className="btn btn-danger" onClick={() => this.updateCart(product.id,-1)}>Remove</button></td>

                                            </tr >
                                    )
                                }
                            </tbody>
                        </table>
                        <div className="row">
                            <button className="btn btn-success" onClick={this.addToDoClicked}>Add</button>
                        </div>
                    </div>
                </div>
            </React.Fragment>
        )
    }
}
