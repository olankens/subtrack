package com.example.subtrack.club;

public class ClubModel {
    public String name;

    public ClubModel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                '}';
    }
}
