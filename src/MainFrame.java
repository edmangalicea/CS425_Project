import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MainFrame(){
        setContentPane(mainPanel);
        setTitle("Customer");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tfName.getText();
                String customerID = tfCustomerID.getText();
                String email = tfEmail.getText();
                String phoneNumber = tfPhone.getText();
                String address = tfAddress.getText();
                String shoppingID = tfShoppingID.getText();
                String rewards = tfRewards.getText();
                lbPrint.setText(name + " " + customerID + " " + email + " " + phoneNumber + " " + address + " " + shoppingID + " " + rewards );
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText(" ");
                tfCustomerID.setText(" ");
                tfEmail.setText(" ");
                tfPhone.setText(" ");
                tfAddress.setText(" ");
                tfShoppingID.setText(" ");
                tfRewards.setText(" ");
            }
        });
    }
    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
    }

}
