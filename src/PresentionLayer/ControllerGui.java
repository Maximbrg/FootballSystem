package PresentionLayer;

import ServiceLayer.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class ControllerGui extends ImageView{

    @FXML
    TextField userName;
    @FXML
    TextField userPass;
    @FXML
    ImageView nameImg;
    @FXML
    ImageView passImg;
    @FXML
    Button loginBtn;


    @FXML
    public void handleLogin(){
        if(userPass.getText().length() == 0) {
            passImg.setVisible(true);
        }
        if(userName.getText().length() == 0) {
            nameImg.setVisible(true);
        }
    String userN = userName.getText();
    String userP = userPass.getText();
    try {
       FanController.getInstance().login(userN, userP);
    }
    catch (Exception e){
        System.out.println(e.toString());
    }
    }

    @FXML
    public void OnHover(){
        loginBtn.setStyle("-fx-background-color: #4179F0 ; -fx-background-radius:10");
    }

    @FXML
    public void OnExit(){
        loginBtn.setStyle("-fx-background-color: #2060E4 ; -fx-background-radius:10");
    }
    @FXML
    public void OnPress(){
        loginBtn.setStyle("-fx-background-color: #000F64 ; -fx-background-radius:10");
    }


    @FXML
    public void handleMouseClickedName(){
        nameImg.setVisible(false);
        if(userPass.getText().length() == 0) {
            passImg.setVisible(true);
        }
    }

    @FXML
    public void handleMouseClickedPass(){
        passImg.setVisible(false);
        if(userName.getText().length() == 0) {
            nameImg.setVisible(true);
        }

    }

}
