package com.gof.display;

import com.gof.core.Universe;

public class ConsoleDisplay implements Display {
    @Override
    public void displayBoard(Universe universe) {
        for (int h = 0; h < universe.getGrid().length; h++) {
            for (int w = 0; w < universe.getGrid()[h].length; w++) {
                if(universe.isAlive(h, w))
                    System.out.print("1 ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }
}
