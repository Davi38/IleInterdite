/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import ileinterdite.vues.Vue;
import ileinterdite.vues.VueAventurier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author giacintf
 */
public class Controleur {
        private NiveauEau niveaueau;
	private ArrayList<Tresor> tresors;
	private ArrayList<Carte_Inondation> piocheI;
	private Grille grille;
        private HashMap<Aventurier,String> joueurs;
	private ArrayList<Carte_Inondation> defausseI;
	private ArrayList<Carte_Tresor> defausseT;
	private ArrayList<Carte_Tresor> piocheT;
	private VueAventurier vueAventurier;
	private Vue vue;
	private Controleur VerifierDeplacementPossible;

        
        Controleur(){
            
            
            for (TypeTrésor tres : TypeTrésor.values()){
                tresors.add(new Tresor(tres));
            }
            
            for (Piece_liste pc : Piece_liste.values()){
                piocheI.add(new Carte_Inondation(pc));
            }
            Collections.shuffle(piocheI);
            
            grille = new Grille();
            
                    
        }
        
        
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

	public Grille getGrille() {
		// TODO - implement Controleur.getGrille
		return this.grille;
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
		piocheI.get(0).getPiece();
                // faire quelque chose
                defausseI.add(piocheI.get(0));
                piocheI.remove(0);
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
