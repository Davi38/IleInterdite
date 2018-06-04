/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import ileinterdite.jeu.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author davidovl
 */
public class JeuVue extends JPanel {


    private HashMap<JButton, Position> plateau;
    
    JPanel zoneJeu = new JPanel(new GridLayout(6, 6));

    public JeuVue(Grille grille) {


        JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);

        // ####################################################################################
        // grille au nord
        this.add(zoneJeu, BorderLayout.CENTER);
        
        int i = 0;
        for (Tuile tuile : grille.getGrille().values()) {
            if (tuile instanceof Tuile_null) {
                zoneJeu.add(new JLabel());
            } else {
                JButton bouton = new JButton(tuile.getPiece());
                if (tuile.getEtat() == Etat.INNONDEE) {
                    bouton.setBackground(Color.BLUE);
                } else if (tuile.getEtat() == Etat.NOYEE) {
                    bouton.setBackground(Color.BLACK);
                }
                plateau.put(bouton, new Position(i/6, i%6));
                zoneJeu.add(bouton);
            }
            i += 1;
        }
        // ####################################################################################
        // les cartes au sud
        JPanel cartes = new JPanel(new GridLayout(1, 6));
        this.add(cartes, BorderLayout.SOUTH);

        cartes.add(new JButton("Ici une carte"));
        cartes.add(new JButton("Ici une carte"));
        cartes.add(new JButton("Ici une carte"));
        cartes.add(new JButton("Ici une carte"));
        cartes.add(new JButton("Ici une carte"));

        // ####################################################################################
        // les actions a l'est
        JPanel actions = new JPanel(new GridLayout(5, 1));
        this.add(actions, BorderLayout.EAST);

        actions.add(new JButton("Assécher"));
        actions.add(new JButton("Se déplacer"));
        actions.add(new JButton("Donner une carte trésor"));
        actions.add(new JButton("Gagner un trésor"));
        actions.add(new JButton("Finir son tour"));
    }
    
    public void mettreAJourZoneDeJeu() {
    }



    //public static void main(String[] args) {
    //    JeuVue jeuVue = new JeuVue();
    //    jeuVue.afficher();
    //}

}
