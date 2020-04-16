/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.MessagingException;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author oXCToo
 */
public class HomeController implements Initializable {

    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField  txtEmail1;  
    @FXML
    private DatePicker txtDOB;
    @FXML
    private Button btnSave;
    @FXML
    private ComboBox<String> txtGender;
    @FXML
    Label lblStatus;
    @FXML
    private TextField searchtxt;

    @FXML
    private Button search;

    @FXML
    private Button refresh;
    @FXML
    TableView tblData;
    
      @FXML
    private Label email;

    /**
     * Initializes the controller class.
     */
    PreparedStatement preparedStatement;
    Connection connection;

    public HomeController() 
    {
        connection = (Connection) ConnectionUtil.conDB();
    }
     public void myFunction( String Text )
        {
            email.setText(Text);
        }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        txtEmail.setEditable(false);
        txtGender.getItems().addAll("Male", "Female", "Other");
        txtGender.getSelectionModel().select("Male");
    if( "".equals(searchtxt.getText()))    
    {
        fetRowList();
    }
        searchtxt.textProperty().addListener(
    (observable, oldvalue, newvalue) -> 
            searchfetRowList(newvalue)
       
    );
      
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                     tblData.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showPersonDetails(newValue));  
               fetColumnList();
                fetRowList();
                    }

                });

            }

        }, 500);
   

    }
     private void showPersonDetails(Object person) {
    if (person != null) {
        // Fill the labels with info from the person object.
         
        
        String chaine=person.toString(); 
        chaine=  chaine.substring(1, chaine.length()-1); 
        
        String[] arrOfStr = chaine.split(","); 
        String[] t = new String [100]; 
        int i=0; 
		for (String a : arrOfStr) 
                {
                     t[i]=a; 
                     i++; 
                    
                }
                        
     txtFirstname.setText(t[12]);
     txtLastname.setText(t[13]);
     txtEmail.setText(t[16].substring(1, t[3].length()));
     txtGender.setValue(t[11]);
     txtEmail1.setText(t[14]); 
        String chaine2=  t[15].substring(1, t[15].length()); 
        String[] date = chaine2.split("-"); 
        String[] tt = new String [100]; 
        int j=0; 
		for (String a : date) 
                {
                     tt[j]=a; 
                     j++; 
                    
                }
       
	int jour  = Integer.parseInt(tt[0]);
        int mois  = Integer.parseInt(tt[1]);
        int annee  = Integer.parseInt(tt[2]);
   
    txtDOB.setValue(LocalDate.of(jour, mois, annee));

    
    }
    else 
    {
        // Person is null, remove all the text.
     
    }
    }
    
    
    
   @FXML
    void searchEvents(MouseEvent event) 
    {
        System.out.println("test valider");
       searchfetRowList(searchtxt.getText());
       
        
      
    }
    
    @FXML
    void refreshEvents(MouseEvent event) 
    {
fetRowList(); 
    }
    @FXML
    private void HandleEvents(MouseEvent event) {
        //check if not empty
        if (txtEmail.getText().isEmpty() || txtFirstname.getText().isEmpty() || txtLastname.getText().isEmpty() || txtDOB.getValue().equals(null)) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        } else {
            saveData();
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

     /* delete*/
      @FXML
    private void HandleDeleteEvents(MouseEvent event) {
        //check if not empty
        if (txtEmail.getText().isEmpty() || txtFirstname.getText().isEmpty() || txtLastname.getText().isEmpty() || txtDOB.getValue().equals(null)) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        } else {
            deleteData();
        }

    }
    /* delete */
    private String deleteData() {

        try {
            String st = "delete from user  where email=? ";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txtEmail.getText());
            preparedStatement.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Added Successfully");

            fetRowList();
            //clear fields
            clearFields();
            return "Success";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());
            return "Exception";
        }
    }
    
    private void clearFields() {
        txtFirstname.clear();
        txtLastname.clear();
        txtEmail.clear();
    }

    private String saveData() {

        try {
            String st = "UPDATE user  set  nom=? , prenom=?, roles=?, telephone=? , dateNa=? where email=? ";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txtFirstname.getText());
            preparedStatement.setString(2, txtLastname.getText());
            preparedStatement.setString(3, txtGender.getValue().toString());
            preparedStatement.setString(4, txtEmail1.getText());
            preparedStatement.setString(5,txtDOB.getValue().toString());
            preparedStatement.setString(6, txtEmail.getText());
            
          



            

            preparedStatement.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Added Successfully");

            fetRowList();
            //clear fields
            clearFields();
            return "Success";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());
            return "Exception";
        }
    }

    private ObservableList<ObservableList> data  ;
        String SQL = "SELECT * from user";

    //only fetch columns
    private void fetColumnList() 
    {
        
        
        
        
        
        
        
        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL);

            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 11; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                              public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {

                    try 
                    {   if (param.getValue().get(j).toString().contains("JURY"))
                        
                            {
                                 return new SimpleStringProperty("JURY");
                            }
                         else
                            {
                           if (param.getValue().get(j).toString().contains("SPONSOR"))
                        
                            {
                                   return new SimpleStringProperty("sponsor");
                            } 

                            }
                    
                           
                      
                         return new SimpleStringProperty(param.getValue().get(j).toString());
                    
                     }
                     catch (Exception ex)
                     {
                         System.err.println("ereur");
                     }
                  
                    return new SimpleStringProperty(); 
                     
                                
                    
                    }
                });

                tblData.getColumns().removeAll(col);
                tblData.getColumns().addAll(col);

                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    //fetches rows and data from the list
  private void fetRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
            
                ObservableList row = FXCollections.observableArrayList();
                  
               
                    
                
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
             }
            

            tblData.setItems(data);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
  private void searchfetRowList(String arg) {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
            
                ObservableList row = FXCollections.observableArrayList();
                  System.out.println("testcrud ");

                  System.out.println(rs.getString(1));
                if ((rs.getString(14)).contains(arg))
                {
                    
                
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
             }
            

            tblData.setItems(data);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @FXML
    private void Handle5Events(MouseEvent event) 
    {
       try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/admin.fxml")); 

                    Parent root = (Parent) loader.load();
                    adminController utcontroller = loader.getController(); 
                    utcontroller.myFunction(email.getText());
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
    
}
