package com.gof.core;

public class Universe {
    int[][] dirs = new int[][]{{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1}};
    private Cell[][] grid;

    public Universe(Cell[][] grid) {
        this.grid = grid;
    }

    /**
     * @param height
     * @param width
     * @param probability generate random number between 0,1,
     *          and use a probability number to choose between if a Cell is alive or dead at start
     */
    public Universe(int height, int width, double probability) {
        grid = new Cell[height][width];
        for (int h=0; h<grid.length; h++){
            for (int w=0; w<grid[h].length; w++){
                grid[h][w] = new Cell();
                if (Math.random() <= probability){
                    grid[h][w].setCurrState(true);
                }
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public boolean isAlive(int row, int col) {
        return grid[row][col].isCurrState();
    }

    public void nextGeneration() {
        calculateNextGeneration();
        updateBoard();
    }

    /**
     * Assigns new state to individual Cells
     * according to GoF rules
     */
    private void calculateNextGeneration() {
        for (int h = 0; h < grid.length; h++){
            for (int w = 0; w < grid[h].length; w++) {
                int liveNeighbors = liveNeighbors(h, w);
                if (liveNeighbors < 2 || liveNeighbors > 3 )
                    grid[h][w].setNextState(false);
                else if (liveNeighbors == 3)
                    grid[h][w].setNextState(true);
                else if (liveNeighbors == 2)
                    grid[h][w].setNextState(grid[h][w].isCurrState());
            }
        }
    }

    /**
     * Updates Cell state based on newState
     */
    private void updateBoard() {
        for (int h=0; h<grid.length; h++){
            for (int w=0; w<grid[h].length; w++){
                grid[h][w].updateState();
            }
        }
    }

    protected int liveNeighbors(int i, int j) {
        int activeNeigh = 0;
        for (int[] dir:dirs){
            int row = i + dir[0];
            int col = j + dir[1];
            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length &&
                    (grid[row][col].isCurrState()))
                activeNeigh++;
        }
        return activeNeigh;
    }
}
