package us.supercheng.mediaplayer.app;/**
 * Created by cl799honchen on 6/5/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class App extends Application {
    final private String App_Name = "Light Weight Media Player";
    final private int App_Default_Width = 1280;
    final private int App_Default_Height = 720;
    final private String FXML_Folder_Path = "/FXMLs/";
    final private String FXML_Extension = ".fxml";

    private FileChooser fileChooser;
    private MenuBar menuBar;
    private Menu menuFile;

    public App(){
        this.fileChooser = new FileChooser();

        this.menuBar = new MenuBar();
        this.menuFile = new Menu("Open File");
        this.menuBar.getMenus().add(this.menuFile);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(this.getClass().getResource(FXML_Folder_Path +"AppController" + FXML_Extension));
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                event.get
            }
        };
        this.menuFile.setOnAction(action);


        primaryStage.setTitle(App_Name);
        BorderPane root = new BorderPane();
        Pane pane = new Pane();
        pane.getChildren().add(this.menuBar);
        root.setTop(pane);
        Scene scene = new Scene(root, App_Default_Width, App_Default_Height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
