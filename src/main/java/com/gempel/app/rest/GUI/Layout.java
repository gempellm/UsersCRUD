package com.gempel.app.rest.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Layout {
    private JPanel Main;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtAge;
    private JTextField txtOccupation;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JTextField txtid;

    public static void main(String[] args) {

    }

    public void start() {
        JFrame frame = new JFrame("Layout");
        frame.setContentPane(new Layout().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Layout() {


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URL url = new URL("http://localhost:8080/save");
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json; utf-8");
                    con.setRequestProperty("Accept", "application/json");
                    con.setDoOutput(true);
                    String firstName = txtFirstName.getText();
                    String lastName = txtLastName.getText();
                    String age = txtAge.getText();
                    String occuptaion = txtOccupation.getText();
                    String jsonInputString = "{\"firstName\": \"" + firstName +
                            "\", \"lastName\": \"" + lastName + "" +
                            "\", \"age\": \"" + age +
                            "\", \"occupation\": \"" + occuptaion + "\"}";
                    try(OutputStream os = con.getOutputStream()) {
                        byte[] input = jsonInputString.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }
                    try(BufferedReader br = new BufferedReader(
                            new InputStreamReader(con.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        System.out.println(response.toString());
                    }


                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Save button pressed");
            }
        });


        updateButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "Update button pressed");
           }
       });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Delete button pressed");
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Search button pressed");
            }
        });
    }

}
