package com.nephew.website.entities;

import jakarta.persistence.*;

public class WebsiteTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column
    private String buttonColor;

    @OneToOne
    @JoinColumn(name = "website_id", referencedColumnName = "id")
    private Website website;



}
