 
package dragons;

public class dragons {
    private int id;
    private String name;
    private String type;
    private int powerLevel;

    public dragons(int id, String name, String type, int powerLevel) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.powerLevel = powerLevel;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getPowerLevel() { return powerLevel; }
    public void setPowerLevel(int powerLevel) { this.powerLevel = powerLevel; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-10s | Type: %-8s | Power: %d", 
                id, name, type, powerLevel);
    }
}
