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
public class Position {
    int col;
    int lig;
    public Position(int col , int lig){
        this.col = col;
        this.lig = lig;
    }
    
    public String toString(){
       return col + "-" + lig; 
    }
    public int getLig(){
        return lig;
    }
    
    public int getCol(){
        return col;
    }
}
