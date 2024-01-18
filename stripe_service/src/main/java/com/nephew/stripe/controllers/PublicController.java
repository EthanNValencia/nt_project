package com.nephew.stripe.controllers;

import com.nephew.stripe.Model;
import com.nephew.stripe.ProductDto;
import com.nephew.stripe.services.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// http://localhost:8765/stripe-service/api/v1/public/published-key
// http://localhost:8765/stripe-service/api/v1/public/key
// http://localhost:8765/stripe-service/api/v1/public/health
@Controller
@RequestMapping("/api/v1/public")
public class PublicController {

    @Autowired
    private StripeService stripeService;

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/key")
    public ResponseEntity<String> getPublishedKey() {
        return new ResponseEntity<>(stripeService.getPublicKey(), HttpStatus.OK);
    }

    @PostMapping("/create-checkout-session")
    @ResponseBody
    public String createCheckoutSession(@RequestBody ArrayList<ProductDto> products) {
        System.out.println(products);
        String YOUR_DOMAIN = "http://localhost:8765/stripe-service/api/v1/public/"; // Adjust the port if necessary

        Stripe.apiKey = stripeService.getPrivateKey();

        try {
            SessionCreateParams params = SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(YOUR_DOMAIN + "?success=true")
                    .setCancelUrl(YOUR_DOMAIN + "?canceled=true")
                    .setAutomaticTax(SessionCreateParams.AutomaticTax.builder()
                            .setEnabled(true).build()).addLineItem(SessionCreateParams.LineItem.builder()
                            .setQuantity(1L)
                    // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                    .setPrice("{{PRICE_ID}}") // So I need
                    .build()).build();

            Session session = Session.create(params);
            // model.addAttribute("sessionUrl", session.getUrl());
            return session.getUrl();
        } catch (StripeException e) {
            e.printStackTrace();
            return "Error creating checkout session: " + e.getMessage();
        }
    }

    public void productExample() throws StripeException {
        ProductCreateParams params = ProductCreateParams.builder()
                .setName("Basic Dashboard").setDefaultPriceData(ProductCreateParams.DefaultPriceData.builder()
                        .setUnitAmount(1000L).setCurrency("usd")
                        .setRecurring(ProductCreateParams.DefaultPriceData.Recurring.builder()
                                .setInterval(ProductCreateParams.DefaultPriceData.Recurring.Interval.MONTH)
                                .build()).build()).addExpand("default_price").build();

        Product product = Product.create(params);
    }

}

/*

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = stripeService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

 */