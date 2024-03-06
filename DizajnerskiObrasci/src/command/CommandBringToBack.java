package command;

import model.DrawingModel;
import shapes.Shape;

public class CommandBringToBack implements Command{
	
	private DrawingModel drawModel;
	private Shape selectedShape;
	private int index;

	public CommandBringToBack(DrawingModel drawModel,Shape selectedShape) {
		this.selectedShape=selectedShape;
		this.drawModel=drawModel;
	}
	
	@Override
	public void execute() {
		index=drawModel.getIndexOfShape(selectedShape);//uzima index selektovanog oblika
		drawModel.getShapes().remove(selectedShape);//uklanja ga sa te pozicije 
		drawModel.getShapes().add(0, selectedShape);//dodaje ga na pocetak 
	}

	@Override
	public void unexecute() {
		drawModel.getShapes().remove(0);//uklanja ga sa pocetne pozicije
		drawModel.getShapes().add(index, selectedShape);//vraca ga na index na kom je bio
	}

	@Override
	public String toLogText() {
		return "Bringed to back->"+selectedShape.toString();
	}
}
