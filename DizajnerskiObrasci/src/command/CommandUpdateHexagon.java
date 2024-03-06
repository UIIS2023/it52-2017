package command;

import adapter.HexagonAdapter;


public class CommandUpdateHexagon implements Command{
	
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState;

	public CommandUpdateHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.moveBy(newState.getX(), newState.getY());
		oldState.setColor(newState.getColor());
		oldState.setInteriorColor(newState.getInteriorColor());
		oldState.setR(newState.getR());	
	}

	@Override
	public void unexecute() {
		oldState.moveBy(originalState.getX(), originalState.getY());
		oldState.setColor(originalState.getColor());
		oldState.setInteriorColor(originalState.getInteriorColor());
		oldState.setR(originalState.getR());
	}

	@Override
	public String toLogText() {
		return "Updated->" + originalState.toString() + "->" + newState.toString();
	}
}