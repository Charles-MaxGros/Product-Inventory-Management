package vue;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//Class servant à la creation de panels et composantes mises en parametre 
public class CreationComposantes {
	public static JPanel creationPanelTextField(String lbl_text, TextField tf) {
		JPanel pnl = new JPanel();
		JLabel lbl = new JLabel(lbl_text);

		pnl.add(lbl);
		pnl.add(tf);

		return pnl;

	}

	public static JPanel creationPanelComboBox(String lbl_txt, JComboBox<String> cb) {
		JPanel pnl = new JPanel();
		JLabel lbl = new JLabel(lbl_txt);

		pnl.add(lbl);
		pnl.add(cb);

		return pnl;
	}

	public static JPanel creationPanel2Label(String lbl_txt1, JLabel lbl) {
		JPanel pnl = new JPanel();
		JLabel lbl1 = new JLabel(lbl_txt1);

		pnl.add(lbl1);
		pnl.add(lbl);

		return pnl;
	}

	public static JPanel ajoutLbl(String lbl_text) {
		// fonction pour factoris� et faciliter la creation de panel
		JPanel pnl = new JPanel();
		JLabel lbl = new JLabel(lbl_text);

		pnl.add(lbl);
		return pnl;

	}

	public static JPanel creationPanelButton(JButton btn_ajouter, JButton btn_effacer, JButton btn_retour) {

		// Ajout bouttons dans un panel
		JPanel pnl_bouttons = new JPanel();
		pnl_bouttons.setLayout(new FlowLayout());

		pnl_bouttons.add(btn_ajouter);
		pnl_bouttons.add(btn_effacer);
		pnl_bouttons.add(btn_retour);

		return pnl_bouttons;

	}

	public static JScrollPane creationScrollPaneJTable(JTable tableau) {
		JPanel pnl_jtable = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(tableau);
		scrollPane.setBounds(50, 50, 400, 400);
		//tableau.setFillsViewportHeight(true);
		
		//pnl_jtable.add(scrollPane);
		
		return scrollPane;
	}
}
