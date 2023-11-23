package ar.com.jg.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;


public class MenuForm extends JPanel {

    private JTextField optionField;

    public MenuForm(String menu, int longLabel, int gap){

        setLayout(new MigLayout("wrap,fillx,insets 5 10 5 10", "[fill,100]"));

        JLabel menuLabel = new JLabel(menu);
        JLabel optionLabel = new JLabel("Seleccione una Opción:");
        optionField = new JTextField(20);

        optionField.setHorizontalAlignment(SwingConstants.RIGHT);

        add(menuLabel,"width " + longLabel + ":" + longLabel + ":" + longLabel + ", growx");
        add(optionLabel,"split 2, width 140:140:140, growx");
        add(optionField,"width 50:50:50,pushx, gapright " + gap);

    }

    public String getOption(){

        return optionField.getText();

    }

    public void setOption(String option){

        this.optionField.setText(option);

    }

}
