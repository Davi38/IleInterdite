/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.vues;

import ileinterdite.jeu.Role;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
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
        
        Image imgFond=null;
        try {
            imgFond = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/autre/fond.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de fond.png");
        }
        
        Image titre=null;
        try {
            titre = ImageIO.read(new File(System.getProperty("user.dir") + "/src/images/autre/titre.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de titre.png");
        }


        //déclaration de la fenêtre
        fenetre = new JFrame("Choix des Joueurs");
        
        JPanel pTitre = new PanelFond(titre,null);
        pTitre.setOpaque(false);
        pTitre.setPreferredSize(new Dimension(fenetre.getWidth(),180));
        
        
        
        
        
        PanelFond main = new PanelFond(imgFond, new BorderLayout());
        fenetre.add(main);
        main.add(pTitre,BorderLayout.NORTH);

        JPanel zonehaut = new JPanel(new GridLayout(4,2));
        zonehaut.setOpaque(false);
        JPanel zonebas = new JPanel();
        zonebas.setOpaque(false);
        JPanel panelCentre = new JPanel(new GridLayout(4, 2));
        panelCentre.setOpaque(false);
        
        
        listeNiveau = new JComboBox(new String[]{"Novice", "Normal" , "Elite", "Légendaire"});

        listeRole1 = new JComboBox();
        listeRole2 = new JComboBox();
        listeRole3 = new JComboBox();
        listeRole4 = new JComboBox();
        
        roles = new ArrayList<String>();

        //Déclaration des éléments de la fenêtre
        JLabel choixNiveau = new JLabel("Choix du niveau de difficulté :");
        choixNiveau.setForeground(Color.WHITE);
        choixNiveau.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        JLabel choixJoueur = new JLabel("Choix des joueurs participants :");
        choixJoueur.setForeground(Color.WHITE);
        choixJoueur.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
     
        //panels
        roles.add("Pilote");
        roles.add("Navigateur");
        roles.add("Messager");
        roles.add("Explorateur");
        roles.add("Plongeur");
        roles.add("Ingenieur");
        
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
            listeRole1.setVisible(true);
            
            listeRole2.addItem(roles.get(i));
            listeRole2.setSelectedItem(null);
            listeRole2.setVisible(true);
            
            listeRole3.addItem(roles.get(i));
            listeRole3.setSelectedItem(null);
            listeRole3.setVisible(false);   
            
            listeRole4.addItem(roles.get(i));
            listeRole4.setSelectedItem(null);
            listeRole4.setVisible(false);   
        }
        
        removeJoueur.setVisible(false);
        champNom1.setVisible(true);
        champNom2.setVisible(true);
        champNom3.setVisible(false);
        champNom4.setVisible(false);
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
        JPanel centre = new JPanel(new BorderLayout());
        centre.setOpaque(false);
        main.add(centre,BorderLayout.CENTER);
        
        centre.add(zonehaut, BorderLayout.NORTH);
        centre.add(panelCentre, BorderLayout.CENTER);
        main.add(zonebas, BorderLayout.SOUTH);

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
                if(listeRole4.isVisible()){ 
                    Role r4 = Role.valueOf(listeRole4.getSelectedItem().toString().toUpperCase());
                    Role r3 = Role.valueOf(listeRole3.getSelectedItem().toString().toUpperCase());
                    liste.put(r4,champNom4.getText());
                    liste.put( r3,champNom3.getText());
                }else if(listeRole3.isVisible()){
                    Role r3 = Role.valueOf(listeRole3.getSelectedItem().toString().toUpperCase());
                    liste.put(r3,champNom3.getText());
                }
                    Role r2 = Role.valueOf(listeRole2.getSelectedItem().toString().toUpperCase());
                    Role r1 = Role.valueOf(listeRole1.getSelectedItem().toString().toUpperCase());
                    liste.put(r2, champNom2.getText());
                    liste.put(r1, champNom1.getText());

                m.listeJ = liste;
                m.ind = listeNiveau.getSelectedIndex()+1;
                notifierObservateur(m);
                }
            }

        });
        
        addJoueur.addActionListener ( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!listeRole3.isVisible()) {
                    listeRole3.setVisible(true);
                    champNom3.setVisible(true);
                    
        
                    removeJoueur.setVisible(true);
                } else if (!listeRole4.isVisible()) {
                    listeRole4.setVisible(true);
                    champNom4.setVisible(true);
                    addJoueur.setVisible(false);
                    removeJoueur.setVisible(true);
                }
        }});
        removeJoueur.addActionListener ( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole3.isVisible() && listeRole4.isVisible()) {
                    listeRole4.setVisible(false);
                    listeRole4.setSelectedItem(null);
                    champNom4.setVisible(false);
                    addJoueur.setVisible(true);
                    
                } else if (listeRole4.isVisible() == false) {
                    listeRole3.setVisible(false);
                    listeRole3.setSelectedItem(null);
                    champNom3.setVisible(false);
                    removeJoueur.setVisible(false);
                    addJoueur.setVisible(true);
                    
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
        if(listeRole4.isVisible()){
            test = listeRole4.getSelectedItem()!=null && listeRole3.getSelectedItem()!=null;
            
        }else if(listeRole3.isVisible()){
            test = listeRole3.getSelectedItem()!=null;
        }
        return test && listeRole1.getSelectedItem()!= null && listeRole2.getSelectedItem()!=null; 
    }
    
   
    }


        
        

    
    

