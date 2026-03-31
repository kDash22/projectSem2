package salessystem.gui.salesgui;

import salessystem.dao.SaleDAO;
import salessystem.model.Sale;

import javax.swing.*;
import java.awt.*;

//provides the JDialog for deleting a sale
public class DeleteSaleGUI extends JDialog{

    private final JTextField textField;//input field for sale id
    private final JLabel saleIDStatus;//displays errors

    //initisalise the JDialog
    //includes
    // - sale info form
    // - sale table (right)
    // - confirm button
    public DeleteSaleGUI(JFrame parent){
        super(parent, "Delete Sale",true);

        setSize(1000, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //main panel
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

        //table panel
        JPanel panel = new JPanel();
        panel.setBounds(300,20,700,250);
        parentPanel.add(panel);

        panel.removeAll();
        GetAllSalesGUI.salesTable(panel);

        JLabel saleID = new JLabel("Sale ID ");
        saleID.setBounds(10, 20, 80, 30);
        parentPanel.add(saleID);

        textField = new JTextField(25);
        textField.setBounds(135, 20, 165, 25);
        parentPanel.add(textField);

        saleIDStatus = new JLabel("");
        saleIDStatus.setBounds(10, 50, 200, 30);
        saleIDStatus.setForeground(Color.red);
        parentPanel.add(saleIDStatus);

        JButton button = new JButton(" Confirm ");
        button.setBounds(10, 140, 165, 25);

        //handles the deletion and UI refresh
        button.addActionListener(e -> {
            deleteSale();

            //table refresh
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            GetAllSalesGUI.salesTable(panel);
        });
        parentPanel.add(button);

    }

    //validates info entered
    //deletes the sale from the database using the DAO
    public void deleteSale(){

        String saleIDString = textField.getText();

        //sale id cannot be empty or blank
        if (saleIDString != null && !saleIDString.isBlank()) {

            int saleID = Integer.parseInt(saleIDString);
            saleIDStatus.setText("");

            SaleDAO sdao = new SaleDAO();
            Sale sale = sdao.getSaleBySaleID(saleID);

            //if sale exists
            if (sale != null){
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    sdao.deleteSale(saleID);
                    JOptionPane.showMessageDialog(this, " Sale deleted successfully.");

                }
                else if (result == JOptionPane.NO_OPTION) {
                }
            }else {
                JOptionPane.showMessageDialog(this, " Sale not found ! ");
                textField.setText("");
            }
        } else {
            saleIDStatus.setText(" Sale ID cannot be blank ! ");
        }
    }
}
