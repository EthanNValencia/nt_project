package com.nephew.products.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 60)
    private String companyName;
    @Column(length = 20)
    private String companyAcronym;
    @Column(length = 30)
    private String companyUrl;

    // Removed cascade = CascadeType.ALL,

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("company")
    private List<Product> products;

    public Company() {
        super();
        this.products = new ArrayList<>();
    }

    public Company(long id) {
        super();
        this.id = id;
    }

    public void assignIdToChildren() {
        for (Product product : products) {
            product.setCompany(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getCompanyAcronym() {
        return companyAcronym;
    }

    public void setCompanyAcronym(String companyAcronym) {
        this.companyAcronym = companyAcronym;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Product> getFaqs() {
        return products;
    }

    public void setFaqs(List<Product> products) {
        this.products = products;
    }


    @Override
    public int hashCode() {
        return Objects.hash(companyAcronym, companyName, companyUrl, products, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Company other = (Company) obj;
        return Objects.equals(companyAcronym, other.companyAcronym) && Objects.equals(companyName, other.companyName)
                && Objects.equals(companyUrl, other.companyUrl);
    }

}
