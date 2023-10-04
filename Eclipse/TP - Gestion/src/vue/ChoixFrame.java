package vue;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Stock;


public class ChoixFrame extends JFrame implements ActionListener {

	private JButton btn_ajoutStock;
	private JButton btn_commande;
	private JButton btn_connexion;
	private JButton btn_quitter;

	//Constructeur
	public ChoixFrame(String titre) {
		super(titre);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setLayout(new GridLayout(2, 1));
		this.setResizable(false);

		// initialiser les boutons
		btn_ajoutStock = new JButton("Ajouter du stock");
		btn_commande = new JButton("Commander");
		btn_connexion = new JButton("Connexion");
		btn_quitter = new JButton("Quitter");

		// Label
		JPanel pnl_label = new JPanel();
		JLabel lbl_choix = new JLabel("Choisir une action : ");
		pnl_label.add(lbl_choix);

		// Panels de bouttons
		JPanel pnl_bouttons = new JPanel();
		pnl_bouttons.setLayout(new GridLayout(2, 2));
		pnl_bouttons.add(pnl_label);
		pnl_bouttons.add(btn_ajoutStock);
		pnl_bouttons.add(btn_commande);
		pnl_bouttons.add(btn_connexion);
		pnl_bouttons.add(btn_quitter);

		// Ajout des composantes
		this.add(pnl_label);
		this.add(pnl_bouttons);

		this.addActionEvent();
		this.setVisible(true);

	}

	private void addActionEvent() {
		// TODO Auto-generated method stub
		btn_ajoutStock.addActionListener(this);
		btn_commande.addActionListener(this);
		btn_connexion.addActionListener(this);
		btn_quitter.addActionListener(this);
	}

	// Dependant quel bouton, va fermer la fenetre presente et ouvrir celle
	// correspondante au boutton
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_ajoutStock) {
			this.dispose();
			AjoutStockFrame ajout = new AjoutStockFrame("Ajout de stock");
			ajout.setControleur(Principale.controleur);
			Principale.controleur.setStock(Principale.stock);
			Principale.controleur.setAjoutStock(ajout);

		} else if (e.getSource() == btn_commande) {
			this.dispose();
			CommandeFrame commandeFrame = new CommandeFrame("Preparation Commande");
			commandeFrame.setControleur(Principale.controleur);
			Principale.controleur.setCommandeFrame(commandeFrame);
			
		} else if (e.getSource() == btn_connexion) {
			this.dispose();
			LoginFrame loginFrame = new LoginFrame("Connexion");
			loginFrame.setControleur(Principale.controleur);
			Principale.controleur.setLoginFrame(loginFrame);
		} else if (e.getSource() == btn_quitter) {
			System.exit(0);

		}

	}


}
