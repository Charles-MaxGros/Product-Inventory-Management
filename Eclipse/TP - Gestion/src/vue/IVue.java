package vue;

import controleur.Controleur;

public interface IVue {
	
	public void setControleur(Controleur controleur);
	
	public void erreurChampTextInvalide();
}
