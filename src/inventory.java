import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.*;

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
    private JButton btnMainmenu;
    private JButton clearEntriesButton;

    public inventory(){
        setContentPane(inventoryPanel);
        setTitle("Main Menu");
        setSize(1900,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        updateTable();



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

                    insert = connection.prepareStatement("insert into Brands(BrandID)values(?)");
                    insert.setString(1,brandID);
                 //   insert.setString(2, points);
                    insert.executeUpdate();

                    insert = connection.prepareStatement("insert into Vendors(VendorID, BrandID)values(?,?)");
                    insert.setString(1,vendorID);
               //     insert.setString(2, points);
                    insert.setString(2, brandID);
                    insert.executeUpdate();



                    insert = connection.prepareStatement("insert into Stores(StoreID)values(?)");
                    insert.setString(1,storeID);
                    //     insert.setString(2, points);
                 //   insert.setString(2, brandID);
                    insert.executeUpdate();





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
                updateTable();
            }
        });
        btnMainmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                dispose();
            }
        });
        clearEntriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfProductID.setText(" ");
                tfUPC.setText(" ");
                tfProductName.setText(" ");
                tfPrice.setText(" ");
                tfQuantity.setText(" ");
                tfCategory.setText(" ");
                tfBrandID.setText(" ");
                tfVendorID.setText(" ");
                tfStoreID.setText(" ");
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
        deleteButton.addComponentListener(new ComponentAdapter() {
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent j) {
            //    DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();

                int row = table1.getSelectedRow();
                String cell = table1.getModel().getValueAt(row, 0).toString();
                String query = "DELETE FROM Product where ProductID = " + cell;
                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS425", "root", "Prometheus12");

                    PreparedStatement delete = connection.prepareStatement(query);
                    delete.execute();
                    updateTable();
                    connection.close();
                    delete.close();
                }catch(Exception e){
                    e.printStackTrace();
                }




            }
        });
    }


    private void updateTable(){
            try {
                //  Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS425", "root", "Prometheus12");
                //    Statement  insert = connection.createStatement();
                //   ResultSet result = insert.executeQuery("select * from Customer");

                String query = "SELECT * FROM Product";

                Statement stm = connection.createStatement();
                ResultSet result = stm.executeQuery(query);
                DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ProductID", "UPC", "ProductName", "Price", "Quantity", "Category", "BrandID", "VendorID", "StoreID"}, 0);
                while (result.next()) {
                    String productid = result.getString("ProductID");
                    String upc = result.getString("UPC");
                    String productname = result.getString("ProductName");
                    String price = result.getString("Price");
                    String quantity = result.getString("Quantity");
                    String category = result.getString("Category");
                    String brand = result.getString("BrandID");
                    String vendor = result.getString("VendorID");
                    String store = result.getString("StoreID");

                    tableModel.addRow(new Object[]{productid, upc, productname, price, quantity, category, brand, vendor, store});

                }

                table1.setModel(tableModel);
                connection.close();
                stm.close();

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }



}
