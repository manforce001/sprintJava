/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import models.Reclamation;
import utils.ConnectionUtil;

/**
 *
 * @author Nasri
 */
public class reclamationController implements Initializable 
{
    
    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;

    @FXML
    private Button btnSave;

    @FXML
    private Button update;

    @FXML
    private Button delete;
  
    

    @FXML
    private JFXTreeTableView<Reclamation> treeTableView;

    @FXML
    private TreeTableColumn<Reclamation, String> nameCol;

    @FXML
    private TreeTableColumn<Reclamation, String> jobCol;

    @FXML
    private TreeTableColumn<Reclamation, String> ageCol;

    @FXML
    private TreeTableColumn<Reclamation, String> genderCol;

    @FXML
    private Label lblStatus;

   
    @FXML
    private JFXTextField searchtTF;

    @FXML
    private Button search;
    
    ObservableList<Reclamation> list;

    PreparedStatement preparedStatement;
    Connection connection;

    @FXML
    private Button refresh;
    
    public reclamationController()
    {
        connection = (Connection) ConnectionUtil.conDB();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {  
        
        

        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reclamation, String> param) {
                return param.getValue().getValue().getSujet();
            }
        });

        jobCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reclamation, String> param) {
                return param.getValue().getValue().getReclamation();
            }
        });


        ageCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reclamation, String> param) {
                return param.getValue().getValue().getReponse();
            }
        });


        genderCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reclamation, String> param) {
                return param.getValue().getValue().getEtat();
            }
        });


        list= fetRowList();

       TreeItem<Reclamation> root=new RecursiveTreeItem<Reclamation>(list, RecursiveTreeObject ::getChildren);

        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);
        
        
            searchtTF.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    treeTableView.setPredicate(new Predicate<TreeItem<Reclamation>>() {
                        @Override
                        public boolean test(TreeItem<Reclamation> modelTreeItem) {
                            return modelTreeItem.getValue().getSujet().getValue().contains(newValue)  ;
                        }
                    });
                }
            });
        
            
        
        
    }
    
     public ObservableList<Reclamation> fetRowList() 
     {
        String SQL = "SELECT * from reclamation";
        String []chaine = new String [20]; 

        list= FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
            
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) 
                {
                    //Iterate Column
                    chaine[i]=rs.getString(i); 
                }
              System.out.println("test  insertion");
              

               list.addAll(new Reclamation(chaine[3], chaine[4], chaine[5], chaine[6]));
              System.out.println("test  insertion valider");


             }
            

       
        } catch (SQLException ex) {
            System.err.println("errreuuuur");
        }
        return list; 
    
     
    

}
}
