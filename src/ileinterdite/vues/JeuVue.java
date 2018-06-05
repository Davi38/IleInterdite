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
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    HashMap<Position, JButton> boutPieces;
    ArrayList<JButton> boutCartes;

    public JeuVue(Grille grille) {

        JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);
        JPanel zoneJeu = new JPanel(new GridLayout(6, 6));
        boutPieces = new HashMap<Position, JButton>();
        boutCartes = new ArrayList<JButton>();

        // ####################################################################################
        // grille au nord
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Position pos = new Position(i, j);

                Tuile t = grille.getTuileP(pos);

                if (t.getPiece() == Piece_liste.NULL.toString()) {
                    zoneJeu.add(new JLabel());
                } else {
                    JButton bouton = new JButton(t.getPiece());
                    bouton.setPreferredSize(new Dimension(150, 150));
                    if (t.getEtat() == Etat.INNONDEE) {
                        bouton.setBackground(Color.CYAN);

                    } else if (t.getEtat() == Etat.NOYEE) {
                        bouton.setBackground(Color.BLACK);
                    } else {
                        bouton.setBackground(Color.ORANGE);
                    }
                    boutPieces.put(pos, bouton);
                    zoneJeu.add(bouton);
                    Ile ile = (Ile) t;
                    /*ArrayList<Aventurier> advs = ile.getAventuriers();
                    System.out.println(advs.size());
                    if(!advs.isEmpty()){
                        for(int numJ=0; numJ< advs.size();numJ++){
                           g.setColor(advs.get(numJ).getColor());;
                           g.fillOval(pos.getLig()*150 +35*numJ+10, pos.getCol()*150 +35*numJ+10, 30, 30);
                        }
                    }*/

                }
            }
        }
        
        mainPanel.add(zoneJeu, BorderLayout.CENTER);
        // ####################################################################################
        // les cartes au sud
        JPanel cartes = new JPanel(new GridLayout(1, 6));
        this.add(cartes, BorderLayout.SOUTH);
        for (int i = 0; i < 5; i++) {
            JButton b = new JButton("Aucune carte");
            cartes.add(b);
            boutCartes.add(b);
        }

        // ####################################################################################
        // les actions a l'est
        JPanel actions = new JPanel(new GridLayout(5, 1));
        mainPanel.add(actions, BorderLayout.EAST);

        actions.add(new JButton("Assécher"));
        actions.add(new JButton("Se déplacer"));
        actions.add(new JButton("Donner une carte trésor"));
        actions.add(new JButton("Gagner un trésor"));
        actions.add(new JButton("Finir son tour"));
        this.paintComponent(getGraphics(), grille);
    }

    public void initJoueur(Aventurier j) {
        ArrayList<Carte_Tresor> cartes = j.getCarteTresor();
        for (int i = 0; i < cartes.size(); i++) {
            boutCartes.get(i).setText(cartes.get(i).getType());
        }
        for (int i = cartes.size(); i < 5; i++) {
            boutCartes.get(i).setText("Acune carte");
        }

    }

    public void paintComponent(Graphics graph, Grille grille) {
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Position pos = new Position(i, j);
                Tuile t = grille.getTuileP(pos);
                if (t.getPiece() != Piece_liste.NULL.toString()) {
                    Ile ile = (Ile) t;
                    ArrayList<Aventurier> listeJ = ile.getAventuriers();
                    if (!listeJ.isEmpty()) {
                       for(int numJ=0; numJ< listeJ.size();numJ++){
                           graph.setColor(listeJ.get(numJ).getColor());;
                           graph.fillOval(pos.getLig()*150 +35*numJ+10, pos.getCol()*150 +35*numJ+10, 30, 30);
                        }
                    }
                }
            }
        }
    }

}
