/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sample.entity.NhanvienEntity;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author MSI
 */
public class LoginController implements Initializable{

    @FXML
    private Button btnButton;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("supermarket");

    public void Login(ActionEvent event){
        String username = txtUsername.getText();
        String pass = txtPassword.getText();
 
        NhanvienEntityJpaController ctrl = new NhanvienEntityJpaController(emf);
        
        List<NhanvienEntity> list = new ArrayList<>();
        
        list = ctrl.findNhanvienEntityEntities();
        
        for(int i=0; i<list.size(); i++){
            if(username == list.get(i).getUsername() && pass == list.get(i).getPassword()){
                System.out.println(" login successfully !!!");
            }else{
                System.out.println("login fail");
            }
        }
       
    }
}
