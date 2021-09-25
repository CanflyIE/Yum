package Fonctions;

public class Brelan {


    public static void main(String[] args) {
        int[] tab = {1,2,2,2,5};
        System.out.println(verifBrelon(tab));
    }

    public static int verifBrelon(int[] tab){

        int total = 0;

        for (int i: tab) {
            int nombreDeDoublon = 0;
            for ( int y : tab ) {
                if (i==y){
                    nombreDeDoublon++;
                }

            }
            if(nombreDeDoublon >= 3){
                total = i*3;
                break;
            }
        }
            return total;
    }

    }
