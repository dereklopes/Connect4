/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Derek
 */
public class Connect4 extends Application {
static ColorPicker cp;

	@Override
	public void start(final Stage stage) throws Exception {
		draw(stage);
	}

	public void draw(Stage stage) throws Exception {
                List<String> params = getParameters().getUnnamed();
                
                // Set size of board and win condition from params
                int rows, columns, connectWin;
                if(params.size() == 2) {
                    rows = Integer.parseInt(params.get(0));
                    if(0 == rows)
                        rows = 6;
                    columns = rows;
                    connectWin = Integer.parseInt(params.get(1));
                    if(0 == connectWin || 1 == connectWin)
                        connectWin = rows;
                } else {
                    rows = columns = 6;
                    connectWin = 4;
                }

		stage.setTitle("LiteBrite");
		VBox vbox = new VBox();
		Button reset = new Button("reset");

		ToolBar tb = new ToolBar();
		cp = new ColorPicker();
		vbox.getChildren().add(tb);
		tb.getItems().addAll(cp, reset);

		GridPane grid = new GridPane();
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
				// implemented for Extra Credit
				// 1) first click draws rectangle to pane with the current color
				// selected
				// ****two cases after first click on a pane****
				// 2) if clicked again with the same color selected, it will
				// reset (removing the rectangle)
				// 3) if clicked again with a different color, it will remove
				// the current rectangle and then add a rectangle of the new
				// color
				pane.setOnMouseReleased(e -> {
					int count = (pane.getChildren().isEmpty()) ? 1 : 0;
					if (count == 1) {
						pane.getChildren().add(Anims.getAtoms());
					} else {
						Rectangle r = (Rectangle) pane.getChildren().get(0);
						pane.getChildren().removeAll(pane.getChildren());
						if (r.getFill() != cp.getValue()) {
							pane.getChildren().add(Anims.getAtoms());
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
                if((columns * 10) + 20 < 500) {
                    sceneWidth = 500;
                } else {
                    sceneWidth = (columns * 10) + 20;
                }
                if((rows * 10) + 55 < 500) {
                    sceneHeight = 500;
                } else {
                    sceneHeight = (rows * 10) + 55;
                }
                
		Scene scene = new Scene(vbox, sceneWidth, sceneHeight);
		scene.getStylesheets().add(Connect4.class.getResource("resources/game.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public static class Anims {

		public static Node getAtoms() {
			Rectangle rect = new Rectangle(9, 9);
			rect.setFill(cp.getValue());
			return rect;
		}
	}

	public static void main(final String[] arguments) {
		Application.launch(arguments);
	}

    
}
