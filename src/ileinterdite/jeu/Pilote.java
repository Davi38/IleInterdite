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
            super(Role.PILOTE,pos);
        }


    public boolean verifDeplacement( Position pos2) {
        Position posj = getPosition();
        
        if (pouvoirdispo){
            pouvoirdispo = false;
            return true;
        }else if(abs(posj.col-pos2.col)<=1 ^ abs(posj.lig-pos2.lig)<= 1){
           return true; 
        }
        return false;
    }
    


    public boolean verifAssechement(Position pos2) {
        Position posj = getPosition();
        if((abs(posj.col-pos2.col)==1 && (posj.lig-pos2.lig)==0)^((posj.col-pos2.col)==0 && abs(posj.lig-pos2.lig)==1)){
           return true; 
        }
        return false;
    }
    
    public Color getColor(){
        return couleur;
    }

}
