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
public class Messager extends Aventurier {

    private Color couleur = Color.DARK_GRAY;
    
    Messager(Position pos) {
        super(Role.MESSAGER,pos);
    }

    public boolean verifDeplacement(Position pos2) {
        Position posj = getPosition();
        if (abs(posj.col - pos2.col) == 1 ^ abs(posj.lig - pos2.lig) == 1) {
            return true;
        }
        return false;
    }

    public boolean verifAssechement(Position pos2) {
        Position posj = getPosition();
        if (abs(posj.col - pos2.col) == 1 ^ abs(posj.lig - pos2.lig) == 1) {
            return true;
        }
        return false;
    }
    
    public Color getColor(){
        return couleur;
    }

}
