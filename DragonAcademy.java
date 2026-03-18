package dragons;

import java.util.ArrayList;
import java.util.Scanner;

public class DragonAcademy {
    private static ArrayList<dragons> dragonList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option = 0;

        while (option != 6) {
            System.out.println("\n--- DRAGON TRAINING ACADEMY ---");
            System.out.println("1. Add Dragon");
            System.out.println("2. Show All Dragons");
            System.out.println("3. Search Dragon by ID");
            System.out.println("4. Edit Dragon");
            System.out.println("5. Delete Dragon");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            
            option = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (option) {
                case 1 -> addDragon();
                case 2 -> showDragons();
                case 3 -> searchDragon();
                case 4 -> editDragon();
                case 5 -> deleteDragon();
                case 6 -> System.out.println("Exiting... Fly safe!");
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addDragon() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Type (fire, water, earth, wind, light, darkness): ");
        String type = scanner.nextLine();
        System.out.print("Enter Power Level: ");
        int power = scanner.nextInt();

        dragonList.add(new dragons(id, name, type, power));
        System.out.println("Dragon added successfully!");
    }

    private static void showDragons() {
        if (dragonList.isEmpty()) {
            System.out.println("The academy is empty!");
        } else {
            dragonList.forEach(System.out::println);
        }
    }

    private static void searchDragon() {
        System.out.print("Enter ID to search: ");
        int id = scanner.nextInt();
        for (dragons d : dragonList) {
            if (d.getId() == id) {
                System.out.println("Found: " + d);
                return;
            }
        }
        System.out.println("Dragon not found.");
    }

    private static void editDragon() {
        System.out.print("Enter ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (dragons d : dragonList) {
            if (d.getId() == id) {
                System.out.print("New Name: ");
                d.setName(scanner.nextLine());
                System.out.print("New Type: ");
                d.setType(scanner.nextLine());
                System.out.print("New Power Level: ");
                d.setPowerLevel(scanner.nextInt());
                System.out.println("Dragon updated!");
                return;
            }
        }
        System.out.println("ID not found.");
    }

    private static void deleteDragon() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();
        boolean removed = dragonList.removeIf(d -> d.getId() == id);
        if (removed) {
            System.out.println("Dragon removed.");
        } else {
            System.out.println("Dragon not found.");
        }
    }
}
