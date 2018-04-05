import java.util.ArrayList;

import javax.swing.table.*;

import models.DShapeModel;
import models.ModelListener;
public class ShapeTable extends AbstractTableModel implements ModelListener{
	private String []  colNames = {"X", "Y","Width", "Height"};
	ArrayList<Integer> xData = new ArrayList<Integer>();
	ArrayList<Integer> yData = new ArrayList<Integer>();
	ArrayList<Integer> wData = new ArrayList<Integer>();
	ArrayList<Integer> hData = new ArrayList<Integer>();
	ArrayList<DShapeModel> theModelColumn = new ArrayList<DShapeModel>();
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
		else {
			return hData.get(arg0);
		}
	}
	public void setValueAt(Object modelInfo, int row, int col){
		Integer data = (Integer)modelInfo;
		if(col==0){
			if(xData.size()==0 || xData.size()<=row){
				xData.add(data);
			}
			else{
				xData.set(row, data);
			}
		}
		else if(col==1){
			if(yData.size()==0 || yData.size()<=row){
				yData.add(data);
			}
			else{
				yData.set(row, data);
			}
		}
		else if(col==2){
			if(wData.size()==0 || wData.size()<=row){
				wData.add(data);
			}
			else{
				wData.set(row, data);
			}
		}
		else if(col==3){
			if(hData.size()==0 || hData.size()<=row){
				hData.add(data);
			}
			else{
				hData.set(row, data);
			}
		}
	}
	public void addShapeModel(DShapeModel d){
		theModelColumn.add(d);
		addLastValueToTable();
		fireTableDataChanged();
	}
	//called when a shapeModel is being added to this table. Get the last value of the theModelColumn and setValue 
	//adds it to the bottom of the table
	private void addLastValueToTable(){
		//System.out.println(theModelColumn.size()-1);
		setValueAt(theModelColumn.get(theModelColumn.size()-1).getX(), theModelColumn.size()-1, 0);
		setValueAt(theModelColumn.get(theModelColumn.size()-1).getY(), theModelColumn.size()-1, 1);
		setValueAt(theModelColumn.get(theModelColumn.size()-1).getWidth(), theModelColumn.size()-1, 2);
		setValueAt(theModelColumn.get(theModelColumn.size()-1).getHeight(), theModelColumn.size()-1, 3);
	}
	@Override
	public void modelChanged(DShapeModel model) {
		//listens to models. if the model is removed, it will notify this table and remove it 
		if(model.getSelected()==true){
			int row = 0;
			for(DShapeModel d : theModelColumn){
				if(d.getSelected()==true){
					setValueAt(model.getX(),row, 0);
					setValueAt(model.getY(), row, 1);
					setValueAt(model.getWidth(), row, 2);
					setValueAt(model.getHeight(), row, 3);
					break;
				}
				row++;
			}
			fireTableRowsUpdated(row, row);
		}
		if(model.getDeleted()==true){
			int row = 0;
			for(DShapeModel d : theModelColumn){
				if(d.getDeleted()==true){
					theModelColumn.remove(d);
					//removing the entire row
					xData.remove(row);
					yData.remove(row);
					wData.remove(row);
					hData.remove(row);
					fireTableRowsDeleted(row, row);
					break;
				}
				row++;
			}
		}
	}
}
