import javafx.application.Application;

import java.awt.*;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

public class Main extends Application {


    private BarChart<Number, String> chart;
    private CategoryAxis xAchse;
    private NumberAxis yAchse;


    public Parent createChart() {
        final String[] countries = {"Switzerland", "USA", "Canada", "Sweden"};
        xAchse = new CategoryAxis();
        yAchse = new NumberAxis();
        chart = new BarChart<>(yAchse, xAchse);
        chart.setTitle("Verteilung der NHL-Drafts nach Länder");
        yAchse.setLabel("Land");
        xAchse.setCategories(FXCollections.<String>observableArrayList(Arrays
                .asList(countries)));
        yAchse.setLabel("Anzahl Drafts");

        // add starting data
        XYChart.Series<Number, String> series1 = new XYChart.Series<>();
        series1.setName("Data Series 1");
        series1.getData().addAll(
                new XYChart.Data<Number, String>(1, countries[0]),
                new XYChart.Data<Number, String>(2, countries[1]),
                new XYChart.Data<Number, String>(4, countries[2]),
                new XYChart.Data<Number, String>(6, countries[3]));

        chart.getData().add(series1);
        return chart;
    }

    public HBox createHeader() {
        HBox header = new HBox();
        Button b1 = new Button("Hallo");
        header.getChildren().addAll(b1);
        header.setStyle("-fx-background-color: yellow; ");
        return header;
    }

    public VBox createSideBar(){
        CheckBox cb1 = new CheckBox("Europa");
        CheckBox cb2 = new CheckBox("Nordamerika");
        CheckBox cb3 = new CheckBox("Andere");

        cb1.selectedProperty().addListener(e -> System.out.println("Hoi"));

        VBox checkboxen = new VBox(5);
        checkboxen.getChildren().addAll(cb1, cb2, cb3);
        return  checkboxen;
    }


    public void start (Stage primaryStage) throws Exception {
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
