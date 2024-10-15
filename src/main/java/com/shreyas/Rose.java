package com.shreyas;


import javafx.scene.layout.GridPane;

import java.util.Arrays;


public class Rose extends Plant {

    public Rose() {
        super("Rose", 70, 15, Arrays.asList("Aphids", "Spider Mites"));
    }

    public Rose( GridPane gardenGrid) {
        super("Rose", 70, 15, Arrays.asList("Aphids", "Spider Mites"));
    }


    public void handleRain(int amount) {
        water(amount);
        if (getCurrentWaterLevel() > getWaterRequirement() * 2) {
            setAlive(false);
        }
    }


    public void handleInsectAttack(String insectType) {
        if (getParasites().contains(insectType)) {
            setAlive(false); // Insect attack kills the rose
        }
    }
}
