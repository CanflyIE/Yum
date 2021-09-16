package Fonctions;

public class Occurence {

	public static void main(String args[]) {

		int tab []= {1,2,3,4,4};
		int resultat=recurence(tab);
		System.out.println(resultat + " fois");
	}

	public static int recurence(int tab[]) {
		boolean flag=true;
		int compteur=0;
		int valeurRecuperer=0;

		for (int i=0; i<tab.length-1;i++) {

			if(tab[i]==tab[i+1]) {
				valeurRecuperer=tab[i];
				compteur++;
				flag=false;
			}
		}
		if(flag==false) {
			compteur++;
			System.out.println("j'ai cette valeur duplique : "+ valeurRecuperer);
		}
		else 
			compteur=0;
		return compteur;
	}

}
