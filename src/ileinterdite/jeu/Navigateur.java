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
public class Navigateur extends Aventurier {

	private Color couleur = Color.YELLOW;

    public Navigateur(Position pos) {
        super(Role.NAVIGATEUR,pos);
    }

    @Override
    public boolean verifDeplacement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        if(abs(posj.col-pos2.col)==1 ^ abs(posj.lig-pos2.lig)== 1){
           return tuile.getEtat() == Etat.ASSECHEE; 
        }
           return false;
    }

    @Override
    public boolean verifAssechement(Position pos2, Tuile tuile) {
         Position posj = getPosition();
        if(abs(posj.col-pos2.col)==1 ^ abs(posj.lig-pos2.lig)== 1){
           return tuile.estInnondé(); 
        }
           return false;
        }
    
    public Color getColor(){
        return couleur;
    }

}
