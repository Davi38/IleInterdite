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
public class Ingenieur extends Aventurier {

    private Color couleur = Color.ORANGE;
    private int nbAssechement = 0;

    Ingenieur(Position pos) {
        super(Role.INGENIEUR,pos,3);
    }
        
    @Override
    public boolean verifDeplacement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        int l = abs(posj.col-pos2.col);
        int c = abs(posj.lig-pos2.lig);
        if((l==1&&c==0)||(l==0&&c==1)||(l==0&&c==0)) {
            return tuile.estInnondé() || tuile.getEtat() == Etat.ASSECHEE;
        }
        return false;
    }
    
    // Pour gerer les 2 actions assechements pour 1 action. Le reste est gerer dans controleur CLICTUILE
    public int getNbAssechement() {
        return this.nbAssechement;
    }
    
    // Pour gerer les 2 actions assechements pour 1 action. Le reste est gerer dans controleur CLICTUILE
    public void incrementerNbAssechement() {
        this.nbAssechement += 1;
    }
        
    @Override
    public boolean verifAssechement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        int l = abs(posj.col-pos2.col);
        int c = abs(posj.lig-pos2.lig);
        if((l==1&&c==0)||(l==0&&c==1)||(l==0&&c==0)) {
            return tuile.estInnondé();
        }
        return false;
    }
    public Color getColor(){
        return couleur;
    }

}
