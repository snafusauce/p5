public class Player {
    private static int serial = 0;

    public enum Color {
        BLACK,
        BLUE,
        GREEN,
        ORANGE,
        PURPLE,
        RED,
        YELLOW
    }

    // Instance variables
    private String name;
    private int id;
    private String handed; // Right-handed or left-handed
    private int level; // level is in range [1, 9]
    private Color color; // Uses enum class
    
    // One-argument constructor
    public Player(String name) {
        this.name = name;
        this.id = ++serial;
    }

    // No-argument constructor
    public Player() {
        this.id = ++serial;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public boolean setHanded(String handed) {
        boolean isValid = false;

        // Qualifies valid left-handed input
        if (handed.equals("l") || handed.equals("L") ||
            handed.equals("left") ||
            handed.equals("Left") || handed.equals("LEFT"))
        {
            this.handed = "left";
            isValid = true;
        }

        // Qualifies valid right-handed input
        if (handed.equals("r") || handed.equals("R") ||
            handed.equals("right") ||
            handed.equals("Right") || handed.equals("RIGHT"))
        {
            this.handed = "right";
            isValid = true;
        }

        return isValid;
    }

    public boolean setLevel(int level) {
        boolean isValid = false;

        // Qualifies value of level
        if (0 < level && level < 10) {
            this.level = level;
            isValid = true;
        }

        return isValid;
    }

    public boolean setColor(int color) {
        boolean isValid = false;

        Color arr[] = Color.values();
        for (Color col : arr) {
            if (col.ordinal() == color) {
                this.color = col;
                isValid = true;
                break;
            }
        }

        return isValid;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public String getHanded() {
        return this.handed;
    }

    public int getLevel() {
        return this.level;
    }

    public Color getColor() {
        return this.color;
    }

    // Equals check
    public boolean equals(Player otherPlayer) {
        return this.name.equals(otherPlayer.getName()) &&
            this.id == otherPlayer.getId() &&
            this.handed.equals(otherPlayer.getHanded()) &&
            this.level == otherPlayer.getLevel() &&
            this.color.equals(otherPlayer.getColor());
    }

    // To string
    public String toString() {
        String s = "";
        s = "Name: " + this.name + "\n"
            + "ID: " + this.id + "\n"
            + "Handed: " + this.handed + "-handed\n"
            + "Level: " + this.level + "\n"
            + "Color: " + this.color + "\n";
        return s;
    }
}
