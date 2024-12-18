package com.example.subtrack.activity;

import static com.example.subtrack.utility.InputGetter.*;

public class ActivityView {
    private final ActivityController controller;

    public ActivityView(ActivityController controller) {
        this.controller = controller;
        int choice;
        do {
            System.out.printf("\n%s\n\n", "-".repeat(66));
            System.out.println("Activity Management Menu:\n");
            System.out.println("1. Create Activity");
            System.out.println("2. Update Activity");
            System.out.println("3. Delete Activity");
            System.out.println("4. Output Activity Details");
            System.out.println("5. Output All Activities");
            System.out.println("6. Exit\n");
            choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    detail();
                    break;
                case 5:
                    output();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
    }

    private void create() {
        String title = getStrInput("Enter activity title: ");
        Double price = getDecInput("Enter activity price: ");
        boolean isUnique = getBooInput("Enter activity unique: ");
        ActivityModel newActivity = new ActivityModel(title, price, isUnique);
        controller.create(newActivity);
    }

    private void delete() {
        String title = getStrInput("Enter the activity title to delete: ");
        ActivityModel fetched = controller.search(title);
        if (fetched != null) {
            controller.delete(title);
        } else {
            System.out.println("\u001B[31mNo activity found with this title!\u001B[0m");
        }
    }

    private void detail() {
        String title = getStrInput("Enter the activity title to view details: ");
        ActivityModel fetched = controller.search(title);
        if (fetched != null) {
            controller.detail(title);
        } else {
            System.out.println("\u001B[31mNo activity found with this title!\u001B[0m");
        }
    }

    private void update() {
        String search = getStrInput("Enter the activity title to update: ");
        ActivityModel fetched = controller.search(search);
        if (fetched != null) {
            String title = getStrInput("Enter new activity title: ");
            Double price = getDecInput("Enter new activity price: ");
            ActivityModel newActivity = new ActivityModel(title, price, fetched.isUnique);
            controller.update(search, newActivity);
        } else {
            System.out.println("\u001B[31mNo activity found with this title!\u001B[0m");
        }
    }

    private void output() {
        controller.output();
    }
}
