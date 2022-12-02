package entities;

import tiles.TileType;

import java.awt.*;
import java.util.UUID;

public abstract class Entity extends Point {
    TileType tileType;
    int hitPoints;
    int armorClass;
    int attackValue;
    String name;
    UUID uniqueId;

    Entity(int x, int y) {
        super(x, y);
        uniqueId = UUID.randomUUID();
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

    public UUID getUniqueId() {
        return uniqueId;
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

    private boolean compareObjectProperties(Entity entityObject) {
        if (this.uniqueId != entityObject.uniqueId) {
            return false;
        }

        if (this.name == null || !this.name.equals(entityObject.name)) {
            return false;
        }
        if (this.hitPoints != entityObject.hitPoints) {
            return false;
        }
        if (this.armorClass != entityObject.armorClass) {
            return false;
        }
        if (this.attackValue != entityObject.attackValue) {
            return false;
        }

        return this.tileType == entityObject.tileType;
    }

    private int dealDamageWithAC(int attackValue) {
        int damageToTake = attackValue - armorClass;
        if (damageToTake > 0) {
            return damageToTake;
        }
        return 0;
    }

}
