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
public class CtBonus extends Carte_Tresor {

	private TypeBonus typeBonus;
        
        public CtBonus(TypeBonus typeBonus){
            this.typeBonus = typeBonus;
        }
        
        @Override
        public String getType(){
            return typeBonus.toString() ;
        }
}
