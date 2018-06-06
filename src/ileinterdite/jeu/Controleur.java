/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import ileinterdite.vues.Vue;
import ileinterdite.vues.VueAventurier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import ileinterdite.vues.JeuVue;
import ileinterdite.vues.Observateur;
import ileinterdite.vues.TypeMessage;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author giacintf
 */
public class Controleur implements Observateur {

    private NiveauEau niveaueau;
    private ArrayList<Tresor> tresors;

    private ArrayList<Carte_Inondation> piocheI;
    private Grille grille;
    private HashMap<String, Aventurier> joueurs;
    private ArrayList<Carte_Inondation> defausseI;
    private ArrayList<Carte_Tresor> defausseT;
    private ArrayList<Carte_Tresor> piocheT;
    private JeuVue vue;
    private boolean finJeu;
    private boolean finTour;
    private Aventurier advAct;

    Controleur() {
        finJeu = false;
        defausseI = new ArrayList<Carte_Inondation>();
        piocheI = new ArrayList<Carte_Inondation>();
        piocheT = new ArrayList<Carte_Tresor>();
        joueurs = new HashMap<String, Aventurier>();

        tresors = new ArrayList<Tresor>();

        for (TypeTrésor tres : TypeTrésor.values()) {
            tresors.add(new Tresor(tres));

            for (int i = 1; i < 6; i++) {
                CtTresor carteT = new CtTresor(tres);
                piocheT.add(carteT);
            }
        }
        for (TypeBonus bonus : TypeBonus.values()) {
            int i = 0;

            if (bonus == TypeBonus.SABLE) {
                i = 2;
            } else {
                i = 3;
            }
            for (int j = 1; j > i; j--) {
                CtBonus carteB = new CtBonus(bonus);
                piocheT.add(carteB);
            }
        }

        Collections.shuffle(piocheT);

        for (Piece_liste pc : Piece_liste.values()) {
            if (pc != Piece_liste.NULL) {
                piocheI.add(new Carte_Inondation(pc));
            }
        }
        Collections.shuffle(piocheI);

        grille = new Grille();

        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (i > 5 || i < 1) {

            System.out.println("Entrer un niveau de l'eau : (1-Débutant 2-Normal 3-Expert 4-Legendaire");
            i = sc.nextInt();
        }
        niveaueau = new NiveauEau(i);

        boolean choixJoueur = true;

        while (choixJoueur) {
            String nomJ = sc.nextLine();
            System.out.println("Entrez le nom d'un Joueur:");
            nomJ = sc.nextLine();
            System.out.println("Entrez un role:");
            String unRole = sc.nextLine();

            if (Role.valueOf(unRole.toUpperCase()) != null) {
                Role r = Role.valueOf(unRole.toUpperCase());
                if (roleDisponible(r)) {
                    Position posStart = getPositionDepart(r);
                    Aventurier a = null;
                    switch (r) {
                        
                        case EXPLORATEUR:
                            a = new Explorateur(posStart);
                            break;
                        case NAVIGATEUR:
                            a= new Navigateur(posStart);
                            break;
                        case PILOTE:
                            a = new Pilote(posStart);
                            break;
                        case INGENIEUR:
                            a = new Ingenieur(posStart);
                            break;
                        case MESSAGER:
                            a = new Messager(posStart);
                            break;
                        case PLONGEUR:
                            a = new Plongeur(posStart);
                            break;

                    }
                    joueurs.put(nomJ, a);
                    Ile tuJ =(Ile) grille.getTuileP(posStart);
                    tuJ.addAventurier(a);
                    System.out.println(tuJ.getAventuriers());
                    choixJoueur = false;
                }
            }

        }
        for (Aventurier adv : joueurs.values()) {
            for (int j = 0; j < 2; j++) {
                Carte_Tresor ctT = piocheT.get(0);
                adv.addCarte(ctT);
                piocheT.remove(0);
            }

        }
        for (int j = 0; j < 6; j++) {
            Carte_Inondation ctI = piocheI.get(0);
            Piece_liste pc = ctI.getPiece();
            Tuile tu = grille.getTuilePL(pc);
            tu.innonder();
            piocheI.remove(0);
            defausseI.add(ctI);
        }

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize(dim.height, dim.width);
        vue = new JeuVue(grille);
        window.add(vue);
        window.setVisible(true);
        Jeu();

    }

    private void Jeu() {
        while (!finJeu) {
            for (String nomJ : joueurs.keySet()) {
                nouveauTour(nomJ);
            }
        }
    }

    public void nouveauTour(String nomJ) {
        advAct = joueurs.get(nomJ);
        finTour = false;
        advAct.setNbAct(0);
        vue.initJoueur(advAct);
        while (!finTour && advAct.getNbAct() < 3) {

        }
        for (int i = 0; i < 2; i++) {
            if (piocheT.isEmpty()) {
                piocheT = defausseT;
                defausseT.clear();
                Collections.shuffle(piocheT);
            }
            Carte_Tresor ct = piocheT.get(0);
            advAct.addCarte(ct);
            while (advAct.getCarteTresor().size() > 5) {
            }
        }
        for (int i = 0; i < niveaueau.getNbCarte(); i++) {
            if (piocheI.isEmpty()) {
                piocheI = defausseI;
                defausseI.clear();
                Collections.shuffle(piocheI);
            }
            Carte_Inondation ctI = piocheI.get(0);
            Ile tl = (Ile) grille.getTuilePL(ctI.getPiece());
            tl.assecher();
            if (tl.getAventuriers().size() == 0 || (tl.getPiece().equals(Piece.Heliport) && tl.getEtat().equals(Etat.NOYEE))) {
                finJeu = false;
            }
        }

    }

    public boolean roleDisponible(Role role) {

        return !joueurs.containsKey(role);
    }

    public Aventurier chercherAventurier(String nomJ) {
        return joueurs.get(nomJ);
    }

    public void traiterClicTuile() {
        // TODO - implement Controleur.traiterClicTuile
        throw new UnsupportedOperationException();
    }

    public void traiterMessage() {
        // TODO - implement Controleur.traiterMessage
        throw new UnsupportedOperationException();
    }

    public void piocherCarteT(Aventurier aventurier) {
        aventurier.addCarte(piocheT.get(0));    // on ajoute la premiere carte de la pioche a l'aventurier
        defausseT.add(piocheT.get(0));          // on ajoute a la defausse la premiere carte de la pioche
        piocheT.remove(0);                      // on supprime la premiere carte de la pioche
    }

    public void piocherCarteI() {
        Piece_liste piecepiochee = piocheI.get(0).getPiece();   // on recupere la premiere carte de la pioche (index 0)
        Tuile tuile = grille.getTuilePL(piecepiochee);       // on recupere la tuile associé à la pièce

        tuile.innonder();   // on inonde cette carte

        defausseI.add(piocheI.get(0));  // on ajoute a la defausse la premiere carte de la pioche
        piocheI.remove(0);  // on supprime de la pioche la premiere carte
    }

    public void traiterClicTuile(int lig, int col) {
        // TODO - implement Controleur.traiterClicTuile
        throw new UnsupportedOperationException();
    }

    public void afficherMessage() {
        // TODO - implement Controleur.afficherMessage
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lig
     * @param col
     */
    public void NouveauclicTuile(int lig, int col) {
        // TODO - implement Controleur.NouveauclicTuile
        throw new UnsupportedOperationException();
    }

    private Position getPositionDepart(Role r) {
        switch (r) {
            case EXPLORATEUR:
                return grille.getPositionP(Piece_liste.La_Porte_de_Cuivre);
            case INGENIEUR:
                return grille.getPositionP(Piece_liste.La_Porte_dArgent);
            case MESSAGER:
                return grille.getPositionP(Piece_liste.La_Porte_dOr);
            case NAVIGATEUR:
                return grille.getPositionP(Piece_liste.La_Porte_de_Bronze);
            case PILOTE:
                return grille.getPositionP(Piece_liste.Heliport);
            case PLONGEUR:
                return grille.getPositionP(Piece_liste.La_Porte_de_Fer);
        }
        return null;
    }

    @Override
    public void traiterMessage(TypeMessage msg) {
        
    }

}
