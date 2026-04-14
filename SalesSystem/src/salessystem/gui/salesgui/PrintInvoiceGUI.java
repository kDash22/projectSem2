package salessystem.gui.salesgui;

import salessystem.Global;
import salessystem.dao.SaleDAO;
import salessystem.model.Sale;
import salessystem.model.SaleItem;
import salessystem.model.UnitType;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

//provides the JDialog for the invoice
public class PrintInvoiceGUI extends JDialog {

    //initialise the JDialog
    // includes
    // - company name (is a global variable, can be changed)
    // - a table of sale items
    // - date and total
    public PrintInvoiceGUI(JFrame parent, int saleID){

        super(parent, " Invoice ",true);

        setSize(650, 500);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //main panel
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        add(parentPanel);

        //header that contains the company name
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(Global.companyName);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 40, 10));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel invoice = new JLabel("INVOICE");
        invoice.setFont(new Font("Arial", Font.BOLD, 22));
        invoice.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        invoice.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(invoice);

        parentPanel.add(header, BorderLayout.NORTH);

        String[] columns = {" Product ID ", " Product Name ", " Unit Price ", " Quantity " , "Subtotal"};
        DefaultTableModel model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        JTable table = new JTable(model); // table
        table.setRowHeight(22);

        //Right align preset
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(SwingConstants.RIGHT);


        //setting money columns to the right
        table.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
        table.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        table.getColumnModel().getColumn(4).setCellRenderer(rightAlign);

        //adds scroll function
        JScrollPane scrollPane = new JScrollPane(table);
        parentPanel.add(scrollPane, BorderLayout.CENTER);

        SaleDAO sdao = new SaleDAO();
        Sale sale = sdao.getSaleBySaleID(saleID);

        List<SaleItem> items = sale.getSaleItems();

        for (SaleItem item : items) {
            String unit = "";
            if (item.getProduct().getUnitType() == UnitType.WEIGHT){
                unit = "kg";
            }

            Object[] row = {

                    item.getProduct().getProductId(),
                    item.getProduct().getProductName(),
                    item.getUnitPrice(),
                    (item.getQuantity()+" "+unit),
                    item.getSubtotal()
            };
            model.addRow(row);

        }

        //footer that contains the date and the total
        JPanel footer = new JPanel(new BorderLayout());

        JLabel date = new JLabel("Date : "+Global.dateTimeFormat(sale.getDateTime()));
        date.setFont(new Font("Arial",Font.BOLD, 16));
        date.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));

        JLabel totalLabel = new JLabel("Total : Rs " + sale.getTotal());
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        footer.add(date, BorderLayout.WEST);
        footer.add(totalLabel, BorderLayout.EAST);
        parentPanel.add(footer, BorderLayout.SOUTH);

    }

    //methods which can be used to print old invoices using the sale id
    public static void printInvoiceButton(JFrame parent){

        JDialog popup = new JDialog(parent, "Re-Print Old Invoice",true);
        popup.setSize(500,300);
        popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popup.setLocationRelativeTo(parent);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(null);
        popup.add(parentPanel);

        JLabel saleID = new JLabel("Sale ID : ");
        saleID.setBounds(10,20,80,30);
        parentPanel.add(saleID);

        JTextField textField = new JTextField(25);
        textField.setBounds(135,20,165,25);
        parentPanel.add(textField);

        JLabel status = new JLabel("");
        status.setBounds(10,50,200,30);
        status.setForeground(Color.red);
        parentPanel.add(status);

        JButton button = new JButton(" Print invoice ");
        button.setBounds(10, 80, 165, 25);
        button.addActionListener(e -> {

            String saleIDString = textField.getText();
            int saleid;
            SaleDAO sdao = new SaleDAO();
            Sale sale = null;

            if (saleIDString != null && !saleIDString.isBlank()){
                saleid = Integer.parseInt(saleIDString);
                sale = sdao.getSaleBySaleID(saleid);

                if (sale != null){
                    popup.dispose();
                    new PrintInvoiceGUI(parent,saleid).setVisible(true);

                }else {
                    status.setText("Invalid Sale ID ! ");
                }
            }else {
                status.setText(" Sale ID cannot be empty ! ");

            }

        });
        parentPanel.add(button);
        popup.setVisible(true);
    }
}
