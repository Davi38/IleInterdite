/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VueFinJeu extends Observe {

    private JFrame fenetreFin;

    public VueFinJeu(boolean gagne) {

        //déclaration de la fenêtre
        fenetreFin = new JFrame("Fin De Partie");
        fenetreFin.setLayout(new BorderLayout());

        //panels
        JPanel zonebas = new JPanel();
        JPanel zonehaut = new JPanel();
        JPanel zonemilieu = new JPanel();

        //elements
        JButton recommencer = new JButton("Recommencer");
        JButton quitter = new JButton("Quitter");

        //positionnement des panels
        fenetreFin.add(zonebas, BorderLayout.SOUTH);
        fenetreFin.add(zonemilieu, BorderLayout.CENTER);
        fenetreFin.add(zonehaut, BorderLayout.NORTH);

        zonebas.add(recommencer);
        zonebas.add(quitter);

        if (gagne == false) {

            JLabel jlabelB = new JLabel("PERDU !");
            jlabelB.setFont(new Font("Verdana", 1, 15));
            zonehaut.add(jlabelB);

            JLabel jlabel = new JLabel("L' île vous a malheureusement englouti, vous n'avez pas pu tous atteindre l'hélicoptère avec tout les trésors, vous ferez mieux la prochaine fois !");
            jlabel.setFont(new Font("Verdana", 1, 12));
            zonemilieu.add(jlabel);

        }

        if (gagne == true) {

            JLabel jlabelP = new JLabel("BRAVO !");
            jlabelP.setFont(new Font("Verdana", 1, 15));
            zonehaut.add(jlabelP);

            JLabel jlabel2 = new JLabel("L'île n'a pas eu raison de vous, vous êtes un véritable survivant, défiez à nouveau votre destin ou restez en là !");
            jlabel2.setFont(new Font("Verdana", 1, 12));
            zonemilieu.add(jlabel2);

        }

        //Dimensions de la fenêtre
        fenetreFin.setSize(1100, 150);
        fenetreFin.setVisible(true);

        // ActionListeners
        recommencer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                fenetreFin.setVisible(false);
                Message m = new Message();
                m.type = TypeMessage.RECOMMENCER;
                notifierObservateur(m);

            }

        });

        quitter.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                fenetreFin.setVisible(false);
            }

        });

        //Action sur le ChampNom
        MouseListener click = new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        };
    }
    
    public void afficherFenetre(){
        fenetreFin.setVisible(true);
    }
    public void cacherFenetre(){
        fenetreFin.setVisible(false); 
    }
    
}
