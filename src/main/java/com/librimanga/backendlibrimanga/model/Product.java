package com.librimanga.backendlibrimanga.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private Double price;
    private String img;

    // getter e setter
}
