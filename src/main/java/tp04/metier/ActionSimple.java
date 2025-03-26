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

import java.util.HashMap;
import java.util.Map;

public class ActionSimple extends Action {

    private Map<Jour, Cours> mapCours;

    public ActionSimple(String libelle) {
        super(libelle);
        this.mapCours = new HashMap<>();
    }

    // Enregistre ou met à jour le cours
    public void enrgCours(Jour j, float v) {
        // On autorise la mise à jour :
        this.mapCours.put(j, new Cours(j, v));
    }

    @Override
    public float valeur(Jour j) {
        Cours c = this.mapCours.get(j);
        return (c == null) ? 0f : c.getValeur();
    }
}
