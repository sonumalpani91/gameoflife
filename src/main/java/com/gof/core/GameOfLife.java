package com.gof.core;

import com.gof.display.ConsoleDisplay;
import com.gof.display.Display;

import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("Please enter number of generations to go: ");
        Scanner in = new Scanner(System.in);
        int iterations = in.nextInt();
        in.close();

        Universe b = new Universe(5, 5, 0.3);
        Display display = new ConsoleDisplay();

        System.out.println("Current generation: \n");
        display.displayBoard(b);
        for (int i = 1; i <= iterations; i++) {
            System.out.println("\n" + i + " generation: \n");
            display.displayBoard(b);
            b.nextGeneration();
            Thread.sleep(500);
        }
    }

}
