/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Florian
 */
public class VueRegles extends JPanel {
    private JFrame window;
    private Image regle1,regle2,regle3,regle4,regle5,regle6,regle7,regle8 ;
    private Integer width, height,numP ;
    private JButton suiv;
    private JButton pred;
    
    VueRegles(){
        window = new JFrame("Regles");
        window.setResizable(false);
        
        // Centrage de la fenêtre sur l'écran
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width =(int)(dim.getHeight()*12/16)-50;
        height = (int)dim.getHeight()-50;
        window.setSize(width,height);
        window.setLocation(100,0);
        window.setLayout(new BorderLayout());
        numP = 1;
        try {
            this.regle1 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/regle/regle1.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de regle1.png");
        }
        try {
            this.regle2 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/regle/regle2.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de regle2.png");
        }
        try {
            this.regle3 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/regle/regle3.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de regle3.png");
        }
        try {
            this.regle4 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/regle/regle4.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de regle4.png");
        }
        try {
            this.regle5 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/regle/regle5.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de regle5.png");
        }
        try {
            this.regle6 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/regle/regle6.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de regle6.png");
        }
        try {
            this.regle7 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/regle/regle7.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de regle7.png");
        }
        try {
            this.regle8 = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/regle/regle8.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de regle8.png");
        }
        
        window.setLayout(new BorderLayout());
        JPanel bas = new JPanel(new GridLayout(1,3));
        pred = new JButton("Precedent");
        pred.setEnabled(false);
        pred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numP -= 1;
                    suiv.setEnabled(true);
                if(numP==1){
                   pred.setEnabled(false);
                }
                repaint();
            }
        });
        suiv = new JButton("Suivant");
        suiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numP += 1;
                   pred.setEnabled(true);
                if(numP==8){
                   suiv.setEnabled(false);
                }
                repaint();
            }
        });
        bas.add(pred);
        bas.add(suiv);
        JButton quitter =new JButton("Quitter");
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cacher();
            }
        });
        bas.add(quitter);
        window.add(bas,BorderLayout.SOUTH); 
        window.add(this,BorderLayout.CENTER);
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        switch(numP){
            case 1:
                g.drawImage(regle1, 0, 0, width, height-50, null, window);
                break;
            case 2:
                g.drawImage(regle2, 0, 0, width, height-50, null, window);
                break;
            case 3:
                g.drawImage(regle3, 0, 0, width, height-50, null, window);
                break;
            case 4:
                g.drawImage(regle4, 0, 0, width, height-50, null, window);
                break;
            case 5:
                g.drawImage(regle5, 0, 0, width, height-50, null, window);
                break;    
            case 6:
                g.drawImage(regle6, 0, 0, width, height-50, null, window);
                break;    
            case 7:
                g.drawImage(regle7, 0, 0, width, height-50, null, window);
                break;   
            case 8:
                g.drawImage(regle8, 0, 0, width, height-50, null, window);
                break;
        }
    }
    
    public void afficher(){
        window.setVisible(true);
        numP=1;
        pred.setEnabled(false);
    }
    public void cacher(){
        window.setVisible(false);
    }
    
    public boolean estVisible(){
        return window.isVisible();
    }
}
