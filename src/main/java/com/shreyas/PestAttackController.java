package com.shreyas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class PestAttackController {
    private static final Logger log = LogManager.getLogger(PestAttackController.class);
    private final Random random = new Random();
    boolean pestAttacked = false;

    public void simulatePestAttack(String selectedPest, List<Plant> plants) {
        log.info("Simulating pest attack: {}", selectedPest);
        pestAttacked = false;

        for (Plant plant : plants) {
            if (plant.getParasites().contains(selectedPest)) {
                pestAttacked = true;
                if (plant.isPesticideApplied()) {
                    log.info("Pesticide protects {} from the {} pest attack.", plant.getName(), selectedPest);
                } else {
                    if (random.nextDouble() < 0.25) { // 25% chance the plant will be affected if it is vulnerable
                        plant.setAlive(false);
                        log.warn("Plant {} has been killed by a {} pest attack.", plant.getName(), selectedPest);
                    } else {
                        log.warn("Plant {} resisted a {} pest attack and it survived.", plant.getName(), selectedPest);
                    }
                }
            }
        }

        if(!pestAttacked){
            log.info("Pest {} does not have any impact for any of the plants", selectedPest);
        }
    }
}
