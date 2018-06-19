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
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    private HashMap<BoutonTuile, Position> boutPieces;
    private ArrayList<JButton> boutCartes;
    private JFrame window1 ;
    
    private JButton boutonA;
    private JButton boutonD;
    private JButton boutonFT;
    private JButton boutonDC;
    
    private JPanel pCartes;
    private JLabel etatJeu;
    private VueNiveau nv;
    //
    

    public JeuVue(Grille grille,int niveauEau,Observateur o) {
        this.addObservateur(o);
        window1 = new JFrame("Jeu");
        JPanel mainPanel = new JPanel(new BorderLayout());
        window1.add(mainPanel);
      
        JPanel zoneJeu = new JPanel(new GridLayout(6, 6));
        boutPieces = new HashMap<BoutonTuile, Position>();
        boutCartes = new ArrayList<JButton>();
        etatJeu = new JLabel();
        mainPanel.add(etatJeu,BorderLayout.NORTH);
        nv = new VueNiveau(niveauEau);
        mainPanel.add(nv,BorderLayout.WEST);
         
        // ####################################################################################
        // grille au centre
for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Position pos = new Position(i, j);

                Tuile t = grille.getTuileP(pos);

                if (t.getPiece() == Piece_liste.NULL.toString()) {
                    zoneJeu.add(new JLabel());
                } else {
                    String nomP = t.getPiece();
                    BoutonTuile bT = new BoutonTuile(nomP,pos,o);  
                    JButton bouton = bT.getBoutonTuile();
                    boutPieces.put(bT,pos);
                    zoneJeu.add(bouton);
                }
            }
        }
        
        
        majGrille(grille);

        mainPanel.add(zoneJeu, BorderLayout.CENTER);
        
        
        pCartes = new JPanel();
        
        
        window1.add(pCartes, BorderLayout.SOUTH);
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
        boutonDC = new JButton("Donner une carte trésor");
        boutonDC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypeMessage.DONNER_CARTE;
            }
        });
        
        actions.add(boutonDC);
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

    public void initJoueur(Aventurier j,String nomJ) { 
        majCartes(j);
        String fullClassName = j.getClass().toString();
        etatJeu.setText("Tour de "+ nomJ + " (" +fullClassName.substring(fullClassName.lastIndexOf('.')+1)+ ")");
        if(j.getCarteTresor().size()<6){
            boutonD.setEnabled(true);
            boutonA.setEnabled(true);
            boutonFT.setEnabled(true);
            boutonDC.setEnabled(true);}
        else{
            etatJeu.setText(etatJeu.getText() + " (Defausser "+ (j.getCarteTresor().size()-5) +" carte(s))");
        }
        
    }
    
    public void majCartes(Aventurier j){
        ArrayList<Carte_Tresor> cartes = j.getCarteTresor();
        pCartes.removeAll();
        boutCartes.clear();
        pCartes.setLayout(new GridLayout(1,cartes.size()));
        for (int i = 0; i < cartes.size(); i++) {
            JButton b = new JButton( cartes.get(i).getType());
            pCartes.add(b);
            boutCartes.add(b);
            if(cartes.size()<6){
                if(b.getText().equals("SABLE")||b.getText().equals("HELICOPTERE"))
                    b.setEnabled(true);
                else{
                    b.setEnabled(false);
                }
                b.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Message m = new Message();
                        m.type = TypeMessage.CLIC_CARTE;
                        boutCartes.indexOf(b);
                        notifierObservateur(m); 
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
            }else{
               
               b.addMouseListener(new MouseListener() {
                   @Override
                   public void mouseClicked(MouseEvent e) {
                       Message m = new Message();
                        m.type = TypeMessage.DEFFAUSE;
                        m.ind = boutCartes.indexOf(b);
                        notifierObservateur(m);
                   }

                   @Override
                   public void mousePressed(MouseEvent e) {
                   }

                   @Override
                   public void mouseReleased(MouseEvent e) {
                   }

                   @Override
                   public void mouseEntered(MouseEvent e) {
                   }

                   @Override
                   public void mouseExited(MouseEvent e) {
                   }
               });
            }
            
            
        }
        window1.add(pCartes,BorderLayout.SOUTH);
        
    }

     public void majGrille(Grille g) {
        for (BoutonTuile bouton : boutPieces.keySet()) {
            Position pos = boutPieces.get(bouton);
            Ile t = (Ile) g.getTuileP(pos);
            ArrayList<Aventurier> listeJ = t.getAventuriers();
            Color bg;
                if (t.getEtat() == Etat.INNONDEE) {
                    bg=Color.CYAN;

                } else if (t.getEtat() == Etat.NOYEE) {
                     bg=Color.BLACK;
                } else {
                     bg=Color.ORANGE;
                }
            bouton.majTuile(listeJ,bg);

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
        for(BoutonTuile b : boutPieces.keySet()){
            Position pos = boutPieces.get(b);
            Tuile t = gr.getTuileP(pos);
            if(adv.verifDeplacement(pos, t)){
                b.paint(Color.red);
            }
        }
        
    }
    
    public void majAssechement(Aventurier adv,Grille gr){
        for(BoutonTuile b : boutPieces.keySet()){
            Position pos = boutPieces.get(b);
            Tuile t = gr.getTuileP(pos);
            if(adv.verifAssechement(pos, t)){
               b.paint(Color.red); 
            }
        }        
    }

    public void finT() {
        boutonD.setEnabled(false);
        boutonA.setEnabled(false);
        boutonFT.setEnabled(false);
        boutonDC.setEnabled(false);
    }

    public void majNiveau(int niveauEau) {
        nv.setNiveau(niveauEau);
    }

    
    
}

   
    


 
    
