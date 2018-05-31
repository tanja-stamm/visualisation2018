package Service;

import Model.Country;
import Model.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CountryService {

    public static void importCountries() {
        try {
            File countriesFile = new File("src/countries.txt");
            //use a scanner that will read from the file
            Scanner countriesScanner = new Scanner(countriesFile);

            //skip first line
            countriesScanner.nextLine();

            String line;
            while (countriesScanner.hasNext()){
                line = countriesScanner.nextLine();
                String[] countryComponents = line.split(";");

                //create Country-Object
                Country newCountry = new Country(countryComponents[0],Integer.parseInt(countryComponents[1]));
                Data.addToAllCountries(newCountry);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
