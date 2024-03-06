package command;

import adapter.HexagonAdapter;
import model.DrawingModel;

public class CommandRemoveHexagon implements Command{
	
	private HexagonAdapter hexagon;
	private DrawingModel model;
	
	public CommandRemoveHexagon(HexagonAdapter hexagon,DrawingModel model) {
		this.hexagon=hexagon;
		this.model=model;
	}

	@Override
	public void execute() {
		model.remove(hexagon);
	}

	@Override
	public void unexecute() {
		model.add(hexagon);
	}
	
	@Override
	public String toLogText() {
		return "Deleted->"+hexagon.toString();	 
	}

}