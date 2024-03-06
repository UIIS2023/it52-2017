package command;

import model.DrawingModel;
import shapes.Circle;

public class CommandAddCircle implements Command{
	
	private Circle circle;
	private DrawingModel model;
	
	public CommandAddCircle(DrawingModel model, Circle circle) {
		this.model=model;
		this.circle=circle;
	}
	
	@Override
	public void execute() {
		model.add(circle);
	}

	@Override
	public void unexecute() {
		model.remove(circle);
	}

	@Override
	public String toLogText() {
		return "Added->" + circle.toString();
	}
}