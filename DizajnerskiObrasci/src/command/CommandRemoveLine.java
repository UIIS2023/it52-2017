package command;

import model.DrawingModel;
import shapes.Line;

public class CommandRemoveLine implements Command{

	private Line line;
	private DrawingModel model;
	
	public CommandRemoveLine(Line line, DrawingModel model){
		this.line=line;
		this.model=model;
	}
	
	@Override
	public void execute() {
		model.remove(line);
	}

	@Override
	public void unexecute() {
		model.add(line);
	}
	
	@Override
	public String toLogText() {
		return "Deleted->"+line.toString();	 
	}

}