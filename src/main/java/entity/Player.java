package entity;

public class Player extends CharacterObject {

    public Player() {
        super();
    }

    public void levelUp() {
        level += 1;
        levelStatChange();
    }

    private void levelStatChange() {
        int baseState = 10;
        setMaxHealthPoint(baseState + level * 5);
        setAttackPoint(baseState + level * 2);
    }


}
