package Fonctions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Suite {

    public static void main(String[] args){

        int[] array = {3, 2, 1, 4, 5};

        Map<String, Boolean> isSuite = isSuite(array);

    }

    /**
     * Vérifie si un array contient une suite de quatre ou cinq integer.
     * @param array à vérifier
     * @return Map<String, Boolean> de longueur deux. Exemple si array contient une suite de quatre mais pas de suite
     * de cinq : {suite5=false, suite4=true}
     */
    public static Map<String, Boolean> isSuite(int[] array) {
        Map<String, Boolean> suites = new HashMap<String, Boolean>();
        int[] tmpArray = Arrays.copyOf(array, array.length);
        Arrays.sort(tmpArray);
        int cpt = 0;
        for (int i=1;i<tmpArray.length;i++)
            if (tmpArray[i-1] + 1 == tmpArray[i])
                cpt++;
        if (cpt >= 3)
            suites.put("suite4", true);
        else
            suites.put("suite4", false);
        if (cpt == 4)
            suites.put("suite5", true);
        else
            suites.put("suite5", false);

        return suites;
    }

}
