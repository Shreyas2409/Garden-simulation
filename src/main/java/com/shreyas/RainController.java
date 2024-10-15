package com.shreyas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RainController {
    private static final Logger log = LogManager.getLogger(RainController.class);
    private static final int RAINFALL_THRESHOLD = 5; // Threshold for activating sprinklers
    private final SprinklerController sprinklerController = new SprinklerController();

    public void simulateRain(int rainfallAmount, List<Plant> plants) {
        log.info("Simulating rain of {} units.", rainfallAmount);

        if (rainfallAmount < RAINFALL_THRESHOLD) {
            log.warn("Insufficient rainfall received {} units which is below the {}. Activating sprinkler system.", rainfallAmount, RAINFALL_THRESHOLD);
            sprinklerController.activateSprinklers(plants);
        } else {
            for (Plant plant : plants) {
                plant.water(rainfallAmount);
            }
        }
    }
}