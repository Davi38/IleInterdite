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

    Grille() {
        ArrayList<Piece_liste> listepiece = new ArrayList<Piece_liste>();
        for (Piece_liste npiece : Piece_liste.values()) {
            listepiece.add(npiece);
        }
        Collections.shuffle(listepiece);

        //HashMap<Position, Tuile> tuiles = new HashMap<Position, Tuile>();
        for (int i = 1; i < 7; i++) {
            for (int j = 1; i < 7; i++) {
                Position pos = new Position(i, j);
                if ((i == 1 && (j == 1 || j == 2 || j == 5 || j == 6)) || (i == 2 && (j == 1 || j == 6)) || (i == 5 && (j == 1 || j == 2 || j == 5 || j == 6)) || (i == 6 && (j == 1 || j == 6))) {
                    Tuile_null tuileNulle = new Tuile_null();
                    tuiles.put(pos, tuileNulle);
                } else {
                    Tuile tuile = new Ile(listepiece.get(i * j));
                    tuiles.put(pos, tuile);
                }

            }
        }
    }

    public Tuile getTuile(Piece piece) {
        Iterator i = tuiles.keySet().iterator();
        while (i.hasNext()) {
            Tuile tuile = (Tuile) i.next();
            if (tuile.getPiece() == piece.name()) {
                return tuile;
            }
        }
        Tuile tuilenulle = new Tuile_null();
        return tuilenulle;
    }
    
    public Tuile getTuile(Piece_liste piece) {
        Iterator i = tuiles.keySet().iterator();
        while (i.hasNext()) {
            Tuile tuile = (Tuile) i.next();
            if (tuile.getPiece() == piece.name()) {
                return tuile;
            }
        }
        Tuile tuilenulle = new Tuile_null();
        return tuilenulle;
    }

    public Tuile getTuile(Position pos) {
        return tuiles.get(pos);
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

    public Tuile getTuile(int lig, int col) {
        Position position = new Position(col, lig);
        return tuiles.get(position);
    }
    
    public HashMap<Position, Tuile> getGrille() {
        return tuiles;
    }

}
