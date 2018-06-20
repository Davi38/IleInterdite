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
public class Explorateur extends Aventurier {

    private Color couleur = Color.GREEN;

    Explorateur(Position pos) {
        super(Role.EXPLORATEUR,pos,3);
    }

    public boolean verifDeplacement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        
        return (abs(posj.lig - pos2.lig) < 2 && abs(posj.col - pos2.col) < 2) && (tuile.getEtat()==Etat.ASSECHEE || tuile.getEtat()==Etat.INNONDEE) && !posj.equals(pos2);
    }
    
    public boolean verifAssechement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        return (abs(posj.lig - pos2.lig) < 2 && abs(posj.col - pos2.col) < 2)&& tuile.estInnondee();
    }
    
    public Color getColor(){
        return couleur;
    }

}
