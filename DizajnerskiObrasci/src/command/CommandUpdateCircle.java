package command;

import shapes.Circle;


public class CommandUpdateCircle implements Command {

	private Circle oldState;
	private Circle newState;
	private Circle originalState;
	
	public CommandUpdateCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute()
	{
		originalState=oldState.clone();
		oldState.setRadius(newState.getRadius());
		oldState.setCenter(newState.getCenter());
		oldState.setInteriorColor(newState.getInteriorColor());
		oldState.setColor(newState.getColor());
		
	}
	
	@Override
	public void unexecute() {
		oldState.setRadius(originalState.getRadius());
		oldState.setCenter(originalState.getCenter());
		oldState.setInteriorColor(originalState.getInteriorColor());
		oldState.setColor(originalState.getColor());
	}

	@Override
	public String toLogText() {
		return "Updated->" + originalState.toString() + "->" + newState.toString();
	}
}