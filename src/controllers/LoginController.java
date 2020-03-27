package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import service.forgetPassword;
import utils.ConnectionUtil;

/**
 *
 * @author oXCToo
 */
public class LoginController implements Initializable {

    @FXML
    private ComboBox<String> txtGender;
    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;
    
    @FXML
    private Button btnSignup;
   
    @FXML
    private Button btnSignin2;
    
    @FXML
    private Button Forgot;
    
    

    /// -- 
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
     public void ForgotAction (MouseEvent event) throws MessagingException 
     {
        String email=txtUsername.getText();
        
         if (email.isEmpty() )
         {
               setLblError(Color.TOMATO, "Enter votre  Email");
                 
         }
         else 
         {
             if ("Success".equals(RechercheMail(email)))
             {
                    mail a =new mail(); 
                    forgetPassword f = new forgetPassword(); 
                    /*
                    apres réalisation d'expression reguliere de l email
                     a.send(email, "test",f.generatePassword(8).toString());

                    */
                     

                    
                    a.send("nasr22188@gmail.com", "test",f.generatePassword(8).toString());
                    lblErrors.setTextFill(Color.GREEN);
                    lblErrors.setText("une nouvelle password sera envoyée a ton adresse mail ");
             }
             else 
             {
                        setLblError(Color.TOMATO, "cette email n'existe pas ");

             }
                
                 
         } 
         
 
                    

     }

        
        public void handleButton2Action (MouseEvent event) 
    {
       if (event.getSource() == btnSignup)
       {
           System.out.println("test valider");
            try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/register.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
       }
       
    }
        
    @FXML
    public void handleButtonAction(MouseEvent event) throws MessagingException {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("admin")) {
                try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/OnBoard.fxml")));
                    stage.setScene(scene);
                    stage.show();
                  
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            txtGender.getItems().addAll("admin", "jury", "coatch","utlisateur");
            txtGender.getSelectionModel().select("utlisateur");
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }

    public LoginController() {
        con = ConnectionUtil.conDB();
    }

    //we gonna use string to check for status
    private String logIn() {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        String role = txtGender.getValue(); 
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM admin Where email = ? and password = ? and role = ? ";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, role);

                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return role;
    }
    
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    
    /* verifier si l adresse mail existe ou non */
     private String RechercheMail (String email ) 
     {
        String status = "Success";
      
            //query
            String sql = "SELECT * FROM admin Where email = ?  ";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
         

                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) 
                {
                    status = "Error";
                } 
                else 
                {
                   status = "Success"; 
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        
        
        return status;
    }
    
    
    
}