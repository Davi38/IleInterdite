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
    private int nbAdvAct;
    private TypeMessage actionG;
    private Carte_Tresor carteADonner;
    private Aventurier jADonner;

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

            if (bonus == TypeBonus.HELICOPTERE) {
                i = 3;
            } else {
                i = 2;
            }
            for (int j = 0; j < i; j++) {
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
                            a = new Navigateur(posStart);
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
                    Ile tuJ = (Ile) grille.getTuileP(posStart);
                    tuJ.addAventurier(a);
                    System.out.println(tuJ.getAventuriers());
                    choixJoueur = false;
                }
            }

        }
        vue = new JeuVue(grille, niveaueau.getNv(),this);
        
        for (Aventurier adv : joueurs.values()) {
            for (int j = 0; j < 2; j++) {
                piocherCarteT(adv);
            }

        }
        for (int j = 0; j < 6; j++) {
            piocherCarteI();
        }
        vue.majGrille(grille);
        vue.afficherFenetre();
        nbAdvAct = 0;
        advAct = joueurs.get(joueurs.keySet().toArray()[0]);
        advAct.initAct();
        vue.initJoueur(advAct, joueurs.keySet().toArray()[0].toString());

    }

    public boolean roleDisponible(Role role) {

        for(Aventurier a : joueurs.values()){
            if(a.getRole()== role){
                return false;
            }
                
        }
        return true;
    }

    public Aventurier chercherAventurier(String nomJ) {
        return joueurs.get(nomJ);
    }
    
    public Aventurier chercherAventurier(Role r) {
        for(Aventurier a : joueurs.values()){
            if(a.getRole()==r){
                return a;
            }
        }
        return null;
    }

    public void piocherCarteT(Aventurier aventurier) {
        if (piocheT.isEmpty() && !defausseT.isEmpty()) {
            piocheT.addAll(defausseT);
            defausseT.clear();
        }
        if (!piocheT.isEmpty()) {
            Carte_Tresor cT = piocheT.get(0);
            piocheT.remove(0);
            if (cT.getType() == "MONTEE_EAU") {
                niveaueau.MonteeEau();
                piocheI.addAll(defausseI);
                Collections.shuffle(piocheI);
                vue.majNiveau(niveaueau.getNv());
            } else {
                aventurier.addCarte(cT);
            }
        }
    }

    public void piocherCarteI() {
        if (piocheI.isEmpty()) {
            piocheI.addAll(defausseI);
            defausseI.clear();
        }
        Ile ile = (Ile) grille.getTuilePL(piocheI.get(0).getPiece());
        defausseI.add(piocheI.get(0));
        piocheI.remove(0);
        ile.innonder();  // on supprime de la pioche la premiere carte
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
    public void traiterMessage(ileinterdite.vues.Message m) {
        switch (m.type) {
            case DEPLACER:
                actionG = TypeMessage.DEPLACER;
                vue.majGrille(grille);
                vue.majDeplacement(advAct, grille);
                break;

            case ASSECHER:
                actionG = TypeMessage.ASSECHER;
                vue.majGrille(grille);
                vue.majAssechement(advAct, grille);
                break;

            case FIN_TOUR:
                finTour();
                break;
            case DEFFAUSE:
                Carte_Tresor cT = advAct.getCarteTresor().get(m.ind);
                System.out.println(cT.getType());
                advAct.getCarteTresor().remove(cT);
                vue.initJoueur(advAct, joueurs.keySet().toArray()[nbAdvAct].toString());
                break;
                
            case DONNER_CARTE:
                actionG = TypeMessage.DONNER_CARTE;
                break;
                
            case CLIC_JOUEUR:
                if(actionG == TypeMessage.DONNER_CARTE ){
                    Role r = m.nomR;
                    if(!roleDisponible(r)){
                        jADonner = chercherAventurier(r);
                    }
                }
                    
                break;

            case CLICTUILE:
                Ile tJ = (Ile) grille.getTuileP(grille.getPosition(advAct.getPosition()));
                Position pos = grille.getPosition(m.pos);

                Ile t = (Ile) grille.getTuileP(pos);
                if (advAct instanceof Plongeur) {
                    Plongeur plongeur = (Plongeur) advAct;
                    plongeur.setGrille(grille);
                }
                if (actionG==TypeMessage.DEPLACER && advAct.verifDeplacement(pos, t)){
                    advAct.setPosition(pos);
                    tJ.getAventuriers().remove(advAct);
                    t.addAventurier(advAct);
                    advAct.removeAct();

                } else if (actionG == TypeMessage.ASSECHER && advAct.verifAssechement(m.pos, t)) {
                    t.assecher();
                    advAct.removeAct();

                }
                vue.majGrille(grille);
                actionG = null;
                System.out.println(advAct.getActRest());
                if (advAct.getActRest() == 0) {
                    finTour();
                }
                break;

        }

    }

    public void finTour() {
        vue.finT();
        for (int i = 0; i < 2; i++) {
            piocherCarteT(advAct);
        }
        for (int i = 0; i < niveaueau.getNbCarte(); i++) {
            piocherCarteI();
        }
        vue.majGrille(grille);
        if (nbAdvAct + 1 == joueurs.size()) {
            nbAdvAct = 0;
        } else {
            nbAdvAct += 1;
        }
        initTour();
    }

    public void initTour() {
        advAct = joueurs.get(joueurs.keySet().toArray()[nbAdvAct]);
        advAct.initAct();
        vue.initJoueur(advAct, joueurs.keySet().toArray()[nbAdvAct].toString());

    }

}
