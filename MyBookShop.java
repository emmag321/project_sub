package OOP2_Project_MyShop;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;



public class MyBookShop extends JFrame /*implements ActionListener*/ {

    JMenu fileMenu;
    MyBookShop book1;

    //array
    Customer cust1 = new Customer();
    ArrayList<Customer> customers;

    //customer
    /*String firstName, lastName, address;
    float phoneNum;*/

    //book
    String title, author,isbn;
    int numPages;
    double price;


    public static void main(String[] args) {
        MyBookShop book1 = new MyBookShop();
        book1.setVisible(true);
    }

    public MyBookShop() {
        super("Book");

        setSize(500, 500);
        setLocation(200, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //addWindowListener(new WindowEventHandler());

        Container cPane = getContentPane();
        cPane.setLayout(new FlowLayout());
        cPane.setBackground(Color.DARK_GRAY);

        //menu
        createFileMenu();
        createCustomersMenu();
        createOptionsMenu();
    }

    private void createFileMenu() {
    }

    private void createCustomersMenu() {
    }

    private void createOptionsMenu() {
    }

}