package com.shreyas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SprinklerController {
    private static final Logger log = LogManager.getLogger(SprinklerController.class);

    public void activateSprinklers(List<Plant> plants) {
        int averageWaterRequirement = calculateAverageWaterRequirement(plants);
        log.info("Activating sprinklers, providing an average of {} units of water to all plants.", averageWaterRequirement);
        for (Plant plant : plants) {
            plant.water(averageWaterRequirement);
        }
    }

    private int calculateAverageWaterRequirement(List<Plant> plants) {
        int totalRequirement = 0;
        for (Plant plant : plants) {
            totalRequirement += plant.getWaterRequirement();
        }
        return totalRequirement / plants.size();
    }

}