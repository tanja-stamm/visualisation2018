package Model;

public class Country {

    private String countryName;
    private int population;
    private String continentName;

    public Country(String countryName, int population, String continentName) {
        this.countryName = countryName;
        this.population = population;
        this.continentName = continentName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }
}
