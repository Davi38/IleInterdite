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
public class PanelFond2 extends JPanel {
    Image fond;
    

    public PanelFond2(Image fond, LayoutManager l) {
        super(l);
        this.fond = fond;
        this.setOpaque(false);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        int taille = w<h?w:h;
        
        g.drawImage(fond, (w-taille)/2, (h-taille)/2, taille, taille, null, this);
        
        
        
    }
    
}
