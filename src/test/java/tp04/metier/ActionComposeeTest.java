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
import java.beans.Transient;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionComposeeTest {

 

    //Test pour vérifier si la somme des proportions des actions composées est égale à 100 %.
    @Test
    public void testSomeMethod() {
        ActionComposee actionComposee = new ActionComposee("TestActionCompose");
        ActionSimple a1 = new ActionSimple("test1");
        ActionSimple a2 = new ActionSimple("test2");
        Map<K,V> actioncompose = new HashMap();

        
        actioncompose.put(a1,0.5f);        
        actioncompose.put(a2,0.5f);


        float totalPercentage = actionComposee.getFloatAction();
        //Assertions.  
        Assertions.assertEquals(1.0f,totalPercentage,"false!!!!!!!!!!!!!!");

    }
    
     public class ActionExe extends Action {
     public ActionExe(String nomA) {
         super(nomA);
     }
     public float valeur(Jour j) {
         return 0.0F;
     }
  
    }

    @Test


    
}
