package connect4;

import java.util.ArrayList;

public class MenuModel {
	ArrayList<Player> players;
	Player turn;
	int[][] grid;

	public MenuModel() {
		players = new ArrayList<Player>();

	}

	public void enterPlayer(String name) {
		Player p = new Player(name);
		players.add(p);
		turn = players.get(0);
	}

	public void changeTurn() {
		if (turn.equals(players.get(0))) {
			turn = players.get(1);
		} else {
			turn = players.get(0);
		}
	}

	public void setGrid(int row, int column) {
		grid = new int[row][column];
	}

	public String getTurn() {
		if (turn.equals(players.get(0))) {
			return players.get(0).getName();
		} else {
			return players.get(1).getName();
		}
	}

	public void addWin(String playerName) {
		for (Player p : players) {
			if (p.getName().equals(playerName)) {
				p.addWin();
			}
		}
	}

	public int updateGrid(int column) {
		int row;
		boolean lastGrid = false;
		if (lastGrid){
			return -1;
		}
		for ( row = grid.length-1; row >= 0; row--) {
	
			if (turn.equals(players.get(0))) {
				if (grid[row][column] == 0) {
					grid[row][column] = 1;
					break;
				}
			} 
			else if (grid[row][column] == 0 ) {
				grid[row][column] = 2;
				break;
			}
		}
		
		if ( row == 0){
			lastGrid = true;
			return row;
		}
		return row;
	}
        
    /**
     * Checks to see if a connection has been made.
     * @return The player number of the winner or 0 if no winner was found.
     */
    public int checkWin() {
            return 0;
        }

}
