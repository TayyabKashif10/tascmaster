package org.tasc.tascmaster;

import javafx.application.Application;
import javafx.stage.Stage;
import org.tasc.tascmaster.Models.UserModel;
import org.tasc.tascmaster.Models.ViewFactory;
import org.tasc.tascmaster.database_https.Requests;


public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        ViewFactory.setInstance(stage);


        //TODO: open the dashboard of the logged in user instead of this i.e fix flow control.
        // user isn't logged in
        if (!UserModel.getInstance().userLoggedIn()){
            // Start with the login scene
            ViewFactory.getInstance().setScene(ViewFactory.loginScene());
        }
        else
        {
            ViewFactory.getInstance().setScene(ViewFactory.getScene("test.fxml"));
        }

        stage.show();
    }



}
