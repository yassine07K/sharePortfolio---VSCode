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

     Map<Action, Integer> mapLignes;

    public Portefeuille() {
         this.mapLignes = new HashMap<>();
    }

    public boolean acheter(Action a, int q) {
         //ajouter un situation que rejette l`achete de qte <= 0
        if (q <= 0) {
            return false;
        }else{ 
            if (!this.mapLignes.containsKey(a)) {
                this.mapLignes.put(a, q);
                return true;
            } else {
                this.mapLignes.put(a,this.mapLignes.get(a) + q);
                return true;
            }
        }
       
    }

   public void vendre(Action a, int q) {
         if (this.mapLignes.containsKey(a)) {
             if (this.mapLignes.get(a) > q) {
                this.mapLignes.put(a,this.mapLignes.get(a) - q);
             } else if (this.mapLignes.get(a)== q) {
                  this.mapLignes.remove(a);
             }
        }
    }

    public String toString() {
       return this.mapLignes.toString();
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
