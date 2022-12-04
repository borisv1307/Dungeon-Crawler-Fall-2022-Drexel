package entity;

import values.TileColorMap;

public class Player extends CharacterObject {

    public Player() {
        super();
    }

    public void levelUp() {
        level += 1;
        levelStatChange();
        TileColorMap.changePlayerHpBar(this);
    }

    private void levelStatChange() {
        int baseStat = 10;
        setMaxHealthPoint(baseStat + level * 5);
        setAttackPoint(baseStat + level * 2);
    }

    public void resetPlayerStatus() {
        setToDefaultStatus();
        TileColorMap.changePlayerHpBar(this);
    }

}
