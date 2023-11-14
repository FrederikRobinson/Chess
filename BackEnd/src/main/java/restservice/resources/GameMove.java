package restservice.resources;

public class GameMove {
    private int startXPos;
    private int startYPos;
    private int endXPos;
    private int endYPos;
    private char playerColour;
    private int gameId;

    public int getStartXPos() {
        return startXPos;
    }

    public void setStartXPos(char startXPos) {
        this.startXPos = startXPos-'a';
    }

    public int getStartYPos() {
        return startYPos;
    }

    public void setStartYPos(int startYPos) {
        this.startYPos = startYPos-1;
    }

    public int getEndXPos() {
        return endXPos;
    }

    public void setEndXPos(char endXPos) {
        this.endXPos = endXPos-'a';
    }

    public int getEndYPos() {
        return endYPos;
    }

    public void setEndYPos(int endYPos) {
        this.endYPos = endYPos-1;
    }

    public char getPlayerColour() {
        return playerColour;
    }

    public void setPlayerColour(char playerColour) {
        this.playerColour = playerColour;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
