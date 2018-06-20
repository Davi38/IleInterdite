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
        super(Role.NAVIGATEUR,pos,4);
    }

    @Override
    public boolean verifDeplacement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        int l = abs(posj.col-pos2.col);
        int c = abs(posj.lig-pos2.lig);
        if((l==1&&c==0)||(l==0&&c==1)) {
            return (tuile.getEtat()==Etat.ASSECHEE || tuile.getEtat()==Etat.INNONDEE) && !posj.equals(pos2);
        }
        return false;
    }

    @Override
    public boolean verifAssechement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        int l = abs(posj.col-pos2.col);
        int c = abs(posj.lig-pos2.lig);
        if((l==1&&c==0)||(l==0&&c==1)) {
            return tuile.estInnondee();
        }
        return false;
    }
    
    public Color getColor(){
        return couleur;
    }

}
