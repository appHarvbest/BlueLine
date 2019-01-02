package com.blueline.harvbest.blueline.Modelo;

public class Producto {

    private String titlepro;
    private String imageUrlpro;
    private String sku;

    public Producto(){   }

    public String getImageUrlpro() {
        return imageUrlpro;
    }

    public void setImageUrlpro(String imageUrlpro) {
        this.imageUrlpro = imageUrlpro;
    }


    public String getTitlepro() {
        return titlepro;
    }

    public void setTitlepro(String titlepro) {
        this.titlepro = titlepro;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }


}
