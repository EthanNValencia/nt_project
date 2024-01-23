package com.nephew.products.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * I removed List<Price.CurrencyOption.Tier> tiers.
 */
@Embeddable
public class CurrencyOption {

    /**
     * When set, provides configuration for the amount to be adjusted by the customer during
     * Checkout Sessions and Payment Links.
     */
    @Embedded
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

    public CustomUnitAmount getCustomUnitAmount() {
        return customUnitAmount;
    }

    public void setCustomUnitAmount(CustomUnitAmount customUnitAmount) {
        this.customUnitAmount = customUnitAmount;
    }

    public String getTaxBehavior() {
        return taxBehavior;
    }

    public void setTaxBehavior(String taxBehavior) {
        this.taxBehavior = taxBehavior;
    }

    public Long getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Long unitAmount) {
        this.unitAmount = unitAmount;
    }

    public BigDecimal getUnitAmountDecimal() {
        return unitAmountDecimal;
    }

    public void setUnitAmountDecimal(BigDecimal unitAmountDecimal) {
        this.unitAmountDecimal = unitAmountDecimal;
    }
}
