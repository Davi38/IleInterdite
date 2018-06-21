/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import ileinterdite.vues.JeuVue;
import ileinterdite.vues.Observateur;
import ileinterdite.vues.TypeMessage;
import ileinterdite.vues.VueFinJeu;
import ileinterdite.vues.VueInscription;

/**
 *
 * @author giacintf
 */
public class Controleur implements Observateur {

    private NiveauEau niveauEau;
    private ArrayList<Tresor> tresors;

    private ArrayList<Carte_Inondation> piocheI;
    private Grille grille;
    private HashMap<Aventurier, String> joueurs;
    private ArrayList<Carte_Inondation> defausseI;
    private ArrayList<Carte_Tresor> defausseT;
    private ArrayList<Carte_Tresor> piocheT;

    private JeuVue vue;
    private VueInscription vueI;
    private VueFinJeu vueFinJeu;

    private boolean finJeu;
    private boolean finTour;
    private Aventurier advAct;
    private int nbAdvAct;
    private TypeMessage actionG;
    private Carte_Tresor carteADonner;
    private Aventurier jADonner;
    private ArrayList<Aventurier> jADepl;
    private CtBonus cBact;

    Controleur() {
        initJeu();
    }
    
    public void initJeu() {
        actionG = null;
        finJeu = false;
        defausseI = new ArrayList<Carte_Inondation>();
        defausseT = new ArrayList<Carte_Tresor>();
        piocheI = new ArrayList<Carte_Inondation>();
        piocheT = new ArrayList<Carte_Tresor>();
        joueurs = new HashMap<Aventurier, String>();

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
        vueI = new VueInscription();
        vueI.addObservateur(this);
        vueI.afficherFenetre();
        
        
    }

    public boolean roleDisponible(Role role) {

        for (Aventurier a : joueurs.keySet()) {
            if (a.getRole() == role) {
                return false;
            }

        }
        return true;
    }

    public Aventurier chercherAventurier(Role r) {
        for (Aventurier a : joueurs.keySet()) {
            if (a.getRole() == r) {
                return a;
            }
        }
        return null;
    }

    public void piocherCarteT(Aventurier aventurier) {
        System.out.println("pt: " + piocheT.size());
        System.out.println("dt: " + defausseT.size());
        if (piocheT.isEmpty()) {
            piocheT.addAll(defausseT);
            defausseT.clear();
        }
        if (!piocheT.isEmpty()) {
            Carte_Tresor cT = piocheT.get(0);
            piocheT.remove(0);
            if (cT.getType() == "MONTEE_EAU") {
                defausseT.add(cT);
                niveauEau.MonteeEau();
                piocheI.addAll(defausseI);
                Collections.shuffle(piocheI);
                vue.majNiveau(niveauEau.getNv());
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
        System.out.println(m.type);
        switch (m.type) {
            case DEMARRER:
                demmarerPartie(m.listeJ, m.ind);
                break;
                
            case RECOMMENCER:
                initJeu();
                break;
                
            case DEPLACER:
                jADonner = null;
                vue.desactiverB( grille);
                actionG = TypeMessage.DEPLACER;
                if (advAct instanceof Plongeur) {
                    Plongeur plongeur = (Plongeur) advAct;
                    plongeur.setGrille(grille);
                }
                vue.majGrille(grille);
                vue.majDeplacement(advAct, grille);
                break;

            case ASSECHER:
                jADonner = null;
                if (advAct instanceof Plongeur) {
                    Plongeur plongeur = (Plongeur) advAct;
                    plongeur.setGrille(grille);
                }
                vue.desactiverB( grille);
                actionG = TypeMessage.ASSECHER;
                vue.majGrille(grille);
                vue.majAssechement(advAct, grille);
                break;

            case FIN_TOUR:
                jADonner = null;
                finTour();
                if (verifFinPartie()){
                    vue.cacherFenetre();
                    VueFinJeu vueFinJeu = new VueFinJeu(false);
                    vueFinJeu.addObservateur(this);
                    vueFinJeu.afficherFenetre();
                }
                break;
            case DEFFAUSE:
                Carte_Tresor cT = advAct.getCarteTresor().get(m.ind);
                defausseT.add(cT);
                advAct.getCarteTresor().remove(cT);
                vue.initJoueur(advAct, joueurs.get(advAct));
                break;

            case GAGNER_TRESOR:
                jADonner = null;
                vue.majGrille(grille);
                if (verifGT()) {
                    advAct.removeAct();
                    vue.majCartes(advAct);
                }
                if (advAct.getActRest() == 0) {
                    finTour();
                }
                break;

            case DONNER_CARTE:
                jADonner = null;
                vue.majGrille(grille);
                actionG = TypeMessage.DONNER_CARTE;
                if (advAct instanceof Messager) {
                    vue.colorTousLesJ(advAct);
                } else {
                    vue.colorJ(advAct);
                }
                break;

            case CLIC_JOUEUR:
                if (actionG == TypeMessage.DONNER_CARTE) {
                    Role r = m.nomR;
                    jADonner = chercherAventurier(r);
                    vue.activerB();
                }else if(actionG == TypeMessage.HELICOPTERE){
                    Role r = m.nomR;
                    jADepl.add(chercherAventurier(r));
                    System.out.println("Action");
                    if(!vue.colorJ(jADepl,grille)){
                        vue.majDeplacement(new Pilote(new Position(0,0)), grille);
                        System.out.println("Action2");
                    }
                    
                }

                break;

            case CLIC_CARTE:
                Carte_Tresor ct = advAct.getCarteTresor().get(m.ind);
                System.out.println(ct.getType());
                if (jADonner != null) {
                    if (jADonner.chercherNombreCartes() < 9) {
                        advAct.removeAct();
                        jADonner.addCarte(ct);
                        advAct.getCarteTresor().remove(ct);
                        actionG = null;
                    }
                    vue.desactiverB(grille);
                }else{
                    actionG = null;
                    if(ct instanceof CtBonus){
                    CtBonus cb =(CtBonus) ct;
                    System.out.println(ct.getType()+"Bonus");
                    cBact = cb;
                    actionBonus(cb);
                    
                    }
                }
                jADonner = null;
                vue.majCartes(advAct);
                
                if (advAct.getActRest() == 0) {
                    finTour();
                }

                break;

            case CLICTUILE:
                vue.desactiverB(grille);
                Ile tJ = (Ile) grille.getTuileP(grille.getPosition(advAct.getPosition()));
                Position pos = grille.getPosition(m.pos);

                Ile t = (Ile) grille.getTuileP(pos);
                if (actionG == TypeMessage.DEPLACER && advAct.verifDeplacement(pos, t)) {
                    advAct.setPosition(pos);
                    tJ.getAventuriers().remove(advAct);
                    t.addAventurier(advAct);
                    advAct.removeAct();

                } else if (actionG == TypeMessage.ASSECHER && advAct.verifAssechement(m.pos, t)) {
                    t.assecher();

                    if (advAct instanceof Ingenieur) {
                        Ingenieur ingenieur = (Ingenieur) advAct;
                        ingenieur.incrementerNbAssechement();
                        if (ingenieur.getNbAssechement() % 2 == 0) {
                            advAct.removeAct();
                        }
                    } else {
                        advAct.removeAct();
                    }

                }else if(actionG==TypeMessage.HELICOPTERE){
                    
                    Ile ile =(Ile) grille.getTuileP(grille.getPosition(jADepl.get(0).getPosition()));
                    for (Aventurier a : jADepl){
                        System.out.println(a.getRole().toString());
                        ile.removeAventurier(a);
                        a.setPosition(pos);
                        t.addAventurier(a);
                        
                    }
                    advAct.getCarteTresor().remove(cBact);
                    vue.majCartes(advAct);
                    cBact=null;
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
        for (int i = 0; i < 2; i++){
            piocherCarteT(advAct);
        }
        for (int i = 0; i < niveauEau.getNbCarte(); i++) {
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
        advAct = (Aventurier) joueurs.keySet().toArray()[nbAdvAct];
        advAct.initAct();
        vue.initJoueur(advAct, joueurs.get(advAct));

    }

    private void demmarerPartie(HashMap<Role, String> listeJ, int nv) {
        niveauEau = new NiveauEau(nv);
        for (Role r : listeJ.keySet()) {
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
            joueurs.put(a, listeJ.get(r));
            Ile tuJ = (Ile) grille.getTuileP(posStart);
            tuJ.addAventurier(a);
        }
        vueI.cacherFenetre();
        vue = new JeuVue(grille, niveauEau.getNv(), this);

        for (Aventurier adv : joueurs.keySet()) {
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
        advAct = (Aventurier) joueurs.keySet().toArray()[0];
        advAct.initAct();
        vue.initJoueur(advAct, joueurs.get(advAct));
    }

    public boolean verifGT() {
        TypeTrésor t = convertTresor((Ile) grille.getTuileP(advAct.getPosition()));
        if (t != null) {
            Tresor tres = getTresor(t);
            
            boolean estSurTresor = false;
            
            if (tres.getType() == TypeTrésor.CALICE && advAct.getPosition() == grille.getPositionP(Piece_liste.Le_Palais_de_Corail)) {
                estSurTresor = true;
            } else if (tres.getType() == TypeTrésor.CALICE && advAct.getPosition() == grille.getPositionP(Piece_liste.Le_Palais_des_Marees)) {
                estSurTresor = true;
            } else if (tres.getType() == TypeTrésor.CRYSTAL && advAct.getPosition() == grille.getPositionP(Piece_liste.La_Caverne_des_Ombres)) {
                estSurTresor = true;
            } else if (tres.getType() == TypeTrésor.CRYSTAL && advAct.getPosition() == grille.getPositionP(Piece_liste.La_Caverne_du_Brasier)) {
                estSurTresor = true;
            } else if (tres.getType() == TypeTrésor.PIERRE && advAct.getPosition() == grille.getPositionP(Piece_liste.Le_Temple_de_La_Lune)) {
                estSurTresor = true;
            } else if (tres.getType() == TypeTrésor.PIERRE && advAct.getPosition() == grille.getPositionP(Piece_liste.Le_Temple_du_Soleil)) {
                estSurTresor = true;
            } else if (tres.getType() == TypeTrésor.STATUE && advAct.getPosition() == grille.getPositionP(Piece_liste.Le_Jardin_des_Hurlements)) {
                estSurTresor = true;
            } else if (tres.getType() == TypeTrésor.STATUE && advAct.getPosition() == grille.getPositionP(Piece_liste.Le_Jardin_des_Murmures)) {
                estSurTresor = true;
            }
            
            
            if (!tres.isRecuperé() && advAct.verifGagnerT(tres) && estSurTresor) {
                tres.setRecuperé(true);
                advAct.removeCT(t);
                vue.setImgTresEtat(t, tres.isRecuperé());
                vue.majCartes(advAct);
                return tres.isRecuperé();
            }
        }
        return false;
    }

    public TypeTrésor convertTresor(Ile i) {
        if (i.getPiece().contains("Temple")) {
            return TypeTrésor.PIERRE;
        } else if (i.getPiece().contains("Jardin")) {
            return TypeTrésor.STATUE;
        } else if (i.getPiece().contains("Caverne")) {
            return TypeTrésor.CRYSTAL;
        } else if (i.getPiece().contains("Palais")) {
            return TypeTrésor.CALICE;
        }
        return null;
    }

    public Tresor getTresor(TypeTrésor type) {
        for (Tresor t : tresors) {
            if (t.getType() == (type)) {
                return t;
            }
        }
        return null;
    }
    
    
    public boolean verifFinPartie(){
        if (verifPertePartie1() || verifPertePartie2() || verifPertePartie3() || verifPertePartie4()){
            return true ;
        }
        return false;
    }
    
    public boolean verifPertePartie1(){
        System.out.println("verifPertePartie1 : ");
        for (Tresor t: tresors){   
            if(!t.isRecuperé()){
                ArrayList<Ile> ileT = getIlesT(t.getType());
                boolean testN = true;
                for(Ile iT : ileT){
                    if (!iT.estNoyee()){
                        System.out.println("La tuile trésor " + iT.getPiece() + " n'est pas noyée");
                        testN = false;
                    } else {
                        System.out.println("La tuile trésor " + iT.getPiece() + " est  noyée");
                    }
                }
                return testN;
            }
        }
        
        return false;
    }

    public boolean verifPertePartie2() {
        System.out.println("verifPertePartie2 : ");
        Position posHeliport = grille.getPositionP(Piece_liste.Heliport);
        Ile ile = (Ile) grille.getTuileP(posHeliport);
        if (ile.estNoyee()){
            System.out.println("L'héliport s'est noyé ");
            return true;
        } else {
            return false;
        }
    }

    public boolean verifPertePartie3() {
        for (Aventurier a : joueurs.keySet()) {
            Position posJ = a.getPosition();
            Ile ile = (Ile) grille.getTuileP(posJ);
            if (ile.estNoyee()){
                System.out.println("La tuile du " + a.getRole() + "est noyée ");
                ArrayList<Position> deplPos = new ArrayList<Position>();
                deplPos = a.getPosPossible(grille);
                if(deplPos.isEmpty()){
                    System.out.println("Il n'y a pas de tuile ou il peut se déplacer");
                    return true;
                }
                return false;
            }
        }
        System.out.println("verifPertePartie3");
        return false;
    }
    
    public boolean verifPertePartie4(){
        
        System.out.println("verifPertePartie4 :" );
        System.out.println("Le niveau d'eau est " + niveauEau.getNv());
        boolean test = false;
        if ( niveauEau.getNv() == 10){
            System.out.println("Vous avez perdu : Le niveau d'eau a atteint le maximum ");
            test = true;
        } 
        return test;
         
    }
    
    public void actionBonus(CtBonus cb){
        vue.desactiverB(grille);
        if (cb.getType()=="SABLE"){
            actionG = TypeMessage.SABLE;
            
            
        }else if(cb.getType()=="HELICOPTERE"){
            if(testJeuGagnant()){
                vue.cacherFenetre();
                vueFinJeu = new VueFinJeu(true);
            }else{
            actionG = TypeMessage.HELICOPTERE;
            jADepl = new ArrayList<Aventurier>();
            vue.colorTousLesJ();}
        }
    }
    public ArrayList<Ile> getIlesT(TypeTrésor typeT){
        ArrayList<Ile> ileT = new ArrayList<Ile>();
        switch(typeT){
            case CALICE : 
                ileT.add((Ile)grille.getTuilePL(Piece_liste.Le_Palais_de_Corail));
                ileT.add((Ile)grille.getTuilePL(Piece_liste.Le_Palais_des_Marees));
                break;
            case CRYSTAL:
                ileT.add((Ile)grille.getTuilePL(Piece_liste.La_Caverne_des_Ombres));
                ileT.add((Ile)grille.getTuilePL(Piece_liste.La_Caverne_du_Brasier));
                break;
            case PIERRE:
                ileT.add((Ile)grille.getTuilePL(Piece_liste.Le_Temple_de_La_Lune));
                ileT.add((Ile)grille.getTuilePL(Piece_liste.Le_Temple_du_Soleil));
                break;
            case STATUE:
                ileT.add((Ile)grille.getTuilePL(Piece_liste.Le_Jardin_des_Hurlements));
                ileT.add((Ile)grille.getTuilePL(Piece_liste.Le_Jardin_des_Murmures));
                break;          
        }
        return ileT;
        
    }
    
    public boolean testJeuGagnant(){
        boolean tres=true,heliport =true;
        for(Tresor t : tresors){
            if(!t.isRecuperé()){
                tres=false;
            }
        }
        Ile ile = (Ile) grille.getTuilePL(Piece_liste.Heliport);
        heliport = ile.getAventuriers().size()==joueurs.size();
        
        return tres && heliport;
    }
    

}
