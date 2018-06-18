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
public class JeuVue extends Observe {

    HashMap<JButton, Position> boutPieces;
    ArrayList<JButton> boutCartes;
    JFrame window1 ;
    JButton boutonA;
    JButton boutonD;
    JButton boutonFT;
    //
    

    public JeuVue(Grille grille) {
        window1 = new JFrame("Jeu");
        JPanel mainPanel = new JPanel(new BorderLayout());
        window1.add(mainPanel);
      
        JPanel zoneJeu = new JPanel(new GridLayout(6, 6));
        boutPieces = new HashMap<JButton, Position>();
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
                    boutPieces.put(bouton,pos);
                    bouton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Message m = new Message();
                            m.type = TypeMessage.CLICTUILE;
                            m.pos = boutPieces.get(bouton);
                            notifierObservateur(m);
                        }
                    });
                    
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
        
        window1.add(cartes, BorderLayout.SOUTH);
        for (int i = 0; i < 5; i++) {
            JButton b = new JButton("Aucune carte");
            cartes.add(b);
            boutCartes.add(b);
        }

        // ####################################################################################
        // les actions a l'est
        JPanel actions = new JPanel(new GridLayout(5, 1));
        mainPanel.add(actions, BorderLayout.EAST);
        boutonA = new JButton("Assécher");
        boutonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypeMessage.ASSECHER;
                notifierObservateur(m);
            }
        });
        actions.add(boutonA);
        boutonD = new JButton("Se déplacer");
       
        boutonD.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Message m = new Message();
                        m.type = TypeMessage.DEPLACER;
                        
                        notifierObservateur(m);
            }
        });
        actions.add(boutonD);
        actions.add(new JButton("Donner une carte trésor"));
        actions.add(new JButton("Gagner un trésor"));
        boutonFT = new JButton("Finir son tour");
        boutonFT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   Message m = new Message();
                   m.type = TypeMessage.FIN_TOUR;
                   notifierObservateur(m);
            }
        });
        actions.add(boutonFT);
        
    }

    public void initJoueur(Aventurier j) {
        ArrayList<Carte_Tresor> cartes = j.getCarteTresor();
        for (int i = 0; i < cartes.size(); i++) {
            boutCartes.get(i).setText(cartes.get(i).getType());
        }
        for (int i = cartes.size(); i < 5; i++) {
            boutCartes.get(i).setText("Acune carte");
        }
        
        boutonD.setEnabled(true);
        boutonA.setEnabled(true);
        boutonFT.setEnabled(true);

    }

    public void majGrille(Grille g) {
        for (JButton bouton : boutPieces.keySet()) {
            Position pos = boutPieces.get(bouton);            
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
    
    public void afficherFenetre(){
       window1.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window1.setSize(dim.height, dim.width);
        window1.setVisible(true);
    }
    
    public void majDeplacement(Aventurier adv,Grille gr){
        for(JButton b : boutPieces.keySet()){
            Position pos = boutPieces.get(b);
            Tuile t = gr.getTuileP(pos);
            if(adv.verifDeplacement(pos, t)){
                b.setBackground(Color.red);
            }
        }
        
    }
    
    public void majAssechement(Aventurier adv,Grille gr){
        for(JButton b : boutPieces.keySet()){
            Position pos = boutPieces.get(b);
            Tuile t = gr.getTuileP(pos);
            if(adv.verifAssechement(pos, t)){
                b.setBackground(Color.red);
            }
        }        
    }

    public void finT() {
        boutonD.setEnabled(false);
        boutonA.setEnabled(false);
        boutonFT.setEnabled(false);
    }

    
    
}

   
    


 
    
