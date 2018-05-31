import Service.CountryService;
import javafx.application.Application;
import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {


    private BarChart<Number, String> chart;
    private CategoryAxis xAchse;
    private NumberAxis yAchse;


    public Parent createContent() {
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

    public void start (Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        System.out.println("Start is running!");
    }


    public static void main (String[]args){
        System.out.println("Main is running!");
        launch(args);


    }
}
