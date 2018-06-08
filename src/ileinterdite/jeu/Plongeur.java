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
    public boolean verifDeplacement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        if(abs(posj.col-pos2.col)<=2 ^ abs(posj.lig-pos2.lig)<= 2){
           return tuile.getEtat()==Etat.ASSECHEE; 
        }
        return false;
    }

    @Override
    public boolean verifAssechement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        if(abs(posj.col-pos2.col)<=2 ^ abs(posj.lig-pos2.lig)<= 2){
           return tuile.estInnondé();
        }
        return false;
    }
    
    public Color getColor(){
        return couleur;
    }

}
