import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class inventory extends JFrame {
    private JPanel inventoryPanel;
    private JTextField tfProductID;
    private JTextField tfUPC;
    private JTextField tfProductName;
    private JTextField tfPrice;
    private JTextField tfQuantity;
    private JTextField tfCategory;
    private JTextField tfStoreID;
    private JTextField tfVendorID;
    private JTextField tfBrandID;
    private JTable table1;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton backButton;

    public inventory(){
        setContentPane(inventoryPanel);
        setTitle("Main Menu");
        setSize(1900,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {

                String productID = tfProductID.getText();
                String UPC = tfUPC.getText();
                String productName = tfProductName.getText();
                String price = tfPrice.getText();
                String quantity = tfQuantity.getText();
                String category = tfCategory.getText();
                String brandID = tfBrandID.getText();
                String vendorID = tfVendorID.getText();
                String storeID = tfStoreID.getText();

                //    lbPrint.setText(name + " " + customerID + " " + email + " " + phoneNumber + " " + address + " " + shoppingID + " " + rewards );
                Connection connection;
                PreparedStatement insert;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS425", "root", "Prometheus12");

                    insert = connection.prepareStatement("insert into Product(ProductID, UPC,ProductName,Price,Quantity,Category,BrandID,VendorID,StoreID)values(?,?,?,?,?,?,?,?,?)");
                    insert.setString(1, productID);
                    insert.setString(2, UPC);
                    insert.setString(3, productName);
                    insert.setString(4, price);
                    insert.setString(5, quantity);
                    insert.setString(6, category);
                    insert.setString(7, brandID);
                    insert.setString(8, vendorID);
                    insert.setString(9, storeID);

                    insert.executeUpdate();


                    //  JOptionPane.showMessageDialog(this, "Record Customer");
                    //        ResultSet resultSet = insert.executeQuery("select * from Customer");
                    //          while (resultSet.next()) {
                    //               System.out.println(resultSet.getString("Name"));
                    //             }
                    //             insert.setString(1, name);
                    connection.close();
                    insert.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
