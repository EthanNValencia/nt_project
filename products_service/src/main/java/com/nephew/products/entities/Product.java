package com.nephew.products.entities;

import jakarta.persistence.Entity;

import java.util.List;
import java.util.Map;
// https://stripe.com/docs/api/products/object

// @Entity
public class Product {
    /** Whether the product is currently available for purchase. */
    private Boolean active;

    /** Time at which the object was created. Measured in seconds since the Unix epoch. */
    private Long created;

    /**
     * The ID of the <a href="https://stripe.com/docs/api/prices">Price</a> object that is the default
     * price for this product.
     */
    private Price defaultPrice;

    /** Always true for a deleted object. */
    private Boolean deleted;

    /**
     * The product's description, meant to be displayable to the customer. Use this field to
     * optionally store a long form explanation of the product being sold for your own rendering
     * purposes.
     */
    private String description;

    /**
     * A list of up to 15 features for this product. These are displayed in <a
     * href="https://stripe.com/docs/payments/checkout/pricing-table">pricing tables</a>.
     */
    private List<Feature> features;

    /** Unique identifier for the object. */
    private String id;

    /** A list of up to 8 URLs of images for this product, meant to be displayable to the customer. */
    private List<String> images;

    /**
     * Has the value {@code true} if the object exists in live mode or the value {@code false} if the
     * object exists in test mode.
     */
    private Boolean livemode;

    /**
     * Set of <a href="https://stripe.com/docs/api/metadata">key-value pairs</a> that you can attach
     * to an object. This can be useful for storing additional information about the object in a
     * structured format.
     */
    private Map<String, String> metadata;

    /** The product's name, meant to be displayable to the customer. */
    private String name;

    /**
     * String representing the object's type. Objects of the same type share the same value.
     *
     * <p>Equal to {@code product}.
     */
    private String object;

    /** The dimensions of this product for shipping purposes. */
    private PackageDimensions packageDimensions;

    /** Whether this product is shipped (i.e., physical goods). */
    private Boolean shippable;

    /**
     * Extra information about a product which will appear on your customer's credit card statement.
     * In the case that multiple products are billed at once, the first statement descriptor will be
     * used.
     */
    private String statementDescriptor;

    /** A <a href="https://stripe.com/docs/tax/tax-categories">tax code</a> ID. */
    private TaxCode taxCode;

    /**
     * The type of the product. The product is either of type {@code good}, which is eligible for use
     * with Orders and SKUs, or {@code service}, which is eligible for use with Subscriptions and
     * Plans.
     *
     * <p>One of {@code good}, or {@code service}.
     */
    private String type;

    /**
     * A label that represents units of this product. When set, this will be included in customers'
     * receipts, invoices, Checkout, and the customer portal.
     */
    private String unitLabel;

    /** Time at which the object was last updated. Measured in seconds since the Unix epoch. */
    private Long updated;

    /** A URL of a publicly-accessible webpage for this product. */
    private String url;

}
