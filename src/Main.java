import Model.Data;
import Service.CountryService;
import javafx.application.Application;


import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    //


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
        final String[] countryNames = new String[Data.getAllCountries().length];

        for(int i = 0; i <= Data.getAllCountries().length-1; i++){
            countryNames[i] = Data.getAllCountries()[i].getCountryName();
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
            XYChart.Data d = new XYChart.Data<Number, String>(3, Data.getAllCountries()[i].getCountryName());
            series1.getData().add(d);
        }


        chart.getData().add(series1);




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
        CheckBox cb1 = new CheckBox("Europa");
        CheckBox cb2 = new CheckBox("Nordamerika");
        CheckBox cb3 = new CheckBox("Andere");

        cb1.selectedProperty().addListener(e -> System.out.println("Hoi"));

        VBox checkboxen = new VBox(15);
        checkboxen.getChildren().addAll(cb1, cb2, cb3);
        return  checkboxen;
    }


    public void start (Stage primaryStage) throws Exception {
        CountryService.importCountries();
        BorderPane frame = new BorderPane();
        frame.setCenter(createChart());
        frame.setTop(createHeader());
        frame.setRight(createSideBar());
        primaryStage.setScene(new Scene(frame));
        primaryStage.show();
        System.out.println("Start is running!");
    }


    public static void main (String[]args){
        System.out.println("Main is running!");
        launch(args);


    }
}

