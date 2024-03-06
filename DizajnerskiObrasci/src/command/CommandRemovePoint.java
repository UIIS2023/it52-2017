package command;

import model.DrawingModel;
import shapes.Point;

public class CommandRemovePoint implements Command {

	private Point point;
	private DrawingModel model;
	
	public CommandRemovePoint(Point point,DrawingModel model) {
		this.point=point;
		this.model=model;
	}

	@Override
	public void execute() {
		model.remove(point);
	}

	@Override
	public void unexecute() {
		model.add(point);
	}
	
	@Override
	public String toLogText() {
		return "Deleted->"+point.toString();	 
	}
}