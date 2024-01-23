package com.nephew.products.entities;

import java.math.BigDecimal;
import java.util.List;

/**
 * I removed List<Price.CurrencyOption.Tier> tiers.
 */
public class CurrencyOption {
    /**
     * When set, provides configuration for the amount to be adjusted by the customer during
     * Checkout Sessions and Payment Links.
     */
    private CustomUnitAmount customUnitAmount;

    /**
     * Only required if a <a
     * href="https://stripe.com/docs/tax/products-prices-tax-categories-tax-behavior#setting-a-default-tax-behavior-(recommended)">default
     * tax behavior</a> was not provided in the Stripe Tax settings. Specifies whether the price is
     * considered inclusive of taxes or exclusive of taxes. One of {@code inclusive}, {@code
     * exclusive}, or {@code unspecified}. Once specified as either {@code inclusive} or {@code
     * exclusive}, it cannot be changed.
     */
    private String taxBehavior;


    /**
     * The unit amount in cents (or local equivalent) to be charged, represented as a whole integer
     * if possible. Only set if {@code billing_scheme=per_unit}.
     */
    private Long unitAmount;

    /**
     * The unit amount in cents (or local equivalent) to be charged, represented as a decimal string
     * with at most 12 decimal places. Only set if {@code billing_scheme=per_unit}.
     */
    private BigDecimal unitAmountDecimal;
}
