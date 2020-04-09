

package controllers;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;

/**
 *
 * @author Nasri
 */

public class registerController implements Initializable
{
    @FXML
    private TextField txtFirstname;
    
     @FXML
    private TextField txtPassword;
    
    

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txttele; 
    
    
    @FXML
    private DatePicker txtDOB;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> txtGender;
    
      @FXML
    private Label lblStatus;

    
     PreparedStatement preparedStatement;
    Connection connection;
    public registerController()
    {
        connection = (Connection) ConnectionUtil.conDB();
    
    }

    
     @FXML
    private void HandleEvents(MouseEvent event) 
    {
         if (event.getSource() == btnSave)
          
         {
             System.out.println("test register valider");
             String s=  saveData();
             System.out.println(s);
             if ("Success".equals(s))
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
             else 
             {
                  
                 
             }
             
                     

         }
        //check if not empty
    }
    private String ControleSaisir(String nom ,String prenom, String email , String role, String telephone ,String DateNa , String Passord)
    {
        
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        System.out.println(email +" : "+ matcher.matches());
        Boolean a =matcher.matches(); 
       
    
       

            if(nom.isEmpty())
            {
              return (" le nom est vide ");    
            }
            else{
                if(prenom.isEmpty())
                {
                    return (" le prenom est vide"); 
                }
                else
                    if(email.isEmpty())
                    {
                        return("email est vide "); 
                    }
                    else
                    {
                        if(role.isEmpty())
                        {
                            return (" role est vide "); 
                        }
                        else
                        {
                            if(telephone.isEmpty())
                            {
                                return ("email est vide"); 
                            }
                            else 
                            {
                                if(DateNa.isEmpty())
                                {
                                    return ("date nasiisance  est vide "); 
                                }
                                else
                                {
                                    if (Passord.isEmpty())
                                    {
                                        return (" password est vide"); 
                                    }
                                }
                            }
                            
                        }
                        
                    }
                
                
                        
                    
                
            }
    return "false";    
    }
    private String saveData() 
    {
           
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txtEmail.getText());
       
        Boolean a =matcher.matches(); 
      
        if(txtFirstname.getText().isEmpty())
            {
                lblStatus.setTextFill(Color.WHITE);
                lblStatus.setText("le nom est vide ");
              return (" le nom est vide ");    
            }
            else{
                if(txtLastname.getText().isEmpty())
                {
                      lblStatus.setTextFill(Color.WHITE);
                      lblStatus.setText("le prenom est vide ");
                      return (" le prenom est vide"); 
                }
                else
                    if(txtEmail.getText().isEmpty())
                    {
                          lblStatus.setTextFill(Color.WHITE);
                          lblStatus.setText("le email est vide ");
                        return("email est vide "); 
                    }
                    else
                    {
                        if(txtGender.getValue().toString().isEmpty())
                        {
                          lblStatus.setTextFill(Color.WHITE);
                          lblStatus.setText("le role est vide ");
                            return (" role est vide "); 
                        }
                        else
                        {
                            if(txttele.getText().isEmpty())
                            {
                               lblStatus.setTextFill(Color.WHITE);
                               lblStatus.setText("le telephone est vide ");
                                return ("email est vide"); 
                            }
                            else 
                            {
                                if(txtDOB.getValue().toString().isEmpty())
                                {
                                    lblStatus.setTextFill(Color.WHITE);
                                     lblStatus.setText("la date naissance  est vide ");
                                    return ("date nasiisance  est vide "); 
                                }
                                else
                                {
                                    if (txtPassword.getText().isEmpty())
                                    {
                                            lblStatus.setTextFill(Color.WHITE);
                                            lblStatus.setText("password  vide ");
                                        return (" password est vide"); 
                                    }
                                    else 
                                    {
                                        if(a==false)
                                        {
                                            lblStatus.setTextFill(Color.WHITE);
                                            lblStatus.setText("email n'est pas valide ");
                                             return (" password est vide"); 
                                        }
                                    }
                                }
                            }
                            
                        }
                        
                    }
                
                
                        
                    
                
            }
        String s =txtPassword.getText(); 
        try {
            String st = "INSERT INTO  user ( nom , prenom, email, role, telephone , dateNa, password) VALUES (?,?,?,?,?,?,?)";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txtFirstname.getText());
            preparedStatement.setString(2, txtLastname.getText());
            preparedStatement.setString(3, txtEmail.getText());
            preparedStatement.setString(4, txtGender.getValue().toString());
            preparedStatement.setString(5, txttele.getText());
            
            preparedStatement.setString(6, txtDOB.getValue().toString());
            preparedStatement.setString(7, s);

            

            preparedStatement.executeUpdate();
         //   lblStatus.setTextFill(Color.GREEN);
           // lblStatus.setText("Added Successfully");
            System.out.println("insertion terminer ");
            return "Success";

        } 
        catch (SQLException ex) {
           // System.out.println(ex.getMessage());
           // lblStatus.setTextFill(Color.TOMATO);
            //lblStatus.setText(ex.getMessage());
            return "Exception";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
         txtGender.getItems().addAll("admin", "jury", "coatch","utlisateur");
         txtGender.getSelectionModel().select("utlisateur");
    }

    

    
    
    
    
    
    
}



