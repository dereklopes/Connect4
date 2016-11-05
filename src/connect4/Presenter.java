package connect4;

public class Presenter {
	Connect4GUI view;
	GameModel mm;

	void attachView(Connect4GUI view) {
            this.view = view;
            mm = new GameModel();
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
	}

	void updateModelGrid(int column){
            int row = mm.updateGrid(column);
            if (row == -1){
                // System.out.println("You can't use column anymore");
            }
            else {
                view.paintCircle(row, column);
            }
	}
        
        /**
         * Checks for a winner.
         * @return Player number of winner or 0 for no winner.
        */
        int checkWin(int winCondition) {
            return mm.checkWin(winCondition);
        }
}
