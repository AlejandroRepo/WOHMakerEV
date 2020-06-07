package eventmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    static String[] arguments;

    @Override
    public void start(final Stage primaryStage) throws Exception {


        final Parent root = FXMLLoader.load(this.getClass().getResource("/gui.fxml"));
        primaryStage.setTitle("WOH Maker");
        root.autosize();
        root.getStylesheets().add("/styles.css");

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/help.png")));

        final double width = Screen.getPrimary().getBounds().getWidth();
        final double height = Screen.getPrimary().getBounds().getHeight();

        primaryStage.setResizable(false);

        if (width < 1920 && height < 1080) {
            primaryStage.setWidth(width - (width * 0.05));
            primaryStage.setHeight(height - (height * 0.05));
            Controller.setSmallScreenMode(true);
        }

        Controller.setOpenWithPath(arguments);

        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getRoot().requestLayout();
        primaryStage.show();

        primaryStage.centerOnScreen();
    }


    public static void main(final String[] args) {
        for (final String a : args) {
            System.out.println(a);
        }
        arguments = args;
        launch(args);
    }

}
