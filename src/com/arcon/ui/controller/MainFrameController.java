package com.arcon.ui.controller;

import com.arcon.db.DBConnect;
import com.arcon.lib.Constants;
import com.arcon.ui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrameController {

    private MainFrame mainFrame;

    private JTextField textFieldCard;
    private JButton buttonPerformCard;
    private JComboBox comboBoxDiscount;
    private JCheckBox checkBoxDiscount;
    private JTextField textFieldCash;
    private JLabel jLabelTimeIn;
    private JLabel jLabelTimeOut;
    private JLabel jLabelTimeTotal;
    private JLabel jLabelPrice;
    private JToolBar toolBarMenu;
    private JButton buttonCardInput;
    private JButton buttonIO;
    private JButton buttonOptions;
    private JButton battonLog;

    private boolean isDiscountSet;

    public MainFrameController () {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        mainFrame = new MainFrame();

        textFieldCard = mainFrame.getTextFieldCard();
        textFieldCash = mainFrame.getTextFieldCash();
        buttonPerformCard = mainFrame.getButtonPerformCard();
        jLabelTimeIn = mainFrame.getjLabelTimeIn();
        jLabelTimeOut = mainFrame.getjLabelTimeOut();
        jLabelTimeTotal = mainFrame.getjLabelTimeTotal();
        jLabelPrice = mainFrame.getjLabelPrice();
        comboBoxDiscount = mainFrame.getComboBoxDiscount();
        comboBoxDiscount.addItem(0.0);
        checkBoxDiscount = mainFrame.getCheckBoxDiscount();

        buttonCardInput = mainFrame.getButtonCardInput();
        buttonIO = mainFrame.getButtonIO();
        buttonOptions = mainFrame.getButtonOptions();
        battonLog = mainFrame.getBattonLog();
    }

    private void initListeners() {

        checkBoxDiscount.addItemListener(new checkBoxDiscountItemListener());

    }

    public void showMainFrameWindow() {

        mainFrame.setVisible(true);


    }

    public void updateComponents() {
        mainFrame.setTitle(Constants.getProgramTitle());
        isDiscountSet = false;
    }

    private class checkBoxDiscountItemListener implements ItemListener {
        /**
         * Invoked when an item has been selected or deselected by the user.
         * The code written for this method performs the operations
         * that need to occur when an item is selected (or deselected).
         *
         * @param e
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (checkBoxDiscount.isSelected()) {
                comboBoxDiscount.setEnabled(true);

                if (!isDiscountSet) {
                    Map<Double, String> map = new HashMap<>();

                    DBConnect connect = new DBConnect();
                    connect.openConnect();
                    map = connect.getDiscountSet();
                    connect.closeConnect();

                    for (Map.Entry<Double, String> elem :
                            map.entrySet()) {
                        comboBoxDiscount.addItem(elem.getKey());
                    }

                    isDiscountSet = true;
                }

            }else {
                comboBoxDiscount.setEnabled(false);
            }

        }
    }
}
