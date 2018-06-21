/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import ileinterdite.jeu.TypeTrésor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Florian
 */
public class ImageTresor extends JPanel{
    private Image tresor,tresorR;
    private boolean recupere;
    
    ImageTresor(TypeTrésor type){
        try {
            this.tresor = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/tresors/"+type.toString().toLowerCase()+".png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de tresor.png");
        }
        try {
            this.tresorR = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/tresors/"+type.toString().toLowerCase()+"R.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de tresorR.png");
        }
        recupere=false;
        this.setOpaque(false);
        repaint();
        
    }
     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        int taille =(w<h?w:h);
        if(!recupere){
            g.drawImage(tresor,0, 0,taille ,taille ,null,this );
        }else{
            g.drawImage(tresorR,0, 0,taille ,taille ,null,this );
            
        }
     }
     public void setRecupere(boolean etat){
         recupere = etat;
         repaint();
     }
    
    
}
