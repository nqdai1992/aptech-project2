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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sample.entity.NhanvienEntity;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author MSI
 */
public class LoginController implements Initializable {

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
    EntityManager em = emf.createEntityManager();
    
//    NhanvienEntityJpaController ctrl = new NhanvienEntityJpaController(emf);

//    List<NhanvienEntity> list = new ArrayList<>();

    public void Login(ActionEvent event) {
        String username = txtUsername.getText();
        String pass = txtPassword.getText();
        
        TypedQuery<NhanvienEntity> createQuery = em.createQuery("SELECT n FROM NhanvienEntity n WHERE n.username = :name and n.password = :pass", NhanvienEntity.class);
        createQuery.setParameter("name", username);
        createQuery.setParameter("pass", pass);
        
        List<NhanvienEntity> resultList = createQuery.getResultList();
        for (NhanvienEntity nhanvien : resultList) {
            if(resultList.size() != 0){
                System.out.println("login sucessfully!!!");
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ScreenController screen = new ScreenController();
                try {
                    screen.init(app_stage);
                    screen.setCurrentScreen("createBill", 679, 432);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
