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
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.MessagingException;
import models.Reclamation;
import models.User;
import utils.ConnectionUtil;

/**
 *
 * @author Nasri
 */


public class reclamationAdmin implements Initializable 
{
     @FXML
    private Label nomutlisateur;
     
    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;
    
    @FXML
    private TextField txtFirstname1;

    @FXML
    private TextField txtLastname1;

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
    
    public reclamationAdmin()
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
        
            
         treeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                showDetails(newValue);
            }); 
        
    }
     public void showDetails(TreeItem<Reclamation> treeItem)
     {
         
              txtFirstname.setText(treeItem.getValue().getSujet().getValue());
              txtLastname.setText(treeItem.getValue().getReclamation().getValue()); 

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
              

               list.addAll(new Reclamation(chaine[2], chaine[3], chaine[4], chaine[5]));
              System.out.println("test  insertion valider");


             }
            

       
        } catch (SQLException ex) {
            System.err.println("errreuuuur");
        }
        return list; 
    
     
    

}
     
      public void myFunction( String Text )
        {
            nomutlisateur.setText(Text);
        }
      
    /* retour en arrière*/  
     @FXML
    private void Handle5Events(MouseEvent event) 
    {
       try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/admin.fxml")); 

                    Parent root = (Parent) loader.load();
                    adminController utcontroller = loader.getController(); 
                    utcontroller.myFunction(nomutlisateur.getText());
                    System.out.println(" fonction verid");
                             
                             
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                               
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               
                }
               catch (IOException ex) 
                {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }


    }
    
    @FXML
    private void HandleEvents(MouseEvent event) {
        //check if not empty
        if ( txtFirstname.getText().isEmpty() || txtLastname.getText().isEmpty() )
        {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        } else {
            saveData();
            fetRowList(); 
        }

    }
    
    
     @FXML
    private void Handle3Events(MouseEvent event) 
    {
       try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/ReclamationAdmin.fxml")); 

                    Parent root = (Parent) loader.load();
                    reclamationAdmin utcontroller = loader.getController(); 
                    utcontroller.myFunction(nomutlisateur.getText());
               
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                               
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                  
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
     
    }
    
     /* déconnexion*/
       public void handleButton6Action(MouseEvent event) throws MessagingException
      {
             try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
      }
     @FXML
    private void Handle1Events(MouseEvent event) 
    {
        if ( txtFirstname.getText().isEmpty() || txtLastname.getText().isEmpty() )
        {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        } 
        else
        {
        try 
        {
          
            String req="delete from reclamation where reclamation=?"; 
            preparedStatement = (PreparedStatement) connection.prepareStatement(req);
            preparedStatement.setString(1, txtLastname.getText());
            preparedStatement.executeUpdate();
            
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("reclamation effacée");

        } 
        catch (SQLException ex) 
        {
             System.out.println(ex.getMessage());

        }
        }
      
    }
    
      
      public void saveData()
      {
              try {
            String st = "UPDATE reclamation  set  etat=? , reponse=? where reclamation =?  ";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txtFirstname1.getText());
            preparedStatement.setString(2, txtLastname1.getText());
            preparedStatement.setString(3, txtLastname.getText());

         
            preparedStatement.executeUpdate();
           lblStatus.setText("Added Successfully");
            System.out.println("insertion terminer ");
       

        } 
        catch (SQLException ex) {
           // System.out.println(ex.getMessage());
           lblStatus.setText(ex.getMessage());
          
        }
      }
       public User Recherche(String Text)
      {
          try {
            String req="select * from user where email=? "; 
           
            preparedStatement = (PreparedStatement) connection.prepareStatement(req);


     
            preparedStatement.setString(1, Text);
           ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                
                User p = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                System.out.println(p.toString());
                return (p); 

            }
                    

        } 
        catch (SQLException ex) 
        {
             System.out.println(ex.getMessage());

        }
         User p1 = null;
          return (p1); 
      }
}
