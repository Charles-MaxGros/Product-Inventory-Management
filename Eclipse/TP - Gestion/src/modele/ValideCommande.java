package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import modele.exceptions.CodeCommandeDejaPris;

//Class utiliser pour valider les numero de commande afin d'assurer qu'il
// y a aucun doublons dans le fichier commmande.txt
public class ValideCommande {
	
	public ArrayList<Integer> listNumCommandePris = new ArrayList<>();

	//Constructeur qui lis le fichier commande.txt et rempli la liste de numero pris
	public ValideCommande() throws NumberFormatException, IOException {
		FileReader fr = new FileReader("commande.txt");
		BufferedReader br = new BufferedReader(fr);

		String ligne;
		String[] tabElement;

		while ((ligne = br.readLine()) != null) {
			tabElement = ligne.split("\t");

			int codeFichier = Integer.valueOf(tabElement[0]);
			listNumCommandePris.add(codeFichier);
		}
		
		br.close();
		fr.close();

	}

	// Methode qui valide si le numero de commande n'est pas deja pris
	public  boolean valideNumCommande(int code) throws IOException, CodeCommandeDejaPris {
		boolean valide = true;
		int index = 0;

		while (index < this.listNumCommandePris.size() && valide) {

			if (listNumCommandePris.get(index) == code) {
				valide = false;
				throw new CodeCommandeDejaPris();
			}

			index++;
		}

		return valide;
	}
}
