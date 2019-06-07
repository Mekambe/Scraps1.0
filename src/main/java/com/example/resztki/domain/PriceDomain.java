package com.example.resztki.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@ToString
public class PriceDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int priceid;

    String poductInsideTheMenu;

    String productWithSpecialPriceInsideTheMenu;

    Integer regularPriceOfTheProduct;

    Integer newSaledPriceOfTheProduct;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price")
    UsersDomain priceHandler;

    public int getPriceid() {
        return priceid;
    }

    public void setPriceid(int priceid) {
        this.priceid = priceid;
    }

    public String getPoductInsideTheMenu() {
        return poductInsideTheMenu;
    }

    public void setPoductInsideTheMenu(String poductInsideTheMenu) {
        this.poductInsideTheMenu = poductInsideTheMenu;
    }

    public String getProductWithSpecialPriceInsideTheMenu() {
        return productWithSpecialPriceInsideTheMenu;
    }

    public void setProductWithSpecialPriceInsideTheMenu(String productWithSpecialPriceInsideTheMenu) {
        this.productWithSpecialPriceInsideTheMenu = productWithSpecialPriceInsideTheMenu;
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

    public UsersDomain getPriceHandler() {
        return priceHandler;
    }

    public void setPriceHandler(UsersDomain priceHandler) {
        this.priceHandler = priceHandler;
    }
}

