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
import java.util.*;

public abstract class Aventurier {

    private Position pos;
    private ArrayList<Carte_Tresor> mesCartes;
    private int numJoueur;
    
    private Role role;
    
    Aventurier(Role role) {
        this.role = role;
    }
    
    // Retourne le role de l'aventurier
    public Role getRole() {
        return this.role;
    }
    

    //public Aventurier chercherAventurier(int NumJ) {
    //    // TODO - implement Aventurier.chercherAventurier
    //    throw new UnsupportedOperationException();
    //}
    
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
    
    public void seDeplacer(int lig, int col) {
        
    }
    
    //public boolean verifDeplacement(int lig, int col, int lig2, int col2, Etat etat) {
    public abstract boolean verifDeplacement( Position pos2);
    
    // met a jour la tuile ou est place l'aventurier
    public void setTuile(Position pos) {
        this.pos = pos;
    }
    
    // pour recuperer la tuile de l'aventurier
    public Position getPosition() {
        return this.pos;
    }
    
    public abstract boolean verifAssechement(Position pos2);

}
