package Dragon;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DragonAcademy {
    private static ArrayList<Dragon> dragons = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1:
                    addDragon();
                    break;
                case 2:
                    showDragons();
                    break;
                case 3:
                    searchDragonById();
                    break;
                case 4:
                    searchDragonByName();
                    break;
                case 5:
                    modifyDragon();
                    break;
                case 6:
                    deleteDragon();
                    break;
                case 7:
                    exportDragons();
                    break;
                case 8:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== Dragon Training Academy ===");
        System.out.println("1. Add Dragon");
        System.out.println("2. Show List of Dragons");
        System.out.println("3. Search Dragon by ID");
        System.out.println("4. Search Dragon by Name");
        System.out.println("5. Modify Dragon");
        System.out.println("6. Delete Dragon");
        System.out.println("7. Export Dragon List");
        System.out.println("8. Exit");
        System.out.println("================================");
    }

    private static void addDragon() {
        System.out.println("\n--- Add Dragon ---");
        int id = getValidId();
        String name = getValidName();
        String type = getValidType();
        int level = getPositiveInt("Enter level: ");
        int atk = getPositiveInt("Enter ATK: ");
        int def = getPositiveInt("Enter DEF: ");

        Dragon dragon = new Dragon(id, name, type, level, atk, def);
        dragons.add(dragon);
        System.out.println("Dragon added successfully!");
    }

    private static void showDragons() {
        System.out.println("\n--- Show List of Dragons ---");
        if (dragons.isEmpty()) {
            System.out.println("No dragons in the academy.");
            return;
        }

        String filterChoice = getStringInput("Apply search filter? (YES/NO): ").toUpperCase();
        ArrayList<Dragon> toShow = new ArrayList<>(dragons);

        if (filterChoice.equals("YES")) {
            System.out.println("Filter by: ID, NAME, TYPE, LEVEL, ATK, DEF");
            String attribute = getStringInput("Enter attribute: ").toUpperCase();
            String value = getStringInput("Enter value: ");

            toShow = filterDragons(toShow, attribute, value);
        }

        // Sort by ID
        toShow.sort(Comparator.comparingInt(Dragon::getId));

        System.out.println("\nDragons:");
        for (Dragon d : toShow) {
            System.out.println(d);
        }
    }

    private static ArrayList<Dragon> filterDragons(ArrayList<Dragon> list, String attribute, String value) {
        return list.stream()
                .filter(d -> {
                    switch (attribute) {
                        case "ID":
                            return String.valueOf(d.getId()).equals(value);
                        case "NAME":
                            return d.getName().equalsIgnoreCase(value);
                        case "TYPE":
                            return d.getType().equalsIgnoreCase(value);
                        case "LEVEL":
                            return String.valueOf(d.getLevel()).equals(value);
                        case "ATK":
                            return String.valueOf(d.getAtk()).equals(value);
                        case "DEF":
                            return String.valueOf(d.getDef()).equals(value);
                        default:
                            return false;
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static void searchDragonById() {
        System.out.println("\n--- Search Dragon by ID ---");
        int id = getIntInput("Enter ID: ");
        Dragon dragon = findDragonById(id);
        if (dragon != null) {
            System.out.println("Found: " + dragon);
        } else {
            System.out.println("Dragon with ID " + id + " not found.");
        }
    }

    private static void searchDragonByName() {
        System.out.println("\n--- Search Dragon by Name ---");
        String name = getStringInput("Enter name: ");
        Dragon dragon = findDragonByName(name);
        if (dragon != null) {
            System.out.println("Found: " + dragon);
        } else {
            System.out.println("Dragon with name '" + name + "' not found.");
        }
    }

    private static void modifyDragon() {
        System.out.println("\n--- Modify Dragon ---");
        Dragon dragon = findDragon("Enter ID or name to modify: ");
        if (dragon == null) return;

        System.out.println("Current: " + dragon);
        System.out.println("Enter new values (press Enter to keep current):");

        String newName = getOptionalStringInput("Name (" + dragon.getName() + "): ");
        if (!newName.isEmpty()) {
            if (isNameTaken(newName, dragon.getId())) {
                System.out.println("Name already taken.");
                return;
            }
            dragon.setName(newName);
        }

        String newType = getOptionalStringInput("Type (" + dragon.getType() + "): ");
        if (!newType.isEmpty()) {
            if (!isValidType(newType)) {
                System.out.println("Invalid type.");
                return;
            }
            dragon.setType(newType);
        }

        String levelStr = getOptionalStringInput("Level (" + dragon.getLevel() + "): ");
        if (!levelStr.isEmpty()) {
            try {
                int level = Integer.parseInt(levelStr);
                if (level <= 0) throw new NumberFormatException();
                dragon.setLevel(level);
            } catch (NumberFormatException e) {
                System.out.println("Invalid level.");
                return;
            }
        }

        String atkStr = getOptionalStringInput("ATK (" + dragon.getAtk() + "): ");
        if (!atkStr.isEmpty()) {
            try {
                int atk = Integer.parseInt(atkStr);
                if (atk <= 0) throw new NumberFormatException();
                dragon.setAtk(atk);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ATK.");
                return;
            }
        }

        String defStr = getOptionalStringInput("DEF (" + dragon.getDef() + "): ");
        if (!defStr.isEmpty()) {
            try {
                int def = Integer.parseInt(defStr);
                if (def <= 0) throw new NumberFormatException();
                dragon.setDef(def);
            } catch (NumberFormatException e) {
                System.out.println("Invalid DEF.");
                return;
            }
        }

        System.out.println("Dragon modified successfully!");
    }

    private static void deleteDragon() {
        System.out.println("\n--- Delete Dragon ---");
        Dragon dragon = findDragon("Enter ID or name to delete: ");
        if (dragon == null) return;

        dragons.remove(dragon);
        System.out.println("Dragon deleted successfully!");
    }

    private static void exportDragons() {
        System.out.println("\n--- Export Dragon List ---");
        if (dragons.isEmpty()) {
            System.out.println("No dragons to export.");
            return;
        }

        System.out.println("Formats: CSV, XLS, XLSX, JSON");
        String format = getStringInput("Enter format: ").toUpperCase();
        if (!format.equals("CSV") && !format.equals("XLS") && !format.equals("XLSX") && !format.equals("JSON")) {
            System.out.println("Invalid format.");
            return;
        }

        String filename = getStringInput("Enter filename (without extension): ");
        if (filename.trim().isEmpty()) {
            System.out.println("Invalid filename.");
            return;
        }

        boolean success = DragonExporter.export(dragons, format, filename);
        if (success) {
            System.out.println("Export successful!");
        } else {
            System.out.println("Export failed.");
        }
    }

    // Helper methods
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static int getPositiveInt(String prompt) {
        while (true) {
            int num = getIntInput(prompt);
            if (num > 0) return num;
            System.out.println("Must be positive.");
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String getOptionalStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getValidId() {
        while (true) {
            int id = getPositiveInt("Enter ID: ");
            if (!isIdTaken(id)) return id;
            System.out.println("ID already taken.");
        }
    }

    private static String getValidName() {
        while (true) {
            String name = getStringInput("Enter name: ");
            if (name.isEmpty()) {
                System.out.println("Name cannot be blank.");
                continue;
            }
            if (!isNameTaken(name, -1)) return name;
            System.out.println("Name already taken.");
        }
    }

    private static String getValidType() {
        while (true) {
            String type = getStringInput("Enter type (fire, water, earth, wind, light, darkness): ");
            if (isValidType(type)) return type;
            System.out.println("Invalid type.");
        }
    }

    private static boolean isValidType(String type) {
        for (String valid : Dragon.VALID_TYPES) {
            if (valid.equalsIgnoreCase(type)) return true;
        }
        return false;
    }

    private static boolean isIdTaken(int id) {
        return dragons.stream().anyMatch(d -> d.getId() == id);
    }

    private static boolean isNameTaken(String name, int excludeId) {
        return dragons.stream().anyMatch(d -> d.getId() != excludeId && d.getName().equalsIgnoreCase(name));
    }

    private static Dragon findDragonById(int id) {
        return dragons.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    private static Dragon findDragonByName(String name) {
        return dragons.stream().filter(d -> d.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    private static Dragon findDragon(String prompt) {
        String input = getStringInput(prompt);
        try {
            int id = Integer.parseInt(input);
            return findDragonById(id);
        } catch (NumberFormatException e) {
            return findDragonByName(input);
        }
    }
}
