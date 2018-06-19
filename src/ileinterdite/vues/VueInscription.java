/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import ileinterdite.jeu.Role;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.ldap.HasControls;
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
public class VueInscription extends Observe {
    
    private JComboBox listeNiveau;

    private JComboBox listeRole1;
    private JComboBox listeRole2;
    private JComboBox listeRole3;
    private JComboBox listeRole4;
    
    private ArrayList<String> roles;
    private JButton demarrer;
    private JButton addJoueur;
    private JButton removeJoueur;
    
    private JTextField champNom1; 
    private JTextField champNom2;
    private JTextField champNom3;
    private JTextField champNom4;
    
    private JFrame fenetre;


    public VueInscription() {
        


        //déclaration de la fenêtre
        fenetre = new JFrame("Choix des Joueurs");
        fenetre.setLayout(new BorderLayout());

        JPanel zonehaut = new JPanel(new GridLayout(4,2));
        JPanel zonebas = new JPanel();
        JPanel panelCentre = new JPanel(new GridLayout(4, 2));
        
        
        listeNiveau = new JComboBox(new String[]{"Novice", "Normal" , "Elite", "Légendaire"});

        listeRole1 = new JComboBox();
        listeRole2 = new JComboBox();
        listeRole3 = new JComboBox();
        listeRole4 = new JComboBox();
        
        roles = new ArrayList<String>();

        //Déclaration des éléments de la fenêtre
        JLabel choixNiveau = new JLabel("Choix du niveau de difficulté :");
        JLabel choixJoueur = new JLabel("Choix des joueurs participants :");
        
     
        //panels
        roles.add("Pilote");
        roles.add("Navigateur");
        roles.add("Messager");
        roles.add("Explorateur");
        roles.add("Plongeur");
        roles.add("Ingénieur");
        
        champNom1 = new JTextField("Nom du Joueur 1");
        champNom2 = new JTextField("Nom du Joueur 2");
        champNom3 = new JTextField("Nom du Joueur 3");
        champNom4 = new JTextField("Nom du Joueur 4");
        
        demarrer = new JButton("Demarrer partie");
        addJoueur = new JButton("Add joueur");
        removeJoueur = new JButton("Remove Joueur");

        for (int i = 0; i < roles.size(); i++) {
            listeRole1.addItem(roles.get(i));
            listeRole1.setSelectedItem(null);
            listeRole1.setEnabled(true);
            
            listeRole2.addItem(roles.get(i));
            listeRole2.setSelectedItem(null);
            listeRole2.setEnabled(true);
            
            listeRole3.addItem(roles.get(i));
            listeRole3.setSelectedItem(null);
            listeRole3.setEnabled(false);   
            
            listeRole4.addItem(roles.get(i));
            listeRole4.setSelectedItem(null);
            listeRole4.setEnabled(false);   
        }
        
        removeJoueur.setEnabled(false);
        champNom1.setEnabled(true);
        champNom2.setEnabled(true);
        champNom3.setEnabled(false);
        champNom4.setEnabled(false);
        listeNiveau.setSelectedIndex(0);
        
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
        



        //Ajout des éléments à la fenêtre
        //positionnement des panels
        
        
        fenetre.add(zonehaut, BorderLayout.NORTH);
        fenetre.add(panelCentre, BorderLayout.CENTER);
        fenetre.add(zonebas, BorderLayout.SOUTH);

        //positionnement des boutons
        zonehaut.add(new JLabel("  "));
        zonehaut.add(new JLabel("  "));
        zonehaut.add(choixNiveau);
        zonehaut.add(listeNiveau);
        
        zonehaut.add(new JLabel("  "));
        zonehaut.add(new JLabel("  "));
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
        zonebas.add(addJoueur);
        zonebas.add(removeJoueur);

        //Dimensions de la fenêtre
        fenetre.setSize(700, 500);
        fenetre.setVisible(true);

        // ActionListeners
        demarrer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(TestDemarrer()){
                
                Message m = new Message();
                m.type = TypeMessage.DEMARRER;
                HashMap<Role, String> liste = new HashMap<Role, String>();
                if(listeRole4.isEnabled()){ 
                    Role r4 = Role.valueOf(listeRole4.getSelectedItem().toString().toUpperCase());
                    Role r3 = Role.valueOf(listeRole3.getSelectedItem().toString().toUpperCase());
                    liste.put(r4,champNom4.getText());
                    liste.put( r3,champNom3.getText());
                }else if(listeRole3.isEnabled()){
                    Role r3 = Role.valueOf(listeRole3.getSelectedItem().toString().toUpperCase());
                    liste.put(r3,champNom3.getText());
                }
                    Role r2 = Role.valueOf(listeRole2.getSelectedItem().toString().toUpperCase());
                    Role r1 = Role.valueOf(listeRole1.getSelectedItem().toString().toUpperCase());
                    liste.put(r2, champNom2.getText());
                    liste.put(r1, champNom1.getText());

                m.listeJ = liste;
                notifierObservateur(m);
                }
            }

        });
        
        addJoueur.addActionListener ( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!listeRole3.isEnabled()) {
                    listeRole3.setEnabled(true);
                    champNom3.setEnabled(true);
                    
        
                    removeJoueur.setEnabled(true);
                } else if (!listeRole4.isEnabled()) {
                    listeRole4.setEnabled(true);
                    champNom4.setEnabled(true);
                    addJoueur.setEnabled(false);
                    removeJoueur.setEnabled(true);
                }
        }});
        removeJoueur.addActionListener ( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole3.isEnabled() && listeRole4.isEnabled()) {
                    listeRole4.setEnabled(false);
                    listeRole4.setSelectedItem(null);
                    champNom4.setEnabled(false);
                    addJoueur.setEnabled(true);
                    
                } else if (listeRole4.isEnabled() == false) {
                    listeRole3.setEnabled(false);
                    listeRole3.setSelectedItem(null);
                    champNom3.setEnabled(false);
                    removeJoueur.setEnabled(false);
                    addJoueur.setEnabled(true);
                    
                }
        }});

        

    }
    
    public void afficherFenetre(){
        fenetre.setVisible(true);
    }
    public void cacherFenetre(){
        fenetre.setVisible(false); 
    }
    
    public boolean TestDemarrer(){
        boolean test = true;
        if(listeRole4.isEnabled()){
            test = listeRole4.getSelectedItem()!=null && listeRole3.getSelectedItem()!=null;
            
        }else if(listeRole3.isEnabled()){
            test = listeRole3.getSelectedItem()!=null;
        }
        return test && listeRole1.getSelectedItem()!= null && listeRole2.getSelectedItem()!=null; 
    }
    
   
    }


        
        

    
    

