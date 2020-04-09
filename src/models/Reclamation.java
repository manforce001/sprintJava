/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nasri
 */
public class Reclamation extends RecursiveTreeObject<Reclamation>
{
 
      
    private int id;
    private int id_user; 
    StringProperty sujet , reclamation ,etat ,reponse;

    public Reclamation(int id, int id_user, StringProperty sujet, StringProperty reclamation, StringProperty etat, StringProperty reponse) {
        this.id = id;
        this.id_user = id_user;
        this.sujet = sujet;
        this.reclamation = reclamation;
        this.etat = etat;
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public StringProperty getSujet() {
        return sujet;
    }

    public void setSujet(StringProperty sujet) {
        this.sujet = sujet;
    }

    public StringProperty getReclamation() {
        return reclamation;
    }

    public void setReclamation(StringProperty reclamation) {
        this.reclamation = reclamation;
    }

    public StringProperty getEtat() {
        return etat;
    }

    public void setEtat(StringProperty etat) {
        this.etat = etat;
    }

    public StringProperty getReponse() {
        return reponse;
    }

    public void setReponse(StringProperty reponse) {
        this.reponse = reponse;
    }

    public Reclamation(String sujet , String reclamation, String etat, String reponse)
    {
        this.sujet=new SimpleStringProperty(sujet);
        this.reclamation=new SimpleStringProperty(reclamation);
        this.etat=new SimpleStringProperty(etat);
        this.reponse=new SimpleStringProperty(reponse); 
    }
    
    
 
}
