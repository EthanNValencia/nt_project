package com.nephew.products.entities;

import jakarta.persistence.*;

@Embeddable
public class TaxCode {
    /** A detailed description of which types of products the tax code represents. */
    private String description;

    /** A short name for the tax code. */
    private String name;

    /**
     * String representing the object's type. Objects of the same type share the same value.
     *
     * <p>Equal to {@code tax_code}.
     */
    private String object;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
