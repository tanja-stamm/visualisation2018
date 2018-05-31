import Model.Data;
import Service.CountryService;

public class Main {

    public static void main(String[] args) {
	// write your code here
        CountryService.importCountries();
        Data.printAllCoutnries();
    }
}
