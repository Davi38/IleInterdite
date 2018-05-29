/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

/**
 *
 * @author giacintf
 */
public class NiveauEau {

	private int niveau;
        
        public NiveauEau(int niveau){
            this.niveau=niveau;
        }

    /**
     * @return the niveau
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * @param niveau the niveau to set
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau + 1;
    }

}
