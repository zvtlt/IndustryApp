package eve.wajifeng.industryApp;

import eve.wajifeng.industryApp.mvc.Controlleur;
import eve.wajifeng.industryApp.mvc.Model;
import eve.wajifeng.industryApp.mvc.Vue;

import javax.swing.*;
import java.awt.*;

public class IHMIndustryApp extends JFrame {

    protected IHMIndustryApp(){
        super("IndustryApp");
        Model model = Model.getInstance();
        Controlleur control = new Controlleur(model);
        Vue vue = new Vue(model);

        final JPanel gui = new JPanel(new BorderLayout(5,5));
        gui.add(control, BorderLayout.NORTH);
        gui.add(vue, BorderLayout.CENTER);
        setContentPane(gui);

        setLocation(200, 20);
        setSize(1200, 750);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        pack();
    }

}
