package org.tasc.tascmaster.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tasc.tascmaster.App;

import java.io.IOException;

public class ViewFactory {

    private Stage currentStage;

    private static ViewFactory instance;

    private ViewFactory(Stage stage){
        this.currentStage = stage;
    }

    public static ViewFactory getInstance()
    {
        return instance;
    }

    public static void setInstance(Stage stage){
        instance = new ViewFactory(stage);
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void setScene(Scene scene)
    {
        currentStage.setScene(scene);
    }


    public static Scene loginScene() throws IOException {
        return getScene("login.fxml");
    }

    public static Scene getScene(String sceneFXMLfile) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/" + sceneFXMLfile));
        return new Scene(loader.load());
    }




}
