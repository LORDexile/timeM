package com.arcon.ui.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class TableDiscountsModel implements TableModel{

    List<Discount> discountList;

    public TableDiscountsModel(List<Discount> discountList) {
        this.discountList = discountList;
    }

    @Override
    public int getRowCount() {
        return discountList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Discount";
            case 1:
                return "Description";
            case 2:
                return "User type";
            case 3:
                return "Active";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return double.class;
            case 1:
            case 2:
            case 3:
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
        Discount discount = discountList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return discount.getDiscount();
            case 1:
                return discount.getComment();
            case 2:
                return discount.getUserType();
            case 3:
                if (discount.isActive()){
                    return "+";
                }
                return "-";
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
