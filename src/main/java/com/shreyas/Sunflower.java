package com.shreyas;

import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class Sunflower extends Plant {

    public Sunflower() {
        super("Sunflower", 75, 10, Arrays.asList("Grubs", "Weevils"));
    }

    public Sunflower(GridPane gardenGrid) {
        super("Sunflower", 75, 10, Arrays.asList("Grubs", "Weevils"), gardenGrid);
    }
}
