package de.chris.my_plugin.utils;

public enum CombatVersions {
    // 16 is enough to disable it
    OLD_PVP("1.8", 16),
    NEW_PVP("1.9+", 4);

    private String name;
    private double baseAttackSpeed;

    CombatVersions(String name, double baseAttackSpeed) {
        this.name = name;
        this.baseAttackSpeed = baseAttackSpeed;
    }

    public String getName() {
        return name;
    }

    public double getBaseAttackSpeed() {
        return baseAttackSpeed;
    }
}
