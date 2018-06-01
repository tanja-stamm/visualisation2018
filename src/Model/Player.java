package Model;

public class Player {

    private String playerName;
    private int draftYear;
    private Country playerCountry;

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

