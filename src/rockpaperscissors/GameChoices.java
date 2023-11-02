package rockpaperscissors;

enum GameChoices {
    ROCK("paper"),
    PAPER("scissors"),
    SCISSORS("rock");

    private final String losesAgainst;

    GameChoices(String losesAgainst) {
        this.losesAgainst = losesAgainst;
    }

    public String getLosesAgainst() {
        return losesAgainst;
    }
}
