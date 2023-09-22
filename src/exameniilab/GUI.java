/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exameniilab;

import exameniilab.HashTable.Trophy;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author josuc
 */
public class GUI {
    private PSNUsers psnUsers;
    private JFrame frame;
    private JTextField usernameField, trophyGameField, trophyNameField;
    private JComboBox<Trophy> trophyTypeBox;
    private JTextArea infoArea;

    public GUI() throws IOException {
        psnUsers = new PSNUsers();
        frame = new JFrame("PSN Users");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 10, 160, 25);
        panel.add(usernameField);

        JButton addButton = new JButton("Add User");
        addButton.setBounds(10, 40, 100, 25);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                try {
                    psnUsers.addUser(username);
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
                String username = usernameField.getText();
                try {
                    psnUsers.deactivateUser(username);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(deactivateButton);

        JLabel trophyGameLabel = new JLabel("Trophy Game");
        trophyGameLabel.setBounds(10, 70, 80, 25);
        panel.add(trophyGameLabel);

        trophyGameField = new JTextField(20);
        trophyGameField.setBounds(100, 70, 160, 25);
        panel.add(trophyGameField);

        JLabel trophyNameLabel = new JLabel("Trophy Name");
        trophyNameLabel.setBounds(10, 100, 80, 25);
        panel.add(trophyNameLabel);

        trophyNameField = new JTextField(20);
        trophyNameField.setBounds(100, 100, 160, 25);
        panel.add(trophyNameField);

        JLabel trophyTypeLabel = new JLabel("Trophy Type");
        trophyTypeLabel.setBounds(10, 130, 80, 25);
        panel.add(trophyTypeLabel);

        trophyTypeBox = new JComboBox<>(Trophy.values());
        trophyTypeBox.setBounds(100, 130, 160, 25);
        panel.add(trophyTypeBox);

        JButton addTrophyButton = new JButton("Add Trophy");
        addTrophyButton.setBounds(10, 160, 100, 25);
        addTrophyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String trophyGame = trophyGameField.getText();
                String trophyName = trophyNameField.getText();
                Trophy type = (Trophy)trophyTypeBox.getSelectedItem();
                try {
                    psnUsers.addTrophieTo(username, trophyGame, trophyName, type);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(addTrophyButton);

        JButton searchButton = new JButton("Search User");
        searchButton.setBounds(10, 190, 130, 25);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String info = "";
                try {
                    info = psnUsers.playerInfo(username);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                infoArea.setText(info);
            }
        });
        
	panel.add(searchButton);

	infoArea = new JTextArea();
	infoArea.setBounds(10,220 ,460 ,230 );
	infoArea.setEditable(false);
	panel.add(infoArea);	
    }
}
