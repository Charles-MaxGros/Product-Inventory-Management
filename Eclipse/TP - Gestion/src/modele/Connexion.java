package modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Connexion {
	static String filePath = "login.txt";

	//Methode qui retourne verdict si le username et mot de passe saisie sont valide en lisant "login.txt"
	public static boolean valideLogin(String usernameSaisie, String motDePasseSaisie) throws IOException {
		boolean valide = false;

		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);

		String userLecture;
		String motDePassLecture;

		// La on veux parcourir tout le fichier

		String ligne;
		String[] tabElement;

		while ((ligne = br.readLine()) != null) {
			tabElement = ligne.split(",");

			userLecture = tabElement[0].trim().toLowerCase();
			motDePassLecture = tabElement[1].trim().toLowerCase();
			
			
			if (userLecture.equals(usernameSaisie) && motDePassLecture.equals(motDePasseSaisie)) {
				valide = true;
			}

		}
		return valide;
	}

}
