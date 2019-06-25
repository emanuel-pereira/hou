import React, {Component} from 'react'
import {sendUsernamePassword} from '../actions/actionsLogin';
import { Card, CardImg, Spinner, CardBody, CardTitle, CardSubtitle, Button } from 'reactstrap';
import logo from "../logo.png";


export class LoginPage extends Component {
    constructor(props) {
        super(props);

        // reset login status
        //this.props.dispatch(userActions.logout());

        this.state = {
            username: '',
            password: '',
            submitted: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const {name, value} = e.target;
        this.setState({[name]: value});
    }

    handleSubmit(e) {
        e.preventDefault();
        this.setState({submitted: true});
        const {username, password} = this.state;
        console.log({username}, {password});
        sendUsernamePassword({username, password});
    }


    render() {
        const {loggingIn} = this.props;
        const {username, password, submitted} = this.state;
        return (
            <div style={{position:"absolute", left:'50%', top:'50%', width: '20%', transform:'translate(-50%,-50%)'}}>
                <div className="alert alert-info">
                    Username: admin<br/>
                    Password: admin123
                </div>
                <Card>
                    <img style={{
                        display: 'block',
                        marginLeft: 'auto',
                        marginRight: 'auto',
                        width: '50%'}} src={logo} alt="Card image cap" />
                    <CardBody>
                        <form name="form" onSubmit={this.handleSubmit}>
                            <div>
                                <label htmlFor="username">Username</label>
                                <input type="text" className="form-control" name="username" value={username}
                                       onChange={this.handleChange}/>
                                {submitted && !username &&
                                <div className="help-block">Username is required</div>
                                }
                            </div>
                            <div>
                                <label htmlFor="password">Password</label>
                                <input type="password" className="form-control" name="password" value={password}
                                       onChange={this.handleChange}/>
                                {submitted && !password &&
                                <div className="help-block">Password is required</div>
                                }
                            </div>
                            <div className="form-group">
                                <Button className="btn btn-primary" onSubmit={this.handleSubmit}>Login</Button>
                                {loggingIn && <Spinner color="success" />}
                            </div>
                        </form>
                    </CardBody>
                </Card>

            </div>
        )
    }
}

function mapStateToProps(state) {
    const {loggingIn} = state.authentication;
    return {
        loggingIn
    };
}