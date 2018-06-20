/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

/**
 *
 * @author giacintf
 */
import java.awt.Color;
import java.util.*;

public abstract class Aventurier {

    private Position pos;
    private ArrayList<Carte_Tresor> mesCartes;
    private String nomJoueur;
    private int nbAct;
    private int actRestante;
    private Role role;
    
    Aventurier(Role role,Position pos,int nbAct) {
        this.role = role;
        mesCartes = new ArrayList<Carte_Tresor>();
        this.pos = pos;
        this.nbAct=nbAct;
    }
    
    // Retourne le role de l'aventurier
    public Role getRole() {
        return this.role;
    }
    
    // Renvoi un entier correspondant au nombre de carte du joueur
    public int chercherNombreCartes() {
        return mesCartes.size();
    }

    // On ajoute une carte au joueur
    public void addCarte(Carte_Tresor uneCarteT) {
        mesCartes.add(uneCarteT);
    }
    
    // retourne les cartes que l'aventurier possede
    public ArrayList<Carte_Tresor> getCarteTresor() {
        return mesCartes;
    }
    
    // renvoi les cartes de type Tresor que le joueur possede (sous forme d'une collection)
    public ArrayList<Carte_Tresor> getCtTresor() {
        
        ArrayList<Carte_Tresor> carteT = new ArrayList<>();
        
        for (Carte_Tresor carte : mesCartes) {
            if (carte instanceof CtTresor){
                carteT.add(carte);
            }
        }
        return carteT;
    }
    
    public void setPosition(Position position) {
        this.pos = position;
    }
    
    //public boolean verifDeplacement(int lig, int col, int lig2, int col2, Etat etat) {
    public abstract boolean verifDeplacement(Position pos2,Tuile tuile);
    
    // met a jour la tuile ou est place l'aventurier
    public void setTuile(Position pos) {
        this.pos = pos;
    }
    
    // pour recuperer la tuile de l'aventurier
    public Position getPosition() {
        return this.pos;
    }
    
    public abstract boolean verifAssechement(Position pos2,Tuile tuile);

    public int getNbAct(){
        return nbAct;
    }
    public int getActRest(){
        return actRestante;
    }
    
    public void removeAct(){
        actRestante -= 1;
    }
    
     public abstract Color getColor();

    public void initAct() {
        actRestante = nbAct;
    }
    
    public boolean verifGagnerT(Tresor t){
        
        if(t != null){
            int nbCarteT =0;
            for(Carte_Tresor c : mesCartes){
                if(c.getType().equalsIgnoreCase(t.getType().toString())){
                    nbCarteT +=1;
                }
            }
            return nbCarteT>3;
        }
        return false;    
    }

    void removeCT(TypeTrésor t) {
        for(int i=0; i<4 ;i++){
            int numC = 0;
            while(!mesCartes.get(numC).getType().equalsIgnoreCase(t.toString())){
                numC += 1;
            }
            mesCartes.remove(numC);
        }
    }
    
    public ArrayList<Position> getPosPossible(Grille grille) {
        ArrayList<Position> posPos = new ArrayList<Position>();
        for(Position pos: grille.getGrille().keySet()){
            if(verifDeplacement(pos, grille.getTuileP(pos))){
                posPos.add(pos);
            }
        }
        return posPos;
        
    }
}

