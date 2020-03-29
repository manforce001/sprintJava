/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Nasri
 */
public class UtlisateurControlleur   implements Initializable
{
    @FXML
    private Label nomutlisateur;

    @FXML
    private Label email;

    @FXML
    private Button btndeconnextion;

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {

    }
     public void myFunction( String Text )
        {
            nomutlisateur.setText(Text);
        }
     
    
}
