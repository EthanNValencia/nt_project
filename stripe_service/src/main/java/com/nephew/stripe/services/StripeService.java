package com.nephew.stripe.services;

import com.nephew.stripe.ChargeRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    private final String STRIPE_PRIVATE = System.getenv("STRIPE_TEST_PRIVATE_KEY");
    private final String STRIPE_PUBLIC = System.getenv("STRIPE_TEST_PUBLIC_KEY");

    public String getPublicKey() {
        return STRIPE_PUBLIC;
    }

    public String getPrivateKey() {
        return STRIPE_PRIVATE;
    }

    public Charge charge(ChargeRequest chargeRequest) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }

}
