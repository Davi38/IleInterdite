/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import java.awt.Color;
import static java.lang.Math.abs;

/**
 *
 * @author giacintf
 */
public class Pilote extends Aventurier {

	private final Color couleur = Color.BLUE;
        private boolean pouvoirdispo;
        
        Pilote(Position pos){
            super(Role.PILOTE,pos,3);
            pouvoirdispo = true;
        }

    @Override
    public void initAct() {
        super.initAct(); //To change body of generated methods, choose Tools | Templates.
        pouvoirdispo =true;
    }
        
        


        @Override
    public boolean verifDeplacement( Position pos2, Tuile tuile) {
        Position posj = getPosition();
        int l = abs(posj.col-pos2.col);
        int c = abs(posj.lig-pos2.lig);
        
        if((l==1&&c==0)||(l==0&&c==1)) {
            return tuile.getEtat()==Etat.ASSECHEE; 
        }else if (pouvoirdispo){
            return  (tuile.getEtat()==Etat.ASSECHEE)&& !posj.equals(pos2); }
        return false;
    }
    


        @Override
    public boolean verifAssechement(Position pos2, Tuile tuile) {
        Position posj = getPosition();
        int l = abs(posj.col-pos2.col);
        int c = abs(posj.lig-pos2.lig);
        if((l==1&&c==0)||(l==0&&c==1)){
           return tuile.estInnondé();
        }
        return false;
    }
    
    public Color getColor(){
        return couleur;
    }

    @Override
    public void setPosition(Position pos) {
    
        Position posj = getPosition();
        int l = abs(posj.col-pos.col);
        int c = abs(posj.lig-pos.lig);
        if (!((l==1&&c==0)||(l==0&&c==1))){
            pouvoirdispo = false;}
        super.setPosition(pos);
        }
    }
    
    
    
    

