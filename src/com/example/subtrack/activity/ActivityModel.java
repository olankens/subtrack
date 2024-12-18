package com.example.subtrack.activity;

public class ActivityModel {
    public String title;
    public Double price;
    public boolean isPaid = false;
    public boolean isUnique;
    public int time = 1;

    public ActivityModel(String title, Double price, boolean isUnique) {
        this.title = title;
        this.price = price;
        this.isUnique = isUnique;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "isPaid=" + isPaid +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", isUnique=" + isUnique +
                ", time=" + time +
                '}';
    }
}
