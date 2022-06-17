package com.gof.core;

public class Cell {
    private boolean currState;
    private boolean nextState;

    public Cell(boolean currState) {
        this.currState = currState;
    }

    public Cell() {}

    public boolean isCurrState() {
        return currState;
    }

    public void setCurrState(boolean currState) {
        this.currState = currState;
    }

    public void updateState() {
        currState = nextState;
    }

    public void setNextState(boolean nextState) {
        this.nextState = nextState;
    }
}
