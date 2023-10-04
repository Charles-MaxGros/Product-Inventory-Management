package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import modele.exceptions.QuantiteDemandeeNonValide;

public class Stock {

	private ArrayList<Produit> listeProduit;

	// Contructeur de stock, rempli la listeProduit en lissant le fichier stock.txt
	public Stock() throws IOException {
		listeProduit = new ArrayList<>();

		FileReader fr = new FileReader("stock.txt");
		BufferedReader br = new BufferedReader(fr);

		String ligne;
		String[] tabElement;

		String designation;
		int quantite;
		double prix;

		while ((ligne = br.readLine()) != null) {
			tabElement = ligne.split("\t");

			designation = tabElement[0];
			quantite = Integer.valueOf(tabElement[1]);
			prix = Double.valueOf(tabElement[2]);

			Produit produit = new Produit(designation, prix, quantite);
			listeProduit.add(produit);

		}

		br.close();
		fr.close();

	}

	// Retourne le verdict si le produit existe afin de commander, si existe,
	// modifie le fichier stock.txt
	public boolean produitValideCommande(Produit produitRecherche) throws QuantiteDemandeeNonValide, IOException {
		boolean valide = false;
		int index = 0;

		while (index < this.listeProduit.size() && !valide) {

			if ((this.listeProduit.get(index).getDesignation()).equals(produitRecherche.getDesignation())) {

				if ((this.listeProduit.get(index).getQuantite()) >= produitRecherche.getQuantite()) {
					valide = true;
					int calcul = (this.listeProduit.get(index).getQuantite()) - produitRecherche.getQuantite();
					produitRecherche.setQuantite(calcul);

					// Modifie le fichier stock afin de mettre a jour la quantite
					this.modifieStockText(this.listeProduit.get(index), produitRecherche);

					this.listeProduit.get(index).setQuantite(calcul);

				} else {
					throw new QuantiteDemandeeNonValide();
				}

			}
			index++;

		}

		return valide;
	}
	

	// Methode qui modifier la quantité dans d'un produit dans le fichier stock.txt
	public void modifieStockText(Produit vieuxProduit, Produit nouveauProduit) throws IOException {

		FileReader fr = new FileReader("stock.txt");
		BufferedReader br = new BufferedReader(fr);

		String ligne = br.readLine();

		String strData = "";

		boolean trouver = false;

		while (ligne != null) {

			strData += ligne;
			strData += "\n";
			ligne = br.readLine();

		}

		br.close();

		strData = strData.replace(vieuxProduit.toString(), nouveauProduit.toString());

		FileWriter fw = new FileWriter("stock.txt");
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(strData);
		bw.flush();

		bw.close();

	}

	// Methode pour ajouter un produit a la liste et enregistre le fichier txt
	public void ajouter(Produit produit) throws IOException {
		listeProduit.add(produit);
		FileWriter fw = new FileWriter("stock.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(produit.toString());
		bw.newLine();

		bw.close();

	}

	// Trouve le produit avec une designation, si trouve pas retourne produit null
	public Produit trouverProduit(String designation) {
		Produit produit = null;
		int index = 0;

		while (index < this.listeProduit.size() && produit == null) {
			if ((this.listeProduit.get(index).getDesignation()).equals(designation)) {
				produit = this.listeProduit.get(index);
			}
			index++;
		}

		return produit;

	}

	// Getter pour la liste de produit (stock)
	public ArrayList<Produit> getListeProduit() {
		return listeProduit;
	}
	
	//Getter pour la liste de produit en tableau de String 2 dimensions (pour JTable)
	public String[][] getTableauString(){
		String[][] tableau=new String[this.listeProduit.size()][3];		
		int index=0;
		
		for (Produit p : this.listeProduit) {
			tableau[index][0]=p.getDesignation();
			tableau[index][1]=String.valueOf(p.getPrixUnitaire());
			tableau[index][2]=String.valueOf(p.getQuantite());
			index++;
		}
		
		
		return tableau;
	}

	
	public static void main(String[] args) throws IOException {
		// Test
		Stock stockTest = new Stock();

		stockTest.listeProduit.add(new Produit("Banane", 1, 1));
		stockTest.listeProduit.add(new Produit("Pomme", 2.0, 5));
	}

}
