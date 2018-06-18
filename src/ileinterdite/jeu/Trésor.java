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
public class Trésor {

	private boolean recuperé;
	private TypeTrésor type;
        
        public Trésor(TypeTrésor type){
            this.type=type;
            recuperé= false;
        }

    /**
     * @return the recuperé
     */
    public boolean isRecuperé() {
        return recuperé;
    }

    /**
     * @param recuperé the recuperé to set
     */
    public void setRecuperé(boolean recuperé) {
        this.recuperé = recuperé;
    }

    /**
     * @return the type
     */
    public TypeTrésor getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TypeTrésor type) {
        this.type = type;
    }
        
        

}
