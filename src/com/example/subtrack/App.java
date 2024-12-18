package com.example.subtrack;

import com.example.subtrack.activity.ActivityController;
import com.example.subtrack.activity.ActivityView;
import com.example.subtrack.club.ClubController;
import com.example.subtrack.subscriber.SubscriberController;
import com.example.subtrack.subscriber.SubscriberView;

import static com.example.subtrack.utility.InputGetter.getIntInput;

public class App {
    private final ActivityController activityController;
    private final ClubController clubController;
    private final SubscriberController subscriberController;

    public App(
            ActivityController activityController,
            ClubController clubController,
            SubscriberController subscriberController) {
        this.activityController = activityController;
        this.clubController = clubController;
        this.subscriberController = subscriberController;
        int choice;
        do {
            System.out.printf("\n%s\n\n", "-".repeat(66));
            System.out.println("Management Menu:\n");
            System.out.println("1. Manage Activities");
            System.out.println("2. Manage Subscribers");
            System.out.println("3. Exit\n");
            choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1:
                    manageActivities();
                    break;
                case 2:
                    manageSubscribers();
                    break;
                case 3:
                    System.out.println("Exiting...\n");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 3);
    }

    public static void main(String[] args) {
        String heading = """
                ░██████╗██╗░░░██╗██████╗░████████╗██████╗░░█████╗░░█████╗░██╗░░██╗
                ██╔════╝██║░░░██║██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗██╔══██╗██║░██╔╝
                ╚█████╗░██║░░░██║██████╦╝░░░██║░░░██████╔╝███████║██║░░╚═╝█████═╝░
                ░╚═══██╗██║░░░██║██╔══██╗░░░██║░░░██╔══██╗██╔══██║██║░░██╗██╔═██╗░
                ██████╔╝╚██████╔╝██████╦╝░░░██║░░░██║░░██║██║░░██║╚█████╔╝██║░╚██╗
                ╚═════╝░░╚═════╝░╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚═╝░╚════╝░╚═╝░░╚═╝
                """;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.printf("\n\u001B[32m%s\u001B[0m", heading);
        ActivityController activityController = new ActivityController();
        ClubController clubController = new ClubController();
        SubscriberController subscriberController = new SubscriberController();
        new App(activityController, clubController, subscriberController);
    }

    private void manageActivities() {
        new ActivityView(activityController);
    }

    private void manageSubscribers() {
        new SubscriberView(activityController, clubController, subscriberController);
    }
}
