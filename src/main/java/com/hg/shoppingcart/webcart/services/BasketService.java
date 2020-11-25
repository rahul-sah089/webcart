package com.hg.shoppingcart.webcart.services;

import com.hg.shoppingcart.webcart.DTO.BasketDetailsDTO;
import com.hg.shoppingcart.webcart.DTO.CartDTO;
import com.hg.shoppingcart.webcart.model.Basket;
import com.hg.shoppingcart.webcart.model.BasketDetails;
import com.hg.shoppingcart.webcart.model.Products;
import com.hg.shoppingcart.webcart.model.Users;
import com.hg.shoppingcart.webcart.repositories.BasketDetailsRepository;
import com.hg.shoppingcart.webcart.repositories.BasketRepository;
import com.hg.shoppingcart.webcart.repositories.ProductRepository;
import com.hg.shoppingcart.webcart.repositories.UserRepository;
import com.hg.shoppingcart.webcart.utils.BasketDetailsToDTO;
import com.hg.shoppingcart.webcart.utils.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println("username =>" + username);
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
            Basket basketResponse = basketRepository.save(basket);

            BasketDetails basketDetails = new BasketDetails();
            basketDetails.setAmount(productDb.getPrice());
            basketDetails.setPrice(productDb.getPrice());
            basketDetails.setBasket(basketResponse);
            basketDetails.setProduct(productDb);
            basketDetails.setQuanity(cartDTO.getQuantity());
            basketDetailsRepository.save(basketDetails);
        } else if (basketDb.isPresent()) {
            List<BasketDetails> basketDetails = basketDetailsRepository.findAllByBasketId(basketNumber);
            //Filter the basketDetails based on the product Id
            List<BasketDetails> basketDetailsFilter = basketDetails.stream().filter(basketDetail -> basketDetail.getProduct().getId() == productCode).collect(Collectors.toList());
            //If product is not there in basketDetails we have to persist it in BasketDetails
            if (basketDetailsFilter.size() == 0) {
                BasketDetails bsd = new BasketDetails();
                bsd.setAmount(productDb.getPrice());
                bsd.setPrice(productDb.getPrice());
                bsd.setBasket(basketDb.get());
                bsd.setProduct(productDb);
                bsd.setQuanity(cartDTO.getQuantity());
                basketDetailsRepository.save(bsd);
            }
            //If the product is not there in
            else {
                for (BasketDetails basketDetail : basketDetailsFilter) {
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

    public List<BasketDetailsDTO> getUserItems() {
        String username = LoggedInUser.getCurrentUser();
        Basket basket = basketRepository.findByCustomerUserName(username);
        List<BasketDetails> basketDetails = basketDetailsRepository.findAllByBasketId(basket.getId());
        List<BasketDetailsDTO> basketDetailsDTOS =  new ArrayList<>();
        for(BasketDetails basketDetails1 : basketDetails){
            basketDetailsDTOS.add(BasketDetailsToDTO.convertToDTO(basketDetails1));
        }
        return basketDetailsDTOS;
    }
}
