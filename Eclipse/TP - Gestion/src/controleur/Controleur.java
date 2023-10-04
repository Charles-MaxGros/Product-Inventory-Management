package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import modele.Connexion;
import modele.Produit;
import modele.Stock;
import modele.ValideCommande;
import modele.exceptions.CodeCommandeDejaPris;
import modele.exceptions.QuantiteDemandeeNonValide;
import modele.Commande;
import vue.AjoutStockFrame;
import vue.CommandeFrame;
import vue.LoginFrame;
import vue.exceptions.ChampTexteInvalideException;

public class Controleur {
	Stock stock;

	LoginFrame loginFrame;
	AjoutStockFrame ajoutStock;
	CommandeFrame commandeFrame;

	// Recupere donnees login, si non valide affiche erreur
	public void actionLogin() throws IOException {
		// TODO VALIDE LOGIN avec fichier login.txt

		try {
			String user = this.loginFrame.getUtilisateur();
			String motDePasse = this.loginFrame.getMotDePasse();

			if (Connexion.valideLogin(user.toLowerCase(), motDePasse)) {
				this.loginFrame.loginValide();
			} else {
				this.loginFrame.loginInValide();
			}

		} catch (ChampTexteInvalideException e) {
			// TODO: handle exception
			// e.printStackTrace();
			this.loginFrame.erreurChampTextInvalide();
		}
	}

	// Recupere donnees login, si non valide affiche erreur
	public void actionAjoutStock() throws IOException {
		// Valide l'ajout de produit, si valide
		try {
			String designation = this.ajoutStock.getDesignation();
			Double prix = this.ajoutStock.getPrix();
			int quantite = this.ajoutStock.getQuantite();

			// Creer une produit a ajouter
			Produit nouveauProduit = new Produit(designation, prix, quantite);
			
			//Verifie si le produit existe deja
			Produit produitExistant=this.stock.trouverProduit(designation);
			
			if (produitExistant!=null) {
				nouveauProduit.setQuantite(nouveauProduit.getQuantite()+produitExistant.getQuantite());
				this.stock.modifieStockText(produitExistant, nouveauProduit);
			}
			else {
				// Ajouter le produit dans le stock
				this.stock.ajouter(nouveauProduit);
				
			}
			
			this.ajoutStock.updateJTable();


		} catch (ChampTexteInvalideException e) {
			// TODO Auto-generated catch block
			this.ajoutStock.erreurChampTextInvalide();
		}

	}

	public void actionCommande() throws IOException {
		try {

			int codeCommande = this.commandeFrame.getCode();
			String designationCommande = this.commandeFrame.getDesignation();
			int quantiteCommande = this.commandeFrame.getQuantite();
			double prixCommande = this.commandeFrame.getPrix();

			Produit produitCommande = new Produit(designationCommande, prixCommande, quantiteCommande);
			ValideCommande valideCommande=new ValideCommande();
			
			
			//Valide que le produit existe et le code de code de commmande n'est pas deja prix
			if (this.stock.produitValideCommande(produitCommande) && valideCommande.valideNumCommande(codeCommande)) {
				Commande commande = new Commande(codeCommande, designationCommande, quantiteCommande, prixCommande);
				
				//Modifie le fichier stock.txt pour la nouvelle quantite
				Produit produitExistant=this.stock.trouverProduit(designationCommande);
				int nouvelleQuantite=produitExistant.getQuantite()-quantiteCommande;
				
				Produit produitQuantiteUpdate=produitExistant;
				produitQuantiteUpdate.setQuantite(nouvelleQuantite);
				
				this.stock.modifieStockText(produitExistant, produitQuantiteUpdate);
				
			} 
			else {
				this.commandeFrame.messageProduitNonExistant();
			}

		} catch (ChampTexteInvalideException e) {
			// TODO: handle exception
			this.commandeFrame.erreurChampTextInvalide();
		} catch (QuantiteDemandeeNonValide e) {
			// TODO: handle exception
			// Appelle methode pour afficher message
			this.commandeFrame.messageProduitPasAssez();
		} catch (CodeCommandeDejaPris e) {
			// TODO Auto-generated catch block
			this.commandeFrame.messageCodeCommandeDejaPris();
		}

	}
	
	
	// ******** GETTERS ET SETTERS *********

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public void setAjoutStock(AjoutStockFrame ajoutStock) {
		this.ajoutStock = ajoutStock;
	}

	public void setCommandeFrame(CommandeFrame commandeFrame) {
		this.commandeFrame = commandeFrame;
	}
	
	//Retourne tableau de string de designation pour combobox
	public String[] getTabDesination() {
		String[] tab=new String[this.stock.getListeProduit().size()];
		int index=0;
		
		for (Produit p : this.stock.getListeProduit()) {
			tab[index]=p.getDesignation();
			index++;
		}
		
		return tab;
	}
	
	//Retourne le prix du produit selectionne 
	public double getPrixComboBox(String designation) {
		Produit produit=this.stock.trouverProduit(designation);
		double prix=produit.getPrixUnitaire();
		
		
		
		return prix;
	}
	
	//Retourne la liste de produit en tableau de string 2D de la class stock
	public String[][] getTableauStringStock() {
		return this.stock.getTableauString();
	}

}
