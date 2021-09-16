package Fonctions;

public class MainPleine {

    public static void main(String[] args){

        int[] array = {8, 2, 2, 2, 8};

        boolean isMainPleine = isMainPleine(array);
        System.out.println(isMainPleine);

    }

    // Procédure qui vérifie si @array comprend une main pleine
    public static boolean isMainPleine(int[] array) {
        // Une main pleine est composée d'une paire et d'un brelan
        boolean paire = false;
        boolean brelan = false;

        for (int i=0;i<array.length;i++) {
            int n = array[i];
            int r = 0;
            // On compte le nombre de récurrences de n
            for (int k : array) {
                if (n == k) {
                    r++;
                }
            }

            if (r == 2) {
                paire = true;
            } else if (r == 3) {
                brelan = true;
            }

            if (paire && brelan) {
                return true;
            }
        }

        return false;
    }

}
