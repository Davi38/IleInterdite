/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

/**
 *
 * @author giacintf
 */
public class Plongeur extends Aventurier {

	private Couleur couleur = Couleur.VIOLET;

    public Plongeur() {
        super(Role.PLONGEUR);
    }

    @Override
    public boolean verifDeplacement(Position pos2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifAssechement(Position pos2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
