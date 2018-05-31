/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import java.awt.Color;
import static java.lang.Math.abs;

/**
 *
 * @author giacintf
 */
public class Plongeur extends Aventurier {

	private Color couleur = Color.BLACK;

    public Plongeur(Position pos) {
        super(Role.PLONGEUR,pos);
    }

    @Override
    public boolean verifDeplacement(Position pos2) {
        Position posj = getPosition();
        if(abs(posj.col-pos2.col)<=2 ^ abs(posj.lig-pos2.lig)<= 2){
           return true; 
        }
        return false;
    }

    @Override
    public boolean verifAssechement(Position pos2) {
        return verifDeplacement(pos2);
    }
    
    public Color getColor(){
        return couleur;
    }

}
