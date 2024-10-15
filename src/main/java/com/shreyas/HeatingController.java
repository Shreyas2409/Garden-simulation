package com.shreyas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HeatingController {
    Logger log = LogManager.getLogger(HeatingController.class);
    private static final int MINIMUM_SAFE_TEMPERATURE = 50; // Sets a minimum safe temperature for plants

    public int activateHeating() {
        log.info("Activating heating system to increase temperature to {} °F.", MINIMUM_SAFE_TEMPERATURE);
        return MINIMUM_SAFE_TEMPERATURE;
    }
}