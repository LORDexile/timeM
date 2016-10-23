package com.arcon.ui.controller;

import com.arcon.db.DBConnect;
import com.arcon.lib.Constants;
import com.arcon.ui.model.Card;
import com.arcon.ui.model.Discount;
import com.arcon.ui.view.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

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
    private JButton buttonLog;

    private boolean isDiscountSet = false;
    private List<Discount> discountList;
    private Card card;

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
        checkBoxDiscount = mainFrame.getCheckBoxDiscount();

        buttonCardInput = mainFrame.getButtonCardInput();
        buttonIO = mainFrame.getButtonIO();
        buttonOptions = mainFrame.getButtonOptions();
        buttonLog = mainFrame.getButtonLog();
    }

    private void initListeners() {

        textFieldCard.addKeyListener(new textFieldCardKeyListener());
        buttonPerformCard.addActionListener(new buttonPerformCardActionListener());
        checkBoxDiscount.addItemListener(new checkBoxDiscountItemListener());
        comboBoxDiscount.addActionListener(new comboBoxDiscountActionListener());

    }

    public void showMainFrameWindow() {

        mainFrame.setVisible(true);
        textFieldCard.requestFocus();

    }

    public void updateComponents() {
        mainFrame.setTitle(Constants.getProgramTitle());
        isDiscountSet = false;
    }

    private void cardAction() {

        String msg = "";
        boolean cardInUse;
        String id = textFieldCard.getText();

        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        cardInUse = connect.isCardInUse(id);

        if (cardInUse) {

            System.out.println("Такая карта есть!");
            card = connect.readCardInUSe(id);
            card.setExitTime();

            jLabelTimeIn.setText(card.getEnterTime().toString());
            jLabelTimeOut.setText(card.getExitTime().toString());
            jLabelTimeTotal.setText(String.valueOf(card.getTotalTime()));
            jLabelPrice.setText(String.valueOf(card.getPrice()));
            textFieldCash.requestFocus();

        }else {
            msg = "Добавлена новая карта";
            textFieldCard.requestFocus();
        }
        connect.closeConnect();

        if (!msg.equals("")) {
            JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
        }
        textFieldCard.setText("");
    }

    private class checkBoxDiscountItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (checkBoxDiscount.isSelected()) {
                comboBoxDiscount.setEnabled(true);

                if (!isDiscountSet) {
                    DBConnect connect = new DBConnect();
                    connect.openConnect();
                    discountList = connect.getDiscountSet();
                    connect.closeConnect();

                    for (Discount elem :
                            discountList) {
                        comboBoxDiscount.addItem(elem);
                    }

                    isDiscountSet = true;
                }

            }else {
                comboBoxDiscount.setEnabled(false);
            }

        }
    }

    private class textFieldCardKeyListener implements KeyListener {
        /**
         * Invoked when a key has been typed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key typed event.
         *
         * @param e
         */
        @Override
        public void keyTyped(KeyEvent e) {

        }

        /**
         * Invoked when a key has been pressed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key pressed event.
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getExtendedKeyCode() == 10) {
                if (!textFieldCard.getText().equals("")) {
                    cardAction();
                }
            }
        }

        /**
         * Invoked when a key has been released.
         * See the class description for {@link KeyEvent} for a definition of
         * a key released event.
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private class buttonPerformCardActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!textFieldCard.getText().equals("")) {
                cardAction();
            }
        }
    }

    private class comboBoxDiscountActionListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Discount disc = (Discount)comboBoxDiscount.getSelectedItem();
            card.setDiscount(disc.getDiscount());
            jLabelPrice.setText(String.valueOf(card.getPrice()));

        }
    }
}
