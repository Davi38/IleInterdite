/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Florian
 */
public class VueRegles extends JFrame{
    private Image regle1,regle2,regle3,regle4,regle5,regle6,regle7,regle8 ;
    private Integer width, height,numP ;
    JButton suiv;
    JButton pred;
    public VueRegles(){
        this = new JFrame() ;
        this.setResizable(false);
        // Centrage de la fenêtre sur l'écran
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setSize((int)((dim.getHeight()*12/16)-50), (int)dim.getHeight()-50);

        this.setLocation(100, dim.height/2-this.getSize().height/2);


        this.setVisible(true);
        
    }
}
