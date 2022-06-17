package com.gof.core;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

public class UniverseTest {
    @Test
    public void testGetNeighbours() {
        Cell[][] cells = {
                {new Cell(true), new Cell(true), new Cell(true)},
                {new Cell(true), new Cell(true), new Cell(true)},
                {new Cell(true), new Cell(true), new Cell(true)}
        };
        Universe universe = new Universe(cells);

        assertEquals(3, universe.liveNeighbors(0,0));
        assertEquals(8, universe.liveNeighbors(1,1));
        assertEquals(5, universe.liveNeighbors(1,0));
    }

    @Test
    public void testNextNeighbours() {
        Cell[][] cells = {
                {new Cell(false), new Cell(true), new Cell(false)},
                {new Cell(false), new Cell(false), new Cell(true)},
                {new Cell(true), new Cell(true), new Cell(true)},
                {new Cell(false), new Cell(false), new Cell(false)}
        };
        Universe universe = new Universe(cells);
        universe.nextGeneration();

        assertEquals(false, universe.isAlive(0, 0));
        assertEquals(false, universe.isAlive(0, 1));
        assertEquals(false, universe.isAlive(0, 2));

        assertEquals(true, universe.isAlive(1, 0));
        assertEquals(false, universe.isAlive(1, 1));
        assertEquals(true, universe.isAlive(1, 2));

        assertEquals(false, universe.isAlive(2, 0));
        assertEquals(true, universe.isAlive(2, 1));
        assertEquals(true, universe.isAlive(2, 2));

        assertEquals(false, universe.isAlive(3, 0));
        assertEquals(true, universe.isAlive(3, 1));
        assertEquals(false, universe.isAlive(3, 2));
    }

    @Test
    public void testAllCellsDieInNextGen() {
        Cell[][] cells = {
                {new Cell(true), new Cell(), new Cell(true)},
                {new Cell(), new Cell(), new Cell()},
                {new Cell(true), new Cell(), new Cell(true)}
        };

        Universe universe = new Universe(cells);
        universe.nextGeneration();

        assertEquals(false, universe.isAlive(0, 0));
        assertEquals(false, universe.isAlive(2, 2));
        assertEquals(false, universe.isAlive(2, 0));
        assertEquals(false, universe.isAlive(0, 2));
        assertEquals(false, universe.isAlive(1, 1));
    }

    @Test
    public void testNoChange() {
        Cell[][] cells = {
                {new Cell(true), new Cell(true), new Cell()},
                {new Cell(true), new Cell(true), new Cell()},
                {new Cell(), new Cell(), new Cell()}
        };

        Universe universe = new Universe(cells);
        universe.nextGeneration();
        assertSame(cells, universe.getGrid());
    }

    @Test
    public void testOverpopulation() {
        Cell[][] cells = {
                {new Cell(true), new Cell(true), new Cell(true)},
                {new Cell(true), new Cell(true), new Cell()},
                {new Cell(), new Cell(), new Cell()}
        };

        Universe universe = new Universe(cells);

        universe.nextGeneration();

        assertEquals(true, universe.isAlive(0, 0));
        assertEquals(false, universe.isAlive(0, 1));
        assertEquals(true, universe.isAlive(0, 2));

        assertEquals(true, universe.isAlive(1, 0));
        assertEquals(false, universe.isAlive(1, 1));
        assertEquals(true, universe.isAlive(1, 2));
    }
}
