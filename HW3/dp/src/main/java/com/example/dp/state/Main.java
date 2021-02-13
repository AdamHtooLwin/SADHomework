package com.example.dp.state;

public class Main {
    public static void main(String[] args) {
        Swordman sm = new Swordman();
        sm.increaseAttack(4);
        sm.speedUp(3);
        sm.increaseDefense(1);
        sm.speedUp(2);

        System.out.println("Character present state: ");
        sm.printStates();
    }
}
