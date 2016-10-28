package com.arcon.ui.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.text.SimpleDateFormat;
import java.util.*;

public class TableCardInUseModel implements TableModel {

    private List<Card> cardList;
    private Set<TableModelListener> listeners = new HashSet<>();

    public TableCardInUseModel(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public int getRowCount() {
        return cardList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "Date";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Long.class;
            case 1:
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
        switch (columnIndex){
            case 0:
                return card.getId();
            case 1:
                SimpleDateFormat dateFormat = new SimpleDateFormat("'['HH:mm:ss']' d.MMM.yyyy", new Locale("ru"));

                return dateFormat.format(card.getEnterTime());
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}
