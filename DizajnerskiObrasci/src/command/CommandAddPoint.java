package command;

import model.DrawingModel;
import shapes.Point;

public class CommandAddPoint implements Command{
	
	private DrawingModel model;
	private Point point;
	
	public CommandAddPoint(DrawingModel model,Point point) {
		this.model=model;
		this.point=point;
	}
	
	@Override
	public void execute() {
		model.add(point);
	}
	
	@Override
	public void unexecute() {
		model.remove(point);
	}

	@Override
	public String toLogText() {
		return "Added->" + point.toString();
	}
}