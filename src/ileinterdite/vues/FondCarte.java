/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author Florian
 */
public class FondCarte extends JPanel{
    Image fond;
    
    public FondCarte(Image fond, LayoutManager l) {
        super(l);
        this.fond = fond;
        this.setOpaque(false);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int w = this.getWidth();
        int h = this.getHeight();
        g.drawImage(fond, (w-(5*h)/7)/2, 0, (5*h)/7, h, null, this);
        
        
        
    }
}
