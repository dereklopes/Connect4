package connect4;

public class Presenter {
	Connect4 view;
	MenuModel mm;

	void attachView(Connect4 view) {
		this.view = view;
		mm = new MenuModel();
	}

	void enterPlayer(String name) {
		mm.enterPlayer(name);
	}

	void changeTurn() {
		mm.changeTurn();
	}

	void addWin(String input) {
		mm.addWin(input);
	}

	String getTurn() {
		return mm.getTurn();
	}

	void pushGridValues(int row, int column) {
		mm.setGrid(row, column);
		System.out.println("Pushed: " + row + " " + column);
	}

	void updateModelGrid(int column){
		int row = mm.updateGrid(column);
		if (row == -1){
			System.out.println("You can't use column anymore");
		}
		else {
		view.paintCircle(row, column);
		}
	}
}
