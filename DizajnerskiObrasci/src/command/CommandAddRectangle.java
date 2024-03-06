package command;

import model.DrawingModel;
import shapes.Rectangle;

public class CommandAddRectangle implements Command {
	
	private Rectangle rectangle;
	private DrawingModel model;
	
	public CommandAddRectangle(Rectangle rectangle,DrawingModel model) {
		this.rectangle=rectangle;
		this.model=model;
	}

	@Override
	public void execute() {
		model.add(rectangle);	
	}

	@Override
	public void unexecute() {
		model.remove(rectangle);
	}

	@Override
	public String toLogText() {
		return "Added->" + rectangle.toString();
	}
}