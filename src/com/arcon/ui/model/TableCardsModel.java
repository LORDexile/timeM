package com.arcon.ui.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class TableCardsModel implements TableModel{

    private List<Card> cardList;

    public TableCardsModel(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public int getRowCount() {
        return cardList.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Operation id";
            case 1:
                return "Card id";
            case 2:
                return "User";
            case 3:
                return "Price";
            case 4:
                return "Discount";
            case 5:
                return "Date in";
            case 6:
                return "Date out";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 3:
                return int.class;
            case 4:
                return double.class;
            case 2:
            case 5:
            case 6:
                return String.class;
        }
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Card card = cardList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return card.getOperationId();
            case 1:
                return card.getId();
            case 2:
                return card.getUserName();
            case 3:
                return card.getPrice();
            case 4:
                return card.getDiscount();
            case 5:
                return card.getEnterTimeString();
            case 6:
                return card.getExitTimeString();
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
