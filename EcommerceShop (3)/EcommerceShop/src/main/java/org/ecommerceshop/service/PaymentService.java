package org.ecommerceshop.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentIntent createPaymentIntent(PaymentIntentCreateParams createParams) throws StripeException {
        return PaymentIntent.create(createParams);
    }
}
