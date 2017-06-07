package us.supercheng.mediaplayer.app;/**
 * Created by cl799honchen on 6/5/2017.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private MenuItem menuItemCloseApp;

    private Label mediaVolumeSliderLabel;
    private Slider mediaVolumeSlider;

    private Label mediaProgressSliderLabel;
    private Slider mediaProgressSlider;

    private Button mediaPlayBtn;

    private VBox topMenuContainer;
    private ToolBar bottomToolBar;



    public App(){
        this.mediaVolumeSliderLabel = new Label("Volume: 100%");
        this.mediaVolumeSlider = new Slider();
        this.mediaVolumeSlider.setMin(0.0);
        this.mediaVolumeSlider.setMax(100.0);
        this.mediaVolumeSlider.setValue(100.0);
        this.mediaVolumeSlider.setMaxWidth(60.0);

        this.mediaProgressSliderLabel = new Label("");
        this.mediaProgressSlider = new Slider();

        this.mediaPlayBtn = new Button(Media_Play_Btn_Playing_Stage);

        this.menuBar = new MenuBar();
        this.menuFile = new Menu("Menu");
        this.menuItemOpenFile = new MenuItem("File Path");
        this.menuItemOpenURL = new MenuItem("File URL");
        this.menuItemCloseApp = new MenuItem("Exit");

        this.menuFile.getItems().add(this.menuItemOpenFile);
        this.menuFile.getItems().add(this.menuItemOpenURL);
        this.menuFile.getItems().add(this.menuItemCloseApp);

        this.rootPanel = new BorderPane();
        this.menuBar.getMenus().add(this.menuFile);

        this.bottomToolBar = new ToolBar(
                this.mediaPlayBtn,
                this.mediaProgressSlider,
                this.mediaProgressSliderLabel,
                this.mediaVolumeSlider,
                this.mediaVolumeSliderLabel
        );

        this.topMenuContainer = new VBox();


    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle(App_Name);
        Scene scene = new Scene(this.rootPanel, App_Default_Width, App_Default_Height);
        Pane paneBottom = new Pane();

        // Close App
        this.menuItemCloseApp.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ((Stage)menuBar.getScene().getWindow()).close();
            }
        });

        // Load media from Disk
        this.menuItemOpenFile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try{
                    // String tempStr = new FileChooser().showOpenDialog((Stage) menuBar.getScene().getWindow()).toURI().toString();
                    // System.out.println(tempStr);
                    player = new MediaPlayer(new Media("file:/C:/Users/cl799honchen/Downloads/SampleAudio_0.7mb.mp3"));

                    player.currentTimeProperty().addListener(new InvalidationListener() {
                        public void invalidated(Observable observable) {
                            updateMediaProgress();
                        }
                    });

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

        this.mediaVolumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // System.out.println("Old Val: " + oldValue + " New Val: " + newValue);
                player.setVolume(newValue.doubleValue()/100.00);
                mediaVolumeSliderLabel.setText("Volume: " + (int)Math.round(player.getVolume()* 100)  + "%");
            }
        });

        // Progress Bar
        this.mediaProgressSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // System.out.println("Old Val: " + oldValue + " New Val: " + newValue);
                if(mediaProgressSlider.isValueChanging()){
                    player.seek(new Duration(player.getMedia().getDuration().toMillis() * mediaProgressSlider.getValue() / 100));
                }
            }
        });


        this.topMenuContainer.getChildren().add(this.menuBar);

        paneBottom.getChildren().add(this.bottomToolBar);

        this.rootPanel.setTop(this.topMenuContainer);
        this.rootPanel.setBottom(paneBottom);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Update Progress Bar
    protected void updateMediaProgress(){
        Platform.runLater(new Runnable(){
            public void run() {
                mediaProgressSlider.setValue(player.getCurrentTime().toMillis() / player.getMedia().getDuration().toMillis() * 100);
                mediaProgressSliderLabel.setText(((int)(player.getCurrentTime().toMillis()/3600000) % 24) + ":" + ((int)(player.getCurrentTime().toMillis()/60000) % 60) +
                        ":" + ((int)(player.getCurrentTime().toMillis()/1000) % 60) + " / " + ((int)(player.getMedia().getDuration().toMillis()/3600000)%24) + ":" +
                        ((int)(player.getMedia().getDuration().toMillis()/60000)%60) + ":" + ((int)(player.getMedia().getDuration().toMillis()/1000)%60));
            }
        });
    }
}