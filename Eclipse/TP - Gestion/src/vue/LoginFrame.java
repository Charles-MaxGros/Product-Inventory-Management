package vue;

import javax.swing.*;

import controleur.Controleur;
import vue.exceptions.ChampTexteInvalideException;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginFrame extends JFrame implements ActionListener, IVue {

	Container container = getContentPane();
	JLabel lbl_utilisateur = new JLabel("Nom d'utilisateur");
	JLabel lbl_motDePasse = new JLabel("Mot de passe");
	JTextField tf_user = new JTextField();
	JPasswordField pf_motDePasse = new JPasswordField();
	JButton btn_connexion = new JButton("Connexion");
	JButton btn_effacer = new JButton("Effacer");
	JCheckBox cb_montrerPass = new JCheckBox("Montrer mot de passe");

	Controleur controleur;

	public LoginFrame(String titre) {
		super(titre);

		this.setTitle("Connexion");
		this.setBounds(10, 10, 370, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		tf_user.getText();

		this.setLayoutManager();
		this.setLocationAndSize();
		this.addComponentsToContainer();
		this.addActionEvent();

		this.setVisible(true);
	}

	private void addActionEvent() {
		// TODO Auto-generated method stub
		btn_connexion.addActionListener(this);
		btn_effacer.addActionListener(this);
		cb_montrerPass.addActionListener(this);
	}

	private void addComponentsToContainer() {
		// TODO Auto-generated method stub
		// Adding each components to the Container
		container.add(lbl_utilisateur);
		container.add(lbl_motDePasse);
		container.add(tf_user);
		container.add(pf_motDePasse);
		container.add(cb_montrerPass);
		container.add(btn_connexion);
		container.add(btn_effacer);
	}
	
	public void effacer() {
		tf_user.setText("");
		pf_motDePasse.setText("");
	}

	public void choixPass() {
		if (cb_montrerPass.isSelected()) {
			pf_motDePasse.setEchoChar((char) 0);
		} else {
			pf_motDePasse.setEchoChar('*');
		}
	}
	
	public void loginInValide() {
		JOptionPane.showMessageDialog(this, "Le username ou le password est incorrect.");
	}
	
	public void loginValide() {
		JOptionPane.showMessageDialog(this, "Le username ou le password est a ete accepte.");
		this.dispose();//ferme la fenetre
		ChoixFrame choix=new ChoixFrame("Choix");
	}
	
	public void erreurChampTextInvalide() {
		JOptionPane.showMessageDialog(this, "Les champs de utilisateur ou mot de passes sont invalides. Veuillez entrer votre utilisateur et mot de passe.");
	}

	// Appelle la focntion de actionLogin de controleur avec une action en parametre
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_connexion) {
			try {
				controleur.actionLogin();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == btn_effacer) {
			this.effacer();
		} else if (e.getSource() == cb_montrerPass) {
			this.choixPass();
		}
	}


	// ******** SETTERS ET GETTERS *********
	private void setLocationAndSize() {
		// TODO Auto-generated method stub
		lbl_utilisateur.setBounds(50, 150, 100, 30);
		lbl_motDePasse.setBounds(50, 220, 100, 30);
		tf_user.setBounds(150, 150, 150, 30);
		pf_motDePasse.setBounds(150, 220, 150, 30);
		cb_montrerPass.setBounds(150, 250, 150, 30);
		btn_connexion.setBounds(50, 300, 100, 30);
		btn_effacer.setBounds(200, 300, 100, 30);

	}

	private void setLayoutManager() {
		// TODO Auto-generated method stub
		container.setLayout(null);
	}
	

	// methode pour recuperer le string dans le textfield utilisateur
	//Lance une exception si le champ est vide
	public String getUtilisateur() throws ChampTexteInvalideException {
		String txt = "";

		txt = tf_user.getText().trim();

		if (txt.equalsIgnoreCase("")) {
			throw new ChampTexteInvalideException();
		}

		return txt;
	}

	// methode pour recuperer le string dans le textfield mot de passe
	//Lance une exception si le champ est vide
	public String getMotDePasse() throws ChampTexteInvalideException {
		String txt = "";

		txt = pf_motDePasse.getText().trim();

		if (txt.equalsIgnoreCase("")) {
			throw new ChampTexteInvalideException();
		}

		return txt;
	}

	@Override
	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}
	
	
	//TEST
	public static void main(String[] args) {
		Controleur controleur = new Controleur();
		LoginFrame loginFrame = new LoginFrame("Connexion");
		loginFrame.setControleur(controleur);
		controleur.setLoginFrame(loginFrame);

	}

}
