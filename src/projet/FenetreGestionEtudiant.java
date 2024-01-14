package projet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class FenetreGestionEtudiant extends JFrame {
    private Connecter connecter;

    private JTabbedPane jTabbedPane;
    private JPanel panelAjoutEtudiant, panelListeEtudiants, panelUpdateEtudiant, panelDeleteEtudiant;

    private JLabel labelCin, labelNom, labelPrenom, labelAdresse,labelUpdateNom, labelUpdatePrenom, labelUpdateAdresse,labelDeleteNomValue,
    labelDeleteNom, labelDeletePrenom, labelDeletePrenomValue,
    labelDeleteAdresse, labelDeleteAdresseValue;;
    private JTextField textFieldCin, textFieldNom, textFieldPrenom, textFieldAdresse,textFieldUpdateNom, textFieldUpdatePrenom, textFieldUpdateAdresse;
    private JButton buttonAjouter,buttonModifier,buttonSupprimer;
    private DefaultTableModel modelTable;
    private JTable tableEtudiants;
    private JScrollPane scrollPane;
    private JComboBox<Integer> comboBoxUpdate,comboBoxDelete;

    public FenetreGestionEtudiant() {
        setTitle("Gestion des étudiants");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setLocationRelativeTo(null);

        connecter = new Connecter();

        initOngletAjoutEtudiant();
        initOngletListeEtudiants();
        initOngletUpdateEtudiant();
        initOngletDeleteEtudiant();

        jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Ajout Etudiant", panelAjoutEtudiant);
        jTabbedPane.addTab("Liste Etudiants", panelListeEtudiants);
        jTabbedPane.addTab("Update Etudiant", panelUpdateEtudiant);
        jTabbedPane.addTab("Delete Etudiant", panelDeleteEtudiant);

        getContentPane().add(jTabbedPane);

    }

    public void initOngletAjoutEtudiant() {
        panelAjoutEtudiant = new JPanel();
        panelAjoutEtudiant.setLayout(new GridLayout(5, 2));

        labelCin = new JLabel("CIN:");
        textFieldCin = new JTextField();
        labelNom = new JLabel("Nom:");
        textFieldNom = new JTextField(); 
        labelPrenom = new JLabel("Prénom:");
        textFieldPrenom = new JTextField();
        labelAdresse = new JLabel("Adresse:");
        textFieldAdresse = new JTextField();
        buttonAjouter = new JButton("Ajouter");

        panelAjoutEtudiant.add(labelCin);
        panelAjoutEtudiant.add(textFieldCin);
        panelAjoutEtudiant.add(labelNom);
        panelAjoutEtudiant.add(textFieldNom);
        panelAjoutEtudiant.add(labelPrenom);
        panelAjoutEtudiant.add(textFieldPrenom);
        panelAjoutEtudiant.add(labelAdresse);
        panelAjoutEtudiant.add(textFieldAdresse);
        panelAjoutEtudiant.add(new JLabel());
        panelAjoutEtudiant.add(buttonAjouter);


        buttonAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldCin.getText().isEmpty() || textFieldNom.getText().isEmpty() || textFieldPrenom.getText().isEmpty() || textFieldAdresse.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Ajout effectué avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    ajouterEtudiant();
                   remplirComboBoxUpdate();
                   remplirTableEtudiants();
                   remplirComboBoxDelete();
                }
            }
        });
    }

    public void initOngletListeEtudiants() {
        panelListeEtudiants = new JPanel();
        panelListeEtudiants.setLayout(new BorderLayout());

        modelTable = new DefaultTableModel();
        modelTable.addColumn("CIN");
        modelTable.addColumn("Nom");
        modelTable.addColumn("Prénom");
        modelTable.addColumn("Adresse");

        tableEtudiants = new JTable(modelTable);
        scrollPane = new JScrollPane(tableEtudiants);

        panelListeEtudiants.add(scrollPane, BorderLayout.CENTER);

        remplirTableEtudiants();
    }

    public void initOngletUpdateEtudiant() {
        panelUpdateEtudiant = new JPanel();
        panelUpdateEtudiant.setLayout(new GridLayout(5, 2));

        JLabel labelUpdateCin = new JLabel("CIN:");
        comboBoxUpdate = new JComboBox<>();
        labelUpdateNom = new JLabel("Nom:");
        textFieldUpdateNom = new JTextField();
        labelUpdatePrenom = new JLabel("Prénom:");
        textFieldUpdatePrenom = new JTextField();
        labelUpdateAdresse = new JLabel("Adresse:");
        textFieldUpdateAdresse = new JTextField();
        buttonModifier = new JButton("Modifier");
        
        JScrollPane scrollPaneUpdate = new JScrollPane(comboBoxUpdate);

        panelUpdateEtudiant.add(labelUpdateCin);
        panelUpdateEtudiant.add(comboBoxUpdate);
        panelUpdateEtudiant.add(labelUpdateNom);
        panelUpdateEtudiant.add(textFieldUpdateNom);
        panelUpdateEtudiant.add(labelUpdatePrenom);
        panelUpdateEtudiant.add(textFieldUpdatePrenom);
        panelUpdateEtudiant.add(labelUpdateAdresse);
        panelUpdateEtudiant.add(textFieldUpdateAdresse);
        panelUpdateEtudiant.add(new JLabel());
        panelUpdateEtudiant.add(buttonModifier);
        
       
        
        comboBoxUpdate.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedCIN = comboBoxUpdate.getSelectedItem().toString();
                    afficherDonneesEtudiant(selectedCIN);
                }
            }
        });
        buttonModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	  if (textFieldUpdateNom.getText().isEmpty() || textFieldUpdatePrenom.getText().isEmpty() || textFieldUpdateAdresse.getText().isEmpty()) {
                      JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                  } else {
                      JOptionPane.showMessageDialog(null, "Update effectué avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);

                      modifierEtudiant();
                      remplirComboBoxUpdate();
                      remplirComboBoxDelete();
                  }
              }
                
        });

        
        remplirTableEtudiants();
    }

    public void initOngletDeleteEtudiant() {
        panelDeleteEtudiant = new JPanel();
        panelDeleteEtudiant.setLayout(new GridLayout(5, 2));

        JLabel labelDeleteCin = new JLabel("CIN:");
        comboBoxDelete = new JComboBox<>();
        labelDeleteNom = new JLabel("Nom:");
        labelDeleteNomValue = new JLabel();
        labelDeletePrenom = new JLabel("Prénom:");
        labelDeletePrenomValue = new JLabel();
        labelDeleteAdresse = new JLabel("Adresse:");
        labelDeleteAdresseValue = new JLabel();
        buttonSupprimer = new JButton("Supprimer");

        JScrollPane scrollPaneDelete = new JScrollPane(comboBoxDelete);

        panelDeleteEtudiant.add(labelDeleteCin);
        panelDeleteEtudiant.add(comboBoxDelete);
        panelDeleteEtudiant.add(labelDeleteNom);
        panelDeleteEtudiant.add(labelDeleteNomValue);
        panelDeleteEtudiant.add(labelDeletePrenom);
        panelDeleteEtudiant.add(labelDeletePrenomValue);
        panelDeleteEtudiant.add(labelDeleteAdresse);
        panelDeleteEtudiant.add(labelDeleteAdresseValue);
        panelDeleteEtudiant.add(new JLabel());
        panelDeleteEtudiant.add(buttonSupprimer);

        buttonSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer l'étudiant ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                
                if (confirmation == JOptionPane.YES_OPTION) {
                	  supprimerEtudiant();
                        JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }              
            }
           
        });
        

        comboBoxDelete.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedCIN = comboBoxDelete.getSelectedItem().toString();
                    suppetu(selectedCIN);
                }
            }
        });

        remplirComboBoxDelete();
        remplirComboBoxUpdate();
        remplirTableEtudiants();
    }

    

    public void remplirTableEtudiants() {
        modelTable.setRowCount(0);

        try {
            String query = "SELECT * FROM etudiant";
            ResultSet resultSet = connecter.executerRequete(query);

            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("cin"));
                row.add(resultSet.getString("nom"));
                row.add(resultSet.getString("prenom"));
                row.add(resultSet.getString("adresse"));
                modelTable.addRow(row);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remplirComboBoxUpdate() {
        comboBoxUpdate.removeAllItems();

        try {
            String query = "SELECT cin FROM etudiant";
            ResultSet resultSet = connecter.executerRequete(query);

            while (resultSet.next()) {
                comboBoxUpdate.addItem(resultSet.getInt("cin"));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (comboBoxUpdate.getItemCount() > 0) {
            comboBoxUpdate.setSelectedIndex(-1);
        }
    }

    public void remplirComboBoxDelete() {
        Connecter connecter = new Connecter();
        ResultSet resultat = connecter.executerRequete("SELECT CIN FROM Etudiant");

        try {
            comboBoxDelete.removeAllItems();
            while (resultat.next()) {
                String cin = resultat.getString("CIN");
                comboBoxDelete.addItem(Integer.parseInt(cin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (comboBoxDelete.getItemCount() > 0) {
        	comboBoxDelete.setSelectedIndex(-1);
    }
    }
    public void suppetu(String selectedCIN) {
        String query = "SELECT * FROM Etudiant WHERE CIN = '" + selectedCIN + "'";
        Connecter connecter = new Connecter();
        ResultSet resultat = connecter.executerRequete(query);

        try {
            if (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String adresse = resultat.getString("adresse");

                labelDeleteNomValue.setText(nom);
                labelDeletePrenomValue.setText(prenom);
                labelDeleteAdresseValue.setText(adresse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterEtudiant() {
        String cin = textFieldCin.getText();
        String nom = textFieldNom.getText();
        String prenom = textFieldPrenom.getText();
        String adresse = textFieldAdresse.getText();

        String query = "INSERT INTO etudiant(cin, nom, prenom, adresse) VALUES('" +
                cin + "', '" + nom + "', '" + prenom + "', '" + adresse + "')";
        connecter.executerUpdate(query);

        textFieldCin.setText("");
        textFieldNom.setText("");
        textFieldPrenom.setText("");
        textFieldAdresse.setText("");

        remplirTableEtudiants();
    }
    

    public void modifierEtudiant() {
        int cin = (int) comboBoxUpdate.getSelectedItem();
        String nom = textFieldUpdateNom.getText();
        String prenom = textFieldUpdatePrenom.getText();
        String adresse = textFieldUpdateAdresse.getText();

        String query = "UPDATE etudiant SET nom = '" + nom + "', prenom = '" + prenom +
                "', adresse = '" + adresse + "' WHERE cin = " + cin;
        connecter.executerUpdate(query);

        textFieldUpdateNom.setText("");
        textFieldUpdatePrenom.setText("");
        textFieldUpdateAdresse.setText("");

        remplirTableEtudiants();
    }
    public void afficherDonneesEtudiant(String selectedCIN) {
        String query = "SELECT * FROM Etudiant WHERE CIN = '" + selectedCIN + "'";
        Connecter connecter = new Connecter();
        ResultSet resultat = connecter.executerRequete(query);

        try {
            if (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String adresse = resultat.getString("adresse");

                textFieldUpdateNom.setText(nom);
                textFieldUpdatePrenom.setText(prenom);
                textFieldUpdateAdresse.setText(adresse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerEtudiant() {
        int cin = (int) comboBoxDelete.getSelectedItem();

        String query = "DELETE FROM etudiant WHERE cin = " + cin;
        connecter.executerUpdate(query);

        // Refresh the table and combo boxes
        remplirTableEtudiants();
        remplirComboBoxUpdate();
        remplirComboBoxDelete();
    }

    public static void main(String[] args) {
        // Create and show the main window
        FenetreGestionEtudiant fenetre = new FenetreGestionEtudiant();
        fenetre.setVisible(true);
    }
}
