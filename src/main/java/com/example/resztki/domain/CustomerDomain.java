package com.example.resztki.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@NoArgsConstructor
public class CustomerDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            private int idCustomer;

    @OneToOne
    private ImageDomain image;

    @Column(unique = true)
    String name;
    String address;
    String openHours;
    String days;
    String nameOfTheDish;

    public ImageDomain getImage() {
        return image;
    }

    public void setImage(ImageDomain image) {
        this.image = image;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }



    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getNameOfTheDish() {
        return nameOfTheDish;
    }

    public void setNameOfTheDish(String nameOfTheDish) {
        this.nameOfTheDish = nameOfTheDish;
    }
}
