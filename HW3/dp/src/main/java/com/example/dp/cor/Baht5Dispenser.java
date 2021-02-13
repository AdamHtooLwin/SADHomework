package com.example.dp.cor;

public class Baht5Dispenser implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 5) {
            int num = cur.getAmount() / 5;
            int remainder = cur.getAmount() % 5;
            System.out.println("Dispensing " + num + " 5 baht coin(s");

            if (remainder != 0) {
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            // go to next chain
            this.chain.dispense(cur);
        }

    }

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }
}
