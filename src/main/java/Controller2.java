import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller2 {

   @FXML
   TextField tu,tp;

    Stage stage;
    Scene scene;

   public void bmethod(ActionEvent e) throws IOException{

      Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
      stage = (Stage)((Node)e.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }
   public void smethod(ActionEvent a) throws IOException{

      String s1 = tu.getText();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
      Parent root = loader.load();

      Controller3 c3 = loader.getController();
      c3.dis(s1);
      
      stage = (Stage)((Node)a.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    
   }
}
