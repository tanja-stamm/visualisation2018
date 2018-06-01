package Model;

import Service.ServiceAsList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    //-------------------------------------------Players---------------------------------------------------------------
    private static List<Player> allPlayersList = new ArrayList<>();
    private static final ObservableList<Player> allPlayers = FXCollections.observableList(allPlayersList);

    private static final FilteredList<Player> allPlayersFiltered = new FilteredList<>(allPlayers);

    //Filter
    private static final IntegerProperty lowerBoundFilter = new SimpleIntegerProperty(0);
    private static final IntegerProperty upperBoundFilter = new SimpleIntegerProperty(9000);



    //-------------------------------------------Countries--------------------------------------------------------------
    private static List<Country> allCountriesList = new ArrayList<>();
    private static final ObservableList<Country> allCountries = FXCollections.observableList(allCountriesList);




    //------------------------------------------Custom Methods----------------------------------------------------------



    //------------------------------------------Getter & Setter---------------------------------------------------------


    public static void setupValueChangedListener() {


        lowerBoundFilter.addListener(((observable, oldValue, newValue) -> {
            System.out.println("new lower bound: "+ newValue);
            allPlayersFiltered.setPredicate(player -> {
                if(player.getDraftYear() < (Integer) newValue || player.getDraftYear() > getUpperBoundFilter()) {
                    return false;
                } else {
                    return true;
                }
            });
            printAllPlayersFiltered();
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

    public static void setUpData() {
        setupValueChangedListener();
        ServiceAsList.importCountries();
        ServiceAsList.importPlayers();

        setLowerBoundFilter(1886);
        setUpperBoundFilter(2016);

        System.out.println("allPlayers list: ");
        for (Player p : allPlayers) {
            System.out.println(p.getPlayerName() + " " + p.getDraftYear());
        }

        printAllPlayersFiltered();

        System.out.println("------------------------------------");
        System.out.println("all Countries:");
        for (Country c : allCountries) {
            System.out.println(c.getCountryName());
        }
    }

    public static void printAllPlayersFiltered(){
        System.out.println("------------------------------------");
        System.out.println("allPlayersFiltered list: ");
        for (Player p : allPlayersFiltered) {
            System.out.println(p.getPlayerName() + " " + p.getDraftYear() + " " + p.getPlayerCountry().getCountryName());
        }

    }


    //-----------------------------------Getter & Setter----------------------------------------------------------------


    public static ObservableList<Player> getAllPlayers() {
        return allPlayers;
    }

    public static FilteredList<Player> getAllPlayersFiltered() {
        return allPlayersFiltered;
    }

    public static int getLowerBoundFilter() {
        return lowerBoundFilter.get();
    }

    public static IntegerProperty lowerBoundFilterProperty() {
        return lowerBoundFilter;
    }

    public static void setLowerBoundFilter(int lowerBoundFilter) {
        DataModel.lowerBoundFilter.set(lowerBoundFilter);
    }

    public static int getUpperBoundFilter() {
        return upperBoundFilter.get();
    }

    public static IntegerProperty upperBoundFilterProperty() {
        return upperBoundFilter;
    }

    public static void setUpperBoundFilter(int upperBoundFilter) {
        DataModel.upperBoundFilter.set(upperBoundFilter);
    }

    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }
}
