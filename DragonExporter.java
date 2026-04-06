package Dragon;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DragonExporter {
    
    public static boolean export(ArrayList<Dragon> dragons, String format, String filename) {
        if (dragons == null || dragons.isEmpty()) {
            System.out.println("No dragons to export.");
            return false;
        }

        switch (format.toLowerCase()) {
            case "csv":
                return exportToCSV(dragons, filename);
            case "xls":
            case "xlsx":
                return exportToExcelCompatible(dragons, filename, format.toLowerCase());
            case "json":
                return exportToJSON(dragons, filename);
            default:
                System.out.println("Unknown format: " + format);
                return false;
        }
    }

    private static boolean exportToCSV(ArrayList<Dragon> dragons, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename + ".csv"))) {
            writer.println("ID,Name,Type,Level,ATK,DEF");
            for (Dragon dragon : dragons) {
                writer.println(dragon.toCSV());
            }
            return true;
        } catch (IOException e) {
            System.err.println("CSV Export Error: " + e.getMessage());
            return false;
        }
    }

    private static boolean exportToExcelCompatible(ArrayList<Dragon> dragons, String filename, String ext) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename + "." + ext))) {
            writer.println("ID\tName\tType\tLevel\tATK\tDEF");
            for (Dragon d : dragons) {
                // Using \t for tab-separation so Excel opens it correctly
                writer.printf("%d\t%s\t%s\t%d\t%d\t%d%n", 
                    d.getId(), d.getName(), d.getType(), d.getLevel(), d.getAtk(), d.getDef());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Excel Export Error: " + e.getMessage());
            return false;
        }
    }

    private static boolean exportToJSON(ArrayList<Dragon> dragons, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename + ".json"))) {
            writer.println("[");
            for (int i = 0; i < dragons.size(); i++) {
                writer.print("  " + dragons.get(i).toJSON());
                if (i < dragons.size() - 1) writer.println(",");
                else writer.println();
            }
            writer.println("]");
            return true;
        } catch (IOException e) {
            System.err.println("JSON Export Error: " + e.getMessage());
            return false;
        }
    }
}
