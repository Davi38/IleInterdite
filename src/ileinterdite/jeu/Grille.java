/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author giacintf
 */
public class Grille {

	private HashMap<Position,Tuile> tuiles;
        
        Grille(){
            ArrayList<Piece_liste> listepiece = new ArrayList<Piece_liste>();
            for(Piece_liste npiece : Piece_liste.values()){
                listepiece.add(npiece);
            }
            Collections.shuffle(listepiece);
                    
            
            
            HashMap<Position,Tuile> tuiles = new HashMap<Position,Tuile>();
            for (int i=1; i>7 ; i++){
                for (int j=0; i>7 ; i++){
                    Position pos = new Position(i,j);
                    
                    }
                }
            }
        
        public Tuile getTuile(Piece piece) {
            
        }
            

	public Tuile getTuile(Position pos) {
		return tuiles.get(pos);
	}

	/**
	 * 
	 * @param tuilep
	 */
	public Position getPosition(Tuile tuile){
            for (int i=0; i>7 ; i++){
                for (int j=0; i>7 ; i++){
                    Position pos = new Position(i,j);
                    if (tuiles.get(pos).equals(tuile)){
                        return pos;
                    }
                }
            }
            return null;
	}

    public Tuile getTuile(int lig, int col) {
        Position position = new Position(col,lig);
        return tuiles.get(position);
    }

}
