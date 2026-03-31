package salessystem.gui.salesgui;

import salessystem.dao.SaleDAO;
import salessystem.model.Sale;

import javax.swing.*;
import java.awt.*;

public class DeleteSaleGUI extends JDialog{

    private final JTextField textField;
    private final JLabel saleIDStatus;

    public DeleteSaleGUI(JFrame parent){
        super(parent, "Delete Sale",true);

        setSize(1000, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        add(parentPanel);

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
        button.addActionListener(e -> {
            deleteSale();

            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            GetAllSalesGUI.salesTable(panel);
        });
        parentPanel.add(button);
}
    public void deleteSale(){

        String saleIDString = textField.getText();

        if (saleIDString != null && !saleIDString.isBlank()) {

            int saleID = Integer.parseInt(saleIDString);
            saleIDStatus.setText("");

            SaleDAO sdao = new SaleDAO();
            Sale sale = sdao.getSaleBySaleID(saleID);

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
