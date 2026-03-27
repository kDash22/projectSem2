/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package salessystem;

import salessystem.model.*;

/**
 *
 * @author Kalana Dasanayaka
 */
public class SalesSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Admin a1 = new Admin(1,"Kalana", "Dasanayaka", "Maekar2295!@#");
        System.out.println(a1);
        

        Clerk c1 = new Clerk(2,"john", "java", "Maekar2295!@#");
        System.out.println(c1);
        
        a1.overridePassword(c1,"John@2295");
        System.out.println(c1);

        Customer cus = new Customer("200433504456", "Kalana Dasanyaka", "0725091436");
        System.out.println(cus);

        Product prod1 = new Product( "Rice packet", UnitType.PIECE, 200, 10. );
        System.out.println(prod1);

        Product prod2 = new Product("Sugar ", UnitType.WEIGHT, 150, 25.5);
        System.out.println(prod2);

        SaleItem item1 = new SaleItem(prod1, 10);
        System.out.println(item1);

        SaleItem item2 = new SaleItem(prod2, 10);
        System.out.println(item2);

        Sale sale1 = new Sale(cus);
        System.out.println(sale1);

        sale1.addSaleItem(item1);
        sale1.addSaleItem(item2);

        System.out.println();
        System.out.println(sale1);


    }
    
}
