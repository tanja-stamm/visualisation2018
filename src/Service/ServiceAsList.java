package Service;

import Model.Country;
import Model.DataModel;
import Model.Player;
import TestingArea.TestEnvironment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ServiceAsList {

    public static void importCountries() {
        try {
            File countriesFile = new File("src/countries.txt");
            //use a scanner that will read from the file
            Scanner countriesScanner = new Scanner(countriesFile);

            //skip first line
            countriesScanner.nextLine();

            String line;
            while (countriesScanner.hasNext()) {
                line = countriesScanner.nextLine();
                String[] countryComponents = line.split(";");

                //create Country-Object
                Country newCountry = new Country(countryComponents[0], Integer.parseInt(countryComponents[1]));
                TestEnvironment.getAllCountries().add(newCountry);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void importPlayers() {
        try {
            File playersFile = new File("src/playersShort.txt");
            //use a scanner that will read from the file
            Scanner playersScanner = new Scanner(playersFile);

            //skip first line
            playersScanner.nextLine();

            String line;
            int j  = 0;
            while (playersScanner.hasNext()) {
                line = playersScanner.nextLine();
                String[] playerComponents = line.split(";");
                //create Country-Object
                Player newPlayer = new Player(
                        playerComponents[0],
                        Integer.parseInt(playerComponents[2]),
                        searchForCountry(playerComponents[1]));
                //add here player to list
                TestEnvironment.getAllPlayers().add(newPlayer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Country searchForCountry(String countryString) {
        Country[] countries = DataModel.getAllCountries();
        for(int i = 0; i < countries.length; i++){
            if(countryString.toLowerCase().equals(countries[i].getCountryName().toLowerCase())){
                return countries[i];
            }
        }
        return null;
    }


}
