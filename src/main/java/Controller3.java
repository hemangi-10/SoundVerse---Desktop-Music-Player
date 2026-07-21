import java.io.File;
import java.util.ArrayList;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controller3 {

    @FXML
    Label name,currentTime,totalTime;
    @FXML
    TextField tf;
    @FXML
    Button play,pause,reset,previous,next;
    @FXML
    Slider slider;
    @FXML
    TreeView<String> tv;

    ArrayList<File> arr = new ArrayList<>();
    TreeItem<String> root;
    MediaPlayer mp;
    Media media;
    Stage stage;
    Scene scene;
    File file;
    

    public void dis(String a){
        name.setText("Welcome! "+a);
    }
    public void initialize(){

        root = new TreeItem<>("Your Songs");
        root.setExpanded(true); //the user must click it to open 
        tv.setRoot(root);
    }
    
    public void change(){

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("MP3 files","*.mp3"));

        file = fc.showOpenDialog(stage);

        if(file != null){

            tf.setText(file.getName());

            arr.add(file);
            TreeItem<String> music = new TreeItem<>(file.getName());
            root.getChildren().add(music);
        }

        media = new Media(file.toURI().toString());
        mp = new MediaPlayer(media);
        setupMediaplayer();

        tv.setOnMouseClicked(e -> {

            tf.setText(null);

            TreeItem<String> selected = tv.getSelectionModel().getSelectedItem();

            if(selected != null && selected != root){

                String name = selected.getValue();
                
                for(File file : arr){

                    if(file.getName().equals(name)){
                    playSong(file);
                    break;
                }
                }
            }
        });
    }
   
    private String formate(Duration duration) {
        
        int second = (int)duration.toSeconds() % 60;
        int minute = (int)duration.toMinutes();
        return String.format("%02d:%02d" , minute,second);
    }
    private void playSong(File file2) {

        media = new Media(file2.toURI().toString());
        mp = new MediaPlayer(media);
        setupMediaplayer();
        
    }
    private void setupMediaplayer(){

        mp.setOnReady(() -> {
            slider.setMax(mp.getTotalDuration().toSeconds());
            totalTime.setText(formate(mp.getTotalDuration()));
        });

        mp.currentTimeProperty().addListener((obs, oldTime, newTime) ->{

            if(!slider.isValueChanging()){ //the user is not dragging slider
                slider.setValue(newTime.toSeconds());
                currentTime.setText(formate(newTime));
            }
        });
        slider.valueChangingProperty().addListener((obs, waschanging, ischanging) -> {

            if(!ischanging){
                mp.seek(Duration.seconds(slider.getValue()));
                currentTime.setText(formate(Duration.seconds(slider.getValue())));
            }
        });

    }

    public void playm(){

        mp.play();

    }
    public void pausem(){

        mp.pause();

    }
    public void resetm(){

        mp.seek(Duration.seconds(0.0));

    }
   

   
}
