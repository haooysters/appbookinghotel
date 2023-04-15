package com.example.thuephongkhachsan;

public class Posts {
    private String baiviet;

    private String anhbaiviet;

    public Posts(String baiviet, String anhbaiviet) {
        this.baiviet= baiviet;
        this.anhbaiviet= anhbaiviet;
    }

    public String getBaiviet() {
        return baiviet;
    }

    public void setBaiviet(String baiviet) {
        this.baiviet = baiviet;
    }

    public String getAnhbaiviet() {
        return anhbaiviet;
    }

    public void setAnhbaiviet(String anhbaiviet) {
        this.anhbaiviet = anhbaiviet;
    }

}
