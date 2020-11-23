import axios from 'axios';
import {API_URL} from  '../../Constant';

class ProductDataService {

    getProductById(name, id) {
        console.log(id);
        return axios.get(`${API_URL}/api/v1/product/${id}`,{
            headers: {
                authorization: sessionStorage.getItem("token")
            }
        });
    }

    addProductToCart(id,quantityV){
        debugger;
        let basketId = sessionStorage.getItem("basketId");
        var postData = {productCode:id,quantity:quantityV}
        if(basketId !== null && basketId !== ""){
            postData.basketNumber = basketId;
        }
        console.log("add product to cart");
        return axios.post(`${API_URL}/api/v1/updateItems`,postData,{
            headers: {
                authorization: sessionStorage.getItem("token")
            }
        }).then((response) => {
            debugger;
            if(response.data.id !== null){
                sessionStorage.setItem("basketId",response.data.id);
            }
            console.log("inside success block of axios");
        })
        .catch((err) => {
            console.log(err);
            console.log("Inside failure block of axios");
            
        });
    }

    retrieveAllProducts() {
        console.log("execute service");
        return axios.get(`${API_URL}/api/v1/getProducts`,{
            headers: {
                authorization: sessionStorage.getItem("token")
            }
        });
    }

    retrieveBasketItems(){
        console.log("retrieve baset items");
        return axios.get(`${API_URL}/api/v1/fetchItems`,{
            headers: {
                authorization: sessionStorage.getItem("token")
            }
        });
    }

    
}

export default new ProductDataService();