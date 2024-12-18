package com.example.subtrack.subscriber;

import com.example.subtrack.activity.ActivityController;
import com.example.subtrack.club.ClubController;
import com.example.subtrack.activity.ActivityModel;
import com.example.subtrack.club.ClubModel;

import static com.example.subtrack.utility.InputGetter.*;

public class SubscriberView {
    private final ActivityController activityController;
    private final ClubController clubController;
    private final SubscriberController subscriberController;

    public SubscriberView(
            ActivityController activityController,
            ClubController clubController,
            SubscriberController subscriberController) {
        this.activityController = activityController;
        this.clubController = clubController;
        this.subscriberController = subscriberController;
        int choice;
        do {
            System.out.printf("\n%s\n\n", "-".repeat(66));
            System.out.println("Subscriber Management Menu:\n");
            System.out.println("1. Create Subscriber");
            System.out.println("3. Delete Subscriber");
            System.out.println("4. Append Activity To Subscriber");
            System.out.println("5. Remove Activity From Subscriber");
            System.out.println("6. Enable Is Paid To Activity For Subscriber");
            System.out.println("7. Remove Is Paid To Activity For Subscriber");
            System.out.println("8. Gather Paid Amount For Subscriber");
            System.out.println("9. Output All Subscribers");
            System.out.println("10. Exit\n");
            choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1:
                    createSubscriber();
                    break;
                case 3:
                    deleteSubscriber();
                    break;
                case 4:
                    appendActivityToSubscriber();
                    break;
                case 5:
                    removeActivityFromSubscriber();
                    break;
                case 6:
                    enableIsPaidToActivityForSubscriber();
                    break;
                case 7:
                    removeIsPaidToActivityForSubscriber();
                    break;
                case 8:
                    gatherPaidAmountForSubscriber();
                    break;
                case 9:
                    outputSubscriberList();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 10);
    }

    public void appendActivityToSubscriber() {
        String email = getStrInput("Enter subscriber email: ");
        SubscriberModel fetchedSubscriber = subscriberController.search(email);
        if (fetchedSubscriber != null) {
            String title = getStrInput("Enter activity title: ");
            ActivityModel fetchedActivity = activityController.search(title);
            if (fetchedActivity != null) {
                if (!fetchedSubscriber.activities.contains(fetchedActivity)) {
                    fetchedSubscriber.activities.add(fetchedActivity);
                } else {
                    System.out.println("\u001B[33mActivity already exists for this subscriber! \u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mActivity does not exist!\u001B[0m");
            }
        } else {
            System.out.println("\u001B[31mSubscriber does not exist!\u001B[0m");
        }
    }

    public void removeActivityFromSubscriber() {
        String email = getStrInput("Enter subscriber email: ");
        SubscriberModel fetchedSubscriber = subscriberController.search(email);
        if (fetchedSubscriber != null) {
            String title = getStrInput("Enter activity title: ");
            ActivityModel fetchedActivity = activityController.search(title);
            if (fetchedActivity != null) {
                if (fetchedSubscriber.activities.contains(fetchedActivity)) {
                    fetchedSubscriber.activities.remove(fetchedActivity);
                } else {
                    System.out.println("\u001B[33mActivity already removed from this subscriber!\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mActivity does not exist!\u001B[0m");
            }
        } else {
            System.out.println("\u001B[31mSubscriber does not exist!\u001B[0m");
        }
    }

    public void enableIsPaidToActivityForSubscriber() {
        String email = getStrInput("Enter subscriber email: ");
        SubscriberModel fetchedSubscriber = subscriberController.search(email);
        if (fetchedSubscriber != null) {
            String title = getStrInput("Enter activity title: ");
            ActivityModel fetchedActivity = activityController.search(title);
            if (fetchedActivity != null) {
                fetchedSubscriber.activities.stream()
                        .filter(a -> a.equals(fetchedActivity))
                        .peek(a -> a.isPaid = true)
                        .findFirst()
                        .isPresent();
            } else {
                System.out.println("\u001B[31mActivity does not exist!\u001B[0m");
            }
        } else {
            System.out.println("\u001B[31mSubscriber does not exist!\u001B[0m");
        }
    }

    public void removeIsPaidToActivityForSubscriber() {
        String email = getStrInput("Enter subscriber email: ");
        SubscriberModel fetchedSubscriber = subscriberController.search(email);
        if (fetchedSubscriber != null) {
            String title = getStrInput("Enter activity title: ");
            ActivityModel fetchedActivity = activityController.search(title);
            if (fetchedActivity != null) {
                fetchedSubscriber.activities.stream()
                        .filter(a -> a.equals(fetchedActivity))
                        .peek(a -> a.isPaid = false)
                        .findFirst()
                        .isPresent();
            } else {
                System.out.println("\u001B[31mActivity does not exist!\u001B[0m");
            }
        } else {
            System.out.println("\u001B[31mSubscriber does not exist!\u001B[0m");
        }
    }

    private void gatherPaidAmountForSubscriber() {
        String email = getStrInput("Enter subscriber email: ");
        SubscriberModel fetched = subscriberController.search(email);
        if (fetched != null) {
            if (fetched.isTrainer) {
                System.out.println("\u001B[31mSubscriber is a trainer, unpaid amount calculation skipped.!\u001B[0m");
            } else {
                Double amount = fetched.activities.stream()
                        .filter(a -> !a.isPaid)
                        .mapToDouble(a -> a.price)
                        .sum();
                System.out.println(amount);
            }
        } else {
            System.out.println("\u001B[31mSubscriber already exists with this email!\u001B[0m");
        }
    }

    private void createSubscriber() {
        String firstName = getStrInput("Enter subscriber first name: ");
        String lastName = getStrInput("Enter subscriber last name: ");
        String email = getStrInput("Enter subscriber email: ");
        boolean isTrainer = getBooInput("Enter subscriber isTrainer: ");
        SubscriberModel fetched = subscriberController.search(email);
        if (fetched == null) {
            String clubName = getStrInput("Enter subscriber club name: ");
            ClubModel club = (!clubName.isEmpty()) ? new ClubModel(clubName) : null;
            if (clubController.search(clubName) == null) clubController.create(club);
            SubscriberModel newSubscriber = new SubscriberModel(firstName, lastName, email, club, null, isTrainer);
            subscriberController.create(newSubscriber);
        } else {
            System.out.println("\u001B[31mSubscriber already exists with this email!\u001B[0m");
        }
    }

    private void deleteSubscriber() {
        String email = getStrInput("Enter the subscriber email to delete: ");
        SubscriberModel fetched = subscriberController.search(email);
        if (fetched != null) {
            subscriberController.delete(email);
        } else {
            System.out.println("\u001B[31mNo activity found with this email!\u001B[0m");
        }
    }

    private void outputSubscriberList() {
        subscriberController.output();
    }
}
