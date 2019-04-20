package com.example.resztki.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class ImageDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImage;

    @OneToOne(mappedBy = "image")
            CustomerDomain customerDomain;

    String image;

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
