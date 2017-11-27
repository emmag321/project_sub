//Emma_Griffin_OOP2_Project --  this is just a test to display customer details here
package OOP2_Project_MyShop;

import javax.swing.*;

public class TestMyBookShop {
    public static void main(String[] args)
    {
        //creating customer object
        Employee cust1 = new Employee();

        cust1.setFirstName("Emma");
        cust1.setLastName("Griffin");
        cust1.setAddress("Causeway");
        cust1.setPassword(150514);
        cust1.setPhoneNum("99798809");
        cust1.setAccNum("12334F");
        cust1.setEmail("emma@gmail.com");

        //creating book object
        Book book1 = new Book();

        book1.setTitle("harry potter");
        book1.setAuthor("jk rolling");
        book1.setNumPages(299);
        book1.setPrice(7.99);
        book1.setIsbn("12345IE");

        JOptionPane.showMessageDialog(null, cust1.toString(), "Shop Test", JOptionPane.PLAIN_MESSAGE);
    }

}
