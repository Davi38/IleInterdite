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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
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
    private HashMap<TypeTrésor, ImageTresor> imgTres;
    private JFrame window1;

    private JButton boutonA;
    private JButton boutonD;
    private JButton boutonFT;
    private JButton boutonDC;
    private JButton boutonGT;

    private JPanel pCartes;
    private JLabel etatJeu;
    private VueNiveau nv;
    private VueRegles regles;
    //

    public JeuVue(Grille grille, int niveauEau, Observateur o) {
        this.addObservateur(o);
        window1 = new JFrame("Jeu");
        Image imgFond = null;
        
        try {
            imgFond = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/autre/fond.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de fond.png");
        }
        
        
        
        JPanel mainPanel = new PanelFond(imgFond,new BorderLayout());
        
        window1.add(mainPanel);

        regles = new VueRegles();
        JPanel zoneJeu = new JPanel(new GridLayout(6, 6));
        zoneJeu.setOpaque(false);
        boutPieces = new HashMap<BoutonTuile, Position>();
        boutCartes = new ArrayList<JButton>();

        JPanel haut = new JPanel(new BorderLayout());
        haut.setOpaque(false);
        JButton Bregles = new JButton("Regles du jeu");
        Bregles.setPreferredSize(new Dimension(140, 30));
        Bregles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!regles.estVisible()) {
                    regles.afficher();
                }
            }
        });
        haut.add(Bregles,BorderLayout.WEST);
        etatJeu = new JLabel("",SwingConstants.CENTER);
        etatJeu.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        etatJeu.setForeground(Color.white);
        haut.add(etatJeu,BorderLayout.CENTER);
        
        
        JButton quitter = new JButton("Quitter");
        quitter.setPreferredSize(new Dimension(140, 30));
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window1.setVisible(false);
            }
        });
        
        haut.add(quitter,BorderLayout.EAST);
        mainPanel.add(haut, BorderLayout.NORTH);

        nv = new VueNiveau(niveauEau);
        nv.setOpaque(false);
        mainPanel.add(nv, BorderLayout.WEST);

        // ####################################################################################
        // grille au centre
        int comptTres = 0;
        TypeTrésor[] listeT = TypeTrésor.values();
        imgTres = new HashMap<TypeTrésor, ImageTresor>();
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Position pos = new Position(i, j);

                Tuile t = grille.getTuileP(pos);

                if (t.getPiece() == Piece_liste.NULL.toString()) {
                    if ((pos.getLig() == 1 || pos.getLig() == 6) && (pos.getCol() == 1 || pos.getCol() == 6)) {

                        ImageTresor tres = new ImageTresor(listeT[comptTres]);
                        zoneJeu.add(tres);
                        imgTres.put(listeT[comptTres], tres);
                        comptTres += 1;

                    } else {
                        zoneJeu.add(new JLabel());
                    }
                } else {
                    String nomP = t.getPiece();
                    BoutonTuile bT = new BoutonTuile(nomP, pos, o);
                    JButton bouton = bT.getBoutonTuile();
                    boutPieces.put(bT, pos);
                    zoneJeu.add(bouton);
                }
            }
        }

        //setImgTresEtat(TypeTrésor.CALICE,true);
        majGrille(grille);

        mainPanel.add(zoneJeu, BorderLayout.CENTER);

        pCartes = new JPanel();

        window1.add(pCartes, BorderLayout.SOUTH);
        // ####################################################################################
        // les actions a l'est
        JPanel actions = new JPanel(new GridLayout(5, 1));
        actions.setOpaque(false);
        mainPanel.add(actions, BorderLayout.EAST);
        boutonA = new JButton();
        boutonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypeMessage.ASSECHER;
                notifierObservateur(m);
            }
        });
        Image imgA=null;
        try {
            imgA = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/autre/assecher.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de assecher.png");
        }
        PanelFond2 asch = new PanelFond2(imgA, new BorderLayout());
        asch.add(new JLabel("Assecher"),BorderLayout.SOUTH);
        boutonA.add(asch);
        actions.add(boutonA);
        
        
        boutonD = new JButton();
        Image imgD =null;
        try {
            imgD = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/autre/iconMove.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de iconMove.png");
        }
        PanelFond2 depl = new PanelFond2(imgD, new BorderLayout());
        depl.add(new JLabel("Deplacer"),BorderLayout.SOUTH);
        boutonD.add(depl);
        actions.add(boutonA);
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
        
        
        boutonDC = new JButton();
        Image imgDC =null;
        try {
            imgDC = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/autre/donner.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de donner.png");
        }
        PanelFond2 donner = new PanelFond2(imgDC, new BorderLayout());
        donner.add(new JLabel("Donner une carte"),BorderLayout.SOUTH);
        boutonDC.add(donner);
        
        
        
        boutonDC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypeMessage.DONNER_CARTE;
                notifierObservateur(m);
            }
        });

        actions.add(boutonDC);
        
        
        
        boutonGT = new JButton();
         Image imgGT =null;
        try {
            imgGT = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/autre/tresor.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de tresor.png");
        }
        PanelFond2 gagneT = new PanelFond2(imgGT, new BorderLayout());
        gagneT.add(new JLabel("Gagner un tresor"),BorderLayout.SOUTH);
        boutonGT.add(gagneT);
        
        
        
        
        boutonGT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypeMessage.GAGNER_TRESOR;
                notifierObservateur(m);
            }
        });
        actions.add(boutonGT);
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

    public void initJoueur(Aventurier j, String nomJ) {
        majCartes(j);
        String fullClassName = j.getClass().toString();
        etatJeu.setText("Tour de " + nomJ + " (" + fullClassName.substring(fullClassName.lastIndexOf('.') + 1) + ")");
        etatJeu.setForeground(j.getColor());
        if (j.getCarteTresor().size() < 6) {
            boutonD.setEnabled(true);
            boutonA.setEnabled(true);
            boutonFT.setEnabled(true);
            boutonDC.setEnabled(true);
            boutonGT.setEnabled(true);
        } else {
            etatJeu.setText(etatJeu.getText() + " (Defausser " + (j.getCarteTresor().size() - 5) + " carte(s))");
        }

    }

    public void majCartes(Aventurier j) {
        ArrayList<Carte_Tresor> cartes = j.getCarteTresor();
        pCartes.removeAll();
        boutCartes.clear();
        pCartes.setLayout(new GridLayout(1, cartes.size()));
        for (int i = 0; i < cartes.size(); i++) {
            JButton b = new JButton(cartes.get(i).getType());
            pCartes.add(b);
            boutCartes.add(b);
            if (cartes.size() < 6) {
                if (b.getText().equals("SABLE") || b.getText().equals("HELICOPTERE")) {
                    b.setEnabled(true);
                } else {
                    b.setEnabled(false);
                }
                b.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Message m = new Message();
                        m.type = TypeMessage.CLIC_CARTE;
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
            } else {

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
        window1.add(pCartes, BorderLayout.SOUTH);

    }

    public void majGrille(Grille g) {
        for (BoutonTuile bouton : boutPieces.keySet()) {
            Position pos = boutPieces.get(bouton);
            Ile t = (Ile) g.getTuileP(pos);
            ArrayList<Aventurier> listeJ = t.getAventuriers();
            bouton.getBoutonTuile().setEnabled(false);
            bouton.majTuile(listeJ, t.getEtat());
            

        }
    }

    public void afficherFenetre() {
        window1.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window1.setSize(dim.height, dim.width);
        window1.setVisible(true);
    }

    public void cacherFenetre() {
        window1.setVisible(false);
    }

    public void majDeplacement(Aventurier adv, Grille gr) {
        for (BoutonTuile b : boutPieces.keySet()) {
            Position pos = boutPieces.get(b);
            Tuile t = gr.getTuileP(pos);
            if (adv.verifDeplacement(pos, t)) {
                b.paint(Color.red);
                b.getBoutonTuile().setEnabled(true);
            }
        }

    }

    public void majAssechement(Aventurier adv, Grille gr) {
        for (BoutonTuile b : boutPieces.keySet()) {
            Position pos = boutPieces.get(b);
            Tuile t = gr.getTuileP(pos);
            if (adv.verifAssechement(pos, t)) {
                b.paint(Color.red);
                b.getBoutonTuile().setEnabled(true);
            }
        }
    }

    public void finT() {
        boutonD.setEnabled(false);
        boutonA.setEnabled(false);
        boutonFT.setEnabled(false);
        boutonDC.setEnabled(false);
        boutonGT.setEnabled(false);
    }

    public void majNiveau(int niveauEau) {
        nv.setNiveau(niveauEau);
    }

    public void activerB() {
        for (JButton b : boutCartes) {
            b.setEnabled(true);
        }
    }

    public void desactiverB(Grille grille) {
        for (JButton b : boutCartes) {
            if (!(b.getText().equals("SABLE") || b.getText().equals("HELICOPTERE"))) {
                b.setEnabled(false);
            }
        }
        majGrille(grille);
    }

    public void colorJ(Aventurier advAct) {
        BoutonTuile b = getBT(advAct.getPosition());
        b.activerJ(advAct);
    }

    public void colorTousLesJ(Aventurier advAct) {
        for (BoutonTuile bouton : boutPieces.keySet()) {
            bouton.activerJ(advAct);
        }
    }

    // TRES SUREMENT A REVOIR !!!!
    public void colorTousLesJ() {
        for (BoutonTuile bouton : boutPieces.keySet()) {
            bouton.activerJ2();
        }
    }

    public BoutonTuile getBT(Position pos) {
        for (BoutonTuile b : boutPieces.keySet()) {
            if (boutPieces.get(b).equals(pos)) {
                return b;
            }
        }
        return null;
    }

    public void setImgTresEtat(TypeTrésor type, boolean etat) {
        ImageTresor img = imgTres.get(type);
        img.setRecupere(etat);

    }

    public boolean colorJ(ArrayList<Aventurier> jADepl, Grille grille) {
        if (!jADepl.isEmpty()) {
            desactiverB(grille);
            BoutonTuile b = getBT(jADepl.get(0).getPosition());
            return b.activerJ(jADepl);
        }
        return false;
    }
    
    public void colorAssechement(ArrayList<Position> posA){
        for(Position p : posA){
            BoutonTuile b = getBT(p);
            b.paint(Color.red);
            b.getBoutonTuile().setEnabled(true);
        }
    }
    
    public void desactiverBAct(){
        boutonD.setEnabled(false);
        boutonA.setEnabled(false);
        boutonFT.setEnabled(false);
        boutonDC.setEnabled(false);
        boutonGT.setEnabled(false);
    }
    
    public void desactiverBActsaufFT(){
        boutonD.setEnabled(false);
        boutonA.setEnabled(false);
        boutonDC.setEnabled(false);
        boutonGT.setEnabled(false);
    }
    
    public void desactiverCartes(){
        for(JButton b : boutCartes){
            b.setEnabled(false);
        }
    }
    
    public void activerBact(){
        boutonD.setEnabled(true);
        boutonA.setEnabled(true);
        boutonFT.setEnabled(true);
        boutonDC.setEnabled(true);
        boutonGT.setEnabled(true);
    }
    
    

}
