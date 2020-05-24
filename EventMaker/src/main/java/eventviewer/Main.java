package eventviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/gui.fxml"));
        primaryStage.setTitle("WOH Maker");
        root.autosize();
        root.getStylesheets().add("/styles.css");

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/help.png")));

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setRenderScaleX(1.2);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
