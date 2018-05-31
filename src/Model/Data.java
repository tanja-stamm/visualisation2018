package Model;

public class Data {

    private static Player[] allPlayers = new Player[1066];
    private static Country[] allCountries = new Country[22];

    public Player[] getAllPlayers() {
        return allPlayers;
    }




    public static void addToAllCountries(Country country) {
        for (int i = 0; i < allCountries.length; i++) {
            if(allCountries[i] == null) {
                allCountries[i] = country;
                break;
            }
        }
    }

    public static void printAllCountries(){
        for (int i = 0; i < allCountries.length; i++) {
            System.out.println(i + ": "+ allCountries[i].getCountryName() + " ; " + allCountries[i].getPopulation());
        }
    }



    //GETTERS AND SETTERS
    public void setAllPlayers(Player[] allPlayers) {
        this.allPlayers = allPlayers;
    }

    public Country[] getAllCountries() {
        return allCountries;
    }

    public void setAllCountries(Country[] allCountries) {
        this.allCountries = allCountries;
    }
}
