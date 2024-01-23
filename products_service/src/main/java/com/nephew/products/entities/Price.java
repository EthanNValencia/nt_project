package com.nephew.products.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * I removed List<Price.Tier> tiers and TransformQuantity transformQuantity.
 */
public class Price {
    /** Whether the price can be used for new purchases. */
    private Boolean active;

    /**
     * Describes how to compute the price per period. Either {@code per_unit} or {@code tiered}.
     * {@code per_unit} indicates that the fixed amount (specified in {@code unit_amount} or {@code
     * unit_amount_decimal}) will be charged per unit in {@code quantity} (for prices with {@code
     * usage_type=licensed}), or per unit of total usage (for prices with {@code usage_type=metered}).
     * {@code tiered} indicates that the unit pricing will be computed using a tiering strategy as
     * defined using the {@code tiers} and {@code tiers_mode} attributes.
     *
     * <p>One of {@code per_unit}, or {@code tiered}.
     */
    private String billingScheme;

    /** Time at which the object was created. Measured in seconds since the Unix epoch. */
    private Long created;

    /**
     * Three-letter <a href="https://www.iso.org/iso-4217-currency-codes.html">ISO currency code</a>,
     * in lowercase. Must be a <a href="https://stripe.com/docs/currencies">supported currency</a>.
     */
    private String currency;

    /**
     * Prices defined in each available currency option. Each key must be a three-letter <a
     * href="https://www.iso.org/iso-4217-currency-codes.html">ISO currency code</a> and a <a
     * href="https://stripe.com/docs/currencies">supported currency</a>.
     */
    private Map<String, CurrencyOption> currencyOptions;

    /**
     * When set, provides configuration for the amount to be adjusted by the customer during Checkout
     * Sessions and Payment Links.
     */
    private CustomUnitAmount customUnitAmount;

    /** Always true for a deleted object. */
    private Boolean deleted;

    /** Unique identifier for the object. */
    private String id;

    /**
     * Has the value {@code true} if the object exists in live mode or the value {@code false} if the
     * object exists in test mode.
     */
    private Boolean livemode;

    /**
     * A lookup key used to retrieve prices dynamically from a static string. This may be up to 200
     * characters.
     */
    private String lookupKey;

    /**
     * Set of <a href="https://stripe.com/docs/api/metadata">key-value pairs</a> that you can attach
     * to an object. This can be useful for storing additional information about the object in a
     * structured format.
     */
    private Map<String, String> metadata;

    /** A brief description of the price, hidden from customers. */
    private String nickname;

    /**
     * String representing the object's type. Objects of the same type share the same value.
     *
     * <p>Equal to {@code price}.
     */
    private String object;

    /** The ID of the product this price is associated with. */
    private Long productId;

    /** The recurring components of a price such as {@code interval} and {@code usage_type}. */
    private Recurring recurring;

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
     * Defines if the tiering price should be {@code graduated} or {@code volume} based. In {@code
     * volume}-based tiering, the maximum quantity within a period determines the per unit price. In
     * {@code graduated} tiering, pricing can change as the quantity grows.
     *
     * <p>One of {@code graduated}, or {@code volume}.
     */
    private String tiersMode;

    /**
     * One of {@code one_time} or {@code recurring} depending on whether the price is for a one-time
     * purchase or a recurring (subscription) purchase.
     */
    private String type;

    /**
     * The unit amount in cents (or local equivalent) to be charged, represented as a whole integer if
     * possible. Only set if {@code billing_scheme=per_unit}.
     */
    private Long unitAmount;

    /**
     * The unit amount in cents (or local equivalent) to be charged, represented as a decimal string
     * with at most 12 decimal places. Only set if {@code billing_scheme=per_unit}.
     */
    private BigDecimal unitAmountDecimal;
}
