package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modele.exceptions.CodeCommandeDejaPris;

public class Commande {
	private int numCommande;
	private String designation;
	private int quantiteCommande;
	private double prixUnitaire;
	private double montantTotal;
	

	// Constructeur
	public Commande(int numCommande, String designation, int quantiteCommande, double prixUnitaire) throws IOException {
		super();
		this.numCommande = numCommande;
		this.designation = designation;
		this.quantiteCommande = quantiteCommande;
		this.prixUnitaire = prixUnitaire;
		
		this.calculerMontantTotal();

		this.enregistrer();
	}

	// Redefinition de la methode toString() servant a l'ecriture du fichier commande.txt
	@Override
	public String toString() {
		return numCommande + "\t" + designation + "\t" + quantiteCommande + "\t" + prixUnitaire + "\t" + montantTotal;
	}

	// Calcule le produit entre prixUnitaire et quantite
	public double calculerMontantTotal() {
		this.montantTotal = this.prixUnitaire * this.quantiteCommande;
		return this.montantTotal;
	}

	// Ecrit l'instance de la commande
	public void enregistrer() throws IOException {
		FileWriter fw = new FileWriter("commande.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(this.toString());
		bw.newLine();

		bw.close();
	}
	


}
