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
    Observe obs;

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
                    JButton bouton = new JButton("");
                    bouton.setPreferredSize(new Dimension(150, 150));
                    bouton.setLayout(new GridLayout(5,1));
                    JLabel nomB = new JLabel(t.getPiece());
                    nomB.setPreferredSize(new Dimension(50,30));
                    bouton.add(nomB);
                    bouton.add(new JLabel());
                    bouton.add(new JLabel());
                    bouton.add(new JLabel());
                    bouton.add(new JLabel());
                    boutPieces.put(pos, bouton);
                    zoneJeu.add(bouton);
                }
            }
        }
        majGrille(grille);
        System.out.println("Butoon");

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
        JButton boutonA = new JButton("Assécher");
        boutonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypeMessage.ASSECHER);
            }
        });
        actions.add(boutonA);
        actions.add(new JButton("Se déplacer"));
        actions.add(new JButton("Donner une carte trésor"));
        actions.add(new JButton("Gagner un trésor"));
        actions.add(new JButton("Finir son tour"));
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

    public void majGrille(Grille g) {
        for (Position pos : boutPieces.keySet()) {
            System.out.println(pos);
            JButton bouton = boutPieces.get(pos);
            Ile t = (Ile) g.getTuileP(pos);
            if (t.getPiece() != "NULL") {
                ArrayList<Aventurier> listeJ = t.getAventuriers();
                for (int i=1; i<5;i++){
                    JLabel lbl = (JLabel)bouton.getComponent(i);
                    lbl.setText("");
                    }
                if (!listeJ.isEmpty()) {
                    String nomB = t.getPiece();
                    for (int numJ = 0; numJ < listeJ.size(); numJ++) {
                         JLabel lbl = (JLabel)bouton.getComponent(numJ+1);
                         lbl.setText(listeJ.get(numJ).getRole().toString());
                    }
                }

                }
                if (t.getEtat() == Etat.INNONDEE) {
                    bouton.setBackground(Color.CYAN);

                } else if (t.getEtat() == Etat.NOYEE) {
                    bouton.setBackground(Color.BLACK);
                } else {
                    bouton.setBackground(Color.ORANGE);
                }

            }
        }
    }

