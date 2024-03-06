package command;

import model.DrawingModel;

import adapter.HexagonAdapter;

public class CommandAddHexagon implements Command{

	private HexagonAdapter hexagon;
	private DrawingModel model;
	
	public CommandAddHexagon(HexagonAdapter hexagonAdapter, DrawingModel model) {
		this.hexagon=hexagonAdapter;
		this.model=model;
	}
	
	@Override
	public void execute() {
		model.add(hexagon);
	}

	@Override
	public void unexecute() {
		model.remove(hexagon);
	}

	@Override
	public String toLogText() {
		return "Added->" + hexagon.toString();
	}
}