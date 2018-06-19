/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import java.awt.Color;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author giacintf
 */
public class Plongeur extends Aventurier {

    private Color couleur = Color.BLACK;
    
    private Grille grille;

    public Plongeur(Position pos) {
        super(Role.PLONGEUR, pos, 3);
    }

    public ArrayList<Tuile> get4CartesAutourPosition(Grille grille, Position pos2) {
        ArrayList<Tuile> tuiles = new ArrayList<>();
        if (pos2.col > 1) {
            Position posGauche = new Position(pos2.col - 1, pos2.lig);
            System.out.println(posGauche);
            if (grille.getGrille().containsValue(grille.getTuileP(posGauche))) {
                tuiles.add(grille.getTuileP(posGauche));
            }
        }
        if (pos2.col < 6) {
            Position posDroite = new Position(pos2.col + 1, pos2.lig);
            if (grille.getGrille().containsValue(grille.getTuileP(posDroite))) {
                tuiles.add(grille.getTuileP(posDroite));
            }
        }
        if (pos2.lig > 1) {
            Position posBas = new Position(pos2.col, pos2.lig + 1);
            if (grille.getGrille().containsValue(grille.getTuileP(posBas))) {
                tuiles.add(grille.getTuileP(posBas));
            }
        }
        if (pos2.lig < 6) {
            Position posHaut = new Position(pos2.col, pos2.lig - 1);
            if (grille.getGrille().containsValue(grille.getTuileP(posHaut))) {
                tuiles.add(grille.getTuileP(posHaut));
            }
        }
        return tuiles;
    }

    public boolean verifDeplacement2(Position pos2, Tuile tuile, Grille grille) {

        ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();

        Position posj = getPosition();
        
        //System.out.println("COUCOU !");
        //System.out.println(pos2.col + 1);
        //System.out.println(pos2.lig);
        
        for (Tuile tuil : get4CartesAutourPosition(grille, pos2)) {
            return true;
        }
        return false;

        /*if ((l == 1 && c == 0) || (l == 0 && c == 1)) {
            return tuile.getEtat() == Etat.ASSECHEE; // peut se deplacer sur une tuile assechée à côté de lui
        }

        Position posGauche = new Position(posj.col, posj.lig);
        if (grille.getTuileP(posGauche).getEtat() != Etat.ASSECHEE) {

        }

        return true; */
    }
    
    public void setGrille(Grille grille) {
        this.grille = grille;
    }
    
    public Grille getGrille() {
        return this.grille;
    }

    @Override
    public boolean verifDeplacement(Position pos2, Tuile tuile) {

        return verifDeplacement2(pos2, tuile, getGrille());
    }

    @Override
    public boolean verifAssechement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        if (abs(posj.col - pos2.col) <= 2 ^ abs(posj.lig - pos2.lig) <= 2) {
            return tuile.estInnondé();
        }
        return false;
    }

    public Color getColor() {
        return couleur;
    }

}
