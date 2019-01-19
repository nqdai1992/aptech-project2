/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sample.entity.DanhmucEntity;

/**
 *
 * @author MSI
 */
public class addCategoryController implements Initializable{

    @FXML
    private TextField txtMa;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Button btnSave;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("supermarket");
    EntityManager em = emf.createEntityManager();
    DanhmucEntityJpaController ctrl = new DanhmucEntityJpaController(emf);
    
    public void Save(ActionEvent event) throws Exception{
        int ma = Integer.parseInt(txtMa.getText());
        String name = txtName.getText();
        String des = txtDescription.getText();
        
        DanhmucEntity danhMuc = new DanhmucEntity();
        danhMuc.setMa(ma);
        danhMuc.setTenDanhMuc(name);
        danhMuc.setDescription(des);
        
        ctrl.create(danhMuc); 
    }
    
}
