package Fonctions;

public class Occurence {

	public static void main(String args[]) {

		int tab []= {4,6,6,4,4};
		int []resultat=recurence(tab);
		for(int i : resultat) {
			System.out.println(i + " fois");	
		}
		
	}

	public static int [] recurence(int tab[]) {
		int compteur=0;
		int []tableauVerification= new int [6]; 
		for (int i=1; i<tab.length+2;i++) {
			compteur=0;
			System.out.print(i);
			for(int x=0; x< tab.length; x++) {
				if(i==tab[x]) {
					compteur++;	
				}		
			}
			tableauVerification[i-1]=compteur;
		}
		return tableauVerification;
	}

}
