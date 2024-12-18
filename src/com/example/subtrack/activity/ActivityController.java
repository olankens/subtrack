package com.example.subtrack.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityController {
    private final List<ActivityModel> activities = new ArrayList<>();

    public ActivityController() {
        setDefault();
    }

    public void create(ActivityModel activity) {
        if (activity != null) activities.add(activity);
    }

    public void delete(String title) {
        ActivityModel activity = search(title);
        if (activity != null) activities.remove(activity);
    }

    public void detail(String title) {
        ActivityModel activity = search(title);
        if (activity != null) System.out.println(activity);
    }

    public void output() {
        activities.forEach(System.out::println);
    }

    public void outputWithIndex() {
        AtomicInteger i = new AtomicInteger();
        activities.forEach(a -> System.out.println(i.getAndIncrement() + ": " + a));
    }

    public ActivityModel search(String title) {
        return activities.stream()
                .filter(a -> a.title.equals(title))
                .findFirst()
                .orElse(null);
    }

    public void update(String title, ActivityModel activity) {
        activities.stream()
                .filter(a -> a.title.equals(title))
                .findFirst()
                .ifPresent(a -> {
                    int index = activities.indexOf(a);
                    activities.set(index, activity);
                });
    }

    private void setDefault() {
        activities.add(new ActivityModel("Dinner", 50.0, true));
        activities.add(new ActivityModel("Housing", 80.0, true));
        activities.add(new ActivityModel("Training Hour", 40.0, false));
    }
}
