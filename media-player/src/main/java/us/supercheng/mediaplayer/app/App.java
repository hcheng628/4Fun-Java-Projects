package us.supercheng.mediaplayer.app;/**
 * Created by cl799honchen on 6/5/2017.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URLEncoder;


public class App extends Application {
    final private String EncodingChars = "UTF-8";
    final private String App_Name = "Light Weight Media Player";
    final private int App_Default_Width = 280;
    final private int App_Default_Height = 720;
    final private String FXML_Folder_Path = "/FXMLs/";
    final private String FXML_Extension = ".fxml";
    final private String Media_Play_Btn_Playing_Stage = ">";
    final private String Media_Play_Btn_Pausing_Stage = "|";
    final private String Media_Play_Btn_Finishing_Stage = "||";



    private String mediaFileFullURI = "";
    // private File mediaFile;
    private Media mediaFile;
    private MediaPlayer player;



    // private FileChooser fileChooser;
    private BorderPane rootPanel;
    private MenuBar menuBar;
    private Menu menuFile;
    private MenuItem menuItemOpenFile;
    private MenuItem menuItemOpenURL;

    private Slider mediaVolumeSlider;
    private Slider mediaProgressSlider;

    private Button mediaPlayBtn;



    public App(){
        this.mediaVolumeSlider = new Slider();
        this.mediaVolumeSlider.setMin(0.0);
        this.mediaVolumeSlider.setMax(200.0);

        this.mediaProgressSlider = new Slider();

        this.mediaPlayBtn = new Button(Media_Play_Btn_Playing_Stage);

        this.menuBar = new MenuBar();
        this.menuFile = new Menu("Open");
        this.menuItemOpenFile = new MenuItem("File Path");
        this.menuItemOpenURL = new MenuItem("File URL");

        this.menuFile.getItems().add(this.menuItemOpenFile);
        this.menuFile.getItems().add(this.menuItemOpenURL);
        this.rootPanel = new BorderPane();
        this.menuBar.getMenus().add(this.menuFile);

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle(App_Name);
        Scene scene = new Scene(this.rootPanel, App_Default_Width, App_Default_Height);
        Pane paneTop = new Pane();
        Pane paneBottom = new Pane();

        // Load media from Disk
        this.menuItemOpenFile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try{
                    player = new MediaPlayer(new Media("file:///" + new FileChooser().showOpenDialog((Stage) menuBar.getScene().getWindow()).getAbsolutePath().replace("\\", "/")));
                    rootPanel.setCenter(new MediaView(player));
                }catch(Exception ex){
                    ex.printStackTrace();   // Dont care
                }
            }
        });

        // Media Control Btn
        this.mediaPlayBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Player Status: " + player.getStatus());
                if(player.getStatus().equals(MediaPlayer.Status.PLAYING)){
                    player.pause();
                    mediaPlayBtn.setText(Media_Play_Btn_Playing_Stage);
                }else if(player.getStatus().equals(MediaPlayer.Status.PAUSED) || player.getStatus().equals(MediaPlayer.Status.HALTED) || player.getStatus().equals(MediaPlayer.Status.READY)) {
                    player.play();
                    mediaPlayBtn.setText(Media_Play_Btn_Pausing_Stage);
                }
            }
        });

        // Progress Bar
        this.mediaProgressSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Old Val: " + oldValue + " New Val: " + newValue);
            }
        });

        paneTop.getChildren().add(this.menuBar);

        paneBottom.getChildren().add(this.mediaPlayBtn);
        paneBottom.getChildren().add(this.mediaProgressSlider);
        paneBottom.getChildren().add(this.mediaVolumeSlider);

        this.rootPanel.setTop(paneTop);
        this.rootPanel.setBottom(paneBottom);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}