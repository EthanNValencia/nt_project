package com.nephew.products.entities;

import jakarta.persistence.Embeddable;
@Embeddable
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

    public Long getMaximum() {
        return maximum;
    }

    public void setMaximum(Long maximum) {
        this.maximum = maximum;
    }

    public Long getMinimum() {
        return minimum;
    }

    public void setMinimum(Long minimum) {
        this.minimum = minimum;
    }

    public Long getPreset() {
        return preset;
    }

    public void setPreset(Long preset) {
        this.preset = preset;
    }
}
