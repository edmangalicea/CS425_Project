import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MainFrame extends JFrame {
    private JTextField tfCustomerID;
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JTextField tfShoppingID;
    private JTextField tfRewards;
    private JLabel lbPrint;
    private JPanel mainPanel;
    private JButton buttonOk;
    private JButton buttonClear;
    private JButton mainMenuButton;


    public MainFrame(){
        setContentPane(mainPanel);
        setTitle("Customer");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        buttonOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent d) {
                String name = tfName.getText();
                String customerID = tfCustomerID.getText();
                String email = tfEmail.getText();
                String phoneNumber = tfPhone.getText();
                String address = tfAddress.getText();
                String shoppingID = tfShoppingID.getText();
                String rewards = tfRewards.getText();
                String points = "0";
                lbPrint.setText(name + " " + customerID + " " + email + " " + phoneNumber + " " + address + " " + shoppingID + " " + rewards );
                Connection connection;
                PreparedStatement insert;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS425", "root", "Prometheus12");

                    insert = connection.prepareStatement("insert into Rewards(RewardsID, Points)values(?,?)");
                    insert.setString(1,rewards);
                    insert.setString(2, points);
                    insert.executeUpdate();



                    insert = connection.prepareStatement("insert into Customer(CustomerID,Name,Email,PhoneNumber,Address,ShoppingID,RewardsID)values(?,?,?,?,?,?,?)");
                    insert.setString(1,customerID);
                    insert.setString(2,name);
                    insert.setString(3, email);
                    insert.setString(4, phoneNumber);
                    insert.setString(5, address);
                    insert.setString(6, shoppingID);
                    insert.setString(7, rewards);
                    insert.executeUpdate();

                  //  JOptionPane.showMessageDialog(this, "Record Customer");
            //        ResultSet resultSet = insert.executeQuery("select * from Customer");
          //          while (resultSet.next()) {
         //               System.out.println(resultSet.getString("Name"));
       //             }
       //             insert.setString(1, name);
                    connection.close();
                    insert.close();
                }catch (Exception e){
                    e.printStackTrace();
                }



            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                tfName.setText(" ");
                tfCustomerID.setText(" ");
                tfEmail.setText(" ");
                tfPhone.setText(" ");
                tfAddress.setText(" ");
                tfShoppingID.setText(" ");
                tfRewards.setText(" ");
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new MainMenu();
                dispose();



            }
        });
    }



}
