package projet;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaFenetre extends JFrame {
    private JPanel panel = new JPanel();
    private JLabel labelLogin = new JLabel("Login");
    private JLabel labelPassword = new JLabel("Password");
    private JTextField textFieldLogin = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(15);
    private JButton buttonConnexion = new JButton("Connexion");

    public MaFenetre() {
        this.setTitle("Ma première fenêtre");
        this.setSize(500, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel.setBackground(Color.PINK);
        this.panel.setLayout(new GridLayout(3, 2)); 
   
        this.panel.add(labelLogin);
        this.panel.add(textFieldLogin);
        this.panel.add(labelPassword);
        this.panel.add(passwordField);
        this.panel.add(new JLabel()); 
        this.panel.add(buttonConnexion);
        this.add(panel);
        this.setVisible(true);

        buttonConnexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (authentification(textFieldLogin.getText(), new String(passwordField.getPassword()))) {
                    JOptionPane.showMessageDialog(panel, "Bienvenue! " + textFieldLogin.getText(),
                            "Confirmation de connexion", JOptionPane.INFORMATION_MESSAGE);
                    
                    FenetreGestionEtudiant fenetreGestionEtudiant = new FenetreGestionEtudiant();
                    fenetreGestionEtudiant.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(panel, "Login ou mot de passe incorrect.",
                            "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }

    private boolean authentification(String login, String password) {
        return login.equals("admin") && password.equals("adminpwd");
    }

    public static void main(String[] args) {
        MaFenetre fenetre = new MaFenetre();
    }
}

