package com.example.subtrack.utility;

import java.util.Scanner;

public class InputGetter {
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean getBooInput(String massage) {
        System.out.print(massage);
        while (!scanner.hasNextBoolean()) {
            System.out.print("Invalid input. Please enter true or false: ");
            scanner.next();
        }
        boolean content = scanner.nextBoolean();
        scanner.nextLine();
        return content;
    }

    public static Double getDecInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        Double content = scanner.nextDouble();
        scanner.nextLine();
        return content;
    }

    public static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        int content = scanner.nextInt();
        scanner.nextLine();
        return content;
    }

    public static String getStrInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
