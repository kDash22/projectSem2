package salessystem.testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import salessystem.model.Admin;

public class TestNewAdmin implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        Admin a1 = new Admin("Kalana", "Dasanayaka", "Maekar2295!@#");
        System.out.println(a1);
    }

}
