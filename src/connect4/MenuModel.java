package connect4;

import java.util.ArrayList;

public class MenuModel {
	ArrayList<Player> players;

	public MenuModel() {
		players = new ArrayList<Player>();
	}
	
	public void enterPlayer(String name){
		Player p = new Player(name);
		players.add(p);
	}
	
	public void addWin(String playerName){
		for (Player p: players) {
			if (p.getName().equals(playerName)) {
				p.addWin();
			}
		}
	}
	
	
	
}
