package com.example.subtrack.subscriber;

import java.util.ArrayList;
import java.util.List;

public class SubscriberController {
    private final List<SubscriberModel> subscribers = new ArrayList<>();

    public void create(SubscriberModel subscriber) {
        if (subscriber != null) subscribers.add(subscriber);
    }

    public void delete(String email) {
        SubscriberModel subscriber = search(email);
        if (subscriber != null) subscribers.remove(subscriber);
    }

    public void detail(String email) {
        SubscriberModel subscriber = search(email);
        if (subscriber != null) System.out.println(subscriber);
    }

    public void output() {
        subscribers.forEach(System.out::println);
    }

    public SubscriberModel search(String email) {
        return subscribers.stream()
                .filter(subscriber -> subscriber.email.equals(email))
                .findFirst()
                .orElse(null);
    }

    public void update(String email, SubscriberModel subscriber) {
        subscribers.stream()
                .filter(element -> element.email.equals(email))
                .findFirst()
                .ifPresent(element -> {
                    int index = subscribers.indexOf(element);
                    subscribers.set(index, subscriber);
                });
    }

    private void setDefault() {
        create(new SubscriberModel("John", "Doe", "john@doe.com", null, null, false));
        create(new SubscriberModel("test", "test", "test", null, null, false));
    }
}
