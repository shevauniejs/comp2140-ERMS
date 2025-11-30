import javax.swing.table.DefaultTableModel;

public class NonEditTableMod extends DefaultTableModel {
    public NonEditTableMod(Object[] columnNames, int setRowCount){
        super(columnNames, setRowCount);
    }

        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }    
}
