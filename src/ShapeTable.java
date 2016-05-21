import java.util.ArrayList;

import javax.swing.table.*;
public class ShapeTable extends AbstractTableModel implements ModelListener{
	private String []  colNames = {"X", "Y","Width", "Height"};
	ArrayList<Integer> xData = new ArrayList<Integer>();
	ArrayList<Integer> yData = new ArrayList<Integer>();
	ArrayList<Integer> wData = new ArrayList<Integer>();
	ArrayList<Integer> hData = new ArrayList<Integer>();
	public ShapeTable(){
	}
	public String getColumnName(int col){
		return colNames[col];
	}
	public int getColumnCount() {
		return colNames.length;
	}
	@Override
	public int getRowCount() {
		return xData.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if(arg1==0){
			return xData.get(arg0);
		}
		else if(arg1==1){
			return yData.get(arg0);
		}
		else if(arg1==2){
			return wData.get(arg0);
		}
		else{
			return hData.get(arg0);
		}
	}
	public void setValueAt(Object value, int row, int col){
		
	}
	public void insertShapeModels(ArrayList<DShapeModel> ds){
		for(DShapeModel dM : ds){
			dM.addModelListener(this);
			xData.add(dM.getX());
			yData.add(dM.getY());
			wData.add(dM.getWidth());
			hData.add(dM.getHeight());
		}
		fireTableDataChanged();
	}
	@Override
	public void modelChanged(DShapeModel model) {
		System.out.println("this changed: "+ model);
		fireTableDataChanged();
	}
}
