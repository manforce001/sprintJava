/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Nasrii
 */
public class User 
{
    private int id;
    private String nom;
    private String prenom; 
    private String email ; 
    private String role; 
    private String  telephone ; 
    private String dateNa; 
    private String password; 
    public User()
    {
        
    }
    public User(int id, String nom, String prenom, String email, String role, String telephone, String dateNa, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.telephone = telephone;
        this.dateNa = dateNa;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDateNa() {
        return dateNa;
    }

    public void setDateNa(String dateNa) {
        this.dateNa = dateNa;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", role=" + role + ", telephone=" + telephone + ", dateNa=" + dateNa + ", password=" + password + '}';
    }

 
    
    
    
}
