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
        if (pos2.col > 1 && pos2.col < 6 && pos2.lig > 1 && pos2.lig < 6) {

            Position posGauche = new Position(pos2.col - 1, pos2.lig);
            int i = posGauche.col;
            int j = posGauche.lig;
            if (!(((i == 1 || i == 6) && (j == 1 || j == 2 || j == 5 || j == 6)) || ((i == 2 || i == 5) && (j == 1 || j == 6)))) {
                tuiles.add(grille.getTuileP(posGauche));
            }

            Position posDroite = new Position(pos2.col + 1, pos2.lig);
            i = posDroite.col;
            j = posDroite.lig;
            if (!(((i == 1 || i == 6) && (j == 1 || j == 2 || j == 5 || j == 6)) || ((i == 2 || i == 5) && (j == 1 || j == 6)))) {
                tuiles.add(grille.getTuileP(posDroite));
            }

            Position posBas = new Position(pos2.col, pos2.lig + 1);
            i = posBas.col;
            j = posBas.lig;
            if (!(((i == 1 || i == 6) && (j == 1 || j == 2 || j == 5 || j == 6)) || ((i == 2 || i == 5) && (j == 1 || j == 6)))) {
                tuiles.add(grille.getTuileP(posBas));
            }

            Position posHaut = new Position(pos2.col, pos2.lig - 1);
            i = posHaut.col;
            j = posHaut.lig;
            if (!(((i == 1 || i == 6) && (j == 1 || j == 2 || j == 5 || j == 6)) || ((i == 2 || i == 5) && (j == 1 || j == 6)))) {
                tuiles.add(grille.getTuileP(posHaut));
            }
        }
        return tuiles;
    }

    public boolean verifDeplacement2(Position pos2, Tuile tuile, Grille grille) {

        ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();

        Position posj = getPosition();

        for (Tuile tuil : get4CartesAutourPosition(grille, posj)) {
            if (tuil.getEtat() == Etat.ASSECHEE) {
                tuilesAccessibles.add(tuil);
            } else if (tuil.getEtat() == Etat.INNONDEE) {

                //System.out.println(grille.getPositionP(tuil));
                for (Tuile tuil2 : get4CartesAutourPosition(grille, grille.getPositionP(tuil))) {
                    if (tuil2.getEtat() == Etat.ASSECHEE) {
                        tuilesAccessibles.add(tuil2);
                    } else if (tuil2.getEtat() == Etat.INNONDEE) {

                        for (Tuile tuil3 : get4CartesAutourPosition(grille, grille.getPositionP(tuil2))) {
                            if (tuil3.getEtat() == Etat.ASSECHEE) {
                                tuilesAccessibles.add(tuil3);
                            }

                        }

                    }
                }
            }
        }
        System.out.println("Tuiles presente : " + tuilesAccessibles.size());
        

        //System.out.println("Tuiles presente : " + tuilesAccessibles.size());
        if (tuilesAccessibles.contains(tuile)) {
            tuilesAccessibles.clear();
            return true;
        } else {
            tuilesAccessibles.clear();
            return false;
        }
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
