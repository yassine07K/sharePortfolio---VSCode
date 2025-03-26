/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class Portefeuille {

    private int idPortefeuille;
    Map<Action, Integer> mapLignes;
    static int indexP = 1;
    private String titrePortefeuille;
    private Client proprietaire;

    /* 
     * Classe interne LignePortefeuille (utile seulement dans le cas de cette classe)
    */

    private class LignePortefeuille {

        private Action action;
        private int qte;
       
        public int getQte() {
            return qte;
        }

        public void setQte(int qte) {
            this.qte = qte;
        }

        public Action getAction() {
            return this.action;
        }

        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }

        public String toString() {
            return Integer.toString(qte);
        }
    }


    /*
     * Constructeur et méthodes de la classe principal Portfeuille
     */

    public Portefeuille() {
        this.idPortefeuille = indexP++;
        this.mapLignes = new HashMap<>();
    }
    
    public Portefeuille(String titre) {
        this.idPortefeuille = indexP++;
        this.mapLignes = new HashMap();
        if (titre == null){
            throw new IllegalArgumentException("Le titre ne peut être null");
        }
        if (titre.trim().isEmpty()){
            throw new IllegalArgumentException("Le titre ne peut être vide");
        }
        this.titrePortefeuille = titre; 
    } 

    public Map<Action, Integer> getMapLignes() {
        return mapLignes;
    }

    public int getIdPortefeuille() {
        return idPortefeuille;
    }
    
    public Client getProprietaire(){
        return this.proprietaire;
    }

    public void setProprietaire(Client proprietaire){
        for (Portefeuille portefeuille : proprietaire.getListePortefeuilles()) {
            if (portefeuille.getTitre().equals(this.titrePortefeuille)) {
                throw new IllegalArgumentException("Ce titre existe parmi vos portefeuilles");
            }
        }
        
        this.proprietaire = proprietaire;
    }

    public String getTitre(){
        return this.titrePortefeuille;
    }

    public void setTitre(String newTitle){
        this.titrePortefeuille = newTitle;
    }

    public void setMapLignes(Map<Action, Integer> mapLignes) {
        this.mapLignes = mapLignes;

    }

    public void acheter(Action a, int q) {
        if(a == null){
            throw new IllegalArgumentException("Action ne peut pas être null.");
        }else if(q > 0){
            if (!this.mapLignes.containsKey(a)) {
                this.mapLignes.put(a, q);
            } else {
                this.mapLignes.put(a,this.mapLignes.get(a) + q);
            }
        }
        
    }

    public void vendre(Action a, int q) {
        if (a == null) {
            throw new IllegalArgumentException("Action ne peut pas être null.");
        }else{
            if (this.mapLignes.containsKey(a)) {
                if (this.mapLignes.get(a) > q) {
                    this.mapLignes.put(a,this.mapLignes.get(a) - q);
                } else if (this.mapLignes.get(a)== q) {
                    this.mapLignes.remove(a);
                }
            }
        }
       
    }

    public String toString() {
        return this.titrePortefeuille+this.mapLignes.toString();
    }

    public float valeur(Jour j) {
        float total = 0;
        Iterator i = this.mapLignes.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<Action, Integer> entry = (Map.Entry<Action, Integer>) i.next();
            total = total + (entry.getValue() * entry.getKey().valeur(j));
        }
        return total;
    }
}
