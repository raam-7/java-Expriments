import java.util.Scanner;

public class MenuArithmetic {

    // Addition method
    static int add(int a, int b) {
        return a + b;
    }

    // Subtraction method
    static int subtract(int a, int b) {
        return a - b;
    }

    // Multiplication method
    static int multiply(int a, int b) {
        return a * b;
    }

    // Division method
    static void divide(int a, int b) {
        if (b == 0) {
            System.out.println("Division by zero is not allowed");
        } else {
            System.out.println("Result = " + (a / b));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice, x = 0, y = 0;

        do {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice >= 1 && choice <= 4) {
                System.out.print("Enter first number: ");
                x = sc.nextInt();
                System.out.print("Enter second number: ");
                y = sc.nextInt();
            }

            switch (choice) {
                case 1:
                    System.out.println("Result = " + add(x, y));
                    break;
                case 2:
                    System.out.println("Result = " + subtract(x, y));
                    break;  
                case 3:
                    System.out.println("Result = " + multiply(x, y));
                    break;
                    
                case 4:
                    divide(x, y);
                    break;
                case 5:
                    System.out.println("Program terminated");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);

        sc.close();
    }
}
