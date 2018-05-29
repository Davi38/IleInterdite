package ileinterdite;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giacintf
 */
public abstract class Carte_Tresor {

	private Aventurier appartient;

	/**
	 * 
	 * @param aventurier
	 */
	public void setAventurier(Aventurier aventurier) {
            this.appartient=aventurier;
		
	}

	public abstract String getType();

}
