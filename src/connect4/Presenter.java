package connect4;

public class Presenter {
	Connect4 view;
	MenuModel mm;
	
	void attachView(Connect4 view) {
		this.view = view;
	}
}
