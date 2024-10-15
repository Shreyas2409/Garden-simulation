package com.shreyas;

import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class Lily extends Plant {
    public Lily() {
        super("Lily", 60,12, Arrays.asList("Beetles", "Aphids"));
    }

    public Lily(GridPane gardenGrid) {
        super("Lily", 60,12, Arrays.asList("Beetles", "Aphids"), gardenGrid);
    }
}
