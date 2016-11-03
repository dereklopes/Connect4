/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.util.List;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.Stage;

/**
 *
 * @author Derek
 */
public class Connect4 extends Application {
	GridPane grid;
	Presenter presenter;
        List<String> params;
	Label p1;
	Label p2;

	public Connect4(Presenter presenter, List<String> params) {
		this.presenter = presenter;
                this.params = params;
		presenter.attachView(this);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		draw(stage);
	}

	public void draw(Stage stage) throws Exception {

		// Set size of board and win condition from params
		final int rows, columns, connectWin;
		if (params.size() == 2) {
			rows = Integer.parseInt(params.get(0));
			columns = rows;
			connectWin = Integer.parseInt(params.get(1));
		} else {
			rows = columns = 6;
			connectWin = 4;
		}
		// pushes the grid data to the back end 2d array.
		presenter.pushGridValues(rows, columns);
		stage.setTitle("ConnectX");
		VBox vbox = new VBox();
		Button reset = new Button("reset");
		// p1 is to be set to whatever given input for player names
		// p2 is to be set to whatever given input for player names
		p1 = new Label("Player 1");
		p2 = new Label("Player 2");
		// passing players names to the menumodel
		presenter.enterPlayer(p1.getText());
		presenter.enterPlayer(p2.getText());

		ToolBar tb = new ToolBar();

		vbox.getChildren().add(tb);
		tb.getItems().addAll(reset, p1, p2);

		grid = new GridPane();
		grid.getStyleClass().add("game-grid");

		for (int i = 0; i < columns; i++) {
			ColumnConstraints column = new ColumnConstraints(10);
			grid.getColumnConstraints().add(column);
		}

		for (int i = 0; i < rows; i++) {
			RowConstraints row = new RowConstraints(10);
			grid.getRowConstraints().add(row);
		}

		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				Pane pane = new Pane();
                                pane.setMinSize(50, 50);

				pane.setOnMouseReleased(e -> {
					// paints a circle on every click on the given grid
					// pane.getChildren().add(paintCircle());
//					System.out.println(GridPane.getColumnIndex(pane));
					// updates the grid;
					presenter.updateModelGrid(GridPane.getColumnIndex(pane));
                                        // Check for a winner
                                        int playerWin = presenter.checkWin(connectWin);
                                        if(playerWin != 0) {
                                            // display a win message
                                            Alert winAlert = new Alert(AlertType.INFORMATION);
                                            winAlert.setTitle("Game Over!");
                                            winAlert.setHeaderText(null);
                                            winAlert.setContentText(
                                                    "Player " + playerWin + " wins!");
                                            winAlert.showAndWait();
                                            // reset grid
                                            try {
                                        	draw(stage);
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        }
				});

				pane.getStyleClass().add("game-grid-cell");
				if (i == 0) {
					pane.getStyleClass().add("first-column");
				}
				if (j == 0) {
					pane.getStyleClass().add("first-row");
				}

				grid.add(pane, i, j);

			}
		}

		reset.setOnAction((ActionEvent event) -> {
			try {
				draw(stage);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		vbox.getChildren().add(grid);

		// minimum width and height to 100 so the window isnt too small
		int sceneWidth, sceneHeight;
                System.out.println(columns + "x" + rows);
                System.out.println(((columns * 100) + 20));
		if ((columns * 100) + 20 < 500) {
			sceneWidth = 500;
		} else {
			sceneWidth = (columns * 100) + 20;
		}
		if ((rows * 100) + 55 < 500) {
			sceneHeight = 500;
		} else {
			sceneHeight = (rows * 100) + 20;
		}
                
                grid.setMinHeight(sceneHeight);

		Scene scene = new Scene(vbox, sceneWidth, sceneHeight);
		scene.getStylesheets().add(Connect4.class.getResource("resources/game.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	// finds the node clicked and returned its index for the backend to update
	// the 2d int array.
	public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();

		for (Node node : childrens) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return result;
	}

	// paints the circle onto the grid, while also updating the back end "Turn".
	public void paintCircle(int row, int column) {
		Circle rect = new Circle(25, 25, 25);
		if (presenter.getTurn().equals(p1.getText()))
			rect.setFill(Color.YELLOW);
		else {
			rect.setFill(Color.RED);
		}
		presenter.changeTurn();
		Pane p = (Pane) getNodeByRowColumnIndex(row, column, grid);
		p.getChildren().add(rect);

	}

}
