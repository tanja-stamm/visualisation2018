import Model.DataModel;
import Model.Player;
import View.ToggleSwitch;
import javafx.application.Application;


import java.util.Arrays;


import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    //Data visualisation
    private BarChart<Number, String> chartAbsolute;
    private BarChart<Number, String> chartRelativ;

    BorderPane frame;
    Boolean isAbsolute = true;
    ToggleSwitch toggleSwitch;
    CheckBox checkBoxEurope;
    CheckBox checkBoxNorthAmerica;
    CheckBox checkBoxOthers;

    private boolean chartModeBereinigt = false;

    public Parent createRelativeChart() {
        final String[] countryNames = new String[DataModel.getAllCountriesFiltered().size()];

        for (int i = 0; i <= DataModel.getAllCountriesFiltered().size() - 1; i++) {
            countryNames[i] = DataModel.getAllCountriesFiltered().get(i).getCountryName();
        }

        CategoryAxis xAchse = new CategoryAxis();
        NumberAxis yAchse = new NumberAxis();
        chartRelativ = new BarChart<>(yAchse, xAchse);
        yAchse.setLabel("Land");
        xAchse.setCategories(FXCollections.<String>observableArrayList(Arrays
                .asList(countryNames)));
        yAchse.setLabel("Anzahl Drafts");

        // add starting data
        XYChart.Series<Number, String> series0 = new XYChart.Series<>();
        series0.setName("Data Series 0");

        chartRelativ.setLegendVisible(false);

        //fill in all the data: countries and number of players
        for (int i = 0; i <= countryNames.length - 1; i++) {
            double counter = 0;
            for (Player p : DataModel.getAllPlayersFiltered()) {
                if (p.getPlayerCountry().getCountryName().equals(DataModel.getAllCountriesFiltered().get(i).getCountryName())) {
                    counter++;
                }
            }
            XYChart.Data d = new XYChart.Data<Number, String>(counter / (DataModel.getAllCountriesFiltered().get(i).getPopulation() / 1000000), DataModel.getAllCountriesFiltered().get(i).getCountryName());
            series0.getData().add(d);
        }

        chartRelativ.getData().add(series0);

        // tooltip
        for (int i = 0; i <= countryNames.length-1; i++) {
            XYChart.Data item = (XYChart.Data) series0.getData().get(i);
            String value = series0.getData().get(i).getXValue().toString();
            Tooltip.install(item.getNode(), new Tooltip("Anzahl Drafts: " + value));

            //Changing the color of the bar depending on its value
            if (series0.getData().get(i).getXValue().intValue() < 10) {
                item.getNode().setStyle("-fx-bar-fill: #801638;");
            } else if (series0.getData().get(i).getXValue().intValue() < 50) {
                item.getNode().setStyle("-fx-bar-fill: #027878;");
            } else if (series0.getData().get(i).getXValue().intValue() < 200) {
                item.getNode().setStyle("-fx-bar-fill: #FDB632;");
            } else if (series0.getData().get(i).getXValue().intValue() > 200) {
                item.getNode().setStyle("-fx-bar-fill: #F37338;");
            }
        }
        return chartRelativ;
    }

    public Parent createAbsoluteChart() {
        //final String[] countryNames = new String[DataModel.getAllCountries().size()];
        final String[] countryNames = new String[DataModel.getAllCountriesFiltered().size()];


//        for (int i = 0; i <= DataModel.getAllCountries().size() - 1; i++) {
//            countryNames[i] = DataModel.getAllCountries().get(i).getCountryName();
//        }
        for (int i = 0; i <= DataModel.getAllCountriesFiltered().size() - 1; i++) {
            countryNames[i] = DataModel.getAllCountriesFiltered().get(i).getCountryName();
        }

        CategoryAxis xAchse = new CategoryAxis();
        NumberAxis yAchse = new NumberAxis();
        chartAbsolute = new BarChart<>(yAchse, xAchse);
        yAchse.setLabel("Land");
        xAchse.setCategories(FXCollections.<String>observableArrayList(Arrays
                .asList(countryNames)));
        yAchse.setLabel("Anzahl Drafts");

        // add starting data
        XYChart.Series<Number, String> series1 = new XYChart.Series<>();
        series1.setName("Data Series 1");

        chartAbsolute.setLegendVisible(false);

        //fill in all the data: countries and number of players
        for (int i = 0; i <= countryNames.length - 1; i++) {
            int counter = 0;
            for (Player p : DataModel.getAllPlayersFiltered()) {
                if (p.getPlayerCountry().getCountryName().equals(DataModel.getAllCountriesFiltered().get(i).getCountryName())) {
                    counter++;
                }
            }
            XYChart.Data d = new XYChart.Data<Number, String>(counter, DataModel.getAllCountriesFiltered().get(i).getCountryName());
            series1.getData().add(d);
        }
        chartAbsolute.getData().add(series1);

        // tooltip
        for (int i = 0; i <= countryNames.length-1; i++) {
            XYChart.Data item = (XYChart.Data) series1.getData().get(i);
            String value = series1.getData().get(i).getXValue().toString();
            Tooltip.install(item.getNode(), new Tooltip("Anzahl Drafts: " + value));

            //Changing the color of the bar depending on its value
            if (series1.getData().get(i).getXValue().intValue() < 10) {
                item.getNode().setStyle("-fx-bar-fill: #801638;");
            } else if (series1.getData().get(i).getXValue().intValue() < 50) {
                item.getNode().setStyle("-fx-bar-fill: #027878;");
            } else if (series1.getData().get(i).getXValue().intValue() < 200) {
                item.getNode().setStyle("-fx-bar-fill: #FDB632;");
            } else if (series1.getData().get(i).getXValue().intValue() > 200) {
                item.getNode().setStyle("-fx-bar-fill: #F37338;");
            }
        }
        return chartAbsolute;
    }


    public HBox createHeader() {
        HBox header = new HBox();
        Label title = new Label("NHL Drafts nach Nationalitäten");
        title.setMinHeight(25);

        HBox controleBox = new HBox();
        controleBox.getChildren().addAll();

        header.getChildren().addAll(title);
        header.getStyleClass().add("header");
        header.setAlignment(Pos.TOP_CENTER);
        return header;
    }

    public VBox setBoundariesArea() {
        VBox area = new VBox();
        area.getStyleClass().add("gray-background");

        Label boundsTitle = new Label();
        boundsTitle.getStyleClass().add("area-title");
        boundsTitle.textProperty().bind(
                Bindings.concat("Angezeigte Jahre: ", DataModel.lowerBoundFilterProperty(), " - ", DataModel.upperBoundFilterProperty())
        );

        Label lowerBoundLabel = new Label("Untere Jahresgrenze:");
        Slider lowerBoundSlider = new Slider();
        Label upperBoundLabel = new Label("Obere Jahresgrenze:");
        Slider upperBoundSlider = new Slider();

        lowerBoundSlider.setMin(1898);
        lowerBoundSlider.maxProperty().bind(DataModel.upperBoundFilterProperty());
        lowerBoundSlider.setShowTickLabels(true);
        lowerBoundSlider.setShowTickMarks(true);
        lowerBoundSlider.valueProperty().bindBidirectional(DataModel.lowerBoundFilterProperty());

        upperBoundSlider.minProperty().bind(DataModel.lowerBoundFilterProperty());
        upperBoundSlider.setMax(2018);
        upperBoundSlider.setShowTickLabels(true);
        upperBoundSlider.setShowTickMarks(true);
        upperBoundSlider.valueProperty().bindBidirectional(DataModel.upperBoundFilterProperty());

        area.getChildren().addAll(boundsTitle, lowerBoundLabel, lowerBoundSlider, upperBoundLabel, upperBoundSlider);

        return area;
    }

    public HBox toggleArea() {
        HBox toggleArea = new HBox();
        toggleArea.getStyleClass().add("gray-background");

        Label lblAbsolute = new Label("Anzahl Drafts absolut");
        Label lblRelative = new Label("Drafts pro Million Einwohner");

        toggleSwitch = new ToggleSwitch(true);

        toggleArea.getChildren().addAll(lblAbsolute, toggleSwitch, lblRelative);

        return toggleArea;
    }


    public VBox filterContinentArea() {
        VBox filterContinentArea = new VBox();
        filterContinentArea.getStyleClass().add("gray-background");

        Label filterTitle = new Label("Angezeigte Kontinente:");
        filterTitle.getStyleClass().add("area-title");

        HBox boxCheckBoxes = new HBox();
        boxCheckBoxes.getStyleClass().add("check-boxes");

        checkBoxEurope = new CheckBox("Europa");
        checkBoxEurope.setSelected(true);
        checkBoxNorthAmerica = new CheckBox("Nordamerika");
        checkBoxNorthAmerica.setSelected(true);
        checkBoxOthers = new CheckBox("Andere");
        checkBoxOthers.setSelected(true);

        boxCheckBoxes.getChildren().addAll(checkBoxEurope, checkBoxNorthAmerica, checkBoxOthers);
        filterContinentArea.getChildren().addAll(filterTitle, boxCheckBoxes);

        return filterContinentArea;
    }


    public VBox createSideBar() {
        VBox sideBar = new VBox(15);
        sideBar.getStyleClass().add("sideBar");
        sideBar.getChildren().addAll(setBoundariesArea(), toggleArea(), filterContinentArea());
        return sideBar;
    }


    public void start(Stage primaryStage) throws Exception {
        //------------------Data----------------------------
        DataModel.setUpData();

        //------------------View-----------------------------
        frame = new BorderPane();
        frame.setCenter(createAbsoluteChart());
        frame.setTop(createHeader());
        frame.setRight(createSideBar());
        setupChangeListener();

        //get Sceen-size
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //set size of window
        Scene scene = new Scene(frame, primaryScreenBounds.getWidth() / 3 * 2.2, primaryScreenBounds.getHeight() - 40);

        //link stylesheet
        scene.getStylesheets().add("stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void updateChart() {
        if (isAbsolute) {
            frame.setCenter(createAbsoluteChart());
        } else {
            frame.setCenter(createRelativeChart());
        }
    }

    public void updateCountriesFilter() {
        if (checkBoxEurope.isSelected() && checkBoxNorthAmerica.isSelected() && checkBoxOthers.isSelected()) {
            DataModel.getAllCountriesFiltered().setPredicate(country -> {
                return
                        (country.getContinentName().equals("Europe") ||
                                country.getContinentName().equals("North America") ||
                                country.getContinentName().equals("South America") ||
                                country.getContinentName().equals("Australia") ||
                                country.getContinentName().equals("Asia") ||
                                country.getContinentName().equals("Africa"));

            });
        } else if (checkBoxEurope.isSelected() && checkBoxNorthAmerica.isSelected() && !checkBoxOthers.isSelected()) {
            DataModel.getAllCountriesFiltered().setPredicate(country -> {
                return
                        (country.getContinentName().equals("Europe") ||
                                country.getContinentName().equals("North America"));

            });
        } else if (checkBoxEurope.isSelected() && !checkBoxNorthAmerica.isSelected() && checkBoxOthers.isSelected()) {
            DataModel.getAllCountriesFiltered().setPredicate(country -> {
                return
                        (country.getContinentName().equals("Europe") ||
                                country.getContinentName().equals("South America") ||
                                country.getContinentName().equals("Australia") ||
                                country.getContinentName().equals("Asia") ||
                                country.getContinentName().equals("Africa"));

            });
        } else if (checkBoxEurope.isSelected() && !checkBoxNorthAmerica.isSelected() && !checkBoxOthers.isSelected()) {
            DataModel.getAllCountriesFiltered().setPredicate(country -> {
                return
                        (country.getContinentName().equals("Europe"));

            });
        } else if (!checkBoxEurope.isSelected() && checkBoxNorthAmerica.isSelected() && checkBoxOthers.isSelected()) {
            DataModel.getAllCountriesFiltered().setPredicate(country -> {
                return
                        (country.getContinentName().equals("North America") ||
                                country.getContinentName().equals("South America") ||
                                country.getContinentName().equals("Australia") ||
                                country.getContinentName().equals("Asia") ||
                                country.getContinentName().equals("Africa"));

            });
        } else if (!checkBoxEurope.isSelected() && checkBoxNorthAmerica.isSelected() && !checkBoxOthers.isSelected()) {
            DataModel.getAllCountriesFiltered().setPredicate(country -> {
                return
                        (country.getContinentName().equals("North America"));

            });
        } else if (!checkBoxEurope.isSelected() && !checkBoxNorthAmerica.isSelected() && checkBoxOthers.isSelected()) {
            DataModel.getAllCountriesFiltered().setPredicate(country -> {
                return
                        (country.getContinentName().equals("South America") ||
                                country.getContinentName().equals("Australia") ||
                                country.getContinentName().equals("Asia") ||
                                country.getContinentName().equals("Africa"));

            });
        } else if (!checkBoxEurope.isSelected() && !checkBoxNorthAmerica.isSelected() && !checkBoxOthers.isSelected()) {
            //TODO: do sth when nothing is selected
        }
    }


    public void setupChangeListener() {
        DataModel.getAllPlayersFiltered().addListener((ListChangeListener) (c -> {
            updateChart();
        }));

        toggleSwitch.leftProperty().addListener((observable, oldValue, newValue) -> {
            isAbsolute = newValue;
            updateChart();
        });

        checkBoxEurope.selectedProperty().addListener(event -> updateCountriesFilter());
        checkBoxNorthAmerica.selectedProperty().addListener(event -> updateCountriesFilter());
        checkBoxOthers.selectedProperty().addListener(event -> updateCountriesFilter());

        DataModel.getAllCountriesFiltered().addListener((ListChangeListener) (c -> {
            updateChart();
        }));

    }
}

