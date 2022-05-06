import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
    private JPanel mainMenu;
    private JButton customerTableButton;
    private JButton customerInsertDeleteButton;
    private JButton inventoryDatabaseButton;
    public MainMenu(){
        setContentPane(mainMenu);
        setTitle("Customer");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        customerTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerList();
                dispose();
            }
        });
        customerInsertDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                dispose();
            }
        });
        inventoryDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new inventory();
                dispose();
            }
        });
    }
}
