package vue;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import modele.Stock;
import vue.exceptions.ChampTexteInvalideException;

public class AjoutStockFrame extends JFrame implements IVue,ActionListener {

	private JButton btn_ajouter;
	private JButton btn_effacer;
	private JButton btn_retour;

	private TextField tf_designation;
	private TextField tf_prix;
	private TextField tf_quantite;
	
	private String[] tabNomColonnes= {"Designation","Prix","Quantite"};
	private JTable tab_stock;
	private ScrollPane scrollPane;

	private Controleur controleur;
	
	//Constructeur
	public AjoutStockFrame(String titre) {
		super(titre);// appelle le constructeur Jframe

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(750, 1000);
		this.setLayout(new GridLayout(7, 1));
		//this.setResizable(false);

		// Textfield
		this.tf_designation = new TextField(10);
		this.tf_prix = new TextField(10);
		this.tf_quantite = new TextField(5);

		// Les Boutons
		this.btn_ajouter = new JButton("Ajouter");
		this.btn_effacer = new JButton("Effacer");
		this.btn_retour = new JButton("Retour");
		
		
		this.tab_stock=new JTable();
		
		ImageIcon image=new ImageIcon("produit.jpg");
		JLabel lbl_image=new JLabel(image);
		JPanel pnl_image=new JPanel();
		pnl_image.add(lbl_image);

		// Ajout des panel et image
		this.add(pnl_image);//1
		this.add(CreationComposantes.ajoutLbl("Ajout de stock"));//2
		this.add(CreationComposantes.creationPanelTextField("Designation : ", tf_designation));//3
		this.add(CreationComposantes.creationPanelTextField("Prix : ", tf_prix));//4
		this.add(CreationComposantes.creationPanelTextField("Quantit� : ", tf_quantite));//5

		this.add(CreationComposantes.creationPanelButton(btn_ajouter, btn_effacer, btn_retour));//6
		
		this.addActionEvent();

		this.setVisible(true);

	}
	
	private void addActionEvent() {
		// TODO Auto-generated method stub
		btn_ajouter.addActionListener(this);
		btn_effacer.addActionListener(this);
		btn_retour.addActionListener(this);
	}
	
	//Efface tout les textfield de la fenetre
	private void effacer() {
		this.tf_designation.setText("");
		this.tf_prix.setText("");
		this.tf_quantite.setText("");
	}

	
	//Appelle la fonction de actionAjoutStock de controleur 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_ajouter) {
			try {
				controleur.actionAjoutStock();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		else if (e.getSource() == btn_effacer) {
			this.effacer();
		}
		else if (e.getSource() == btn_retour) {
			//Ferme la fenetre et ouvre choixFrame
			this.dispose();
			ChoixFrame choix=new ChoixFrame("Choix");
		}

	}
	
	//Affiche message erreur
	public void erreurChampTextInvalide() {
		JOptionPane.showMessageDialog(this, "Les valeurs presentee dans les champs de textes sont invalides. Veuillez entrer une designation, un prix plus haut que 0$ et une quantité plus ou égal à 0.");
	}
	
	// ******** GETTERS ET SETTERS *********
	
	//Valide les valeurs entrer, si non valide, lance un exception afin d'avertir le controleur et l'utilisateur
	
	
	public String getDesignation() throws ChampTexteInvalideException {
		String txt="";
		
		txt=tf_designation.getText().toLowerCase();
		
		if (txt.equalsIgnoreCase("")){
			throw new ChampTexteInvalideException();
		}
		
		return txt;
	}

	public double getPrix() throws ChampTexteInvalideException {
		double prix=0;
		
		try {
			prix=Double.valueOf(this.tf_prix.getText());
			
			if (prix<=0) {
				throw new ChampTexteInvalideException();
			}
			
		}catch (NumberFormatException e) {
			// TODO: handle exception
			throw new ChampTexteInvalideException();
		}
		
		
		return prix;
	}

	public int getQuantite() throws ChampTexteInvalideException {
		int quantite=0;
		
		try {
			quantite=Integer.valueOf(this.tf_quantite.getText());
			
			if (quantite<0) {
				throw new ChampTexteInvalideException();
			}
		
		}catch (NumberFormatException e) {
			// TODO: handle exception
			throw new ChampTexteInvalideException();
		}
		
		
		return Integer.valueOf(tf_quantite.getText());
	}
	
	//Set le controleur et contruit le jtable
	@Override
	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
		
		this.updateJTable();
		
		this.add(CreationComposantes.creationScrollPaneJTable(tab_stock)); //7
		this.setVisible(true);

	}
	
	public void updateJTable() {

		String[][] tabStock=this.controleur.getTableauStringStock();
		
		this.tab_stock=new JTable(tabStock,this.tabNomColonnes);
		this.tab_stock.setBounds(30,40,200,300);
		this.tab_stock.setFillsViewportHeight(true);
		
		this.tab_stock.revalidate();
		
		this.setVisible(true);
		
	}
	


	public static void main(String[] args) throws IOException {
		// Test
		Controleur controleur=new Controleur();
		AjoutStockFrame fenetreTest = new AjoutStockFrame("Ajout de Stock");
		Stock stock=new Stock();
		controleur.setStock(stock);
		
		//Pour que la fenetre AjoutStock marche, il faut setter le controleur,
		//Pour le controleur, il faut setter 
		fenetreTest.setControleur(controleur);
		controleur.setAjoutStock(fenetreTest);

	}

}
