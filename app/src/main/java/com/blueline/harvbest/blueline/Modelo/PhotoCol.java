package com.blueline.harvbest.blueline.Modelo;


/**
 * Created by ExpoCode Tech http://expocodetech.com
 */

public class PhotoCol {
    private String titleco;
    private String imageUrlco;
    private String clave;
    private String orden;
    private String idcoleccion;

    private String categoryid;
    private String fileTitle;
    private String url;

    public PhotoCol(){    }

    public String getImageUrlco() {
        return imageUrlco;
    }

    public void setImageUrl(String imageUrlco) {
        this.imageUrlco = imageUrlco;
    }

    public String getTitleco() {
        return titleco;
    }

    public void setTitle(String titleco) {
        this.titleco = titleco;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }


    public String getIdcoleccion() {
        return idcoleccion;
    }

    public void setIdcoleccion (String  idcoleccion) {
        this.idcoleccion = idcoleccion;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

}
