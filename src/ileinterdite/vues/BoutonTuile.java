/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import ileinterdite.jeu.Aventurier;
import ileinterdite.jeu.Position;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Florian
 */
public class BoutonTuile extends Observe{
    
    JButton Tuile;
    JPanel pions;
    HashMap<JButton,Aventurier> listeA;
    Position pos;
    
    BoutonTuile(String nomP,Position pos,Observateur o){
        this.addObservateur(o);
        Tuile = new JButton();
        Tuile.setLayout(new BorderLayout());
        Tuile.add(new JLabel(nomP.replace("_", " ")),BorderLayout.NORTH);
        pions= new JPanel(new GridLayout(2,2));
        Tuile.add(pions,BorderLayout.CENTER);
        listeA = new HashMap<JButton,Aventurier>();
        this.pos = pos;
        Tuile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Message m = new Message();
                            m.type = TypeMessage.CLICTUILE;
                            m.pos = pos;
                            notifierObservateur(m);
                        }
                    });
    }
    

    void majTuile(ArrayList<Aventurier> listeJ, Color bg) {
        Tuile.setBackground(bg);
        listeA.clear();
        pions.removeAll();
        pions.setBackground(bg);
        for (Aventurier a : listeJ){
            JButton pion = new JButton(a.getRole().toString());
            pion.setBackground(a.getColor());
            listeA.put(pion, a);
            pion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message();
                    m.type= TypeMessage.CLIC_JOUEUR;
                    m.nomR = listeA.get(pion).getRole();
                    notifierObservateur(m);
                }
            });
            pions.add(pion);
        }
        for (int i =0; i+listeJ.size()<4;i++){
            pions.add(new JLabel(""));
        }
        pions.revalidate();
    }
    
    public JButton getBoutonTuile(){
        return Tuile;
    }
    
    public void paint(Color bg){
        Tuile.setBackground(bg);
        pions.setBackground(bg);
    }
    
}
