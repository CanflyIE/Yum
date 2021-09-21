package Fonctions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Suite {

    public static void main(String[] args){

        int[] array = {3, 2, 1, 4, 5};

        Map<String, Boolean> isSuite = isSuite(array);

    }

    // Procédure qui vérifie si @array comprend une suite de 4 ou de 5
    public static Map<String, Boolean> isSuite(int[] array) {
        Map<String, Boolean> suites = new HashMap<String, Boolean>();
        Arrays.sort(array);
        int cpt = 0;

        for (int i=1;i<array.length;i++) {
            if (array[i-1] + 1 == array[i])
                cpt++;
        }

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
