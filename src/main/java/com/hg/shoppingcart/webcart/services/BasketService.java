package com.hg.shoppingcart.webcart.services;

import com.hg.shoppingcart.webcart.DTO.CartDTO;
import com.hg.shoppingcart.webcart.model.Basket;
import com.hg.shoppingcart.webcart.model.BasketDetails;
import com.hg.shoppingcart.webcart.model.Products;
import com.hg.shoppingcart.webcart.model.Users;
import com.hg.shoppingcart.webcart.repositories.BasketDetailsRepository;
import com.hg.shoppingcart.webcart.repositories.BasketRepository;
import com.hg.shoppingcart.webcart.repositories.ProductRepository;
import com.hg.shoppingcart.webcart.repositories.UserRepository;
import com.hg.shoppingcart.webcart.utils.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("BasketService")
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketDetailsRepository basketDetailsRepository;

    @Transactional
    public Basket updateBasketItems(CartDTO cartDTO) {
        String username = LoggedInUser.getCurrentUser();
        Users user = userRepository.findByUsername(username);
        Long basketNumber = cartDTO.getBasketNumber();
        Long productCode = cartDTO.getProductCode();
        Optional<Basket> basketDb = Optional.ofNullable(null);
        if (basketNumber != null) {
            basketDb = basketRepository.findById(basketNumber);
        }
        Products productDb = productRepository.findById(productCode).get();
        Basket basket = new Basket();
        if (basketDb.isEmpty()) {
            //create the basket
            basket.setOrderDate(new Date());
            basket.setCustomerEmail(user.getUsername());
            basket.setCustomerUserName(user.getUsername());
            basket.setAmount(productDb.getPrice());
            basket.setBasketNumber(UUID.randomUUID().toString());
            Basket basketReponse = basketRepository.save(basket);

            BasketDetails basketDetails = new BasketDetails();
            basketDetails.setAmount(productDb.getPrice());
            basketDetails.setPrice(productDb.getPrice());
            basketDetails.setBasket(basketReponse);
            basketDetails.setProduct(productDb);
            basketDetails.setQuanity(cartDTO.getQuantity());
            basketDetailsRepository.save(basketDetails);
        } else if (basketDb.isPresent()) {
            List<BasketDetails> basketDetails = basketDetailsRepository.findAllByBasketId(basketNumber);
            for (BasketDetails basketDetail : basketDetails) {
                if (basketDetail.getProduct().getId() == productCode) {
                    basketDetail.setQuanity((basketDetail.getQuanity() + cartDTO.getQuantity()));
                    if (basketDetail.getQuanity() < 1) {
                        basketDetailsRepository.delete(basketDetail);
                    } else {
                        basketDetailsRepository.save(basketDetail);
                    }
                }
            }
        }
        return basket;
    }

    public List<BasketDetails> getUserItems() {
        String username = LoggedInUser.getCurrentUser();
        System.out.println(username);
        Basket basket = basketRepository.findByCustomerUserName(username);
        System.out.println("*********************");
        System.out.println(basketRepository.findByCustomerUserName(username));
        System.out.println("*********************");
        System.out.println(basket.getId());
        List<BasketDetails> basketDetails = basketDetailsRepository.findAllByBasketId(basket.getId());
        System.out.println("*******basket details************");
        System.out.println(basketDetails);
        System.out.println("*******************");
        return basketDetails;
    }
}
