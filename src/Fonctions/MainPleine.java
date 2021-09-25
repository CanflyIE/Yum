package Fonctions;

public class MainPleine {

    public static void main(String[] args){

        int[] array = {8, 2, 2, 2, 8};

        boolean isMainPleine = isMainPleine(array);
        System.out.println(isMainPleine);

    }

    /**
     * Vérifie un array contient une main pleine. C'est-à-dire deux valeurs identiques et trois autres valeurs
     * identiques (une paire et un brelan).
     * @param array array à vérifier
     * @return true si array contient une main pleine, sinon false
     */
    public static boolean isMainPleine(int[] array) {
        boolean paire = false;
        boolean brelan = false;

        for (int n : array) {
            int r = 0;
            // On compte le nombre de récurrences de n
            for (int k : array)
                if (n == k)
                    r++;
            if (r == 2)
                paire = true;
            else if (r == 3)
                brelan = true;
            if (paire && brelan)
                return true;
        }
        return false;
    }

}
