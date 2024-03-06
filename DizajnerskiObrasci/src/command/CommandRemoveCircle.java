package command;

import model.DrawingModel;
import shapes.Circle;

public class CommandRemoveCircle implements Command {

	private Circle circle;
	private DrawingModel model;
	
	public CommandRemoveCircle(Circle circle, DrawingModel model) {
		this.circle=circle;
		this.model=model;
	}
	
	@Override
	public void execute() {
		model.remove(circle);
	}

	@Override
	public void unexecute() {
		model.add(circle);
	}
	
	@Override
	public String toLogText() {
		return "Deleted->"+circle.toString();	 
	}
}