package entities;

import tiles.TileType;

import java.awt.*;

public abstract class Entity extends Point {
    TileType tileType;
    int hitPoints;
    int armorClass;
    int attackValue;

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

    @Override
    public boolean equals(Object obj){
        if (!super.equals(obj)){
            return false;
        }

        Entity entityObj = (Entity) obj;
        return compareObjectProperties(entityObj);
    }

    private boolean compareObjectProperties(Entity entityObj){
        if (this.hitPoints != entityObj.hitPoints){
            return false;
        }
        if (this.armorClass != entityObj.armorClass){
            return false;
        }
        if (this.attackValue != entityObj.attackValue){
            return false;
        }
        if (this.tileType == entityObj.tileType){
            return false;
        }
        return true;
    }


}
