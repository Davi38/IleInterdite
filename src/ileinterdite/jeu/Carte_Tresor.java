/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

public abstract class Carte_Tresor {

	private Aventurier appartient;

	/**
	 * 
	 * @param aventurier
	 */
	public void setAventurier(Aventurier aventurier) {
		// TODO - implement Carte_Tresor.setAventurier
		aventurier.addCarte(this);
	}

	public abstract String getType();
}