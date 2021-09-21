import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Programme principal qui d�marre le jeu de YUM pour un seul joueur.
 * 
 * Une s�rie de 5 d�s est g�n�r� al�atoirement et le joueur a droit
 * a changer les d�s qu'il d�sire � deux reprises � moins qu'il les
 * garde tous.
 * 
 * Par la suite, le programme offre toutes les possibilit�s de points 
 * pouvant �tre jou�s et le joueur d�cide quel est son choix parmi 
 * ces possibilit�s.
 * 
 * Dans le cadre du cours inf111 (voir �nnonc� fourni).
 * 
 * Auteur : Mettez le nom de chaque membre du groupe qui a suffisamment 
 *          contribu� en �criture de code et de commentaires.
 *          
 * Auteur :
 * Auteur :
 * Auteur :
 * Auteur :
 * Auteur :
 * 
 * 
 * Auteur : Pierre B�lisle
 *          
 * Version : Copyright A2021
 */

public class YumVsEtud {

	
	// Les constantes sont d�finies dans le module Constantes.java
	// Si vous en ajoutez, fa�tes-le ici.

	// Permet la saisie de donnée au clavier en mode console.
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args){

		int[] tableDes  = new int[Constantes.NB_DES];
		double[] tableBoolPointage;
		int[][] tableScorePointage;

		// Boucle qui demande à l'utilisateur les dés qu'il souhaite relancer
		for ( int nbTours=0; nbTours<Constantes.NB_TOURS; nbTours++ ) {
			System.out.println("TOUR " + nbTours);
			int nbLancers = 0;

			initTableDes(tableDes); // Lance les dés une première fois
			ModAffichage.afficherDes(tableDes);

			// Le joueur a le droit à 3 lancers
			while (nbLancers < 3) {
				int[] tableDesARelancer = obtenirTableDesARelancer();

				if (tableDesARelancer == null) {
					break;

				} else {
					tableDes = relancerDesChoisis(tableDes, tableDesARelancer);
					ModAffichage.afficherDes(tableDes);
					nbLancers++;
				}
			}
			System.out.println("Tour termine.");

		}

	}

	/*
	 * Écrivez TOUS vos sous-programmes ici. Il y en a entre 15 et 20.
	 */

	// Initialise la valeur des dés (action réalisée à chaque début de tour)
	public static int[] initTableDes(int[] tableDesDe){
		for (int i=0; i<Constantes.NB_DES; i++){
			tableDesDe[i] = Constantes.DES_MIN + (int)(Math.random() * ((Constantes.NB_FACES - Constantes.DES_MIN) + 1));
		}
		return tableDesDe;
	}

	// Récupère les dés à relancer choisis et vérifie si le choix est valide
	public static int[] obtenirTableDesARelancer( ){

		while(true) {
			int desARelancer = demanderInteger(sc, "Entrez les des a relancer (0 pour garder le lancer actuel) :");

			// Si desARelancer est nul, le joueur finit le tour
			if (desARelancer == 0)
				return null;
			else
				if (verifierChoixDesARelancer(desARelancer))
					return intToArray(desARelancer);

			System.out.println("Choix invalide.");
		}
	}

	// Vérifie si le choix de dés à relancer est valide : le choix doit être un integer positif de 5 digits maximum, distincts et compris entre 1 et 5
	public static boolean verifierChoixDesARelancer(int desARelancer) {
		int[] tableDesARelancer = intToArray(desARelancer);

		return desARelancer >= 0 && tableDesARelancer.length <= 5 && !tableADoublons(tableDesARelancer) && !tablePossedeValInvalide(tableDesARelancer);
	}

	// Vérifie si un array contient plusieurs fois la même valeur
	public static boolean tableADoublons(int[] array) {
		for (int i=1; i<array.length; i++)
			if (array[i-1] == array[i])
				return true;
		return false;
	}

	// Vérifie sur un array contient un int inférieur à 1 ou supérieur à 5
	public static boolean tablePossedeValInvalide(int[] array) {
		for (int e : array)
			if (e > 5 || e < 1)
				return true;
		return false;
	}

	// Relance les dés choisis par le joueur
	public static int[] relancerDesChoisis(int[] tableDes, int[] tableDesARelancer){
		for (int i : tableDesARelancer )
			if (i!=0)
				tableDes[i-1] = Constantes.DES_MIN + (int)(Math.random() * ((Constantes.NB_FACES - Constantes.DES_MIN) + 1));
		return tableDes;
	}

	// Transforme un int en array
	public static int[] intToArray(int integer) {
		int[] array = new int[String.valueOf(integer).length()];
		int i = 0;
		while (integer > 0) {
			int n = integer%10;
			integer = integer/10;
			array[i] = n;
			i++;
		}
		return array;
	}

	public static int demanderInteger(Scanner sc, String message) {
		System.out.println(message);
		int integer = -1; // -1 est un integer invalide dans le cadre du programme
		try {
			integer = sc.nextInt();
		} catch (Exception e) {
			sc.nextLine();
		}
		return integer;
	}

}