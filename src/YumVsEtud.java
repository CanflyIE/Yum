import java.util.*;

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
 * Auteur : Thomas Saudemont
 * Auteur : Aicha Fadia
 * Auteur : Ilyes Essid
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

	/**
	 * @author Thomas Saudemont
	 * @author Ilyes Essid
	 */
	public static void main(String[] args){

		int[] tableDes  = new int[Constantes.NB_DES];
		int[] tablePointage = new int[Constantes.NB_CASES];
		int[] feuillePointage = {0,-1,-1,-1,-1,-1,-1,0,0,0,-1,-1,-1,-1,-1,-1,-1,0,0} ;


		boolean quitter = false;
		do {
			feuillePointage = new int[]{0, -1, -1, -1, -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, 0, 0};
			tablePointage = new int[Constantes.NB_CASES];
			tableDes  = new int[Constantes.NB_DES];

			// Boucle qui demande à l'utilisateur les dés qu'il souhaite relancer
			int nbTours=0;
			while (feuillePointage[0] == 0 && nbTours<Constantes.NB_TOURS) {
				System.out.println("TOUR " + nbTours);
				int nbLancers = 0;
				initTableDes(tableDes); // Lance les dés une première fois
				ModAffichage.afficherDes(tableDes);
				// Le joueur a le droit à 3 lancers
				while (nbLancers < 3) {
					int[] tableDesARelancer = obtenirTableDesARelancer();
					if (tableDesARelancer == null)
						nbLancers = 4;
					else {
						tableDes = relancerDesChoisis(tableDes, tableDesARelancer);
						ModAffichage.afficherDes(tableDes);
						nbLancers++;
					}
					tablePointage =  TraitementDePossibilite(tableDes,feuillePointage);
					ModAffichage.afficherGrillePossibilite(tablePointage);
				}

				// Choix de la feuille de pointage
				int choix = -1;
				while(!verifierLeChoix(tablePointage ).contains(choix)) {
					if (verifierLeChoix(tablePointage ).isEmpty())
						break;
					choix = demanderInteger(sc, "(1,6) ou 10 = Brelan , 11 = carre , 12 = Main pleine , 13 = Petite , 14 = Grosse, 15 = surplus , 16 Yum ");
					if (choix == 0) {
						feuillePointage[0] = -1;
						break;
					}

				}
				// Pointage
				if (choix != -1) {
					feuillePointage = updateArrayScore(choix,tablePointage[choix],feuillePointage);
					feuillePointage = calculScoreFinal(feuillePointage);
				}

				ModAffichage.afficherGrille(feuillePointage);
				System.out.println(Arrays.toString(feuillePointage));
				nbTours++;
			}

			System.out.println("\n\n Voulez-vous recommencer ? (oui OU non)");
			String rep = sc.next();

			if (!rep.equals("oui"))
				quitter = true;

		} while(!quitter);



	}

	/*
	 * Écrivez TOUS vos sous-programmes ici. Il y en a entre 15 et 20.
	 */

	/**
	 * Initialise un array de d'integer, représentant les dés, avec des valeurs aléatoires correspondantes au nombre de
	 * faces du dé.
	 * @param tableDes tableau d'integer à initialiser
	 */
	public static void initTableDes(int[] tableDes){
		for (int i=0; i<Constantes.NB_DES; i++)
			tableDes[i] = Constantes.DES_MIN + (int)(Math.random() * ((Constantes.NB_FACES - Constantes.DES_MIN) + 1));
	}

	/**
	 * Verifie si le choix de coup de l'utilisateur est possible ou non.
	 * @Author Ilyes Essid
	 * @param tableauPossiblite tableau qui contient le coup possibles
	 * @return un boolean qui indique si oui ou non le coup et possible
	 */
	public static List<Integer> verifierLeChoix( int[] tableauPossiblite){

		List<Integer> choixPossible = new ArrayList<>();
		for (int i=0; i<tableauPossiblite.length;i++){
			if (tableauPossiblite[i] != 0){
				choixPossible.add(i);
			}
		}

		return choixPossible ;
	}


	/**
	 * Demande à l'utilisateur un integer dont chaque chiffre correspond à un dé qu'il souhaite relancer.
	 * @author Thomas Saudemont
	 * * @return un array d'integer correspondant à l'integer entré par l'utilisateur (un chiffre par case de
	 * l'array) OU null si l'utilisateur ne souhaite pas relancer de dé
	 */
	public static int[] obtenirTableDesARelancer( ){
		while(true) {
			int desARelancer = demanderInteger(sc, "Entrez les des a relancer (0 pour garder le lancer actuel) :");
			int[] tableDesARelancer = intToArray(desARelancer);

			// Si desARelancer est nul, le joueur finit le tour
			if (desARelancer == 0)
				return null;
			else
				// Vérifie si le choix de dés à relancer est valide : le choix doit être un integer positif de 5 digits
				// maximum, distincts et compris entre 1 et 5
				if (desARelancer >= 0
						&& tableDesARelancer.length <= 5
						&& !tableADoublons(tableDesARelancer)
						&& !tablePossedeValInvalide(tableDesARelancer))
					return tableDesARelancer;

			System.out.println("Choix invalide.");
		}
	}

	/**
	 * Vérifie si un array contient plusieurs fois la même valeur.
	 * @author Thomas Saudemont
	 * @param array à vérifier
	 * @return true si l'array a des doublons, sinon false
	 */
	public static boolean tableADoublons(int[] array) {

		int[] tempArray = Arrays.copyOf(array,array.length);
		Arrays.sort(tempArray);

		for (int i=1; i<tempArray.length; i++)
			if (tempArray[i-1] == tempArray[i])
				return true;
		return false;
	}

	/**
	 * 	Vérifie sur un array contient un int inférieur à un ou supérieur à cinq.
	 * @author Thomas Saudemont
	 * @param array array à vérifier
	 * @return true si l'array possède une valeur interdite, sinon false
	 */
	public static boolean tablePossedeValInvalide(int[] array) {
		for (int e : array)
			if (e > 5 || e < 1)
				return true;
		return false;
	}

	/**
	 * Attribue, dans un array, une nouvelle valeur aléatoire aux indices spécifiés.
	 * @author Thomas Saudemont
	 * @param tableDes array à modifier avec de nouvelles valeurs
	 * @param tableDesARelancer array contenant les indices de l'array à modifier
	 * @return tableDes modifié
	 */
	public static int[] relancerDesChoisis(int[] tableDes, int[] tableDesARelancer){
		for (int i : tableDesARelancer )
			if (i!=0)
				tableDes[i-1] = Constantes.DES_MIN + (int)(Math.random() * ((Constantes.NB_FACES - Constantes.DES_MIN) + 1));
		return tableDes;
	}

	/**
	 * Convertit un integer en array (un chiffre par case).
	 * @author Thomas Saudemont
	 * @param integer integer à convertir en array
	 * @return array
	 */
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

	/**
	 * Affiche un String puis récupère l'integer entré par l'utilisateur.
	 * @author Thomas Saudemont
	 * @param sc Scanner
	 * @param message String à afficher à l'utilisateur
	 * @return integer entré par l'utilisateur
	 */
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

	/**
	 * Vérifie un array contient une main pleine. C'est-à-dire deux valeurs identiques et trois autres valeurs
	 * @author Thomas Saudemont
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

	/**
	 * Vérifie si un array contient une suite de quatre ou cinq integer.
	 * @author Thomas Saudemont
	 * @param array à vérifier
	 * @return Map<String, Boolean> de longueur deux. Exemple si array contient une suite de quatre mais pas de suite
	 * de cinq : {suite5=false, suite4=true}
	 */
	public static Map<String, Boolean> isSuite(int[] array) {
		Map<String, Boolean> suites = new HashMap<String, Boolean>();

		int [] tempArray = Arrays.copyOf(array,array.length);
		Arrays.sort(tempArray);
		int cpt = 0;
		boolean notSuite = false ;
		for (int i=0;i<tempArray.length-1;i++)
			if (tempArray[i] + 1 == tempArray[i+1])
				cpt++;
			else if (i == 1)
				notSuite = true;

	if(!notSuite) {
		if (cpt >= 3)
			suites.put("suite4", true);
		else
			suites.put("suite4", false);
		if (cpt == 4)
			suites.put("suite5", true);
		else
			suites.put("suite5", false);
	} else {
		suites.put("suite4", false);
		suites.put("suite5", false);
	}

	return suites;
}



	/**
	 * la fonction permet de parcourir de verifier pour le
	 * joueur tout les possibilites de faire des points avec la main
	 * qu'il a. les choix sont parmis : Brelan/MainPleine/carre/
	 * Yum/Petit_Suite-Grosse_Suite/Surplus.
	 * @param tableauDes: la tableau des des qui nous permet de verifeir les
	 * possibilite en fonction de ses valeurs.
	 * @return: un tableauPossibilite qui comprend les valeurs au cases de chacune
	 * des possibilite.
	 *
	 * @author Aicha Berthe & Ilyes Essid
	 */

	public static int  [] TraitementDePossibilite(int []tableauDes, int []tableauVerification ) {

		boolean main =isMainPleine(tableauDes);

		Map<String, Boolean> res = isSuite(tableauDes);

		int []tableau = Occurrence(tableauDes);

		int []tableauPossibilite= new int [19];
		for(int i=0; i<tableau.length; i++) {
			if(tableau[i]>1 && (tableauVerification[i+1]==-1) ) {
				tableauPossibilite[i+1]=(i+1)*tableau[i];
			}
			if((tableau[i]==3)&&(tableauVerification[Constantes.BRELAN]==-1)) {
				tableauPossibilite[Constantes.BRELAN]=(i+1)*tableau[i];

			}
			if((tableau[i]==4)&&(tableauVerification[Constantes.CARRE]==-1)) {
				tableauPossibilite[Constantes.CARRE]=(i+1)*tableau[i];

			}
			if((tableau[i]==5)&&(tableauVerification[Constantes.YUM]==-1)) {
				tableauPossibilite[Constantes.YUM]=30;
			}
		}

		if((res.get("suite5")==true)&&(tableauVerification[Constantes.GROSSE_SUITE]==-1)) {
			tableauPossibilite[Constantes.GROSSE_SUITE]=20;
		}
		if((res.get("suite4")==true)&&(tableauVerification[Constantes.PETITE_SUITE]==-1)) {
			tableauPossibilite[Constantes.PETITE_SUITE]=15;
		}
		if((main==true)&&(tableauVerification[Constantes.MAIN_PLEINE]==-1)){
			tableauPossibilite[Constantes.MAIN_PLEINE]=25;
		}
		if((tableauVerification[Constantes.SURPLUS]==-1)) {
			tableauPossibilite[Constantes.SURPLUS]= somme(tableauDes);
		}



		return tableauPossibilite;
	}

	/** Cette fonction compte d'ocurrance d'une donne dans le tableau de des.
	 * 	@param tableauDes : tableau de des a verifier
	 *	@return int tableau qui indique a la position (i-1),
	 *  le nombre de fois ou la valeur i se repete
	 *  @author Aïcha berthe &  Ilyes Essid
	 * **/

	public static int[] Occurrence(int[] tableauDes) {
		int compteur=0;
		int []tableauVerification= new int [6];
		for (int i=1; i<tableauDes.length+2;i++) {
			compteur=0;
			for (int x=0; x< tableauDes.length; x++) {
				if(i==tableauDes[x]) {
					compteur++;
				}
			}
			tableauVerification[i-1]=compteur;
		}
		return tableauVerification;

	}

	/** Calculer la somme des elements qui sont dans le
	 * tableau de des
	 * 	@param tab le tableau d'element a compter
	 *	@return somme : la somme de tout les elements qui etait dans le
	 *tableau
	 *@author Aïcha Berthe & Ilyes Essid
	 * **/
	public static int somme( int [] tab ) {
		int somme=0;
		for ( int i=0; i<tab.length;i++) {
			somme +=tab[i];
		}
		return somme;
	}

	/** Calculer la somme de la partie supérieur / inferieur si une des cases de cette dérniere à été mise à jour
	 *  @author Ilyes Essid
	 * 	@param pos qui indiaue la position de la case a modifier
	 *  @param score indiaue le score obtenu a cette case
	 *  @param arrayFinal le tableau de score a modifier
	 *	@return arrayFinal le tableau de score a modifier
	 * **/

	public static int[] updateArrayScore(int pos, int score, int[] arrayFinal){

		if (pos != 0)
			arrayFinal[pos] = score;

		if (0<pos && pos<7){
			int sommePartieSup =0;

			for(int i =1; i<Constantes.SOUS_TOTAL_HAUT;i++){
				if (arrayFinal[i] != -1)
					sommePartieSup += arrayFinal[i];
			}
			arrayFinal[Constantes.SOUS_TOTAL_HAUT] = sommePartieSup;

		}


		if (Constantes.BRELAN<=pos && pos<Constantes.GRAND_TOTAL) {
			int sommePartieinf =0;;

			for (int i=Constantes.BRELAN; i<Constantes.TOTAL_BAS;i++){
				if (arrayFinal[i] != -1)
					sommePartieinf += arrayFinal[i];
				arrayFinal[Constantes.TOTAL_BAS] = sommePartieinf;

			}



		}


		return arrayFinal;
	}

	/** Calculer la somme de la partie supérieur et rajouter une bonification si ce dérnier est supérieur à 63 et calcule ensuite le score final
	 *  @param arrayFinal le tableau de score a modifier
	 *	@return arrayFinal le tableau de score apres les calculs
	 * @author Ilyes Essid
	 **/
	public static int[] calculScoreFinal(int[] arrayFinal)
	{
		arrayFinal[Constantes.BONUS_DU_HAUT] = (arrayFinal[Constantes.SOUS_TOTAL_HAUT]>=63)? 25 : 0;
		arrayFinal[Constantes.TOTAL_HAUT] =  arrayFinal[Constantes.SOUS_TOTAL_HAUT] +  arrayFinal[Constantes.BONUS_DU_HAUT];


		arrayFinal[Constantes.GRAND_TOTAL] = arrayFinal[Constantes.TOTAL_BAS] + arrayFinal[Constantes.TOTAL_HAUT];

		return arrayFinal;
	}

}