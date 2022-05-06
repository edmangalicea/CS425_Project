import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CustomerList extends JFrame {
    private JTable table1;
    private JPanel mainMenu;
    private JButton btnRefresh;

    public  CustomerList(){
        setContentPane(mainMenu);
        setTitle("Main Menu");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
     //   setContentPane(mainMenu);
     //   setTitle("Main Menu");
     //   setSize(450,300);
    //    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        try {
            //  Class.forName("com.mysql.jdbc.Driver");
            Connection   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS425", "root", "Prometheus12");
            //    Statement  insert = connection.createStatement();
            //   ResultSet result = insert.executeQuery("select * from Customer");

            String query = "SELECT * FROM Customer";

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            DefaultTableModel tableModel = new DefaultTableModel(new String[]{"CustomerID", "Name", "Email", "Address", "ShoppingID", "RewardsID"},0);
            while(result.next()){
                String customerid = result.getString("CustomerID");
                String name = result.getString("Name");
                String email = result.getString("Email");
                String address = result.getString("Address");
                String shoppingID = result.getString("ShoppingID");
                String rewardsID = result.getString("RewardsID");

                tableModel.addRow(new Object[]{customerid,name,email,address,shoppingID,rewardsID});

            }

            table1.setModel(tableModel);
            stm.close();
          //  result.close();
            connection.close();
        }catch (Exception e){
            // System.out.println(e.getMessage());
            e.printStackTrace();
        }



        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                new MainMenu();
                dispose();
              //  Connection connection;
             //   PreparedStatement insert;
             //   int count;

            }
        });
    }


}
