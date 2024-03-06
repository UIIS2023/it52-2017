package command;

import model.DrawingModel;
import shapes.Line;

public class CommandAddLine implements Command{

	private Line line;
	private DrawingModel model;
	
	public CommandAddLine(DrawingModel model, Line line) {
		this.model=model;
		this.line=line;
	}
	
	@Override
	public void execute() {
		model.add(line);	
	}

	@Override
	public void unexecute() {
		model.remove(line);
	}
	
	@Override
	public String toLogText() {
		return "Added->" + line.toString();
	}

}