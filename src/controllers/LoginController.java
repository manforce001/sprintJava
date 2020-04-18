package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import utils.mail;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import utils.SMS;

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
                    String newPass = f.generatePassword(8).toString(); 
                    System.out.println(newPass);
                    
                      String sql = "update user set password  =? where email=?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, newPass);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
              
             } 
            catch (SQLException ex)
                    {
                        System.err.println(ex.getMessage());

                    }
                    // a.send(email, "test",f.generatePassword(8).toString());

                    a.send("mohamedamin.nasri@esprit.tn", "nouveau passwords",f.generatePassword(8).toString());
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
        /* connexion */
    @FXML
    public void handleButtonAction(MouseEvent event) throws MessagingException {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().contains("ADMIN")) {
               try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/admin.fxml")); 

                    Parent root = (Parent) loader.load();
                    adminController utcontroller = loader.getController(); 
                    utcontroller.myFunction(txtUsername.getText());
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
            else
            {
                /* pour utlisateur*/
                /* changer le lien de fxml*/
            if (logIn().contains("UTILISATEUR"))
            {
                try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/InterfaceUtlisateur.fxml")); 

                    Parent root = (Parent) loader.load();
                    UtlisateurControlleur utcontroller = loader.getController(); 
                    utcontroller.myFunction(txtUsername.getText());
               
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
            /* pour le jury */
            /* changer justement le lien de fxml */
            else 
            { 
                 if (logIn().contains("JURY"))
                  {
                try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/InterfaceUtlisateur.fxml")); 

                    Parent root = (Parent) loader.load();
                    UtlisateurControlleur utcontroller = loader.getController(); 
                    utcontroller.myFunction(txtUsername.getText());
               
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
            /* pour le coatch */
            /* changer justement le lien de fxml */
                 else
                 {
                     if (logIn().contains("COATCH"))
                    {
                        try {
                           FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/InterfaceUtlisateur.fxml")); 

                            Parent root = (Parent) loader.load();
                            UtlisateurControlleur utcontroller = loader.getController(); 
                            utcontroller.myFunction(txtUsername.getText());

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
                     else
                         /* pour le sponsor */
                         /* changer le lien de fxml*/
                     {
                          if (logIn().contains("SPONSOR"))  
                            {
                                try {
                                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/InterfaceUtlisateur.fxml")); 

                                    Parent root = (Parent) loader.load();
                                    UtlisateurControlleur utcontroller = loader.getController(); 
                                    utcontroller.myFunction(txtUsername.getText());

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
                     }
                     
                 }
                
            } 
            
                
            }
         

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } 
        else {
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
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txtUsername.getText());
        System.out.println(email +" : "+ matcher.matches());
        Boolean a =false; 
        String b=RechercheMail(txtUsername.getText()); 
    
        if(a==matcher.matches())
        {
            setLblError(Color.TOMATO, "adresse mail invalid");
            status = "Error";
        }
        
        else 
        {    
                
            if(email.isEmpty() || password.isEmpty()) 
            {   

                setLblError(Color.TOMATO, "Empty credentials");
                status = "Error";
            }
        
        else {
            
            //query
            String sql = "SELECT * FROM user Where email = ? and password = ?  ";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    System.out.println("test role");
                    System.out.println(resultSet.getString(12)); 
                    String role =resultSet.getString(12); 
                    System.err.println("test role");
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    SMS s =new SMS(); 
                    s.envoyer(29216716, "ton compte est connectée");
                    
                     
                    return role; 
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        }
       return "false"; 
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
            String sql = "SELECT * FROM user Where email = ?  ";
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
