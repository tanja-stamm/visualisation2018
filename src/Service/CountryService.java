package Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CountryService {

    public static void importCountries() {
        try {
            File countriesFile = new File("src/countries.txt");
            //use a scanner that will read from the file
            Scanner countriesScanner = new Scanner(countriesFile);

            //read a line from the file
            String nextLine = countriesScanner.nextLine();

            System.out.println("Next Line: " + nextLine);

            //split the file into parts
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
