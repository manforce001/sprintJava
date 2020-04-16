/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import models.Reclamation;
import models.User;
import utils.ConnectionUtil;

/**
 *
 * @author Nasri
 */
public class adminController implements Initializable
{
    
     @FXML
    private Label nomutlisateur;
    @FXML
    private Pane GestionSpon;

    @FXML
    private Button gestionUti;
     @FXML
    private Button Gestionrec;
    

    @FXML
    private Button GestionVote;

    @FXML
    private Button GestionPublication;

    @FXML
    private Button GestionCommentaire;
    
    @FXML
    private TextField nomUt;

    @FXML
    private TextField prenomUti;

    @FXML
    private TextField emailut;

    @FXML
    private TextField datena;

    @FXML
    private Button GestionSpo;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    public adminController()
    {
        con = ConnectionUtil.conDB();
    }
    
     
    /* gestion d'utlisateur*/
     @FXML
    public void handleButton1Action(MouseEvent event) throws MessagingException {

        if (event.getSource() == gestionUti) {
            //login here
                 try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/OnBoard.fxml")); 

                    Parent root = (Parent) loader.load();
                    HomeController utcontroller = loader.getController(); 
                    utcontroller.myFunction(emailut.getText());
               
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

    /* gestion de reclamation*/
      public void handleButton2Action(MouseEvent event) throws MessagingException {

        if (event.getSource() == Gestionrec) {
            //login here
                try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/ReclamationAdmin.fxml")); 

                    Parent root = (Parent) loader.load();
                    reclamationAdmin utcontroller = loader.getController(); 
                    utcontroller.myFunction(emailut.getText());
               
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
    }
      public void myFunction( String Text )
        {
            emailut.setText(Text);
            User p1 ; 
            p1=  Recherche(Text); 
            nomUt.setText(p1.getNom());
            prenomUti.setText(p1.getPrenom());
            datena.setText(p1.getDateNa());
              
           
        }
        public User Recherche(String Text)
        {
            try {
              String req="select * from user where email=? "; 
              preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(req);


              preparedStatement.setString(1, Text);
             ResultSet rs = preparedStatement.executeQuery();
              while(rs.next())
              {

                  User p = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(16), rs.getString(8));
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
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
    }
    
}
