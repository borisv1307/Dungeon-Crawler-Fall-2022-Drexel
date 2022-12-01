package entities;

import tiles.TileType;

import java.awt.*;

public abstract class Entity extends Point {
    TileType tileType;
    int hitPoints;
    int armorClass;
    int attackValue;

    String name;

    Entity(int x, int y) {
        super(x, y);
    }

    public int receiveDamage(int attackValue) {
        int damageToDeal = dealDamageWithAC(attackValue);
        hitPoints = hitPoints - damageToDeal;
        return hitPoints;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public String getName() {
        return name;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    @Override
    public boolean equals(Object object) {
        try {
            Entity entityObject = (Entity) object;
            return compareObjectProperties(entityObject);
        } catch (ClassCastException e) {
            return super.equals(object);
        }
    }

    private boolean compareObjectProperties(Entity entityObect) {
        if (this.name != entityObect.name) {
            return false;
        }
        if (this.hitPoints != entityObect.hitPoints) {
            return false;
        }
        if (this.armorClass != entityObect.armorClass) {
            return false;
        }
        if (this.attackValue != entityObect.attackValue) {
            return false;
        }

        return this.tileType == entityObect.tileType;
    }

    private int dealDamageWithAC(int attackValue) {
        int damageToTake = attackValue - armorClass;
        if (damageToTake > 0) {
            return damageToTake;
        }
        return 0;
    }

}
