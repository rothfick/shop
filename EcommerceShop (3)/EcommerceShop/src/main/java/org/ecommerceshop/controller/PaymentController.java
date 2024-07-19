package org.ecommerceshop.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.ecommerceshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public PaymentIntent createPaymentIntent(@RequestParam Long amount, @RequestParam String currency, @RequestParam String paymentMethodType) throws StripeException {
        PaymentIntentCreateParams.Builder createParamsBuilder = new PaymentIntentCreateParams.Builder()
                .setAmount(amount * 100) // Kwota w centach
                .setCurrency(currency)
                .addPaymentMethodType(paymentMethodType);

        if (paymentMethodType.equals("google_pay")) {
            // Konfiguracja dla Google Pay
            createParamsBuilder.addPaymentMethodType("card"); // Google Pay korzysta z karty jako źródła
            // Dodaj dodatkowe parametry konfiguracji dla Google Pay, jeśli są wymagane
        } else if (paymentMethodType.equals("apple_pay")) {
            // Konfiguracja dla Apple Pay
            createParamsBuilder.addPaymentMethodType("card"); // Apple Pay korzysta z karty jako źródła
            // Dodaj dodatkowe parametry konfiguracji dla Apple Pay, jeśli są wymagane
        }

        PaymentIntentCreateParams createParams = createParamsBuilder.build();
        return paymentService.createPaymentIntent(createParams);
    }
}
