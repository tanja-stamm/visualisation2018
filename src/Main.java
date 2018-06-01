import Model.DataModel;
import Model.Player;
import javafx.application.Application;


import java.util.Arrays;


import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private BarChart<Number, String> chart;
    private CategoryAxis xAchse;
    private NumberAxis yAchse;




    //Versuch, Farbe des Balkens nach Wert anzupassen => funktioniert aber nicht!!
    private void setNodeStyle(XYChart.Data<Number, String> data) {
        Node node = data.getNode();
        if (data.getXValue().intValue() > 8) {
            node.setStyle("-fx-bar-fill: -fx-exceeded;");
        } else if (data.getXValue().intValue() > 5) {
            node.setStyle("-fx-bar-fill: -fx-achieved;");
        } else {
            node.setStyle("-fx-bar-fill: -fx-not-achieved;");
        }
    }


    public Parent createChart() {
        final String[] countryNames = new String[DataModel.getAllCountries().size()];

        for(int i = 0; i <= DataModel.getAllCountries().size()-1; i++){
            countryNames[i] = DataModel.getAllCountries().get(i).getCountryName();
        }

        xAchse = new CategoryAxis();
        yAchse = new NumberAxis();
        chart = new BarChart<>(yAchse, xAchse);
        chart.setTitle("Verteilung der NHL-Drafts nach Länder");
        yAchse.setLabel("Land");
        xAchse.setCategories(FXCollections.<String>observableArrayList(Arrays
                .asList(countryNames)));
        yAchse.setLabel("Anzahl Drafts");

        // add starting data
        XYChart.Series<Number, String> series1 = new XYChart.Series<>();
        series1.setName("Data Series 1");

        //Jelena: Versuch, den Node Style je nach Wert anzupassen, siehe Methode "setNodeStyle" Auskommentiert, da es sonst nicht kompiliert
        //setNodeStyle(series1.getData());



        for(int i = 0; i<=countryNames.length-1; i++){
            int counter = 0;
            for(Player p: DataModel.getAllPlayersFiltered()){
                if(p.getPlayerCountry().getCountryName().equals(DataModel.getAllCountries().get(i).getCountryName())){
                    counter++;
                }
            }
            XYChart.Data d = new XYChart.Data<Number, String>(counter, DataModel.getAllCountries().get(i).getCountryName());
            series1.getData().add(d);
            XYChart.Data item = (XYChart.Data)series1.getData().get(i);
            Tooltip.install(item.getNode(), new Tooltip("fasd"));
        }

        chart.getData().add(series1);

        // tooltip

            for(int i = 0; i<=21; i++) {
            XYChart.Data item = (XYChart.Data) series1.getData().get(i);
            String value = series1.getData().get(i).getXValue().toString();
            Tooltip.install(item.getNode(), new Tooltip("Anzahl Drafts: " + value));
        }
        return chart;
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




    public VBox createSideBar(){
        final double TOGGLEBUTTON_WIDTH = 250;
        final double TOGGLEBUTTON_HEIGHT = 40;

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
                    System.out.println("Unbereinigte Spielerliste");

                } if(selectedToggle != null && selectedToggle == tb2){
                    System.out.println("Bereinigte Spielerliste");
                }
            });



        CheckBox cb1 = new CheckBox("Europa");
        CheckBox cb2 = new CheckBox("Nordamerika");
        CheckBox cb3 = new CheckBox("Andere");

        cb1.selectedProperty().addListener(e -> System.out.println("Hoi"));

        VBox checkboxen = new VBox(15);
        checkboxen.getChildren().addAll(tb1, tb2, cb1, cb2, cb3);
        return  checkboxen;

    }



    public void start (Stage primaryStage) throws Exception {
        //------------------Data----------------------------
        DataModel.setUpData();

        //------------------View-----------------------------
        BorderPane frame = new BorderPane();
        frame.setCenter(createChart());
        frame.setTop(createHeader());
        frame.setRight(createSideBar());
        Scene scene = new Scene(frame);
        //link stylesheet
        scene.getStylesheets().add("stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main (String[]args){
        launch(args);
    }
}

