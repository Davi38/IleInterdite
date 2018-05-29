/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import ileinterdite.vues.Vue;
import ileinterdite.vues.VueAventurier;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author giacintf
 */
public class Controleur {
        private NiveauEau NiveauEau;
	private Collection<Trésor> trésors;
	private Collection<Carte_Innondation> Pioche;
	private Grille grille;
        private HashMap<Aventurier,Integer> joueurs;
	private Collection<Carte_Innondation> DefausseI;
	private Collection<Carte_Tresor> defausseT;
	private Collection<Carte_Tresor> piocheT;
	private VueAventurier vueAventurier;
	private Vue vue;
	private Controleur VerifierDeplacementPossible;

	/**
	 * 
	 * @param NomBouton
	 */
	public void traiterMessage(Message NomBouton) {
		// TODO - implement Controleur.traiterMessage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numJ
	 */
	public void nouveauTour(int numJ) {
		// TODO - implement Controleur.nouveauTour
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomJ
	 */
	public Aventurier chercherAventurier(int nomJ) {
		// TODO - implement Controleur.chercherAventurier
		throw new UnsupportedOperationException();
	}

	public void getGrille() {
		// TODO - implement Controleur.getGrille
		throw new UnsupportedOperationException();
	}

	public void traiterClicTuile() {
		// TODO - implement Controleur.traiterClicTuile
		throw new UnsupportedOperationException();
	}

	public void traiterMessage() {
		// TODO - implement Controleur.traiterMessage
		throw new UnsupportedOperationException();
	}

	public void piocherCarteT() {
		// TODO - implement Controleur.piocherCarteT
		throw new UnsupportedOperationException();
	}

	public void piocherCarteI() {
		// TODO - implement Controleur.piocherCarteI
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomPiece
	 */
	public Tuile getTuile(Piece nomPiece) {
		// TODO - implement Controleur.getTuile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param lig
	 * @param col
	 */
	public void traiterClicTuile(int lig, int col) {
		// TODO - implement Controleur.traiterClicTuile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param lig
	 * @param col
	 */
	public Tuile getGTuile(int lig, int col) {
            
		return grille.getTuile(lig,col);
	}

	public boolean verifierAssechement() {
		// TODO - implement Controleur.verifierAssechement
		throw new UnsupportedOperationException();
	}

	public boolean VerifierDeplacementPossible() {
		// TODO - implement Controleur.VerifierDeplacementPossible
		throw new UnsupportedOperationException();
	}

	public void afficherMessage() {
		// TODO - implement Controleur.afficherMessage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param lig
	 * @param col
	 */
	public void NouveauclicTuile(int lig, int col) {
		// TODO - implement Controleur.NouveauclicTuile
		throw new UnsupportedOperationException();
	}

}
