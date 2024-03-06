package command;

import shapes.Donut;


public class CommandUpdateDonut implements Command{

	private Donut oldState;
	private Donut newState;
	private Donut originalState;
	
	public CommandUpdateDonut(Donut oldState, Donut newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setRadius(newState.getRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setCenter(newState.getCenter().clone());
		oldState.setInteriorColor(newState.getInteriorColor());
		oldState.setColor(newState.getColor());
	}

	@Override
	public void unexecute() {
		oldState.setRadius(originalState.getRadius());
		oldState.setInnerRadius(originalState.getInnerRadius());
		oldState.setCenter(originalState.getCenter());
		oldState.setInteriorColor(originalState.getInteriorColor());
		oldState.setColor(originalState.getColor());
	}

	@Override
	public String toLogText() {
		return "Updated->" + originalState.toString() + "->" + newState.toString();
	}

}