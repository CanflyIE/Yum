public class calculScore {

        public int[] updateArrayScore(int pos , int score , int[] arrayFinal){

                arrayFinal[pos] = score;
                
              // Calculer la somme de la partie supérieur si une des cases de cette dérnier à été mise à jour

                if(1<pos && pos<7){
                int sommePartieSup =0;;
                for(int i =1; i<Constantes.SOUS_TOTAL_HAUT;i++){
                    sommePartieSup += arrayFinal[i];
                }

                arrayFinal[Constantes.SOUS_TOTAL_HAUT] = sommePartieSup;
                }

              // Calculer la somme de la partie inférieur si une des cases de cette dérnier à été mise à jour

                else if (9<pos && pos> 17) {
                    int sommePartieinf =0;;
                    for(int i=Constantes.BRELAN; i<Constantes.TOTAL_BAS;i++){
                        sommePartieinf += arrayFinal[i];
                    }
                    
                    arrayFinal[Constantes.TOTAL_BAS] = sommePartieinf;
                }


            return arrayFinal;
        }


        public int[] calculScoreFinal (int[] arrayFinal)
        {
                
            // Calculer la somme de la partie supérieur et rajouter une bonification si ce dérnier est supérieur à 63
           
                arrayFinal[Constantes.BONUS_DU_HAUT] = (arrayFinal[Constantes.SOUS_TOTAL_HAUT]>=63)? 25 : 0;
                arrayFinal[Constantes.TOTAL_HAUT] =  arrayFinal[Constantes.SOUS_TOTAL_HAUT] +  arrayFinal[Constantes.BONUS_DU_HAUT];


            // Calculer la somme de la partie supérieur et rajouter une bonification si ce dérnier est supérieur à 63

                int sommePartieinf =0;;
                for(int i=Constantes.BRELAN; i<Constantes.TOTAL_BAS;i++){
                    sommePartieinf += arrayFinal[i];
                }
                
                arrayFinal[Constantes.TOTAL_BAS] = sommePartieinf;

            // Score finale

                arrayFinal[Constantes.GRAND_TOTAL] = arrayFinal[Constantes.TOTAL_BAS] + arrayFinal[Constantes.TOTAL_HAUT];
                 




            return arrayFinal;
        }


}
