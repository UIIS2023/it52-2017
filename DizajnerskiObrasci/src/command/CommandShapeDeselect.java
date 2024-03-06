package command;

import controller.DrawingController;
import shapes.Shape;

public class CommandShapeDeselect  implements Command{
	
	private DrawingController controller;
	private Shape shape;
	
	public CommandShapeDeselect(DrawingController controller, Shape shape) {
		this.controller = controller;
		this.shape=shape;
	}

	@Override
	public void execute() {
		shape.setSelected(false);
		controller.getSelectedShapes().remove(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(true);
		controller.getSelectedShapes().add(shape);
	}

	@Override
	public String toLogText() {
			return "Unselected->" + shape.toString();
	}
	
}