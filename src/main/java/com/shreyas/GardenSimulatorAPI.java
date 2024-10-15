package com.shreyas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GardenSimulatorAPI {
    Logger log = LogManager.getLogger(GardenSimulatorAPI.class);
    ArrayList<Plant> plants = new ArrayList<>();
    GardenController gardenController;

    public void initializeGarden() {
        loadPlants("config.json");
        log.info("Garden initialized with plants: {}", plants);
        gardenController = new GardenController(plants);
    }


     // Loads the required plants from configuration file

    public void loadPlants(String configFile) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(configFile)) {
            if (is == null) {
                throw new FileNotFoundException("Resource file '" + configFile + "' not found in the classpath");
            }
            String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            JSONObject json = new JSONObject(content);
            JSONArray plantsArray = json.getJSONArray("plants");
            for (int i = 0; i < plantsArray.length(); i++) {
                JSONObject plantObj = plantsArray.getJSONObject(i);
                String name = plantObj.getString("name");
                int waterRequirement = plantObj.getInt("waterRequirement");
                int temperature = plantObj.getInt("temperature");  // Added line to read the temperature
                JSONArray parasitesArray = plantObj.getJSONArray("parasites");
                List<String> parasites = new ArrayList<>();
                for (int j = 0; j < parasitesArray.length(); j++) {
                    parasites.add(parasitesArray.getString(j));
                }
                plants.add(new Plant(name, waterRequirement, temperature, parasites)); // Adjusted to use the read temperature
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while loading and reading the plant details config.json file", e);
        }
    }

    public Map<String, Object> getPlants() {
        return getPlantsInfo();
    }

    public Map<String, Object> getPlantsInfo() {
        List<String> plantNames = new ArrayList<>();
        List<Integer> waterRequirements = new ArrayList<>();
        List<List<String>> parasitesList = new ArrayList<>();

        for (Plant plant : plants) {
            if (plant.isAlive()) {
                plantNames.add(plant.getName());
                waterRequirements.add(plant.getWaterRequirement());
                parasitesList.add(plant.getParasites());
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("plants", plantNames);
        result.put("waterRequirement", waterRequirements);
        result.put("parasites", parasitesList);
        return result;
    }


    public void rain(int amount) {
        new GardenThread(() -> {
            gardenController.simulateRain(amount);
            log.info("It rained {} units", amount);
        }, "rainThread").start();
    }

    public void temperature(int temperature) {
        new GardenThread(() -> {
            log.info("Temperature reached {} F", temperature);
            gardenController.simulateTemperature(temperature);
        }, "temperatureThread").start();
    }

    public void parasites(String parasiteName) {
        log.info("Parasite {} infested the garden", parasiteName);
        gardenController.simulatePestAttack(parasiteName);
    }


    public void getStatus() {
        // Retrieves and logs the current status of the garden
        log.info("Alive Plants : {}", gardenController.getAlivePlants());
        log.info("Dead Plants : {}", gardenController.getDeadPlants());
    }

}
