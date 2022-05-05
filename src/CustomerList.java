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
            //    ResultSetMetaData meta = result.getMetaData();
            //    count = meta.getColumnCount();

            //   DefaultTableModel deTable = (DefaultTableModel) table1.getModel();
            //   deTable.setRowCount(0);
            DefaultTableModel tableModel = new DefaultTableModel(new String[]{"CustomerID", "Name", "Email", "Address", "ShoppingID", "RewardsID"},0);
            while(result.next()){
                String customerid = result.getString("CustomerID");
                String name = result.getString("Name");
                String email = result.getString("Email");
                String address = result.getString("Address");
                String shoppingID = result.getString("ShoppingID");
                String rewardsID = result.getString("RewardsID");

                tableModel.addRow(new Object[]{customerid,name,email,address,shoppingID,rewardsID});


                        /*
                        Vector loop = new Vector();

                        for(int i = 0; i < count; count++){
                            loop.add(result.getString("CustomerID"));
                            loop.add(result.getString("Name"));
                            loop.add(result.getString("Email"));
                            loop.add(result.getString("PhoneNumber"));
                            loop.add(result.getString("Address"));
                            loop.add(result.getString("ShoppingID"));
                            loop.add(result.getString("RewardsID"));
                        }
                        deTable.addRow(loop);
                    */



               // setVisible(true);
            }
         //   String tableData[] = {name, address};

         //   tableModel.addRow(tableData);
            table1.setModel(tableModel);




            //    connection.close();
            //     insert.close();
            //  DefaultTableModel model = new DefaultTableModel(loop, columns)



        }catch (Exception e){
            // System.out.println(e.getMessage());
            e.printStackTrace();
        }



        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                new MainFrame();
                dispose();
              //  Connection connection;
             //   PreparedStatement insert;
             //   int count;

            }
        });
    }


}
