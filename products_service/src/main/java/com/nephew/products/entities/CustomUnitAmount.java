package com.nephew.products.entities;

public class CustomUnitAmount {
    /** The maximum unit amount the customer can specify for this item. */
    private Long maximum;

    /**
     * The minimum unit amount the customer can specify for this item. Must be at least the
     * minimum charge amount.
     */
    private Long minimum;

    /** The starting unit amount which can be updated by the customer. */
    private Long preset;
}
