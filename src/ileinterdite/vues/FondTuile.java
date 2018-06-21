/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import ileinterdite.jeu.Etat;
import ileinterdite.jeu.Piece_liste;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PageAttributes;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Florian
 */
public class FondTuile extends JPanel{
    Etat etat;
    Image imgT,imgTI;
    
    FondTuile(String pc){
        super();
        etat = Etat.ASSECHEE;
        try {
            this.imgT = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/cartes/"+pc+".png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de tresor.png");
        }
        try {
            this.imgTI = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/cartes/"+pc+"_Inonde.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de tresor.png");
        }
        this.setOpaque(false);
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        if(etat==Etat.ASSECHEE){
            g.drawImage(imgT,0, 0,w ,h ,null,this );
        }else if(etat==Etat.INNONDEE) {
            g.drawImage(imgTI,0, 0,w ,h ,null,this );
        }else{
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, w, h);
        }}
    
    public void setEtat(Etat et){
        etat = et;
    }
    
}
