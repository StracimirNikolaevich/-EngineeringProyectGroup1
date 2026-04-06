package dragon;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DragonExporter {
    
    // Single export method that handles all formats
    public static boolean export(ArrayList<Dragon> dragons, String format, String filename) {
        switch (format.toLowerCase()) {
            case "csv":
                return exportToCSV(dragons, filename);
            case "xls":
                return exportToXLS(dragons, filename);
            case "xlsx":
                return exportToXLSX(dragons, filename);
            case "json":
                return exportToJSON(dragons, filename);
            default:
                System.out.println("Unknown format: " + format);
                return false;
        }
    }

    private static boolean exportToCSV(ArrayList<Dragon> dragons, String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename + ".csv"));
            writer.println("ID,Name,Type,Level,ATK,DEF");
            for (Dragon dragon : dragons) {
                writer.println(dragon.toCSV());
            }
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    private static boolean exportToXLS(ArrayList<Dragon> dragons, String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename + ".xls"));
            writer.println("ID\tName\tType\tLevel\tATK\tDEF");
            for (Dragon dragon : dragons) {
                writer.println(dragon.getId() + "\t" + dragon.getName() + "\t" + 
                    dragon.getType() + "\t" + dragon.getLevel() + "\t" + 
                    dragon.getAtk() + "\t" + dragon.getDef());
            }
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    private static boolean exportToXLSX(ArrayList<Dragon> dragons, String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename + ".xlsx"));
            writer.println("ID,Name,Type,Level,ATK,DEF");
            for (Dragon dragon : dragons) {
                writer.println(dragon.toCSV());
            }
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    private static boolean exportToJSON(ArrayList<Dragon> dragons, String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename + ".json"));
            writer.println("[");
            for (int i = 0; i < dragons.size(); i++) {
                writer.print("  " + dragons.get(i).toJSON());
                if (i < dragons.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("]");
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
