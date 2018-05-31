/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

public class NiveauEau {

	private int niveau;
        
        NiveauEau(String nv){
            switch(nv){
                case "Novice": niveau= 1; break;
                case "Normal": niveau= 2; break;
                case "Elite": niveau= 3; break;
                case "Legendaire": niveau= 4; break;
            }
        }
        
        public int getNiveau(){
            return niveau;
        }
        
        public void MonteeEau(){
            niveau = niveau+1;
        }

}