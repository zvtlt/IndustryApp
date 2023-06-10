package eve.wajifeng.industryApp.mvc;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Vue extends JPanel implements Observer {

    private final JLabel etatModel;
    private final Model model;
    private JPanel gui;
    private JTable table;
    private JTable table2;
    private JTable table3;
    private JScrollPane tableScroll;
    private JScrollPane tableScroll2;

    public Vue(Model model) {
        super();
        this.model = model;
        etatModel = new JLabel();
        add(etatModel);
        setBackground(new Color(43, 43, 43));
        // inscription auprès du modèle comme observateur
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {


        try {
            remove(gui);
            repaint();
        }catch (Exception e){
        }

        gui = new JPanel(new BorderLayout(5,20));


        //////////////// JTABLE 1 //////////////////
        String[] header = {"", "Name", "Quantity", "Volume", "BasePrice", "MultPrice"};
        int len = model.getComponentBlueprintList().size();
        int count = 0;
        Object[][] data = new Object[len][6];
        List<ComponentBlueprint> componentBlueprintList = model.getComponentBlueprintList();
        Icon bpImage;
        for(ComponentBlueprint cbp : componentBlueprintList){

            try {
                URL url = Vue.class.getClassLoader().getResource(cbp.getMaterialImage());
                bpImage = new ImageIcon(url);

                data[count][0] = bpImage;
            }catch (Exception e){
                e.printStackTrace();
            }

            data[count][1] = cbp.getMaterialName();
            data[count][2] = cbp.getMaterialQuantityString();
            data[count][3] = cbp.getMaterialVolumeString();
            data[count][4] = cbp.getBasePriceString();
            data[count][5] = cbp.getMultPriceString();

            count++;
        }

        DefaultTableModel modelTable = new DefaultTableModel(data, header)
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class

            public Class getColumnClass(int column)
            {
                if (column == 0) {
                    return Icon.class;
                }
                return String.class;
            }
        };

        table = new JTable(modelTable);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        table.setRowHeight(40);
        table.getColumnModel().getColumn(0).setMaxWidth(40);

        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        try {
            // 1.6+
            table.setAutoCreateRowSorter(true);
        } catch(Exception continuewithNoSort) {
        }
        tableScroll = new JScrollPane(table);
        Dimension tablePreferred = tableScroll.getPreferredSize();
        tableScroll.setPreferredSize(
                new Dimension(tablePreferred.width*2, (int)(tablePreferred.height/1.2)) );
        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(43, 43, 43), 3));

        gui.add(tableScroll, BorderLayout.NORTH);
        validate();


        //////////////// JTABLE 2 //////////////////
        String[] header2 = {"", "", "", "", "", ""};
        Object[][] data2 = new Object[1][6];

        data2[0][2] = "Total Volume :  ";
        data2[0][3] = model.getTotal().getTotalVolumeString();
        data2[0][4] = "Total Price :  ";
        data2[0][5] = model.getTotal().getTotalPriceString();


        DefaultTableModel modelTotal = new DefaultTableModel(data2, header2);

        table2 = new JTable(modelTotal);

        DefaultTableCellRenderer rightRenderer2 = new DefaultTableCellRenderer();
        rightRenderer2.setHorizontalAlignment(JLabel.RIGHT);

        table2.setRowHeight(40);
        table2.getColumnModel().getColumn(0).setMaxWidth(40);

        table2.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table2.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table2.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        table2.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        try {
            // 1.6+
            table2.setAutoCreateRowSorter(true);
        } catch(Exception continuewithNoSort) {
        }
        Dimension tablePreferred2 = table2.getPreferredSize();
        table2.setPreferredSize(
                new Dimension(tablePreferred2.width*2, (tablePreferred2.height)) );
        table2.setBorder(BorderFactory.createLineBorder(new Color(43, 43, 43), 3));

        gui.add(table2, BorderLayout.CENTER);
        validate();


        //////////////// JTABLE 3 //////////////////
        String[] header3 = {"", "Name", "Quantity", "Volume", "Base Price"};
        Object[][] data3 = new Object[1][5];

        try {
            URL url = Vue.class.getClassLoader().getResource(model.getProductedBlueprint().getProductedImage());
            bpImage = new ImageIcon(url);

            data3[0][0] = bpImage;
        }catch (Exception e){
        }

        data3[0][1] = model.getProductedBlueprint().getProductedName();
        data3[0][2] = model.getProductedBlueprint().getProductedQuantityString();
        data3[0][3] = model.getProductedBlueprint().getProductedVolumeString();
        data3[0][4] = model.getProductedBlueprint().getProductedPriceString();


        DefaultTableModel modelProductedItem = new DefaultTableModel(data3, header3)
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class

            public Class getColumnClass(int column)
            {
                if (column == 0) {
                    return Icon.class;
                }
                return String.class;
            }
        };

        table3 = new JTable(modelProductedItem);

        DefaultTableCellRenderer rightRenderer3 = new DefaultTableCellRenderer();
        rightRenderer3.setHorizontalAlignment(JLabel.RIGHT);

        table3.setRowHeight(40);
        table3.getColumnModel().getColumn(0).setMaxWidth(40);

        table3.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table3.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table3.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        try {
            // 1.6+
            table3.setAutoCreateRowSorter(true);
        } catch(Exception continuewithNoSort) {
        }
        tableScroll2 = new JScrollPane(table3);
        Dimension tablePreferred3 = tableScroll2.getPreferredSize();
        tableScroll2.setPreferredSize(
                new Dimension(tablePreferred3.width*2, (int)(tablePreferred3.height/6.1)) );
        tableScroll2.setBorder(BorderFactory.createLineBorder(new Color(43, 43, 43), 3));

        gui.add(tableScroll2, BorderLayout.SOUTH);
        gui.setBackground(new Color(43, 43, 43));
        validate();

        add(gui, BorderLayout.CENTER);

        validate();

    }

}
