/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exameniilab;

import exameniilab.HashTable.Trophy;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author josuc
 */
public class GUI {
    private PSNUsers USERS;
    private JFrame frame;
    private JTextField txtuser, txtGame, txtName;
    private JComboBox<Trophy> ComboBox;
    private JTextArea infoArea;

    public GUI() throws IOException {
        USERS = new PSNUsers();
        frame = new JFrame("PSN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        txtuser = new JTextField(20);
        txtuser.setBounds(100, 10, 160, 25);
        panel.add(txtuser);

        JButton addButton = new JButton("Add User");
        addButton.setBounds(10, 40, 100, 25);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtuser.getText();
                try {
                    USERS.addUser(username);                                        
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(addButton);
        
        JButton deactivateButton = new JButton("Deactivate User");
        deactivateButton.setBounds(120, 40, 140, 25);
        deactivateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtuser.getText();
                try {
                    USERS.deactivateUser(username);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(deactivateButton);

        JLabel trophyGameLabel = new JLabel("Juego");
        trophyGameLabel.setBounds(10, 70, 80, 25);
        panel.add(trophyGameLabel);

        txtGame = new JTextField(20);
        txtGame.setBounds(120, 70, 160, 25);
        panel.add(txtGame);

        JLabel trophyNameLabel = new JLabel("Nombre Trofeo");
        trophyNameLabel.setBounds(10, 100, 100, 25);
        panel.add(trophyNameLabel);

        txtName = new JTextField(20);
        txtName.setBounds(120, 100, 160, 25);
        panel.add(txtName);

        JLabel trophyTypeLabel = new JLabel("Tipo Trofeo");
        trophyTypeLabel.setBounds(10, 130, 80, 25);
        panel.add(trophyTypeLabel);

        ComboBox = new JComboBox<>(Trophy.values());
        ComboBox.setBounds(120, 130, 160, 25);
        panel.add(ComboBox);

        JButton addTrophyButton = new JButton("Agregar Trofeo");
        addTrophyButton.setBounds(10, 160, 120, 25);
        addTrophyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtuser.getText();
                String trophyGame = txtGame.getText();
                String trophyName = txtName.getText();
                Trophy type = (Trophy)ComboBox.getSelectedItem();
                try {
                    USERS.addTrophieTo(username, trophyGame, trophyName, type);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(addTrophyButton);

        JButton searchButton = new JButton("Search User");
        searchButton.setBounds(270, 40, 130, 25);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtuser.getText();
                String info = "";
                try {
                    info = USERS.playerInfo(username);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                infoArea.setText(info);
            }
        });
        
	panel.add(searchButton);

        infoArea = new JTextArea();
        infoArea.setBounds(10,200 ,460 ,250 );
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(10,200 ,460 ,250 );
        panel.add(scrollPane);	

        frame.setVisible(true);
    }
}
