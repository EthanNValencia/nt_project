package com.nephew.products.entities;

import jakarta.persistence.*;

@Entity
public class Recurring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Specifies a usage aggregation strategy for prices of {@code usage_type=metered}. Allowed
     * values are {@code sum} for summing up all usage during a period, {@code last_during_period}
     * for using the last usage record reported within a period, {@code last_ever} for using the
     * last usage record ever (across period bounds) or {@code max} which uses the usage record with
     * the maximum reported usage during a period. Defaults to {@code sum}.
     *
     * <p>One of {@code last_during_period}, {@code last_ever}, {@code max}, or {@code sum}.
     */
    private String aggregateUsage;

    /**
     * The frequency at which a subscription is billed. One of {@code day}, {@code week}, {@code
     * month} or {@code year}.
     */
    private String interval;

    /**
     * The number of intervals (specified in the {@code interval} attribute) between subscription
     * billings. For example, {@code interval=month} and {@code interval_count=3} bills every 3
     * months.
     */
    private Long intervalCount;

    /**
     * Default number of trial days when subscribing a customer to this price using <a
     * href="https://stripe.com/docs/api#create_subscription-trial_from_plan">{@code
     * trial_from_plan=true}</a>.
     */
    private Long trialPeriodDays;

    /**
     * Configures how the quantity per period should be determined. Can be either {@code metered} or
     * {@code licensed}. {@code licensed} automatically bills the {@code quantity} set when adding
     * it to a subscription. {@code metered} aggregates the total usage based on usage records.
     * Defaults to {@code licensed}.
     */
    private String usageType;

    @ManyToOne
    @JoinColumn(name = "recurring_id")
    private Recurring recurring;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAggregateUsage() {
        return aggregateUsage;
    }

    public void setAggregateUsage(String aggregateUsage) {
        this.aggregateUsage = aggregateUsage;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Long getIntervalCount() {
        return intervalCount;
    }

    public void setIntervalCount(Long intervalCount) {
        this.intervalCount = intervalCount;
    }

    public Long getTrialPeriodDays() {
        return trialPeriodDays;
    }

    public void setTrialPeriodDays(Long trialPeriodDays) {
        this.trialPeriodDays = trialPeriodDays;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }
}
