import Model.DataModel;
import Service.CountryService;
import javafx.application.Application;


import java.util.Arrays;

import javafx.collections.FXCollections;
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

    //


    //DataModel visualisation
    private BarChart<Number, String> chart;
    private CategoryAxis xAchse;
    private NumberAxis yAchse;


    public Parent createChart() {
        final String[] countryNames = new String[DataModel.getAllCountries().length];

        for(int i = 0; i <= DataModel.getAllCountries().length-1; i++){
            countryNames[i] = DataModel.getAllCountries()[i].getCountryName();
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
        series1.setName("DataModel Series 1");


        for(int i = 0; i<=countryNames.length-1; i++){
        XYChart.Data d = new XYChart.Data<Number, String>(10, DataModel.getAllCountries()[i].getCountryName());
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
        //----------------------Data-------------------------
        CountryService.importCountries();
        CountryService.importPlayers();
        DataModel.printAllPlayers();

        //----------------------View-------------------------
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
