package eventmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/gui.fxml"));
        primaryStage.setTitle("WOH Maker");
        root.autosize();
        root.getStylesheets().add("/styles.css");

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/help.png")));

        primaryStage.setResizable(false);

        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();

        if (width<1920 && height<1080){
          primaryStage.setWidth(width-(width*0.05));
          primaryStage.setHeight(height-(height*0.05));
          Controller.setSmallScreenMode(true);
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getRoot().requestLayout();
        primaryStage.show();



        primaryStage.centerOnScreen();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
