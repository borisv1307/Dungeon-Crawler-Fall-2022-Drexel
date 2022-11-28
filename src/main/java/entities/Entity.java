package entities;

import tiles.TileType;

import java.awt.*;

public abstract class Entity extends Point {
    public TileType tileType;
    public int hitPoints;
    public int armorClass;
    public int attackValue;

    Entity(int x, int y){
        super(x, y);
    }

    public int receiveDamage(int attackValue){
        int damageToDeal = dealDamageWithAC(attackValue);
        hitPoints = hitPoints - damageToDeal;
        return hitPoints;
    }

    private int dealDamageWithAC(int attackValue){
        int damageToTake = attackValue - armorClass;
        if (damageToTake > 0) {
            return damageToTake;
        }
        return 0;
    }

    public int getAttackValue(){
        return attackValue;
    }
    public int getHitPoints(){
        return hitPoints;
    }

    public int getArmorClass(){
        return armorClass;
    }

    public TileType getTileType() {return tileType;}
    public void setTileType(TileType tileType){
        this.tileType = tileType;
    }

}
