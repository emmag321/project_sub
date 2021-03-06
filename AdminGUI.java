/*****************************************************
 *    Title: Bicycle.java
 *    Author: John Walsh
 *    Site owner/sponsor: https://mydocs.ittralee.ie
 *    Date: 2017
 *    Code version: edited Nov 13 2017 10.04AM
 *    Availability: https://mydocs.ittralee.ie/xythoswfs/webview/fileManager.action?x=y&XY_performTicketSessionBypass=true&cookieConsentSet=on&shareLogin=false&stk=00938CC0736D2E9 (Accessed 22/11/2017)
 *    Modified: changed value to do with bike to book for my project
 *****************************************************/

/*****************************************************
 *    Title:  JTableRow.java
 *    Author: unknown- doesn't say
 *    Site owner/sponsor: 1bestcsharp.blogspot.ie
 *    Date: unknown- doesn't say
 *    Code version: edited 26/11/17
 *    Availability: http://1bestcsharp.blogspot.ie/2015/05/java-jtable-add-delete-update-row.html(Accessed 27/11/2017)
 *    Modified: changed to suit my project and to be displayed with in this GUI
 *****************************************************/

package OOP2_Project_MyShop;


import jdk.nashorn.internal.scripts.JO;
import sun.applet.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class AdminGUI extends JFrame implements ActionListener {

    //creating JTextField, JMenu, JButton, JTextArea here - they are called below then
    JTextField textTitle, textAuthor, textPages, textPrice, textIsbn, textNumInStock;
    JMenu optionsMenu, adminMenu;
    JButton backButton, bookButton;
    JTextArea display;

    //making this class the parent class
    private JFrame parent;

    public static ArrayList<Person> employees = MainGUI.employees;
    public static ArrayList<Book> books = MainGUI.books;


    public AdminGUI(JFrame parent) { // constructor starts here

        DefaultListModel listCust = new DefaultListModel();
        JList list = new JList(listCust);

        //makes this class the parent - feeds threw info
        this.parent = parent;

        Container cPane;

        //setting default values for GUI
        setTitle("My Book Shop ADMIN SECTION");
        setSize(900, 550);
        setResizable(true);
        setLocation(200, 100);

        //this is what the GUI does when you press the 'x' button
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                if (books.size() > 0) {

                    try {
                        //got from a youtube tutorial https://www.youtube.com/watch?v=V-sgbrg5jW4
                        BufferedWriter writer = new BufferedWriter(new FileWriter("myBooks.txt", true));
                        display.write(writer);
                        writer.close();

                        System.out.printf("Serialized data to myBooks.txt file");
                    }

                    catch (IOException i) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to save books",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        i.printStackTrace();
                    }
                }

                AdminGUI.this.dispose();
                System.exit(0);
            }
        });

        cPane = getContentPane();
        cPane.setLayout(new FlowLayout());
        cPane.setBackground(new Color(240,210,240));

        createOptionsMenu();
        createAdminMenu();
        //createCustomerMenu();

        //menu bar for customer section
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.green);
        menuBar.add(optionsMenu);
        menuBar.add(adminMenu);

        //display added employees
        display = new JTextArea();
        cPane.add(display);

        bookButton = new JButton("Book");
        cPane.add(bookButton);

        backButton = new JButton("BACK");
        cPane.add(backButton);

        //the back button - bring u to MainGUI
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        //referenced code starts here
        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Title","Author","Pages","Price","ISBN", "Num in Stock"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        loadSavedData();
        // load the books got from stackOverFlow https://stackoverflow.com/questions/16265693/how-to-use-buffered-reader-in-java
        BufferedReader reader = null;
        try {
            File file = new File("myBooks.txt");
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String [] data = line.split("-");
                model.addRow(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // display and saved books
        for (Book book : books) {
            Object[] row = new Object[6];

            //Update the display textArea for saving
            row[0] = textTitle.getText();
            display.append(textTitle.getText() + "-");
            row[1] = textAuthor.getText();
            display.append(textAuthor.getText() + "-");
            row[2] = textPages.getText();
            display.append(textPages.getText() + "-");
            row[3] = textPrice.getText();
            display.append(textPrice.getText() + "-");
            row[4] = textIsbn.getText();
            display.append(textIsbn.getText() + "-");
            row[5] = textNumInStock.getText();
            display.append(textNumInStock.getText() + "-");

            // add row to the model
            model.addRow(row);
        }

        //http://1bestcsharp.blogspot.ie/2015/05/java-jtable-add-delete-update-row.html
        // create JFrame and JTable
        JTable table = new JTable();

        // create JTextFields
        //JTextField textId = new JTextField();
        textTitle = new JTextField();
        textAuthor = new JTextField();
        textPages = new JTextField();
        textPrice = new JTextField();
        textIsbn = new JTextField();
        textNumInStock = new JTextField();

        //creating JLabels here
        JLabel titleLabel = new JLabel("Title :");
        JLabel authorLabel = new JLabel("Author :");
        JLabel pagesLabel = new JLabel("Pages :");
        JLabel priceLabel = new JLabel("Price :");
        JLabel isbnLabel = new JLabel("ISBN :");
        JLabel numInStock = new JLabel("Stock Qty :");

        // set the model to the table
        table.setModel(model);

        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        JButton btnPurchaseBook = new JButton("Purchase");

        //setting bounds of my JTextFields here
        textTitle.setBounds(70, 220, 100, 25);
        textAuthor.setBounds(70, 250, 100, 25);
        textPages.setBounds(70, 280, 100, 25);
        textPrice.setBounds(70, 310, 100, 25);
        textIsbn.setBounds(70, 340, 100, 25);
        textNumInStock.setBounds(70, 370, 100, 25);

        //setting bounds of my JLabels here
        titleLabel.setBounds(20, 220, 100, 25);
        authorLabel.setBounds(20, 250, 100, 25);
        pagesLabel.setBounds(20, 280, 100, 25);
        priceLabel.setBounds(20, 310, 100, 25);
        isbnLabel.setBounds(20, 340, 100, 25);
        numInStock.setBounds(13, 370, 100, 25);

        //add labels to GUI here to make visable
        cPane.add(titleLabel);
        cPane.add(authorLabel);
        cPane.add(pagesLabel);
        cPane.add(priceLabel);
        cPane.add(isbnLabel);
        cPane.add(numInStock);

        //setting bounds for my buttons here
        btnAdd.setBounds(180, 220, 100, 25);
        btnUpdate.setBounds(180, 265, 100, 25);
        btnDelete.setBounds(180, 310, 100, 25);
        btnPurchaseBook.setBounds(180, 355, 100, 25);

        // create JScrollPane - this allows you to be able to scroll with in data grid
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        cPane.setLayout(null);

        //create window pane to jframe here
        cPane.add(pane);

        // add JTextFields to the window
        cPane.add(textTitle);
        cPane.add(textAuthor);
        cPane.add(textPages);
        cPane.add(textPrice);
        cPane.add(textIsbn);
        cPane.add(textNumInStock);

        //add JButtons to the jframe
        cPane.add(btnAdd);
        cPane.add(btnDelete);
        cPane.add(btnUpdate);
        cPane.add(btnPurchaseBook);

        //sets the size of window
        cPane.setSize(900,400);

        // action listener for button add row
        btnAdd.addActionListener(new ActionListener(){

            // create an array of objects to set the row data
            Object[] row = new Object[6];
            @Override
            public void actionPerformed(ActionEvent e) {

                //Update of display textArea
                row[0] = textTitle.getText();
                display.append(textTitle.getText() + "-");
                row[1] = textAuthor.getText();
                display.append(textAuthor.getText() + "-");
                row[2] = textPages.getText();
                display.append(textPages.getText() + "-");
                row[3] = textPrice.getText();
                display.append(textPrice.getText() + "-");
                row[4] = textIsbn.getText();
                display.append(textIsbn.getText() + "-");
                row[5] = textNumInStock.getText();
                display.append(textNumInStock.getText() + "-");

                // add row to the model
                model.addRow(row);

                addBook();

            }
        });

        // action listener - button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);

                    try {
                        //got from a youtube tutorial https://www.youtube.com/watch?v=V-sgbrg5jW4
                        //adding a blank line to delete
                        BufferedWriter writer = new BufferedWriter(new FileWriter("myBooks.txt",true));
                        display.write(writer);
                        writer.close();

                        System.out.printf("Serialized data to myBooks.txt file");
                    }

                    catch (IOException o) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to save books",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        o.printStackTrace();
                    }
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });

        btnPurchaseBook.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

               /* int i = table.getSelectedRow();
                //trying to create process here when you click the purchase button the stock drops by one

                    model.getValueAt(i, 5);*/

            /*    int i = table.getSelectedRow();
                textNumInStock.setText(model.getValueAt(i, 5).toString());*/


                }

                 /*if (books.size() > 0) {
                try {
                    FileOutputStream fileOut = new FileOutputStream("myBooks.txt");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(books);
                    out.close();
                    fileOut.close();
                    System.out.printf("Serialized data to myBooks.txt file");
                } catch (IOException i) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Failed to save books",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    i.printStackTrace();
                }
            }*/
    });



        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e){

                // i = the index of the selected row
                int i = table.getSelectedRow();

                textTitle.setText(model.getValueAt(i, 0).toString());
                textAuthor.setText(model.getValueAt(i, 1).toString());
                textPages.setText(model.getValueAt(i, 2).toString());
                textPrice.setText(model.getValueAt(i, 3).toString());
                textIsbn.setText(model.getValueAt(i, 4).toString());
                textNumInStock.setText(model.getValueAt(i, 5).toString());
            }
        });

        // button update row
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if(i >= 0)
                {
                    model.setValueAt(textTitle.getText(), i, 0);
                    model.setValueAt(textAuthor.getText(), i, 1);
                    model.setValueAt(textPages.getText(), i, 2);
                    model.setValueAt(textPrice.getText(), i, 3);
                    model.setValueAt(textIsbn.getText(), i, 4);
                    model.setValueAt(textNumInStock.getText(), i, 5);

                    try {
                        //got from a youtube tutorial https://www.youtube.com/watch?v=V-sgbrg5jW4
                        //update the Display textArea with the new data for saving
                        BufferedWriter writer = new BufferedWriter(new FileWriter("myBooks.txt", true));
                        display.append(textTitle.getText() + "-");
                        display.append(textAuthor.getText() + "-");
                        display.append(textPages.getText() + "-");
                        display.append(textPrice.getText() + "-");
                        display.append(textIsbn.getText() + "-");
                        display.append(textNumInStock.getText() + "-");
                        display.write(writer);
                        writer.close();

                        System.out.printf("Serialized data to myBooks.txt file");
                    }

                    catch (IOException a) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to save books",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        a.printStackTrace();
                    }
                }
                else{
                    System.out.println("Update Error");
                }
            }
        });
        //referenced code ends here
    } // constructor ends here

    //add book method here
    private void addBook() {
        //my attributes here
        String title;
        String author;
        int numPages;
        float price;
        String isbn;
        int numStock;

        //this basically reads in the text that was inserted into these fields
        title = textTitle.getText();
        author = textAuthor.getText();
        numPages = (Integer.parseInt(textPages.getText()));
        price = (Float.parseFloat(textPrice.getText()));
        isbn = textIsbn.getText();
        numStock = (Integer.parseInt(textNumInStock.getText()));

        Book book = new Book(title, author, numPages, price, isbn, numStock);

        books.add(book);
    }

    //open method
    public void open() {
        try {
            ObjectInputStream is;
            is = new ObjectInputStream(new FileInputStream("employees.dat"));
            employees = (ArrayList<Person>) is.readObject();
            is.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "open didn't work");
            e.printStackTrace();
        }

    }//here


    //adds employees to system
    public void addEmployee(){
        String firstName = JOptionPane.showInputDialog("Enter first name: ");
        String lastName = JOptionPane.showInputDialog("Enter surname: ");
        String address = JOptionPane.showInputDialog("Enter address: ");
        String phoneNum = JOptionPane.showInputDialog("Enter phone number: ");
        String email = JOptionPane.showInputDialog("enter email:");
        String userName = JOptionPane.showInputDialog("enter user name:");
        int password = (Integer.parseInt(JOptionPane.showInputDialog("enter password(must be digits):")));
        Employee employee = new Employee(firstName,lastName,address,phoneNum, email, userName, password);
        employees.add(employee);

        JOptionPane.showMessageDialog(null,firstName + "s account has successfully created");
    }

    //displays employees that have been put into system
    public void display(){
        JTextArea area = new JTextArea();
        int numCustomers = employees.size();
        if (numCustomers>0) {
            area.setText("Employees: \n\n");
            for (int i = 0; i<numCustomers; i++)
                area.append("User no: " + i + " " + employees.get(i).toString()+"\n");
            showMessage(area);
        }
        else
            showMessage("No Users in the system");
    }

    //makes make button go back to MainGUI
    public void backButton(){
        if (books.size() > 0) {
            try {
                FileOutputStream fileOut = new FileOutputStream("books.dat");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(books);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data to books.dat file");
            } catch (IOException i) {
                JOptionPane.showMessageDialog(
                        null,
                        "Failed to save books",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                i.printStackTrace();
            }
        }
        this.dispose();
        parent.setVisible(true);
    }

    //menu bar with - back,quit,save
    private void createOptionsMenu() {
        JMenuItem item;

        optionsMenu = new JMenu("Options");

        item = new JMenuItem("Back");
        item.addActionListener(this);
        optionsMenu.add(item);

        item = new JMenuItem("Quit");
        item.addActionListener(this);
        optionsMenu.add(item);

        item = new JMenuItem("Save");
        item.addActionListener(this);
        optionsMenu.add(item);
    }

    //menubar with Add Employee,Display Employee,Add Book,Display Book
    private void createAdminMenu() {
        JMenuItem item;

        adminMenu = new JMenu("Admin");

        //add employee button
        item = new JMenuItem("Add Employee");
        item.addActionListener(this);
        adminMenu.add(item);

        //lists employee button
        item = new JMenuItem("Display Employee");
        item.addActionListener(this);
        adminMenu.add(item);
    }

    //events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Quit")) {
            showMessage("Shutting down the system");
            System.exit(0);
        }
        else if (e.getActionCommand().equals ("Add Employee")){
            addEmployee();//adds employee here to system
        }
        else if (e.getActionCommand().equals ("Display Employee")){
            display();//displays employye
            open();
        }
        else if (e.getActionCommand().equals ("Back")){
            backButton();//back button
        }
        else
            showMessage("Did not work");
    }

    //this displays
    public void showMessage (String s){ JOptionPane.showMessageDialog(null,s); }

    public void showMessage (JTextArea s){
        JOptionPane.showMessageDialog(null,s);
    }

    private void loadSavedData() {
        // load the employees

        try {
            FileInputStream fileIn = new FileInputStream("employees.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            employees = (ArrayList<Person>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}