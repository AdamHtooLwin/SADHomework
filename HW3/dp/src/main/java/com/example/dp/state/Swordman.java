package com.example.dp.state;

public class Swordman implements State {
    int agi = 7;
    int atk = 13;
    int def = 5;

    @Override
    public void increaseAttack(int increment) {
        atk = atk + increment;
        def = def - (int) 0.3 * increment;
    }

    @Override
    public void increaseDefense(int increment) {
        def = def + increment;
    }

    @Override
    public void printStates() {
        System.out.println("Agi-atk-def: " + agi + "-" + atk + "-" + def);
    }

    @Override
    public void speedUp(int increment) {
        atk = atk + 2 * increment;
        agi = agi + increment;
    }
    
}
