package com.example.resztki.dto;

public class MenuDto {

    String poductInsideTheMenu;


    Integer regularPriceOfTheProduct;

    Integer newSaledPriceOfTheProduct;

    Integer customerID;

    public String getPoductInsideTheMenu() {
        return poductInsideTheMenu;
    }

    public void setPoductInsideTheMenu(String poductInsideTheMenu) {
        this.poductInsideTheMenu = poductInsideTheMenu;
    }

    public Integer getRegularPriceOfTheProduct() {
        return regularPriceOfTheProduct;
    }

    public void setRegularPriceOfTheProduct(Integer regularPriceOfTheProduct) {
        this.regularPriceOfTheProduct = regularPriceOfTheProduct;
    }

    public Integer getNewSaledPriceOfTheProduct() {
        return newSaledPriceOfTheProduct;
    }

    public void setNewSaledPriceOfTheProduct(Integer newSaledPriceOfTheProduct) {
        this.newSaledPriceOfTheProduct = newSaledPriceOfTheProduct;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
}
