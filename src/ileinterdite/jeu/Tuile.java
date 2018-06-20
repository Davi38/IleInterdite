/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

/**
 *
 * @author giacintf
 */
import java.util.*;

public abstract class Tuile {
    
	private Etat etat;
        private Piece_liste piece;
        
        
        Tuile(Piece_liste piece){
            this.etat = etat.ASSECHEE;
            this.piece = piece;
        }
        
	public void assecher() {
            etat = Etat.ASSECHEE;
	}

	public void innonder() {
            if (etat == Etat.ASSECHEE){
            etat = Etat.INNONDEE;
            }else {
             etat = Etat.NOYEE;   
            }
	}

	public Etat getEtat() {
		return etat;
	}
        public boolean estNoyee() {
		return etat == Etat.INNONDEE;
	}
        
	public boolean estInnondee() {
		return etat == Etat.INNONDEE;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

        public abstract String getPiece();

        

}

