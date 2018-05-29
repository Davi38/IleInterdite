/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author giacintf
 */
public class Ile extends Tuile{
    private ArrayList<Aventurier> aventuriers;
    private Piece_liste piece;
    
    Ile(Piece_liste piece){
        super();
        this.piece = piece;
        ArrayList<Aventurier> aventuriers = new ArrayList<Aventurier>();
        
    }

    @Override
    public String getPiece() {
        return piece.toString();
    }
       

       
       
}
