package com.shreyas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GardenController {
    private static final Logger log = LogManager.getLogger(GardenController.class);

    private List<Plant> plants;
    private RainController rainController;
    private TemperatureController temperatureController;
    private PestAttackController pestAttackController;
    private PesticideController pesticideController;
    private Random random = new Random();


    public GardenController(List<Plant> plants) {
        this.plants = plants;
        this.rainController = new RainController();
        this.temperatureController = new TemperatureController();
        this.pestAttackController = new PestAttackController();
        this.pesticideController = new PesticideController();
    }

    void simulateRain(int rainfall) {
        // Simulating rain
        rainController.simulateRain(rainfall, plants);
    }

    void simulateTemperature(int temperature) {
        // Simulating rain
        temperatureController.adjustTemperature(temperature, plants);
    }


     // Simulates pest attacks and pesticide.

    void simulatePestAttack(String pest) {
        // Deciding whether to apply pesticide randomly
        if (random.nextBoolean()) {
            pesticideController.applyPesticide(plants);
        }

        // Simulating pest attack
        pestAttackController.simulatePestAttack(pest, plants);
    }

    public List<String> getAlivePlants() {
        List<String> alive = new ArrayList<>();
        plants.forEach(plant -> {
            if (plant.isAlive()) {
                alive.add(plant.getName());
            }
        });
        return alive;
    }

    public List<String> getDeadPlants() {
        List<String> dead = new ArrayList<>();
        plants.forEach(plant -> {
            if (!plant.isAlive()) {
                dead.add(plant.getName());
            }
        });
        return dead;
    }

}
