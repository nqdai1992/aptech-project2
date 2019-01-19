/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import sample.entity.DanhmucEntity;

/**
 *
 * @author MSI
 */
public class addProductController implements Initializable{

    @FXML
    private TextField txtMa;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private ComboBox<String> cbCategory;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button btnSave;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("supermarket");
    EntityManager em = emf.createEntityManager();
    
    SanphamEntityJpaController ctrl = new SanphamEntityJpaController(emf);
    
    public void getComboBox(){
        TypedQuery<DanhmucEntity> createQuery = em.createQuery("SELECT d FROM DanhmucEntity d ", DanhmucEntity.class);
        List<DanhmucEntity> resultList = createQuery.getResultList();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (DanhmucEntity rs : resultList) {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, rs.getTenDanhMuc());
            }
        }
        cbCategory.setItems(list);
    }
}
