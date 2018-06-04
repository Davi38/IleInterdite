/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.jeu;

public class NiveauEau {

	private int niveau;
        
        NiveauEau(int nv){
            niveau = nv;
        }
        
        public int getNbCarte(){
            if (niveau<3){
               return 2; 
            }else if(niveau<6){
               return 3;
            }else if(niveau<8){
                return 4;
            }else {
                return 5;
            }
            }
        
        public void MonteeEau(){
            niveau = niveau+1;
        }

}

