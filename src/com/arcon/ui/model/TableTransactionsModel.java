package com.arcon.ui.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class TableTransactionsModel implements TableModel{
    List<Transaction> transactionList;

    public TableTransactionsModel(List<Transaction> list) {
        this.transactionList = list;
    }

    @Override
    public int getRowCount() {
        return transactionList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Cash number";
            case 1:
                return "Action";
            case 2:
                return "User name";
            case 3:
                return "Date";
            case 4:
                return "Comment";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return int.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaction transaction = transactionList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return transaction.getCashNumber();
            case 1:
                return transaction.getActionType();
            case 2:
                return transaction.getUserName();
            case 3:
                return transaction.getDate();
            case 4:
                return transaction.getComment();
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
