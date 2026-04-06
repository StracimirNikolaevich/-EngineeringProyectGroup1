package dragon;

public class Dragon {
	private int id;
    private String name;
    private String type;
    private int powerLevel;
    private int atk;
    private int def;
    
    public static final String[] VALID_TYPES = {"fire", "water", "earth", "wind", "light", "darkness"};
    
    public Dragon(int id, String name, String type, int level, int atk, int def) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.powerLevel = level;
        this.atk = atk;
        this.def = def;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getLevel() { return powerLevel; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setLevel(int level) { this.powerLevel = powerLevel; }
    public void setAtk(int atk) { this.atk = atk; }
    public void setDef(int def) { this.def = def; }
    
    @Override
    public String toString() {
        return String.format("ID:%d | Name:%s | Type:%s | Level:%d | ATK:%d | DEF:%d",
                id, name, type, powerLevel, atk, def);
    }
}
