package command;

import controller.DrawingController;
import shapes.Shape;

public class CommandSelectShape implements Command {

	private DrawingController controller;
	private Shape shape;
	
	public CommandSelectShape(DrawingController controller,Shape shape) {
		this.controller = controller;
		this.shape=shape;
	}
	
	@Override
	public void execute() {
		shape.setSelected(true);
		controller.getSelectedShapes().add(shape);//dodaje u listu selekovanih 
	}
	
	@Override
	public void unexecute() {
		shape.setSelected(false);
		controller.getSelectedShapes().remove(shape);
	}
	
	@Override
	public String toLogText() {
			return "Selected->" + shape.toString();
		
	}
	
}