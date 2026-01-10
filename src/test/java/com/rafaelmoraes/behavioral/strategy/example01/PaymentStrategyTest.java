package com.rafaelmoraes.behavioral.strategy.example01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentStrategyTest {
    ShoppingCart shoppingCart;

    @Mock
    PaymentStrategy paymentStrategy;

    @BeforeEach
    void setup() {
        shoppingCart = new ShoppingCart();
        shoppingCart.setPaymentStrategy(paymentStrategy);
    }

    @Test
    @DisplayName("Deve usar a strategy configurada para pagar o total")
    void shouldUsePaymentStrategyToPayTotal() {

        shoppingCart.addItem(new Item("Item 1", 100));
        shoppingCart.addItem(new Item("Item 2", 200));

        shoppingCart.checkout();

        verify(paymentStrategy).pay(300);
    }


    @Test
    @DisplayName("Deve retornar true pagamento via Paypal")
    void paypalPayment() {
        PaymentStrategy paypal =
                new PaypalPayment("a@b.com", "123", "****");

        paypal.pay(300);
    }

    @Test
    @DisplayName("Deve retornar true pagamento via Cartão de credito")
    void creditCardPayment() {
        PaymentStrategy card =
                new CreditCardPayment("Rafael", "123", "2030", "12", "456");

        card.pay(400);
    }


}