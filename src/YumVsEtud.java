import java.util.Arrays;
import java.util.HashMap;
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
	public static Scanner clavier = new Scanner(System.in);

	public static void main(String[] args){



		int[] tableDesDe  = new int[Constantes.NB_DES];
		double[] tableBoolPointage;
		int[][] tableScorePointage;
		HashMap<Integer, int[]> tableDesDeFinal = new HashMap<Integer, int[]>();


		for ( int nombreDeToursJouer=0; nombreDeToursJouer < Constantes.NB_TOURS ; nombreDeToursJouer++ ){
			System.out.println("nouveau tour");
			int nbrDeLancer = 0;
			tableDesDeFinal.clear();
			boolean pasDeRelance = false;

			init(tableDesDe);
			ModAffichage.afficherDes(tableDesDe);
			tableDesDeFinal.put(nbrDeLancer,tableDesDe);

			while ( nbrDeLancer < 3 || pasDeRelance == false ){

			int[] table = testInput();

			if(table == null){

				System.out.println("vous avez fini le tour");
				pasDeRelance = true;
			}else {

				tableDesDe = nouveauTableau(tableDesDe, table);
				ModAffichage.afficherDes(tableDesDe);
				tableDesDeFinal.put(nbrDeLancer, tableDesDe);
				nbrDeLancer++;
			}
			}


		}

	}

	/*
	 * Écrivez TOUS vos sous-programmes ici.  Il y en a entre 15 et 20.
	 */

	// Inialiser
	public static int[] init( int[] tableDesDe){
		for ( int i =0; i< Constantes.NB_DES;i++){
			tableDesDe[i] = Constantes.DES_MIN + (int)(Math.random() * ((Constantes.NB_FACES - Constantes.DES_MIN) + 1));
		}
		return tableDesDe;
	}

	// permet de tester si l'input est bien un input et qu'il est composé seulement de chiffre entre 1 et 6
	public static int[] testInput( ){
		boolean test = true;
		int input = 0;
		int[] inputAModifier = new int[5];

		while(test) {
			try {

				input = clavier.nextInt();

				if(input == 0){
					return null;
				}

				int i = 0 ;
				boolean bool = false;

				while( input%10 !=0 && !bool ) {

					if( 0<(input%10) && (input%10)<6) {
						inputAModifier[i] = input % 10;
						input = input / 10;
						i++;
					}
					else{
						bool= true;
					}
				}

				if(!bool && !verifLesDoublons(inputAModifier)  ){
					break;

				}

			} catch (Exception e) {
				System.out.println("Il faut entrer un nombre entre 1 et 5");

			}
		}
		return inputAModifier ;
	}

	// Remplace les dés séléctionner par le joueur
	public static int[] nouveauTableau(int[] ancienneTable , int[] valeurAChanger){
		for (int i: valeurAChanger ) { if ( i!=0 ){
			ancienneTable[i-1] = Constantes.DES_MIN + (int)(Math.random() * ((Constantes.NB_FACES - Constantes.DES_MIN) + 1)); }
		}
		return ancienneTable;
	}

	// Verifie qu'il y a pas de doublons dans le chiffre entré par l'utilisateur
	public static boolean verifLesDoublons(int[] n){
		for (int i: n) {
			if( i !=0 ) {
				int nbrDeDoublon = 0;
				for (int y : n) {
					if (i == y) {
						nbrDeDoublon++;
					}
				}
				if (nbrDeDoublon > 1) {
					return true;

				}
			}
		}
		return false;
	}


}