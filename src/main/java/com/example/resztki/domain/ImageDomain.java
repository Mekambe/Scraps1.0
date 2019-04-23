package com.example.resztki.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@ToString
@NoArgsConstructor
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
