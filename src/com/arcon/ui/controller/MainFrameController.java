package com.arcon.ui.controller;

import com.arcon.Main;
import com.arcon.db.DBConnect;
import com.arcon.lib.Constants;
import com.arcon.ui.model.ActionType;
import com.arcon.ui.model.Card;
import com.arcon.ui.model.Discount;
import com.arcon.ui.view.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MainFrameController {

    private MainFrame mainFrame;

    private JTextField textFieldCard;
    private JTextField textFieldCash;

    private JButton buttonPerformCard;
    private JButton buttonCancel;
    private JButton buttonIO;
    private JButton buttonOptions;
    private JButton buttonLog;

    private JComboBox comboBoxDiscount;
    private JCheckBox checkBoxDiscount;

    private JLabel jLabelTimeIn;
    private JLabel jLabelTimeOut;
    private JLabel jLabelTimeTotal;
    private JLabel jLabelPrice;
    private JLabel jLabelTotalCards;
    private JLabel jLabelMoney;

    private JLabel labelTextTimeIn;
    private JLabel labelTextTimeOut;
    private JLabel labelTextTimeTotal;
    private JLabel labelTextDiscount;
    private JLabel labelTextPrice;
    private JLabel labelTextCash;

    private boolean setText = false;
    private boolean isDiscountSet = false;
    private List<Discount> discountList;
    private Card card;
    private boolean cardReady = false;

    public MainFrameController () {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        mainFrame = new MainFrame();

        textFieldCard = mainFrame.getTextFieldCard();
        textFieldCash = mainFrame.getTextFieldCash();

        jLabelTimeIn = mainFrame.getjLabelTimeIn();
        jLabelTimeOut = mainFrame.getjLabelTimeOut();
        jLabelTimeTotal = mainFrame.getjLabelTimeTotal();
        jLabelPrice = mainFrame.getjLabelPrice();
        jLabelTotalCards = mainFrame.getjLabelTotalCards();
        jLabelMoney = mainFrame.getjLabelMoney();

        labelTextTimeIn = mainFrame.getLabelTextTimeIn();
        labelTextTimeOut = mainFrame.getLabelTextTimeOut();
        labelTextTimeTotal = mainFrame.getLabelTextTimeTotal();
        labelTextDiscount = mainFrame.getLabelTextDiscount();
        labelTextPrice = mainFrame.getLabelTextPrice();
        labelTextCash = mainFrame.getLabelTextCash();

        comboBoxDiscount = mainFrame.getComboBoxDiscount();
        checkBoxDiscount = mainFrame.getCheckBoxDiscount();

        buttonPerformCard = mainFrame.getButtonPerformCard();
        buttonCancel = mainFrame.getButtonCancel();
        buttonIO = mainFrame.getButtonIO();
        buttonOptions = mainFrame.getButtonOptions();
        buttonLog = mainFrame.getButtonLog();
    }

    private void initListeners() {

        textFieldCard.addKeyListener(new textFieldCardKeyListener());
        buttonPerformCard.addActionListener(new buttonPerformCardActionListener());
        buttonPerformCard.addKeyListener(new buttonPerformCardKeyListener());
        checkBoxDiscount.addItemListener(new checkBoxDiscountItemListener());
        comboBoxDiscount.addActionListener(new comboBoxDiscountActionListener());
        buttonCancel.addActionListener(new buttonCancelActionListener());
        textFieldCash.addKeyListener(new textFieldCashKeyListener());
        buttonIO.addActionListener(new buttonIOActionListener());

    }

    public void showMainFrameWindow() {

        mainFrame.setVisible(true);
        textFieldCard.requestFocus();
        setText();

    }

    public void updateComponents() {
        mainFrame.setTitle(Constants.getProgramTitle());
        isDiscountSet = false;
        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        setCardCount(connect, 0);
        setMoneyCount(connect, 0);
        connect.closeConnect();
    }

    private void cardPreparationAction() {

        String msg = "";
        boolean cardInUse;
        String id = textFieldCard.getText();

        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        cardInUse = connect.isCardInUse(id);

        if (cardInUse) {

            card = connect.readCardInUse(id);
            card.setExitTime();

            jLabelTimeIn.setText(card.getEnterTime().toString());
            jLabelTimeOut.setText(card.getExitTime().toString());
            jLabelTimeTotal.setText(String.valueOf(card.getTotalTime()));
            jLabelPrice.setText(String.valueOf(card.getPrice()));
            setText();
            textFieldCash.requestFocus();

        }else {
            connect.writeNewCardInUse(id);
            setCardCount(connect, 0);
            msg = "Added new Card";
            textFieldCard.setText("");
            textFieldCard.requestFocus();
        }
        connect.closeConnect();

        if (!msg.equals("")) {
            JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void cardAction() {
        if (cardReady){
            DBConnect connect = DBConnect.getInstance();
            connect.openConnect();
            connect.writeCard(card);
            connect.deleteCardInUse(String.valueOf(card.getId()));
            connect.setTransaction(card.getPrice(), ActionType.CARD_OUTPUT);
            setCardCount(connect, 0);
            setMoneyCount(connect, 0);
            connect.closeConnect();

            cancelAction();
        }
    }

    private void cancelAction() {
        card = null;
        cardReady = false;
        textFieldCard.setText("");
        textFieldCard.requestFocus();
        setText();
    }

    private void countChange() {
        String msg = "";
        String errorMsg = "";
        int cash = 0;
        int price = card.getPrice();
        int moneyBack;
        try {
            cash += Integer.parseInt(textFieldCash.getText());
        }catch (Exception exp) {
            errorMsg = "Invalid input tex field Cash";
        }

        if(cash >= price) {
            moneyBack = cash - price;
            msg = "<html>Money back: <span style='color:red'><big>" + moneyBack + "</big></span> UAH</html>";
        }else {
            errorMsg = "Cash less than price!";
        }

        textFieldCash.setText("");

        if (!msg.equals("")) {
            JOptionPane.showMessageDialog(null, msg, "Cash", JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
        }

        buttonPerformCard.grabFocus();
    }

    private void setCardCount(DBConnect connect, int cardAction) {
        if(cardAction != 0) {
            connect.setCardCount(cardAction);
        }
        String str = String.valueOf(connect.getCardCount());
        jLabelTotalCards.setText("<html><font color='red'><big>" + str + "</big></font></html>");
    }

    private void setMoneyCount(DBConnect connect, int money) {
        if(money != 0) {
            connect.setMoneyCount(money);
        }
        String str = String.valueOf(connect.getMoneyCount());
        jLabelMoney.setText("<html><font color='red'><big>" + str + "</big></font></html>");
    }

    private void setText() {
        labelTextTimeIn.setVisible(setText);
        labelTextTimeOut.setVisible(setText);
        labelTextTimeTotal.setVisible(setText);
        labelTextDiscount.setVisible(setText);
        labelTextPrice.setVisible(setText);
        labelTextCash.setVisible(setText);

        jLabelTimeIn.setVisible(setText);
        jLabelTimeOut.setVisible(setText);
        jLabelTimeTotal.setVisible(setText);
        jLabelPrice.setVisible(setText);

        checkBoxDiscount.setVisible(setText);
        comboBoxDiscount.setVisible(setText);

        textFieldCash.setVisible(setText);

        buttonCancel.setVisible(setText);
        buttonPerformCard.setVisible(setText);

        setText = !setText;
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
                comboBoxDiscount.setSelectedIndex(0);
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
                    cardPreparationAction();
                    cardReady = true;
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

    private class buttonPerformCardKeyListener implements KeyListener {
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

    private class comboBoxDiscountActionListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (card != null) {
                Discount disc = (Discount) comboBoxDiscount.getSelectedItem();
                card.setDiscount(disc.getDiscount());
                jLabelPrice.setText(String.valueOf(card.getPrice()));
            }
        }
    }

    private class buttonCancelActionListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            cancelAction();
        }
    }

    private class textFieldCashKeyListener implements KeyListener {
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
                countChange();
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

    private class buttonIOActionListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.ioFrameController.showIOFrameWindow();
        }
    }
}