/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import java.awt.Color;

/**
 *
 * @author giacintf
 */
public class Navigateur extends Aventurier {

	private Color couleur = Color.YELLOW;

    public Navigateur() {
        super(Role.NAVIGATEUR);
    }

    @Override
    public boolean verifDeplacement(Position pos2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifAssechement(Position pos2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Color getColor(){
        return couleur;
    }

}
