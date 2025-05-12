package SysUtils;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Validations {
    public static String readNonEmptyString(String prompt) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if(input.isEmpty()){
                System.out.println("Input cannot be empty.");
                continue; // restart loop and prompt the user again
            }
            return input;
        }
    }

    public static int readPositiveInt(String prompt) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine());

                if (input < 0) {
                    System.out.println("Input cannot be negative.");
                    continue;
                }
                return input;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static int readIntInRange (String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine());

                if (input < min || input > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return input;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static String readEmail(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please enter a valid email address.");
                    continue;
                }

                // Regular expression (Regex) to match with valid email formats
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

                // Compile Regex
                Pattern p = Pattern.compile(emailRegex);

                // Check to see if user's input matches with the valid pattern
                if (p.matcher(input).matches()) {
                    return input;
                }
                System.out.println("Please enter a valid email address.");
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid email address.");
            }
        }
    }
}
