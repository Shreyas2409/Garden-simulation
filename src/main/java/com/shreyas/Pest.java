package com.shreyas;

import java.util.ArrayList;

public class Pest {
    public static ArrayList<Pest> pests = new ArrayList<>();
    int row;
    int col;
    int numPests;
    private String plantFood;

    public Pest(int row, int col, int numPests) {
        this.row = row;
        this.col = col;
        this.numPests = numPests;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
