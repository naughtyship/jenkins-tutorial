package com.example.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Calculator App!");

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("Type 'exit' to quit the application.");

            String input = scanner.nextLine();

            // Check for the exit command
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the Calculator App. Goodbye!");
                break;
            }

            try {
                int choice = Integer.parseInt(input);

                System.out.println("Enter first number:");
                int num1 = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter second number:");
                int num2 = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Result: " + calculator.add(num1, num2));
                        break;
                    case 2:
                        System.out.println("Result: " + calculator.subtract(num1, num2));
                        break;
                    case 3:
                        System.out.println("Result: " + calculator.multiply(num1, num2));
                        break;
                    case 4:
                        System.out.println("Result: " + calculator.divide(num1, num2));
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number or 'exit'.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
