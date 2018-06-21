/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author Florian
 */
public class PanelFond extends JPanel{
    
    Image fond;
    

    public PanelFond(Image fond, LayoutManager l) {
        super(l);
        this.fond = fond;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), null, this);
        
        
        
    }
    
    
    
    
}
