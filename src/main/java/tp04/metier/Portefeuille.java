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
    private String nomPortefeuille;
    Map<Action, Integer> mapLignes;
    static int indexP = 1;

    public Portefeuille(String nomP) {
        this.idPortefeuille = indexP++;
        this.nomPortefeuille = nomP;
        this.mapLignes = new HashMap<>();
    }

    public Portefeuille() {
        this.idPortefeuille = indexP++;
        this.mapLignes = new HashMap<>();
    }

    public int getIdPortefeuille() {
        return idPortefeuille;
    }

    public String getNomPortefeuille() {
        return nomPortefeuille;
    }

    public Map<Action, Integer> getMapLignes() {
        return mapLignes;
    }

    public void setnomPortefeuille(String nomPortefeuille) {
        this.nomPortefeuille = nomPortefeuille;
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
        return this.nomPortefeuille+this.mapLignes.toString();
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
