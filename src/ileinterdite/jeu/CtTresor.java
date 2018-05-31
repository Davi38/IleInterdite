/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

public class CtTresor extends Carte_Tresor {

	private Piece_Tresor type;
        
        public CtTresor(){}
        
        
        @Override
        public String getType(){
            return type.toString();
        }

}