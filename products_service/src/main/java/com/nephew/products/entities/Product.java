package com.nephew.products.entities;

import jakarta.persistence.*;

import java.util.*;
// https://stripe.com/docs/api/products/object

@Entity
public class Product {

    /**
     * Set of <a href="https://stripe.com/docs/api/metadata">key-value pairs</a> that you can attach
     * to an object. This can be useful for storing additional information about the object in a
     * structured format.
     */
    @ElementCollection
    @CollectionTable(name = "product_metadata", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "key")
    private Map<String, String> metadata;

    // Could not determine recommended JdbcType for Java type 'java.util.Map<java.lang.String, java.lang.String>'

    /** Whether the product is currently available for purchase. */
    private Boolean active;

    /** Time at which the object was created. Measured in seconds since the Unix epoch. */
    private Long created;

    /**
     * The ID of the <a href="https://stripe.com/docs/api/prices">Price</a> object that is the default
     * price for this product.
     */
    @OneToOne(mappedBy = "product")
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
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Feature> features;

    /** Unique identifier for the object. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** A list of up to 8 URLs of images for this product, meant to be displayable to the customer. */
    private List<String> images;

    /**
     * Has the value {@code true} if the object exists in live mode or the value {@code false} if the
     * object exists in test mode.
     */
    private Boolean livemode;

    /** The product's name, meant to be displayable to the customer. */
    private String name;

    /**
     * String representing the object's type. Objects of the same type share the same value.
     *
     * <p>Equal to {@code product}.
     */
    private String object;

    /** The dimensions of this product for shipping purposes. */
    @Embedded
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
    @Embedded
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

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Price getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(Price defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Long getId() {
        return id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Boolean getLivemode() {
        return livemode;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public PackageDimensions getPackageDimensions() {
        return packageDimensions;
    }

    public void setPackageDimensions(PackageDimensions packageDimensions) {
        this.packageDimensions = packageDimensions;
    }

    public Boolean getShippable() {
        return shippable;
    }

    public void setShippable(Boolean shippable) {
        this.shippable = shippable;
    }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public TaxCode getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(TaxCode taxCode) {
        this.taxCode = taxCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitLabel() {
        return unitLabel;
    }

    public void setUnitLabel(String unitLabel) {
        this.unitLabel = unitLabel;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
