import axios from 'axios';
import {API_URL} from '../../Constant';

class AuthenticationService {

    createBasicAuthToken(username, password) {
        let basicAuthHeader = 'Basic ' + window.btoa(username + ":" + password);
        return basicAuthHeader;
    }

    executeBasicAuthenticationService(username, password) {
        return axios.get(API_URL, { headers: { authorization: this.createBasicAuthToken(username, password) } })

    }

    executeLogInService(username, password) {
        return axios.post(`${API_URL}/login`, {
            username: username,
            password: password
        })
    }

    executeSignUpService(username, password){
        return axios.post(`${API_URL}/api/v1/signup`, {
            username: username,
            password: password
        })
    }




    registerSuccesfulLogin(username, password) {
        sessionStorage.setItem('authenticatedUser', username);
        
        this.setupAxiosInterceptors(this.createBasicAuthToken(username, password));
    }

    registerSuccesfulLoginForJWT(username, token) {
        sessionStorage.setItem("token",token);
        sessionStorage.setItem('authenticatedUser', username);
        this.setupAxiosInterceptors(token);
    }


    getLoggedInUserName() {
        let users = sessionStorage.getItem("authenticatedUser");
        if (users === null) {
            return '';
        } else {
            return users;
        }
    }

    logout() {
        sessionStorage.removeItem('authenticatedUser');
    }

    isUserLoggedIn() {
        let values = sessionStorage.getItem("authenticatedUser");
        if (values === null) {
            return false;
        } else {
            return true;
        }
    }

    setupAxiosInterceptors(tokenValue) {
        axios.interceptors.request.use((config) => {
            debugger;
            if (this.isUserLoggedIn()) {
                config.headers.authorization = tokenValue
            }
            return config;
        })
    }
}

export default new AuthenticationService();
