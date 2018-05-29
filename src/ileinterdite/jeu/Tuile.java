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
    
        private ArrayList<Aventurier> aventuriers;
	private Etat etat;
        
        
        Tuile(){
            etat = etat.ASSECHEE;
            aventuriers = new ArrayList<Aventurier>();
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


	public void addAventurier(Aventurier aventurier) {
            aventuriers.add(aventurier);
	}


	public void removeAventurier(Aventurier aventurier) {
		aventuriers.remove(aventurier);
	}

	public Etat getEtat() {
		return etat;
	}
        
	public boolean estInnondé() {
		return etat == Etat.INNONDEE;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

        public abstract String getPiece();

        

}
