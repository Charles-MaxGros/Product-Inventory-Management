package modele;

public class Produit {

	private String designation;
	private double prixUnitaire;
	private int quantite;

	//Constructeur
	public Produit(String designation, double prixUnitaire, int quantite) {
		super();
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
	}

	//Getters & Setters
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	
	//Redefini toString() servant a l'ecriture du fichier txt
	@Override
	public String toString() {
		return designation + "\t"+ quantite+"\t"+ prixUnitaire;
	}

	
	

	public static void main(String[] args) {
		// Test
		Produit p1 = new Produit("banane", 12.0, 5);

		System.out.println(p1.toString());
	}

}
