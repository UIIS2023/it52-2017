package command;

import model.DrawingModel;
import shapes.Donut;

public class CommandRemoveDonut implements Command {


	private Donut donut;
	private DrawingModel model;
	
	public CommandRemoveDonut(Donut donut, DrawingModel model) {
		this.donut=donut;
		this.model=model;
	}
	
	@Override
	public void execute() {
		model.remove(donut);	
	}

	@Override
	public void unexecute() {
		model.add(donut);
	}
	
	@Override
	public String toLogText() {
		return "Deleted->"+donut.toString();	 
	}
}