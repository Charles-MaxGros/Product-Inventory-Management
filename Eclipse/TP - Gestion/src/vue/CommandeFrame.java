package vue;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.Stock;
import controleur.Controleur;
import vue.exceptions.ChampTexteInvalideException;

public class CommandeFrame extends JFrame implements IVue,ActionListener {

	private JButton btn_ajouter;
	private JButton btn_effacer;
	private JButton btn_retour;

	private TextField tf_code;
	private TextField tf_quantite;

	private JLabel lbl_prix;

	private JComboBox<String> cb_designation;

	private Controleur controleur;

	//Constructeur
	public CommandeFrame(String titre) {
		super(titre);// appelle le constructeur Jframe

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setLayout(new GridLayout(8, 1));
		this.setResizable(false);

		// Textfield
		this.tf_quantite = new TextField(5);
		this.tf_code = new TextField(5);

		// ComboBox Designation
		this.cb_designation = new JComboBox<>();

		// Label prix
		this.lbl_prix = new JLabel("");

		// Les Boutons
		this.btn_ajouter = new JButton("Ajouter");
		this.btn_effacer = new JButton("Effacer");
		this.btn_retour = new JButton("Retour");

		// Ajout des panel
		this.add(CreationComposantes.ajoutLbl("Preparation de commande"));
		this.add(CreationComposantes.creationPanelTextField("Code : ", tf_code));
		// this.add(CreationComposantes.creationPanelTextField("Designation : ",
		// tf_designation));
		this.add(CreationComposantes.creationPanelComboBox("Designation", cb_designation));
		this.add(CreationComposantes.creationPanelTextField("Quantite : ", tf_quantite));
		this.add(CreationComposantes.creationPanel2Label("Prix Unitaire", lbl_prix));
		// this.add(CreationComposantes.creationPanelTextField("Prix : ", tf_prix));

		this.add(CreationComposantes.creationPanelButton(btn_ajouter, btn_effacer, btn_retour));

		this.addActionEvent();

		this.setVisible(true);
	}

	private void addActionEvent() {
		// TODO Auto-generated method stub
		btn_ajouter.addActionListener(this);
		btn_effacer.addActionListener(this);
		btn_retour.addActionListener(this);
		cb_designation.addActionListener(this);
	}

	// Appelle la focntion de actionCommande de controleur
	// Et les fonctions de plus comme effacer textfield et retour
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_ajouter) {
			try {
				this.controleur.actionCommande();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == btn_effacer) {
			this.tf_quantite.setText("");
			this.tf_code.setText("");
		} else if (e.getSource() == btn_retour) {
			this.dispose();
			ChoixFrame choix = new ChoixFrame("Choix");
		} else if (e.getSource() == cb_designation) {
			this.setPrix();
		}

	}

	//***Messages Erreurs***
	public void erreurChampTextInvalide() {
		JOptionPane.showMessageDialog(this,
				"Les valeurs presentee dans les champs de textes sont invalides. Veuillez entrer une designation et une quantité plus ou égal à 0.");
	}

	public void messageProduitNonExistant() {
		JOptionPane.showMessageDialog(this, "Le produit entr� n'existe pas.");
	}
	
	public void messageProduitPasAssez() {
		JOptionPane.showMessageDialog(this, "Il n'y a pas assez de ce produit. Veuillez rentre une quantite plus petite");
	}
	
	public void messageCodeCommandeDejaPris() {
		JOptionPane.showMessageDialog(this, "Le code entre est deja prix, veuillez rentrer un autre code.");
	}
	
	//Set le controleur, rempli le combobox et set le prix actuel du produit selectionné
	@Override
	public void setControleur(Controleur controleur) {
		this.controleur = controleur;

		// Pour chaque produit, je prend designation et je ajoute au combobox
		for (String designation : this.controleur.getTabDesination()) {
			this.cb_designation.addItem(designation);
		}

		// Change la valeur de prix
		this.setPrix();

	}

	// Set le prix sur le lable a partir du produit actuel
	public void setPrix() {
		// Change la valeur de prix, getPrixComboBox retourne le bon produit
		String prix = String.valueOf(this.controleur.getPrixComboBox((String) cb_designation.getSelectedItem()));
		this.lbl_prix.setText(prix);
	}

	//Valide les valeurs entrer, si non valide, lance un exception afin d'avertir le controleur et l'utilisateur
	
	public int getCode() throws ChampTexteInvalideException {
		int code = 0;

		try {
			code = Integer.valueOf(this.tf_code.getText());

			if (code < 0) {
				throw new ChampTexteInvalideException();
			}

		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw new ChampTexteInvalideException();
		}

		return code;
	}

	public String getDesignation() throws ChampTexteInvalideException {
		String txt = "";

		txt = this.cb_designation.getSelectedItem().toString().toLowerCase();

		if (txt.equalsIgnoreCase("")) {
			throw new ChampTexteInvalideException();
		}

		return txt;
	}

	public int getQuantite() throws ChampTexteInvalideException {
		int quantite = 0;

		try {
			quantite = Integer.valueOf(this.tf_quantite.getText());

			if (quantite < 0) {
				throw new ChampTexteInvalideException();
			}

		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw new ChampTexteInvalideException();
		}

		return quantite;
	}

	public double getPrix() {
		double prix = 0;

		prix = Double.valueOf(this.lbl_prix.getText());

		return prix;
	}

	public static void main(String[] args) throws IOException {
		//Test
		Controleur controleur = new Controleur();
		CommandeFrame fenetreTest = new CommandeFrame("Preparation de commande");
		Stock stock = new Stock();
		controleur.setStock(stock);
		fenetreTest.setControleur(controleur);
		controleur.setCommandeFrame(fenetreTest);
	}
}
