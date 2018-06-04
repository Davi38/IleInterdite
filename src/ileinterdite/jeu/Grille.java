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
import java.util.Iterator;

/**
 *
 * @author giacintf
 */
public class Grille {

    private HashMap<Position, Tuile> tuiles;

    Grille(){
        ArrayList<Piece_liste> listepiece = new ArrayList<Piece_liste>();
        for (Piece_liste npiece : Piece_liste.values()) {
            listepiece.add(npiece);
        }
        Collections.shuffle(listepiece);
        tuiles = new HashMap<Position, Tuile>() ;
        

        for (int i = 1; i < 7; i++) {
            for (int j = 1; i < 7; i++) {
                Position pos = new Position(i,j);
                  if ( ((i==1||i==6)&& (j==1 ||j==2||j==5 || j==6))||((i==2||i==5)&&(j==1 || j==6))){
                      Tuile_null tuile = new Tuile_null();
                      tuiles.put(pos, tuile);
                }else{
                      Ile tuile = new Ile(listepiece.get(0));
                      listepiece.remove(0);
                      tuiles.put(pos, tuile);
                  }

            }
        }
    }

    

    /**
     *
     * @param tuilep
     */
    public Position getPosition(Tuile tuile) {
        for (int i = 0; i > 7; i++) {
            for (int j = 0; i > 7; i++) {
                Position pos = new Position(i, j);
                if (tuiles.get(pos).equals(tuile)) {
                    return pos;
                }
            }
        }
        return null;
    }

    
    public HashMap<Position, Tuile> getGrille() {
        return tuiles;
    }

   public Tuile getTuilePL(Piece_liste pc) {
        
        for (Tuile t : tuiles.values()){
            System.out.println("1");
            if(t.getPiece().equals(pc)){
                return t;
            }
        }
        return null;
    }

}
