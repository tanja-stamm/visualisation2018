package Model;

public class Player {

    private String playerName;
    private int draftYear;
    private Country playerCountry;

    Country Switzerland = new Country("Switzerland", 8000000);
    Country USA = new Country("United States", 325000000);

    Player p1 = new Player("Tanja", 1990, Switzerland);
    Player p2 = new Player("Jelena", 1992, USA);




    public Player(String playerName, int draftYear, Country playerCountry) {
        this.playerName = playerName;
        this.draftYear = draftYear;
        this.playerCountry = playerCountry;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getDraftYear() {
        return draftYear;
    }

    public void setDraftYear(int draftYear) {
        this.draftYear = draftYear;
    }

    public Country getPlayerCountry() {
        return playerCountry;
    }

    public void setPlayerCountry(Country playerCountry) {
        this.playerCountry = playerCountry;
    }

}

