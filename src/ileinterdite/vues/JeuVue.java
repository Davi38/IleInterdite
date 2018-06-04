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


    
    JPanel zoneJeu ;

    public JeuVue(Grille grille) {


        JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);
        zoneJeu = new JPanel(new GridLayout(6, 6));

        // ####################################################################################
        // grille au nord
        
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Position pos = new Position(i,j);
                Tuile t = grille.getTuileP(pos);
            if (t instanceof Tuile_null){
                zoneJeu.add(new JLabel());
            } else {
                JButton bouton = new JButton(t.getPiece());
                if (t.getEtat() == Etat.INNONDEE) {
                    bouton.setBackground(Color.BLUE);
                }else if (t.getEtat() == Etat.NOYEE) {
                    bouton.setBackground(Color.BLACK);
                }else{
                     bouton.setBackground(Color.ORANGE);
                }
                zoneJeu.add(bouton);
            }
        }}
        
        this.add(zoneJeu, BorderLayout.CENTER);
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
    
    //public void mettreAJourZoneDeJeu() {
    //}



    //public static void main(String[] args) {
    //    JeuVue jeuVue = new JeuVue();
    //    jeuVue.afficher();
    //}

}
