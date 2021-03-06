package OOP2_Project_MyShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class MainGUI extends JFrame {

    JLabel logoLabel;
    Employee employee = new Employee();

    public static ArrayList<Person> employees = new ArrayList<>();
    public static ArrayList<Book> books = new ArrayList<>();


    //main method
    public static void main(String[] args) {
        MainGUI gui = new MainGUI();
        gui.setVisible(true);
    }

    public MainGUI() {
        setTitle("Main GUI");
        setSize(600, 550);
        setResizable(true);
        setLocation(500, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container cpane = getContentPane();
        cpane.setLayout(new FlowLayout());

        //image
        logoLabel = new JLabel(new ImageIcon("book.png"));//https://www.google.es/search?biw=1366&bih=656&tbm=isch&sa=1&ei=qsgWWouPMsTAgAbz5Z2IBg&q=book+shop+logo&oq=book+shop+logo&gs_l=psy-ab.3..0l2.24526.24763.0.25467.3.3.0.0.0.0.111.323.0j3.3.0....0...1c.1.64.psy-ab..0.2.217....0.lgX832TuvBY#imgrc=BrF0Ryz5D9pQ1M:
        cpane.add(logoLabel);

        //creates buttons
        JButton adminButton = new JButton("Admin");
        JButton customerButton = new JButton("Customer");
        JButton quitButton = new JButton("Quit");

        //set bounds for JButtons & Image Jlabel
        adminButton.setBounds(50, 20, 100, 25);
        customerButton.setBounds(50, 50, 100, 25);
        quitButton.setBounds(50, 80, 100, 25);
        logoLabel.setBounds(150, 180, 300, 225);

        cpane.setBounds(0, 0, 880, 200);

        cpane.setLayout(null);

        //adds buttons to window
        cpane.add(adminButton);
        cpane.add(customerButton);
        cpane.add(quitButton);

        //makes admin button open up login windows for username and pass - the displays AdminGUI
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // loginGUI login = new loginGUI();
                // login.setVisible(true);
                //MainGUI.setVisable(false);
                if (e.getActionCommand().equals("Admin")) {
                    String adminUserName = "e";
                    int password = 1;
                    String message;

                    employee.setEmail(JOptionPane.showInputDialog(null, "Enter user name:"));
                    employee.setPassword(Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Pin:")));

                    if (employee.getEmail().equals(adminUserName) && employee.getPassword() == password) {
                        JOptionPane.showMessageDialog(null, "Welcome to the System " + adminUserName, "Welcome!",
                                JOptionPane.INFORMATION_MESSAGE);


                        {
                            AdminGUI admin = new AdminGUI(MainGUI.this);

                            admin.setVisible(true);
                            //added this here to get MainGUI to close when AdminGUI button was clicked
                            MainGUI.this.dispose();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Error! Your email or pin was incorrect \n HINT UserName: e \nPass:: 1", "Warning!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        //makes button do something here
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerGUI();
                //added this change also so the MainGUI would close when the custommer button was clicked
                MainGUI.this.dispose();

            }
        });

        //closes system when quit is clicked.. shows warning message before hand
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmMessage = JOptionPane.showConfirmDialog(null, "Are you sure you want to Quit?",
                        "Closing", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirmMessage == 0) {
                    System.exit(0);
                }
            }
        });

        // prevent the default window closing operation
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // override the default window closing event
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {

                // modified from this source: https://www.tutorialspoint.com/java/java_serialization.htm
                if (employees.size() > 0) {
                    try {
                        FileOutputStream fileOut = new FileOutputStream("employees.dat");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(employees);
                        out.close();
                        fileOut.close();
                        System.out.printf("Serialized data to employees.dat file");
                    } catch (IOException i) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to save employees",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        i.printStackTrace();
                    }
                }

                MainGUI.this.dispose();

            }
        });
    }
}