package eventmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    static String[] arguments;
    private static Stage stage;

    @Override
    public void start(final Stage primaryStage) throws Exception {

        VBox root = FXMLLoader.load(this.getClass().getResource("/gui.fxml"));
        primaryStage.setTitle("WOH Maker");
        root.autosize();
        root.getStylesheets().add("/styles.css");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/help.ico")));
        Controller.setOpenWithPath(arguments);

        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getRoot().requestLayout();
        primaryStage.show();

        stage = primaryStage;

        primaryStage.centerOnScreen();
    }

    public static Stage getPrimaryStage(){
        return stage;
    }


    public static void main(final String[] args) {
        for (final String a : args) {
            System.out.println(a);
        }
        arguments = args;
        launch(args);
    }

}
