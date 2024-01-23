package com.nephew.products.entities;

public class TaxCode {
    /** A detailed description of which types of products the tax code represents. */
    private String description;

    /** Unique identifier for the object. */
    private Long id;

    /** A short name for the tax code. */
    private String name;

    /**
     * String representing the object's type. Objects of the same type share the same value.
     *
     * <p>Equal to {@code tax_code}.
     */
    private String object;

}
