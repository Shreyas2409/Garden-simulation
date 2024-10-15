package com.shreyas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PesticideController {
    private static final Logger log = LogManager.getLogger(PesticideController.class);

    public void applyPesticide(List<Plant> plants) {
        log.info("Applying pesticides to all plants to prevent pest attacks.");
        for (Plant plant : plants) {
            if (!plant.isAlive()) {
                log.info("Skipping pesticide application on {} as it is not alive.", plant.getName());
                continue;
            }
            plant.setPesticideApplied(true);
            log.info("Pesticide applied to {} to enhance resistance against pests.", plant.getName());
        }
    }
}
