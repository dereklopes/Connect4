package connect4;

import javafx.application.Application;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author narayan
 */
public class Connect4Launcher extends Application {
    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connect4 app = new Connect4(new Presenter(), getParameters().getUnnamed());
        app.start(primaryStage); //To change body of generated methods, choose Tools | Templates.
    }
    
}