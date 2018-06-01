package Model;

public class DataModel {

    private static Player[] allPlayers = new Player[1063];
    private static Country[] allCountries = new Country[22];



    public static void addToAllPlayers(Player player) {
        for (int i = 0; i < allPlayers.length; i++) {
            if(allPlayers[i] == null) {
                allPlayers[i] = player;
                break;
            }
        }
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

    public static void printAllPlayers(){
        for (int i = 0; i < getAllPlayers().length; i++) {
            if(allPlayers[i] != null) {
                System.out.println(i + ": " +
                        allPlayers[i].getPlayerName() + "; " +
                        allPlayers[i].getDraftYear() + "; " +
                        allPlayers[i].getPlayerCountry().getCountryName()
                );
            }
        }
    }



    //GETTERS AND SETTERS
    public static Player[] getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(Player[] allPlayers) {
        this.allPlayers = allPlayers;
    }

    public static Country[] getAllCountries() {
        return allCountries;
    }

    public void setAllCountries(Country[] allCountries) {
        this.allCountries = allCountries;
    }
}
