/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import ileinterdite.jeu.Aventurier;
import ileinterdite.jeu.Position;
import ileinterdite.jeu.Role;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
public class BoutonTuile extends Observe {

    JButton tuile;
    JPanel pions;
    HashMap<JButton, Aventurier> listeA;
    Position pos;

    BoutonTuile(String nomP, Position pos, Observateur o) {
        this.addObservateur(o);
        tuile = new JButton();
        tuile.setLayout(new BorderLayout());
        JLabel nomT = new JLabel(nomP.replace("_", " "));
        nomT.setFont((new Font("Arial", Font.PLAIN, 20)));
        tuile.add(nomT, BorderLayout.NORTH);
        pions = new JPanel(new GridLayout(2, 2));
        tuile.add(pions, BorderLayout.CENTER);
        listeA = new HashMap<JButton, Aventurier>();
        this.pos = pos;
        tuile.addActionListener(new ActionListener() {
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
        tuile.setBackground(bg);
        listeA.clear();
        pions.removeAll();
        pions.setBackground(bg);
        for (Aventurier a : listeJ) {
            JButton pion = new JButton(a.getRole().toString());
            pion.setBackground(a.getColor());
            pion.setEnabled(false);
            listeA.put(pion, a);
            pion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message();
                    m.type = TypeMessage.CLIC_JOUEUR;
                    m.nomR = listeA.get(pion).getRole();
                    notifierObservateur(m);
                }
            });
            pions.add(pion);
        }
        for (int i = 0; i + listeJ.size() < 4; i++) {
            pions.add(new JLabel(""));
        }
        pions.revalidate();
    }

    public JButton getBoutonTuile() {
        return tuile;
    }

    public void paint(Color bg) {
        tuile.setBackground(bg);
        pions.setBackground(bg);
    }

    public void activerJ(Aventurier adv) {
        for (Component c : pions.getComponents()) {
            if (c instanceof JButton) {
                JButton b = (JButton) c;
                if (!b.getText().equalsIgnoreCase(adv.getRole().toString())) {
                    b.setBackground(Color.red);
                    b.setEnabled(true);
                }
            }
        }
    }
    
    public boolean activerJ(ArrayList<Aventurier> adv) {
        boolean change =false;
        for(JButton b : listeA.keySet()){
            if(!adv.contains(listeA.get(b))){
               b.setEnabled(true);
               b.setBackground(Color.red);
               change = true;
            }
        }
        return change;
         
    }
    
    

    public void activerJ2() {
        for (Component c : pions.getComponents()) {
            if (c instanceof JButton) {
                JButton b = (JButton) c;
                b.setBackground(Color.red);
                b.setEnabled(true);
            }
        }

    }

    public Color getColor() {
        return tuile.getBackground();
    }

}
