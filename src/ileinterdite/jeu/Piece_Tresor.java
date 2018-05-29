/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

import static ileinterdite.jeu.Piece_Joueur.values;

/**
 *
 * @author giacintf
 */
public enum Piece_Tresor {
    	Le_Palais_de_Corail,
	Le_Jardin_des_Hurlements,
	La_Caverne_du_Brasier,
	Le_Temple_du_Soleil,
	Le_Temple_de_La_Lune,
	Le_Palais_des_Marees,
	Le_Jardin_des_Murmures,
	La_Caverne_des_Ombres;
        
        public static boolean contains(Piece_liste pc){
            for (Piece_Tresor p : values()){
                if (p.name().equals(pc))
                    return true;
            }
            return false;
        };
}
