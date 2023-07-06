public class Player {

	private String playerName;
	private int score;
	private int numberOfWins;
	
	// Default Constructor
	Player() {
	}
	
	// Construct a Player Object with a name and an age
	Player(String name) {
		this.playerName = name;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore (int score) {
		this.score = score;
	}
	
	public int getNumberOfWins() {
		return numberOfWins;
	}
	
	public void setNumberOfWins(int numberOfWins) {
		this.numberOfWins = numberOfWins;
	}
	
	public void increaseNumberOfWins() {
		this.numberOfWins += 1;
	}	
	
	public void incrementScore(int points) {
	    score += points;
	}
}