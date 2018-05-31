package Model;

public class Country {

    private String countryName;
    private int population;

    public Country(String countryName, int population) {
        this.countryName = countryName;
        this.population = population;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
