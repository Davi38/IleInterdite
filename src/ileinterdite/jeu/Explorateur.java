/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import static java.lang.Math.abs;

/**
 *
 * @author giacintf
 */
public class Explorateur extends Aventurier {

    private Couleur couleur = Couleur.VERT;

    Explorateur() {
        super(Role.EXPLORATEUR);
    }

    public boolean verifDeplacement(Position pos2) {
        Position posj = getPosition();
        
        return abs(posj.lig - pos2.lig) < 2 && abs(posj.col - pos2.col) < 2;
    }
    
    public boolean verifAssechement(Position pos2) {
        return verifDeplacement(pos2);
    }

}
