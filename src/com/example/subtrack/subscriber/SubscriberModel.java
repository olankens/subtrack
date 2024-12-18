package com.example.subtrack.subscriber;

import com.example.subtrack.activity.ActivityModel;
import com.example.subtrack.club.ClubModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubscriberModel {
    public String firstName;
    public String lastName;
    public String email;
    public ClubModel club;
    public List<ActivityModel> activities;
    public boolean isTrainer;

    public SubscriberModel(String firstName, String lastName, String email, ClubModel club, List<ActivityModel> activities, boolean isTrainer) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = email;
        this.club = club;
        this.activities = activities == null ? new ArrayList<>() : activities;
        this.isTrainer = isTrainer;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "activities=" + (activities != null ? activities.toString() : null) +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + email + '\'' +
                ", club=" + (club != null ? club.toString() : null) +
                ", isTrainer=" + isTrainer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubscriberModel that = (SubscriberModel) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
