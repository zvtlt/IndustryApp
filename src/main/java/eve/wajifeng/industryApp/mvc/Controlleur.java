package eve.wajifeng.industryApp.mvc;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Controlleur extends JPanel {

    private final Model model;

    private final JTextField donnee;
    private final JButton search;
    private final JComboBox comboBox;
    private final JComboBox comboBoxStation;
    private final JSpinner spinnerTE;
    private final JSpinner spinnerME;
    private final JSpinner subSpinnerTE;
    private final JSpinner subSpinnerME;

    public Controlleur(Model model) {
        super();
        this.model = model;

        donnee = new JTextField();
        search = new JButton("Search");
        JButton update = new JButton("Update Prices");
        JTextArea info = new JTextArea("For later offline uses");


        comboBox = new JComboBox(model.getComboboxList().toArray());
        JCheckBox checkSub = new JCheckBox("Display Sub-Components ");
        checkSub.setFont(new Font("Arial", Font.BOLD, 16));
        checkSub.setForeground(Color.white);
        checkSub.setBackground(new Color(43, 43, 43));


        JTextArea spinnerModelTEBefore = new JTextArea("Blueprint TE : -");
        SpinnerModel spinnerModelTE = new SpinnerNumberModel(0, 0, 20, 2);
        JTextArea spinnerModelTEAfter = new JTextArea("%");
        spinnerTE = new JSpinner(spinnerModelTE);
        JTextArea spinnerModelMEBefore = new JTextArea("ME : -");
        SpinnerModel spinnerModelME = new SpinnerNumberModel(0, 0, 10, 1);
        JTextArea spinnerModelMEAfter = new JTextArea("%");
        spinnerME = new JSpinner(spinnerModelME);
        JTextArea subSpinnerModelTEBefore = new JTextArea("Sub Blueprint(s) TE : -");
        SpinnerModel subSpinnerModelTE = new SpinnerNumberModel(0, 0, 20, 2);
        JTextArea subSpinnerModelTEAfter = new JTextArea("%");
        subSpinnerTE = new JSpinner(subSpinnerModelTE);
        JTextArea subSpinnerModelMEBefore = new JTextArea("ME : -");
        SpinnerModel subSpinnerModelME = new SpinnerNumberModel(0, 0, 10, 1);
        JTextArea subSpinnerModelMEAfter = new JTextArea("%");
        subSpinnerME = new JSpinner(subSpinnerModelME);

        comboBoxStation = new JComboBox(model.getStationHashMap().keySet().toArray(new String[0]));
        JTextArea stationText = new JTextArea("Select the system where you want prices to be updated from");

        setLayout(new GridLayout(3,2));


        donnee.setPreferredSize(new Dimension(140,30));
        info.setFont(new Font("Arial", Font.BOLD, 16));
        info.setForeground(Color.white);
        info.setBackground(new Color(43, 43, 43));


        JPanel firstRowLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));
        firstRowLeft.add(donnee);
        firstRowLeft.add(search);
        firstRowLeft.add(update);
        firstRowLeft.add(info);
        firstRowLeft.setBackground(new Color(43, 43, 43));

        JPanel firstRowRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,10));
        spinnerModelTEBefore.setFont(new Font("Arial", Font.BOLD, 16));
        spinnerModelTEBefore.setForeground(Color.white);
        spinnerModelTEBefore.setBackground(new Color(43, 43, 43));
        spinnerTE.setFont(new Font("Arial", Font.BOLD, 16));
        spinnerTE.setForeground(Color.white);
        spinnerTE.setBackground(new Color(43, 43, 43));
        spinnerModelTEAfter.setFont(new Font("Arial", Font.BOLD, 16));
        spinnerModelTEAfter.setForeground(Color.white);
        spinnerModelTEAfter.setBackground(new Color(43, 43, 43));
        spinnerModelMEBefore.setFont(new Font("Arial", Font.BOLD, 16));
        spinnerModelMEBefore.setForeground(Color.white);
        spinnerModelMEBefore.setBackground(new Color(43, 43, 43));
        spinnerME.setFont(new Font("Arial", Font.BOLD, 16));
        spinnerME.setForeground(Color.white);
        spinnerME.setBackground(new Color(43, 43, 43));
        spinnerModelMEAfter.setFont(new Font("Arial", Font.BOLD, 16));
        spinnerModelMEAfter.setForeground(Color.white);
        spinnerModelMEAfter.setBackground(new Color(43, 43, 43));
        checkSub.setBackground(new Color(43, 43, 43));
        checkSub.setForeground(new Color(255, 255, 255));
        checkSub.setFocusable(false);
        firstRowRight.add(checkSub);
        firstRowRight.add(spinnerModelTEBefore);
        firstRowRight.add(spinnerTE);
        firstRowRight.add(spinnerModelTEAfter);
        firstRowRight.add(spinnerModelMEBefore);
        firstRowRight.add(spinnerME);
        firstRowRight.add(spinnerModelMEAfter);
        firstRowRight.setBackground(new Color(43, 43, 43));

        JPanel secondRowLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));
        secondRowLeft.add(comboBox);
        secondRowLeft.setBackground(new Color(43, 43, 43));

        JPanel secondRowRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,10));
        subSpinnerModelTEBefore.setFont(new Font("Arial", Font.BOLD, 16));
        subSpinnerModelTEBefore.setForeground(Color.white);
        subSpinnerModelTEBefore.setBackground(new Color(43, 43, 43));
        subSpinnerTE.setFont(new Font("Arial", Font.BOLD, 16));
        subSpinnerTE.setForeground(Color.white);
        subSpinnerTE.setBackground(new Color(43, 43, 43));
        subSpinnerModelTEAfter.setFont(new Font("Arial", Font.BOLD, 16));
        subSpinnerModelTEAfter.setForeground(Color.white);
        subSpinnerModelTEAfter.setBackground(new Color(43, 43, 43));
        subSpinnerModelMEBefore.setFont(new Font("Arial", Font.BOLD, 16));
        subSpinnerModelMEBefore.setForeground(Color.white);
        subSpinnerModelMEBefore.setBackground(new Color(43, 43, 43));
        subSpinnerME.setFont(new Font("Arial", Font.BOLD, 16));
        subSpinnerME.setForeground(Color.white);
        subSpinnerME.setBackground(new Color(43, 43, 43));
        subSpinnerModelMEAfter.setFont(new Font("Arial", Font.BOLD, 16));
        subSpinnerModelMEAfter.setForeground(Color.white);
        subSpinnerModelMEAfter.setBackground(new Color(43, 43, 43));
        secondRowRight.add(subSpinnerModelTEBefore);
        secondRowRight.add(subSpinnerTE);
        secondRowRight.add(subSpinnerModelTEAfter);
        secondRowRight.add(subSpinnerModelMEBefore);
        secondRowRight.add(subSpinnerME);
        secondRowRight.add(subSpinnerModelMEAfter);
        secondRowRight.setBackground(new Color(43, 43, 43));

        JPanel thirdRowLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));
        comboBoxStation.setSelectedIndex(3);
        stationText.setFont(new Font("Arial", Font.BOLD, 16));
        stationText.setForeground(Color.white);
        stationText.setBackground(new Color(43, 43, 43));
        thirdRowLeft.add(comboBoxStation);
        thirdRowLeft.add(stationText);
        thirdRowLeft.setBackground(new Color(43, 43, 43));

        JPanel thirdRowRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,10));
        thirdRowRight.setBackground(new Color(43, 43, 43));

        add(firstRowLeft);
        add(firstRowRight);
        add(secondRowLeft);
        add(secondRowRight);
        add(thirdRowLeft);
        add(thirdRowRight);


        search.setEnabled(false);


        donnee.getDocument().addDocumentListener(new donneeDocumentListener());

        donnee.addKeyListener(new donneeKeyListener());

        search.addActionListener(new searchActionListener());

        update.addActionListener(new updateActionListener());

        comboBox.addActionListener(new comboboxActionListener());

        checkSub.addActionListener(new checkSubActionListener());

        spinnerTE.addChangeListener(new spinnerTeChangeListener());

        spinnerME.addChangeListener(new spinnerMeChangeListener());

        subSpinnerTE.addChangeListener(new subSpinnerTeChangeListener());

        subSpinnerME.addChangeListener(new subSpinnerMeChangeListener());

        comboBoxStation.addActionListener(new comboboxStationActionListener());
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
            try {
                model.updateJsonEM();
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

    private class spinnerTeChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            model.setTe((int)spinnerTE.getValue());

            if(model.isSub()) {
                try {
                    model.bpFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            else {
                try {
                    model.bpSubFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private class spinnerMeChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            model.setMe((int)spinnerME.getValue());

            if(!model.isSub()) {
                try {
                    model.bpFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            else {
                try {
                    model.bpSubFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private class subSpinnerTeChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            model.setSubME((int)subSpinnerME.getValue());

            if(!model.isSub()) {
                try {
                    model.bpFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            else {
                try {
                    model.bpSubFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private class subSpinnerMeChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            model.setSubME((int)subSpinnerME.getValue());

            if(!model.isSub()) {
                try {
                    model.bpFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            else {
                try {
                    model.bpSubFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private class comboboxStationActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            model.setStation(model.getStationHashMap().get(comboBoxStation.getSelectedItem()));

            if(!model.isSub()) {
                try {
                    model.bpFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            else {
                try {
                    model.bpSubFinalFormated((String)comboBox.getSelectedItem());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

}


