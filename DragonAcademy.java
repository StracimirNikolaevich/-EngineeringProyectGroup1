package dragon;

public class Dragon {
    private int id;
    private String name;
    private String type;
    private int level;
    private int atk;
    private int def;

    public Dragon(int id, String name, String type, int level, int atk, int def) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.atk = atk;
        this.def = def;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getLevel() { return level; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setLevel(int level) { this.level = level; }
    public void setAtk(int atk) { this.atk = atk; }
    public void setDef(int def) { this.def = def; }

    // toCSV
    public String toCSV() {
        return id + "," + name + "," + type + "," + level + "," + atk + "," + def;
    }

    // toJSON 
    public String toJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":").append(id).append(",");
        sb.append("\"name\":\"").append(name).append("\",");
        sb.append("\"type\":\"").append(type).append("\",");
        sb.append("\"level\":").append(level).append(",");
        sb.append("\"atk\":").append(atk).append(",");
        sb.append("\"def\":").append(def);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Type: " + type + " | Level: " + level + " | ATK: " + atk + " | DEF: " + def;
    }
}
