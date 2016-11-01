package connect4;

public class Player {
	private int winCount;
	private String name;
	
	public Player(String name) {
		this.name = name;
		setWinCount(0);
	}

	void addWin() {
		winCount++;
	}

	void reset() {
		setWinCount(0);
		name = "";
	}

	public int getWinCount() {
		return winCount;
	}

	private void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}

}
