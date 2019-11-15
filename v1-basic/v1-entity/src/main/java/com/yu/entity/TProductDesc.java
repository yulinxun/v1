package com.yu.entity;

import java.io.Serializable;

public class TProductDesc implements Serializable{
    private Long id;

    private Long prodcutId;

    private String productDesc;

    @Override
    public String toString() {
        return "TProductDesc{" +
                "id=" + id +
                ", prodcutId=" + prodcutId +
                ", productDesc='" + productDesc + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdcutId() {
        return prodcutId;
    }

    public void setProdcutId(Long prodcutId) {
        this.prodcutId = prodcutId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }
}