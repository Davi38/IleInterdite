/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author bouchtir
 */
public class VueInscription extends JPanel {
    private JPanel zonehaut;
    private JPanel panelCentre;
    private JPanel zonebas;
    private JComboBox listeRole1;
    private JComboBox listeRole2;
    private JComboBox listeRole3;
    private JComboBox listeRole4;
    private ArrayList<String> roles;
    


    public VueInscription() {
        


        //déclaration de la fenêtre
        JFrame fenetre = new JFrame("Choix des Joueurs");
        fenetre.setLayout(new BorderLayout());

        zonebas = new JPanel();
        panelCentre = new JPanel(new GridLayout(4, 2));
        zonehaut = new JPanel();

        listeRole1 = new JComboBox();
        listeRole2 = new JComboBox();
        listeRole3 = new JComboBox();
        listeRole4 = new JComboBox();
        roles = new ArrayList<String>();

        //Déclaration des éléments de la fenêtre
        JLabel choixJoueur = new JLabel("Choix des joueurs participants :");

        //panels
        roles.add("Pilote");
        roles.add("Navigateur");
        roles.add("Messager");
        roles.add("Explorateur");
        roles.add("Plongeur");
        roles.add("Ingénieur");

        for (int i = 0; i < roles.size(); i++) {
            listeRole1.addItem(roles.get(i));
            listeRole1.setSelectedItem(null);
            listeRole1.setEnabled(true);
            
            listeRole2.addItem(roles.get(i));
            listeRole2.setSelectedItem(null);
            listeRole2.setEnabled(true);
            
            listeRole3.addItem(roles.get(i));
            listeRole3.setSelectedItem(null);
            listeRole3.setEnabled(true);   
            
            listeRole4.addItem(roles.get(i));
            listeRole4.setSelectedItem(null);
            listeRole4.setEnabled(true);   
        }
        
        listeRole1.addItemListener(
                new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie){
                        Object a1 = ie.getItem();
                        Object a2 =  listeRole1.getSelectedItem();

                        if (ie.getStateChange() == ItemEvent.DESELECTED) {
                            listeRole2.addItem(ie.getItem());
                            listeRole3.addItem(ie.getItem());
                            listeRole4.addItem(ie.getItem());
                            listeRole2.removeItem(a2);
                            listeRole3.removeItem(a2);
                            listeRole4.removeItem(a2);
                        } else if(ie.getStateChange() == ItemEvent.SELECTED){
                            listeRole2.removeItem(a2);
                            listeRole3.removeItem(a2);
                            listeRole4.removeItem(a2);
                        } 
                    }
                });
        listeRole2.addItemListener(
                new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie){
                        Object b1 = ie.getItem();
                        Object b2 =  listeRole2.getSelectedItem();

                        if (ie.getStateChange() == ItemEvent.DESELECTED) {
                            listeRole1.addItem(ie.getItem());
                            listeRole3.addItem(ie.getItem());
                            listeRole4.addItem(ie.getItem());
                            listeRole1.removeItem(b2);
                            listeRole3.removeItem(b2);
                            listeRole4.removeItem(b2);
                        } else if(ie.getStateChange() == ItemEvent.SELECTED){
                            listeRole1.removeItem(b2);
                            listeRole3.removeItem(b2);
                            listeRole4.removeItem(b2);
                        }
                    }
                });
        
        listeRole3.addItemListener(
                new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie){
                        Object c1 = ie.getItem();
                        Object c2 =  listeRole3.getSelectedItem();  
                        
                        if (ie.getStateChange() == ItemEvent.DESELECTED) {
                            listeRole1.addItem(ie.getItem());
                            listeRole2.addItem(ie.getItem());
                            listeRole4.addItem(ie.getItem());
                            listeRole1.removeItem(c2);
                            listeRole2.removeItem(c2);
                            listeRole4.removeItem(c2);
                        } else if(ie.getStateChange() == ItemEvent.SELECTED){
                            listeRole1.removeItem(c2);
                            listeRole2.removeItem(c2);
                            listeRole4.removeItem(c2);
                        }
                    }
                });
        listeRole4.addItemListener(
                new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie){
                        Object d1 = ie.getItem();
                        Object d2 =  listeRole4.getSelectedItem();
                        
                        if (ie.getStateChange() == ItemEvent.DESELECTED) {
                            listeRole1.addItem(ie.getItem());
                            listeRole2.addItem(ie.getItem());
                            listeRole3.addItem(ie.getItem());
                            listeRole1.removeItem(d2);
                            listeRole2.removeItem(d2);
                            listeRole3.removeItem(d2);
                        } else if(ie.getStateChange() == ItemEvent.SELECTED){
                            listeRole1.removeItem(d2);
                            listeRole2.removeItem(d2);
                            listeRole3.removeItem(d2);
                        }
                    }
                });
        
        
        
        //elements
        

        JButton demarrer = new JButton("Demarrer partie");
        JButton addjoueur = new JButton("Add joueur");
        JButton remove = new JButton("Remove Joueur");

        JTextField champNom1 = new JTextField("Nom du Joueur 1");
        JTextField champNom2 = new JTextField("Nom du Joueur 2");
        JTextField champNom3 = new JTextField("Nom du Joueur 3");
        JTextField champNom4 = new JTextField("Nom du Joueur 4");

        //Ajout des éléments à la fenêtre
        //positionnement des panels
        fenetre.add(zonebas, BorderLayout.SOUTH);
        fenetre.add(panelCentre, BorderLayout.CENTER);
        fenetre.add(zonehaut, BorderLayout.NORTH);

        //positionnement des boutons
        zonehaut.add(choixJoueur);

        panelCentre.add(champNom1);
        panelCentre.add(listeRole1);

        panelCentre.add(champNom2);
        panelCentre.add(listeRole2);

        panelCentre.add(champNom3);
        panelCentre.add(listeRole3);

        panelCentre.add(champNom4);
        panelCentre.add(listeRole4);

        zonebas.add(demarrer);
        zonebas.add(addjoueur);
        zonebas.add(remove);

        //Dimensions de la fenêtre
        fenetre.setSize(700, 500);
        fenetre.setVisible(true);

        // ActionListeners
        demarrer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


            }

        });

        

    }
    public static void main(String [] args) {
        // Instanciation de la fenêtre 
        VueInscription vueInscription = new VueInscription();
    }

        
        

    }
    

