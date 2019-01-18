/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dainguyen
 */
public class ScreenController {
    private boolean auth = false;
    private String currentScreen = "login";
    private Map routes = new HashMap();
    private Stage stage = null;
    
    public ScreenController () {
        routes.put("login", "/view/login.fxml");
        routes.put("createBill", "/view/createbill.fxml");
    }
    
    public void init (Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        if (!auth) {
            this.setCurrentScreen("login", 640, 400);
        }
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public void setCurrentScreen(String currentScreen, int width, int height) throws IOException {
        this.currentScreen = currentScreen;
        String route = (String) this.routes.get(this.currentScreen);
        Parent root = FXMLLoader.load(getClass().getResource(route));
        this.stage.setTitle(this.currentScreen);
        this.stage.setScene(new Scene(root, width, height));
        this.stage.show();
    }
}
