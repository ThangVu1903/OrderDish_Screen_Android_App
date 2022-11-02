package com.example.orderdish.model;

import java.io.Serializable;

public class Dish implements Serializable {

    public String idMon, tenMon,thongTin, giaTien, diaChi,monAnThem;
    public String anh;

    public Dish(){
    }

    public Dish(String idMon,String anh, String tenMon, String thongTin, String giaTien, String diaChi, String monAnThem) {
        this.idMon = idMon;
        this.anh = anh;
        this.tenMon = tenMon;
        this.thongTin = thongTin;
        this.giaTien = giaTien;
        this.diaChi = diaChi;
        this.monAnThem = monAnThem;
    }
}
