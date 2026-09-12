package model;

public enum TriageLevel {

    ESI_1(1, "Resuscitation"),
    ESI_2(2, "Emergent"),
    ESI_3(3, "Urgent"),
    ESI_4(4, "Less Urgent"),
    ESI_5(5, "Non-Urgent");

    private final int level;
    private final String description;

    TriageLevel(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }
}