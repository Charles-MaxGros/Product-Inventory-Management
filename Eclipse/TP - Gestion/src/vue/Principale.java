package vue;
import java.io.IOException;

import controleur.Controleur;
import modele.Stock;
import vue.AjoutStockFrame;
import vue.ChoixFrame;
import vue.CommandeFrame;
import vue.LoginFrame;

public class Principale {

	public static Controleur controleur;
	public static Stock stock;
	
	public static void main(String[] args) throws IOException {
		// Demarrer programme ici
		controleur=new Controleur();
		stock=new Stock();
		controleur.setStock(stock);
		
		LoginFrame login=new LoginFrame("Connexion"); //login.setVisible(true);
	
		login.setControleur(controleur);

		controleur.setLoginFrame(login);
		
		
	}

}
