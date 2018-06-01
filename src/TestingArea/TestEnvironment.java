package TestingArea;

import Model.Country;
import Model.Player;
import Service.ServiceAsArray;
import Service.ServiceAsList;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TestEnvironment extends Application {
    //-------------------------------------------Players---------------------------------------------------------------
    private static List<Player> allPlayersList = new ArrayList<>();
    public static final ObservableList<Player> allPlayers = FXCollections.observableList(allPlayersList);

    private final FilteredList<Player> allPlayersFiltered = new FilteredList<>(allPlayers);

    //Filter
    private final IntegerProperty lowerBoundFilter = new SimpleIntegerProperty(0);
    private final IntegerProperty upperBoundFilter = new SimpleIntegerProperty(9000);



    //-------------------------------------------Countries--------------------------------------------------------------
    private static List<Country> allCountriesList = new ArrayList<>();
    public static final ObservableList<Country> allCountries = FXCollections.observableList(allCountriesList);




    //------------------------------------------Custom Methods----------------------------------------------------------



    //------------------------------------------Getter & Setter---------------------------------------------------------




    public static void main(String[] args) {
        launch(args);
    }

    private void setupValueChangedListener() {
        lowerBoundFilter.addListener(((observable, oldValue, newValue) -> {
            allPlayersFiltered.setPredicate(player -> {
                if(player.getDraftYear() < (Integer) newValue || player.getDraftYear() > getUpperBoundFilter()) {
                    return false;
                } else {
                    return true;
                }
            });
        }));

        upperBoundFilter.addListener(((observable, oldValue, newValue) -> {
            allPlayersFiltered.setPredicate(player -> {
                if(player.getDraftYear() < getLowerBoundFilter() || player.getDraftYear() > (Integer) newValue) {
                    return false;
                } else {
                    return true;
                }
            });
        }));
    }

    public void setUpData() {
        setupValueChangedListener();
        ServiceAsList.importCountries();
        ServiceAsList.importPlayers();

        setLowerBoundFilter(1986);
        setUpperBoundFilter(1990);

        System.out.println("allPlayers list: ");
        for (Player p : allPlayers) {
            System.out.println(p.getPlayerName() + " " + p.getDraftYear());
        }
        System.out.println("------------------------------------");
        System.out.println("allPlayersFiltered list: ");
        for (Player p : allPlayersFiltered) {
            System.out.println(p.getPlayerName() + " " + p.getDraftYear());
        }

        System.out.println("------------------------------------");
        System.out.println("all Countries:");
        for (Country c : allCountries) {
            System.out.println(c.getCountryName());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        setUpData();


        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis, yAxis);
        barChart.setData(getChartData());
        barChart.setTitle("A");
        primaryStage.setTitle("BarChart example");

        StackPane root = new StackPane();
        root.getChildren().add(barChart);
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    private ObservableList<XYChart.Series<String, Double>> getChartData() {
        double aValue = 17.56;
        double cValue = 17.06;
        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
        XYChart.Series<String, Double> aSeries = new XYChart.Series<String, Double>();
        XYChart.Series<String, Double> cSeries = new XYChart.Series<String, Double>();
        aSeries.setName("a");
        cSeries.setName("C");

        for (int i = 2011; i < 2021; i++) {
            aSeries.getData().add(new XYChart.Data(Integer.toString(i), aValue));
            aValue = aValue + Math.random() - .5;
            cSeries.getData().add(new XYChart.Data(Integer.toString(i), cValue));
            cValue = cValue + Math.random() - .5;
        }
        answer.addAll(aSeries, cSeries);
        return answer;
    }


    //-----------------------------------Getter & Setter----------------------------------------------------------------
    public static ObservableList<Player> getAllPlayers() {
        return allPlayers;
    }

    public int getLowerBoundFilter() {
        return lowerBoundFilter.get();
    }

    public IntegerProperty lowerBoundFilterProperty() {
        return lowerBoundFilter;
    }

    public void setLowerBoundFilter(int lowerBoundFilter) {
        this.lowerBoundFilter.set(lowerBoundFilter);
    }

    public int getUpperBoundFilter() {
        return upperBoundFilter.get();
    }

    public IntegerProperty upperBoundFilterProperty() {
        return upperBoundFilter;
    }

    public void setUpperBoundFilter(int upperBoundFilter) {
        this.upperBoundFilter.set(upperBoundFilter);
    }

    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }


}
