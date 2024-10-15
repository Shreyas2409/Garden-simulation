package com.shreyas;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static com.shreyas.Pest.pests;
import static com.shreyas.Plant.plantImageViewMap;
import static com.shreyas.Plant.plantsList;

public class ViewController {
    private static final Logger log = LogManager.getLogger(GardenController.class);
    public static final Image sunflowerImage = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/sunflower.jpg")));
    public static final Image lilyImage = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/lily.jpg")));
    public static final Map<Pest, ImageView> pestImageViewMap = new HashMap<>();
    private static final Image roseImage = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/rose.jpg")));
    private static final Image soilImage = new Image("file:OIP.jfif");
    private static final Image pestImage = new Image(Objects.requireNonNull(ViewController.class.getResourceAsStream("/images/bug.jpg")));
    public static Set<String> occupiedCells = new HashSet<>();
    public static ArrayList<String> occupiedPestCells = new ArrayList<>();
    private HeatingController heatingController;
    private SprinklerController sprinklerController;
    private PesticideController pesticideController;
    private RainController rainController;
    private TemperatureController temperatureController;


    public static int day = 1;
    public Button pressToPlayButton;
    public Button iterateDayButton;
    @FXML
    private GridPane gardenGrid;
    @FXML
    private GridPane weatherGrid;
    @FXML
    private Pane imagePane;
    @FXML
    private RadioButton roseButton;
    @FXML
    private RadioButton sunflowerButton;
    @FXML
    private RadioButton lilyButton;
    @FXML
    private HBox imageBox = new HBox();
    @FXML
    private HBox weatherBox = new HBox();
    private Timeline timeline;
    @FXML
    private Label userInfoLabel;
    @FXML
    private Label label2;
    @FXML
    private Label systemLabel;
    @FXML
    private Button heatingButton;
    @FXML
    private Button sprinklersButton;
    @FXML
    private Button pesticideButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Button rainButton;
    @FXML private Label temperatureWarningLabel;

    public ViewController() {
        heatingController = new HeatingController();
        sprinklerController = new SprinklerController();
        pesticideController = new PesticideController();
        rainController = new RainController();
        temperatureController = new TemperatureController();
    }

    @FXML
    public void initializeGarden() throws FileNotFoundException {
        int rows = gardenGrid.getRowCount() + 1;
        int cols = gardenGrid.getColumnCount() + 1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                HBox imageBox = new HBox();
                ImageView soilView = new ImageView();
                soilView.setFitHeight(25);
                soilView.setFitWidth(25);
                soilView.setImage(soilImage);
                imageBox.getChildren().add(soilView);
                gardenGrid.add(imageBox, row, col);
                userInfoLabel.setText("   Today is Day 1");
            }
        }
        pressToPlayButton.setDisable(true);
    }

    //this is our method to call each plant method depending on which button is selected
    @FXML
    public void plantPlants() {
        log.info("Planting plants");
        EventHandler<MouseEvent> plantHandler = event -> {
            Node node = (Node) event.getSource();
            int row = GridPane.getRowIndex(node);
            int col = GridPane.getColumnIndex(node);
            try {
                if (roseButton.isSelected()) {
                    plantRose(row, col);
                }
                if (sunflowerButton.isSelected()) {
                    plantSunflower(row, col);
                }
                if (lilyButton.isSelected()) {
                    plantLily(row, col);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
        for (Node node : gardenGrid.getChildren()) {
            node.setOnMouseClicked(plantHandler);
        }
    }

    //planting a Rose ; creating rose object and adding it to the gird
    public void plantRose(int row, int col) throws FileNotFoundException {
        String roseCell = row + "," + col;
        if (occupiedCells.contains(roseCell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(roseImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(roseCell);
        Rose rose = new Rose(gardenGrid);
        plantImageViewMap.put(rose, plantView);
        plantsList.add(rose);
    }


    //planting a Sunflower; create Sunflower object and add it to the grid
    public void plantSunflower(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(sunflowerImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
        Sunflower sunflower = new Sunflower(gardenGrid);
        plantImageViewMap.put(sunflower, plantView);
        plantsList.add(sunflower);
    }

    //planting a Lily; create lily object and add it to the grid
    public void plantLily(int row, int col) throws FileNotFoundException {
        String cell = row + "," + col;
        if (occupiedCells.contains(cell)) {
            return;
        }
        HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
        ImageView plantView = new ImageView();
        plantView.setFitHeight(25);
        plantView.setFitWidth(25);
        plantView.setImage(lilyImage);
        imageBox.getChildren().add(plantView);
        occupiedCells.add(cell);
        Lily lily = new Lily(gardenGrid);
        plantImageViewMap.put(lily, plantView);
        plantsList.add(lily);
    }
    public void activateHeating() {
        int temperature = heatingController.activateHeating();
        // Updates UI with temperature
    }

    public void activateSprinklers() {
        sprinklerController.activateSprinklers(plantsList);
        // Updates UI to indicate sprinklers are active
    }

    @FXML
    private void applyPesticide(ActionEvent event) {
        pesticideController.applyPesticide(plantsList);
        removePestsImmediately();
    }
    private void removePestsImmediately() {
        Platform.runLater(() -> {
            List<Pest> pestsToRemove = new ArrayList<>();
            for (Pest pest : pests) {
                ImageView pestView = pestImageViewMap.get(pest);
                if (pestView != null) {
                    // Remove ImageView from grid
                    HBox imageBox = (HBox) pestView.getParent();
                    imageBox.getChildren().remove(pestView);
                    pestsToRemove.add(pest);
                    pestImageViewMap.remove(pest);
                    occupiedPestCells.remove(pest.getRow() + "," + pest.getCol());
                }
            }
            pests.removeAll(pestsToRemove);
        });
    }
    @FXML
    private void activateRain(ActionEvent event) {
        int rainfallAmount = 10; // Set the desired rainfall amount
        rainController.simulateRain(rainfallAmount, plantsList);
        adjustSprinklersBasedOnRainfall(rainfallAmount);
    }
    private void adjustSprinklersBasedOnRainfall(int rainfallAmount) {
        Platform.runLater(() -> {
            if (rainfallAmount <=5) {
                log.warn("Insufficient rainfall received. Activating sprinkler system.");
                sprinklerController.activateSprinklers(plantsList);
            } else {
                log.info("Sufficient rainfall received. No need to activate sprinklers.");
            }
        });
    }
    @FXML
    private void adjustTemperature(ActionEvent event) {
        Platform.runLater(() -> {
            int currentTemperature = getCurrentTemperature();
            temperatureController.adjustTemperature(currentTemperature, plantsList);
            handleTemperatureWarnings(currentTemperature);
        });
    }

    private void handleTemperatureWarnings(int temperature) {
        if (temperature < TemperatureController.LOWER_TEMPERATURE_THRESHOLD) {
            int adjustedTemperature = heatingController.activateHeating();
            log.warn("Warning: Low temperature detected. Heating system activated. Current temperature: " + adjustedTemperature + "°F");
        } else if (temperature > 120) {
            log.warn("Extreme high temperature detected. All plants will die.");
        } else {
            log.info("Temperature is within the normal range.");
        }
    }


    private int getCurrentTemperature() {
        return 24;
    }

    //remove plant object from grid
    public void die() {
        ArrayList<Plant> plants = new ArrayList<>();

        for (Plant plant : plantsList) {
            int col = plant.getCol();
            int row = plant.getRow();
            String cell = row + "," + col;
            ImageView plantView = plantImageViewMap.get(plant);

            if (plantView != null) {
                // remove ImageView from grid
                HBox imageBox = (HBox) plantView.getParent();
                imageBox.getChildren().remove(plantView);

                // remove association between Flower object and ImageView
                plantImageViewMap.remove(plant);
                //occupiedCells.remove(cell);
                occupiedCells.remove(cell);
                plants.add(plant);
            }
        }

        plantsList.removeAll(plants);
    }


    //incrementing each day and calling appropriate methods to run each day
    public void iterateDay() throws IOException {
        day++;
        userInfoLabel.setText("Today is Day " + day);
        pestControl();
        pestKillPlant();
        addPestsToCells();
        if (!occupiedCells.isEmpty()) {
            //pests();
        }
    }

    public void iterateDayWithTimer() throws InterruptedException, IOException {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<>() {
            int i = 0;

            @Override
            public void handle(ActionEvent event) {
                try {
                    iterateDay();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
        }));
        timeline.setCycleCount(100);
        timeline.play();
        iterateDayButton.setDisable(true);
    }

    public void finish() {
        System.exit(0);
    }

    public void addPestsToCells() {
        for (String cell : occupiedCells) {
            Random random = new Random();
            int num = random.nextInt(3);
            if (num == 1) {
                String[] parts = cell.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                HBox imageBox = (HBox) gardenGrid.getChildren().get(col * gardenGrid.getRowCount() + (row + 1));
                ImageView pestView = new ImageView();
                pestView.setFitHeight(25);
                pestView.setFitWidth(25);
                pestView.setImage(pestImage);
                imageBox.getChildren().add(pestView);
                Pest pest = new Pest(row, col, 0);
                pestImageViewMap.put(pest, pestView);
                occupiedPestCells.add(cell);
                pests.add(pest);
                for (Plant plant : Plant.plantsList) {
                    if (plant.getRow() == row && plant.getCol() == col) {
                        plant.numPests++;
                    }
                }
            }
        }
    }

    public void pestKillPlant() {
        Platform.runLater(() -> {
            List<Plant> plantsToRemove = new ArrayList<>();
            List<Pest> pestsToRemove = new ArrayList<>();
            for (Plant plant : plantsList) {
                if (plant.numPests >= 1) {
                    int col = plant.getCol();
                    int row = plant.getRow();
                    String cell = row + "," + col;
                    ImageView plantView = plantImageViewMap.get(plant);

                    if (plantView != null) {
                        // remove ImageView from grid
                        HBox imageBox = (HBox) plantView.getParent();
                        imageBox.getChildren().remove(plantView);

                        // remove association between Plant object and ImageView
                        plantImageViewMap.remove(plant);
                        occupiedCells.remove(cell);
                        plantsToRemove.add(plant);

                        for (Pest pest : pests) {
                            if (pest.getRow() == row && pest.getCol() == col) {
                                ImageView pestView = pestImageViewMap.get(pest);
                                imageBox.getChildren().remove(pestView);
                                pestImageViewMap.remove(pest, pestView);
                                pestsToRemove.add(pest);
                            }
                        }
                    }
                }
            }
            Plant.plantsList.removeAll(plantsToRemove);
            pests.removeAll(pestsToRemove);
        });
    }

    public void pestControl() {
        List<Pest> pestsToRemove = new ArrayList<>();

        for (Pest pest : pests) {
            Random ran = new Random();
            int rando = ran.nextInt(8);

            String cell = pest.getRow() + "," + pest.getCol();
            if (rando != 1) {
                ImageView spiderView = pestImageViewMap.get(pest);

                if (spiderView != null) {
                    // remove ImageView from grid
                    HBox imageBox = (HBox) spiderView.getParent();
                    imageBox.getChildren().remove(spiderView);
                    pestsToRemove.add(pest);

                    pestImageViewMap.remove(pest);
                    occupiedPestCells.remove(cell);
                }
            }
        }
        pests.removeAll(pestsToRemove);
        pestKillPlant();
    }


}
