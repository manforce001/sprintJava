/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import home.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.User;
import utils.ConnectionUtil;

/**
 *
 * @author Nasri
 */
public class editUserController implements Initializable
{
   
    @FXML
    private AnchorPane searchTF;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Label nomutlisateur;

    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;

    @FXML
    private ComboBox<String> txtGender;

    @FXML
    private TextField tel;

    @FXML
    private TextField pass;

    @FXML
    private Button update1;
    
    PreparedStatement preparedStatement;
    Connection connection;
    public editUserController()
    {
                connection = (Connection) ConnectionUtil.conDB();

    }

    /* retour en arrière*/
     @FXML
    void Handle3Events(MouseEvent event) 
    {
       try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/InterfaceUtlisateur.fxml")); 

                    Parent root = (Parent) loader.load();
                    UtlisateurControlleur utcontroller = loader.getController(); 
                    utcontroller.myFunction(nomutlisateur.getText());
               
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                               
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
 
         
    }
    /* delete user et les reclamation de user forgein key*/
    @FXML
    void Handle1Events(MouseEvent event) 
    {
     int  a = Recherche(nomutlisateur.getText()).getId(); 
     deleteReclamation(a);
     DeleteUSer();
     
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
    /* afficher les cordonnées de l'utlisateur*/
    @FXML
    void HandleEvents(MouseEvent event)
    {
       String email=nomutlisateur.getText(); 
       txtFirstname.setText(Recherche(email).getNom());
       txtLastname.setText(Recherche(email).getPrenom());
       txtGender.setValue(Recherche(email).getRole());
       tel.setText(Recherche(email).getTelephone());
       pass.setText(Recherche(email).getPassword());
       
    }
    
    /* supprimer utlisateur*/
    public void DeleteUSer()
    {
         try 
        {
          
            String req="delete from user where email=?"; 
            preparedStatement = (PreparedStatement) connection.prepareStatement(req);
            preparedStatement.setString(1, nomutlisateur.getText());
            preparedStatement.executeUpdate();
            
            

        } 
        catch (SQLException ex) 
        {
             System.out.println(ex.getMessage());

        }
    }
    /*supprimer reclamation */
    public void deleteReclamation(int a )
    {
        
           try 
        {
          
            String req="delete from reclamation where id_user =?"; 
            preparedStatement = (PreparedStatement) connection.prepareStatement(req);
            preparedStatement.setInt(1,a);
            preparedStatement.executeUpdate();
               

        } 
        catch (SQLException ex) 
        {
             System.out.println(ex.getMessage());

        }
        
    }
     /* modifier */
    public void saveData()
      {
            try {
            String st = "UPDATE user  set  nom=? , prenom=? , roles=? ,telephone=?,password =?  where email =?  ";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txtFirstname.getText());
            preparedStatement.setString(2, txtLastname.getText());
            preparedStatement.setString(3, txtGender.getValue().toString());
            preparedStatement.setString(4, tel.getText());
            preparedStatement.setString(5, pass.getText());
            preparedStatement.setString(6, nomutlisateur.getText());
            preparedStatement.executeUpdate();
            System.out.println("insertion terminer ");
       

        } 
        catch (SQLException ex) {
           System.out.println(ex.getMessage());
          
        } 
      }
    @FXML
      /* action  de modification*/
    void Handle2Events(MouseEvent event) 
    {
        System.err.println("test valider");
        saveData();        
         
    }
    
 public void myFunction( String Text )
        {
            nomutlisateur.setText(Text);
        }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        
         txtGender.getItems().addAll("admin", "jury", "coatch","utlisateur");
       
    }
    /* recherche user*/
      public User Recherche(String Text)
      {
          try {
            String req="select * from user where email=? "; 
           
            preparedStatement = (PreparedStatement) connection.prepareStatement(req);


     
            preparedStatement.setString(1, Text);
           ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                
                User p = new User(rs.getInt(1), rs.getString(13), rs.getString(14),rs.getString(4), rs.getString(12), rs.getString(15), rs.getString(16), rs.getString(8));
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
