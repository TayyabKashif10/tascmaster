package org.tasc.tascmaster.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.tasc.tascmaster.Models.UserModel;
import org.tasc.tascmaster.Models.ViewFactory;
import org.tasc.tascmaster.authentication.SupabaseAuthentication;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public TextField email_field;
    public PasswordField password_field;
    public Button login_btn;
    public Label status_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        login_btn.setOnAction(event -> onLogin());


    }

    public void onLogin(){
        status_label.setText("");
        String email = email_field.getText();
        String password = password_field.getText();

        try
        {
            int responseCode = SupabaseAuthentication.login(email, password);

            switch (responseCode) {

                // SUCCESSFUL LOGIN
                case 200:
                    status_label.setStyle("-fx-text-fill: #39bd39");
                    status_label.setText("Logged in successfully!");
                    ViewFactory.getInstance().setScene(ViewFactory.getScene("test.fxml"));
                    break;

                // INVALID DETAILS
                case 400:
                    status_label.setText("Invalid Login Credentials.");
                    break;

                // UNEXPECTED ERROR BUT NO EXCEPTIONS
                default:
                    status_label.setText("Unexpected error. Please try again.");
                    System.err.println(responseCode);

            }

        }
        // EXCEPTIONS: INCLUDES lack of Internet Connectivity error.
        catch (Exception e){
            status_label.setText("Unexpected error.\nPlease ensure Internet Connectivity and try again.");
            e.printStackTrace();
        }

    }


}