package creatures;

import tiles.TileType;

import java.awt.*;
import java.util.UUID;

public abstract class Creature extends Point {
    TileType tileType;
    int hitPoints;
    int armorClass;
    int attackValue;
    String name;
    UUID uniqueId;

    Creature(int x, int y) {
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
            Creature creatureObject = (Creature) object;
            return compareObjectProperties(creatureObject);
        } catch (ClassCastException e) {
            return super.equals(object);
        }
    }

    private boolean compareObjectProperties(Creature creatureObject) {
        if (this.uniqueId != creatureObject.uniqueId) {
            return false;
        }

        if (this.name == null || !this.name.equals(creatureObject.name)) {
            return false;
        }
        if (this.hitPoints != creatureObject.hitPoints) {
            return false;
        }
        if (this.armorClass != creatureObject.armorClass) {
            return false;
        }
        if (this.attackValue != creatureObject.attackValue) {
            return false;
        }

        return this.tileType == creatureObject.tileType;
    }

    private int dealDamageWithAC(int attackValue) {
        int damageToTake = attackValue - armorClass;
        if (damageToTake > 0) {
            return damageToTake;
        }
        return 0;
    }

}
