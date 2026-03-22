/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package salessystem;

/**
 *
 * @author Kalana Dasanayaka
 */
public class SalesSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Admin a1 = new Admin("Kalana", "Dasanayaka", "Maekar2295!@#");
        System.out.println(a1);
        

        Clerk c1 = new Clerk("john", "java", "Maekar2295!@#");
        System.out.println(c1);
        
        a1.overridePassword(c1,"John@2295");
        System.out.println(c1);
        
        
        
    }
    
}
