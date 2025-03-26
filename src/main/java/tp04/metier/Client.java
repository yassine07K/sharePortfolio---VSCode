/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

import java.util.ArrayList;

import javax.sound.sampled.Port;

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
    public Portefeuille getPortefeuille(int index){return this.portefeuilles.get(index);}
    public ArrayList<Portefeuille> getPortefeuilles(){return this.portefeuilles;}

    public void AcheterPortefueille(Portefeuille portefeuille){
        portefeuilles.add(portefeuille);
    }

    public int getLocalisationPortefeuilleParNom(String nomP){
        
        for(Portefeuille exp : this.portefeuilles){
            if(exp.getNomPortefeuille().equals(nomP)){
                return this.portefeuilles.indexOf(exp);
            }
        }return -1;
    }

    public boolean creerPortefeuille(String nomPortefeuille){
        Portefeuille p = new Portefeuille(nomPortefeuille);
        return this.portefeuilles.add(p);
    }

    public boolean removePortefeuille(String nomPortefeuille){
        int index = getLocalisationPortefeuilleParNom(nomPortefeuille);
        if(index!=-1){
            this.portefeuilles.remove(index);
            return true;
        }return false;
    }

 

    public boolean acheter(String nomPortefeuille,Action a,int q){
        int index = getLocalisationPortefeuilleParNom(nomPortefeuille);
        if(index != -1 && q > 0){
            this.portefeuilles.get(index).acheter(a,q);
            return true;
        }else{
            return false;
        }
        
    }
        

    public boolean existeAction(String nomP,Action a){
        int index = getLocalisationPortefeuilleParNom(nomP);
        if(index == -1){
            return false;
        }else{
            return this.portefeuilles.get(index).getMapLignes().containsKey(a);
        }
    }
    


    public String toString(){
        return "Client : "+this.nom+" "+this.prenom+" "+this.portefeuilles;
    }


}



