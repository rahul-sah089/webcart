import React, { Component } from 'react'
import Header from './Header'
import moment from 'moment';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import ProductDataService from './ProductDataService';
import AuthenticationService from './AuthenticationService';

export default class UpateProductComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: 1,
            description: 'Learn Forms',
            targetDate: moment(new Date()).format('YYYY-MM-DD')
        }

        this.onSubmit = this.onSubmit.bind(this);
        this.validate = this.validate.bind(this);
    }

    componentDidMount() {
        let todoId = this.props.match.params.id;
        let userName = AuthenticationService.getLoggedInUserName();
        if (todoId === -1) {
            return;
        } else {
            ProductDataService.getProductById(userName, todoId).then(response => this.setState({
                description: response.data.description,
                targetDate: moment(response.data.targetDate).format('YYYY-DD-MM')
            }));
        }
    }

    onSubmit(values) {
        let todoId = this.props.match.params.id;
        console.log(values);
        let userName = AuthenticationService.getLoggedInUserName();
        alert("toDoId =>" + todoId);
        if (todoId === -1) {
            ProductDataService.createToDo(userName,
                {
                    id: this.state.id,
                    description: values.description,
                    targetDate: values.targetDate
                }).then(() => { this.props.history.push('/todos') });
        } else {
            ProductDataService.updateToDo(userName,
                {
                    id: this.state.id,
                    description: values.description,
                    targetDate: values.targetDate
                }).then(() => { this.props.history.push('/todos') });
        }
    }

    validate(values) {
        let errors = {};
        if (!values.description) {
            errors.description = 'Enter a Description'
        } else if (values.description.length < 5) {
            errors.description = 'Enter alteast 5 Characters in Description'
        }
        if (!moment(values.targetDate).isValid()) {
            errors.targetDate = 'Enter a valid Target Date';
        }
        console.log(values);
        return errors;
    }


    render() {
        let description = this.state.description;
        let targetDate = this.state.targetDate;

        return (
            <React.Fragment>
                <Header />
                <h1>Todo</h1>
                <div className="container">
                    <Formik
                        initialValues={{ description: description, targetDate: targetDate }}
                        onSubmit={this.onSubmit}
                        enableReinitialize={true}
                        validate={this.validate}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="description" component="div" className="alert alert-warning" />
                                    <ErrorMessage name="targetDate" component="div" className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Description</label>
                                        <Field className="form-control" type="text" name="description" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Target Date</label>
                                        <Field className="form-control" type="date" name="targetDate" />
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Save</button>
                                </Form>

                            )
                        }
                    </Formik>
                </div>
            </React.Fragment>
        )
    }
}
