import Model.DataModel;
import Model.Player;
import javafx.application.Application;


import java.util.Arrays;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;

public class Main extends Application {

    //Data visualisation
    private BarChart<Number, String> chartAbsolute;
    private BarChart<Number, String> chartRelativ;

    BorderPane frame;
    Boolean isAbsolute  ;

    private boolean chartModeBereinigt = false;

    public Parent createRelativeChart(){
        final String[] countryNames = new String[DataModel.getAllCountries().size()];

        for (int i = 0; i <= DataModel.getAllCountries().size() - 1; i++) {
            countryNames[i] = DataModel.getAllCountries().get(i).getCountryName();
        }

        CategoryAxis xAchse = new CategoryAxis();
        NumberAxis yAchse = new NumberAxis();
        chartRelativ = new BarChart<>(yAchse, xAchse);
        chartRelativ.setTitle("Verteilung der NHL-Drafts nach Länder");
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
                if (p.getPlayerCountry().getCountryName().equals(DataModel.getAllCountries().get(i).getCountryName())) {
                    counter++;
                }
            }
            XYChart.Data d = new XYChart.Data<Number, String>(counter/(DataModel.getAllCountries().get(i).getPopulation()/1000000), DataModel.getAllCountries().get(i).getCountryName());
            System.out.println("Pop / 100 " + DataModel.getAllCountries().get(i).getPopulation()/1000000);
            System.out.println("counter / pop / 100 " + counter/(DataModel.getAllCountries().get(i).getPopulation()/1000000));
            series0.getData().add(d);

        }

        chartRelativ.getData().add(series0);

        // tooltip

        for (int i = 0; i <= 21; i++) {
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
        final String[] countryNames = new String[DataModel.getAllCountries().size()];

        for (int i = 0; i <= DataModel.getAllCountries().size() - 1; i++) {
            countryNames[i] = DataModel.getAllCountries().get(i).getCountryName();
        }

        CategoryAxis xAchse = new CategoryAxis();
        NumberAxis yAchse = new NumberAxis();
        chartAbsolute = new BarChart<>(yAchse, xAchse);
        chartAbsolute.setTitle("Verteilung der NHL-Drafts nach Länder");
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
                if (p.getPlayerCountry().getCountryName().equals(DataModel.getAllCountries().get(i).getCountryName())) {
                    counter++;
                }
            }
            XYChart.Data d = new XYChart.Data<Number, String>(counter, DataModel.getAllCountries().get(i).getCountryName());
            series1.getData().add(d);
        }

        chartAbsolute.getData().add(series1);

        // tooltip

        for (int i = 0; i <= 21; i++) {
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
        Button b1 = new Button("Hallo");
        Label title = new Label("NHL Drafts nach Nationalitäten 1899 - 2016");
        title.setMinHeight(25);
        header.getChildren().addAll(title);
        header.setStyle("-fx-background-color: yellow; ");
        header.setAlignment(Pos.TOP_CENTER);
        return header;
    }


    public VBox createSideBar() {
        final double TOGGLEBUTTON_WIDTH = 250;
        final double TOGGLEBUTTON_HEIGHT = 40;


        Button b1 = new Button("set lowerbound to 1920");
        b1.setOnAction(event -> DataModel.setLowerBoundFilter(1920));
        Button b2 = new Button("set lowerbound to 1980");
        b2.setOnAction(event -> DataModel.setLowerBoundFilter(1980));

        // create label to show result of selected toggle button
        final Label label = new Label();
        label.setStyle("-fx-font-size: 2em;");
        label.setAlignment(Pos.CENTER);
        // create 2 toggle buttons and a toogle group for them
        final ToggleButton tb1 = new ToggleButton("Anzahl Drafts ingesamt");
        tb1.setMinSize(TOGGLEBUTTON_WIDTH, TOGGLEBUTTON_HEIGHT);
        final ToggleButton tb2 = new ToggleButton("Anzahl Drafts bereinigt nach Einwohner");
        tb2.setMinSize(TOGGLEBUTTON_WIDTH, TOGGLEBUTTON_HEIGHT);
        ToggleGroup group = new ToggleGroup();
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle selectedToggle) -> {
            if (selectedToggle != null && selectedToggle == tb1) {
                isAbsolute = true;
                frame.setCenter(null);
                frame.setCenter(createAbsoluteChart());


            }
            if (selectedToggle != null && selectedToggle == tb2) {
                isAbsolute = false;
                frame.setCenter(null);
                frame.setCenter(createRelativeChart());
            }
        });


        CheckBox cb1 = new CheckBox("Europa");
        CheckBox cb2 = new CheckBox("Nordamerika");
        CheckBox cb3 = new CheckBox("Andere");

        cb1.selectedProperty().addListener(e -> System.out.println("Hoi"));

        VBox checkboxen = new VBox(15);
        checkboxen.getChildren().addAll(tb1, tb2, cb1, cb2, cb3,b1,b2);
        return checkboxen;

    }


    public void start(Stage primaryStage) throws Exception {
        //------------------Data----------------------------
        DataModel.setUpData();

        //------------------View-----------------------------
        frame = new BorderPane();
        setupChangeListener();

        frame.setCenter(createAbsoluteChart());
        frame.setTop(createHeader());
        frame.setRight(createSideBar());
        Scene scene = new Scene(frame);
        //link stylesheet
        scene.getStylesheets().add("stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void setupChangeListener(){
        DataModel.getAllPlayersFiltered().addListener((ListChangeListener)(c -> {
            System.out.println("List has changed");
            frame.setCenter(null);

            if(isAbsolute){
            frame.setCenter(createAbsoluteChart());
            }
            else{
                frame.setCenter(createRelativeChart());
            }
        }));
    }
}

