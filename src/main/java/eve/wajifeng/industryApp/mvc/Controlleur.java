package eve.wajifeng.industryApp.mvc;


import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Controlleur extends JPanel {

    private final Model model;

    private JTextField donnee;
    private JButton search;
    private JButton update;
    private JComboBox comboBox;
    private JCheckBox checkSub;


    public Controlleur(Model model) {
        super();
        this.model = model;

        donnee = new JTextField();
        search = new JButton("Search");
        update = new JButton("Update Prices");
        comboBox = new JComboBox(model.comboboxList.toArray());
        checkSub = new JCheckBox("Display Sub-Components ");

        setLayout(new GridLayout(2,2));

        donnee.setPreferredSize(new Dimension(140,30));

        JPanel firstRowLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));
        firstRowLeft.add(donnee);
        firstRowLeft.add(search);
        firstRowLeft.add(update);
        firstRowLeft.setBackground(new Color(43, 43, 43));

        JPanel firstRowRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,10));
        checkSub.setBackground(new Color(43, 43, 43));
        checkSub.setForeground(new Color(255, 255, 255));
        checkSub.setFocusable(false);
        firstRowRight.add(checkSub);
        firstRowRight.setBackground(new Color(43, 43, 43));

        JPanel secondRowLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));
        secondRowLeft.add(comboBox);
        secondRowLeft.setBackground(new Color(43, 43, 43));

        JPanel secondRowRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,10));
        secondRowRight.setBackground(new Color(43, 43, 43));

        add(firstRowLeft);
        add(firstRowRight);
        add(secondRowLeft);
        add(secondRowRight);


        search.setEnabled(false);


        donnee.getDocument().addDocumentListener(new donneeDocumentListener());

        donnee.addKeyListener(new donneeKeyListener());

        search.addActionListener(new searchActionListener());

        update.addActionListener(new updateActionListener());

        comboBox.addActionListener(new comboboxActionListener());

        checkSub.addActionListener(new checkSubActionListener());

    }

    private class donneeDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            warn();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            warn();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            warn();
        }

        public void warn() {
            search.setEnabled(donnee.getText().length() != 0);
        }
    }

    private class donneeKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {        }

        @Override
        public void keyPressed(KeyEvent e) {        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                search.doClick();
            }
        }
    }

    private class searchActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            comboBox.removeAllItems();
            for(String s : model.typeIDsGetFilteredList(donnee.getText())){
                comboBox.addItem(s);
            }
        }
    }

    private class updateActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            model.setUpdatePrices(true);
            try {
                model.jsonPriceUpdate(model.getComponentBlueprintList());
            } catch (IOException IOe) {
                IOe.printStackTrace();
            }
        }
    }

    private class comboboxActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String selection = (String)comboBox.getSelectedItem();

            if(!model.isSub()){
                try {
                    model.bpFinalFormated(selection);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }else{
                try {
                    model.bpSubFinalFormated(selection);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private class checkSubActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String selection = (String)comboBox.getSelectedItem();

            if(model.isSub()) {
                model.setSub(false);
                try {
                    model.bpFinalFormated(selection);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            else {
                model.setSub(true);
                try {
                    model.bpSubFinalFormated(selection);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

}


