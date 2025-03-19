package tp04.metier;

import java.util.ArrayList;

public class Client {


    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String codePostal;
    private String telephone;
    private String email;
    private String dateNaissance;
    private String password;
    private ArrayList<Portefeuille> portefeuilles;
    private boolean connected;


    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.portefeuilles = new ArrayList<>();
        this.connected = false;
    }

    public String getNom() {return nom;}
    public String getPrenom() { return prenom;}
    public String getAdresse() {return adresse;}
    public String getVille() {return ville;}
    public String getCodePostal() {return codePostal;}
    public String getTelephone() {return telephone;}
    public String getEmail() {return email;}
    public String getDateNaissance() {return dateNaissance;}
    public String getPassword() {return this.password;}
    public void setNom(String nom) {this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setAdresse(String adresse) {this.adresse = adresse;}
    public void setVille(String ville) {this.ville = ville;}
    public void setCodePostal(String codePostal) {this.codePostal = codePostal;}
    public void setTelephone(String telephone) {this.telephone = telephone;}
    public void setEmail(String email) {this.email = email;}
    public void setDateNaissance(String dateNaissance) {this.dateNaissance = dateNaissance;}
    public void setPassword(String password) {this.password = password;}
    public void setConnected(){this.connected = true;}
    public void setDisconnected(){this.connected=false;}
    public boolean isConnected(){
       return this.connected;
    }

    public void AcheterPortefueille(Portefeuille portefeuille){
        portefeuilles.add(portefeuille);
    }

    @Override
    public String toString() {
        return "Client{" + "nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", ville=" + ville + ", codePostal=" + codePostal + ", telephone=" + telephone + ", email=" + email + ", dateNaissance=" + dateNaissance + ", Password=" + password + '}';
    }


}



