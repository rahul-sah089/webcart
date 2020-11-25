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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@RunWith(SpringRunner.class)
public class BasketServiceTest {
    @Mock
    private BasketRepository basketRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BasketDetailsRepository basketDetailsRepository;

    @Mock
    private LoggedInUser loggedInUser;

    @InjectMocks
    private BasketService testClass;

    //@Test
    public void updateBasketTest(){
        String username = "rahulsah";
        Long basketNumber = 1L;
        Long productCode = 1L;
        Products productsMock = new Products(1L,"CloseUp",1212.12,new Date());
        Optional<Products> products =  Optional.of(productsMock);
        Users users = new Users(1,"rahulsah","rahulsah");
        Optional<Basket> basketDb = Optional.ofNullable(null);
        when(LoggedInUser.getCurrentUser()).thenReturn("Rahul");
        when(userRepository.findByUsername(username)).thenReturn(users);
        when(basketRepository.findById(basketNumber)).thenReturn(basketDb);
        when(productRepository.findById(productCode)).thenReturn(products);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setBasketNumber(1212L);
        cartDTO.setProductCode(12121L);
        cartDTO.setQuantity(1L);
        testClass.updateBasketItems(cartDTO);

        verify(userRepository).findByUsername(username);
        verify(basketRepository).findById(basketNumber);
        verify(productRepository).findById(productCode);

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(basketRepository);
        verifyNoMoreInteractions(productRepository);
    }





}
