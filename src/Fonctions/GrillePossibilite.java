/**
 * cette classe gere les differentes possibilite de coups
 * pour le joueur. 
 * Il a le choix entre BRELAN;CARRE;YUM;
 * PETITE SUITE; GRANDE SUITE ET ROULEMENT DE SURPLUS
 * @author Aïcha Berthe
 * @version 1.0
 */
package Fonctions;
import Fonctions.Occurence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import Fonctions.MainPleine;
public class GrillePossibilite {

	public static void main(String[] args){

		Occurence occurrence= new Occurence();	
		int tab []= {4,6,4,4,6};
		double [] tableauVerification=  {0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0};
		int [] possibilite= TraitementDePossibilite(tab,tableauVerification );
		for(int i=0; i<possibilite.length;i++) {
			System.out.println(i+" "+possibilite[i]);	
		}
	}

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
	/**
	 * 
	 * @param tableauDes
	 * @return
	 * @author Aïcha Berthe Ilyes Essid
	 */

	public static int  [] TraitementDePossibilite(int []tableauDes,double []tableauVerification ) {

		boolean main=isMainPleine(tableauDes); 
		Map<String, Boolean> res = isSuite(tableauDes);
		int []tableau = Occurrence(tableauDes);
		int []tableauPossibilite= new int [19];
		for(int i=0; i<tableau.length; i++) {
			if(tableau[i]>1) {
				tableauPossibilite[i+1]=(i+1)*tableau[i];
			}
			if((tableau[i]==3)&&(tableauVerification[i]==-1)) {
				tableauPossibilite[10]=(i+1)*tableau[i];

			}
			if((tableau[i]==4)&&(tableauVerification[i]==-1)) {
				tableauPossibilite[11]=(i+1)*tableau[i];

			}
			if((tableau[i]==5)&&(tableauVerification[i]==-1)) {
				tableauPossibilite[16]=30;
			}	
		}

		if((res.get("suite5")==true)&&(tableauVerification[13]==-1)) {  
			tableauPossibilite[13]=15;
		}
		else if((res.get("suite4")==true)&&(tableauVerification[14]==-1)) {  
			tableauPossibilite[14]=20;
		}
		if((main==true)&&(tableauVerification[12]==-1)){
			tableauPossibilite[12]=25;
		}
		if((tableauVerification[15]==-1)) {
			tableauPossibilite[15]= somme(tableauDes);	
		}



		return tableauPossibilite;
	}
	private static int[] Occurrence(int[] tableauDes) {
		// TODO Auto-generated method stub
		int compteur=0;
		int []tableauVerification= new int [6]; 
		for (int i=1; i<tableauDes.length+2;i++) {
			compteur=0;
			for(int x=0; x< tableauDes.length; x++) {
				if(i==tableauDes[x]) {
					compteur++;	
				}		
			}
			tableauVerification[i-1]=compteur;
		}
		return tableauVerification;

	}

	public static int somme( int [] tab ) {
		int somme=0;
		for ( int i=0; i<tab.length;i++) {
			somme +=tab[i];
		}
		return somme;
	}


	public static boolean isMainPleine(int[] array) {
		// Une main pleine est composÃ©e d'une paire et d'un brelan
		boolean paire = false;
		boolean brelan = false;

		for (int n : array) {
			int r = 0;
			// On compte le nombre de rÃ©currences de n
			for (int k : array) {
				if (n == k)
					r++;
			}

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
