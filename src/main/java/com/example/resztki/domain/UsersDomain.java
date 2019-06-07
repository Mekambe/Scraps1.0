package com.example.resztki.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@ToString
public class UsersDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @OneToMany(mappedBy = "imageHandler")
    private List<DBFile> imageId = new ArrayList();

    @OneToMany(mappedBy = "priceHandler")
    private List<PriceDomain> priceId = new ArrayList();



    String userName;
    String companyName;
    String companyAddress;
    String openFrom;
    String openTo;




    public String getOpneFrom() {
        return openFrom;
    }

    public void setOpneFrom(String opneFrom) {
        this.openFrom = opneFrom;
    }

    public String getOpenTo() {
        return openTo;
    }

    public void setOpenTo(String openTo) {
        this.openTo = openTo;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<DBFile> getImageId() {
        return imageId;
    }

    public void setImageId(List<DBFile> imageId) {
        this.imageId = imageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
