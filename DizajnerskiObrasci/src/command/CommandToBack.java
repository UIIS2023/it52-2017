package command;

import java.util.Collections;
import model.DrawingModel;
import shapes.Shape;


public class CommandToBack implements Command {
	
	private DrawingModel drawModel;
	private Shape s;
	private int index;

    public CommandToBack(DrawingModel drawModel,Shape s) {	
    	this.drawModel=drawModel;
    	this.s=s;	
	}

	@Override
	public void execute() {
		index=drawModel.getIndexOfShape(s);
		if(index!=0) { 
			Collections.swap(drawModel.getShapes(), index-1, index);//vraca ga za poziciju nazad 
		}
	}

	@Override
	public void unexecute() {
		if(index!=0) {
			Collections.swap(drawModel.getShapes(), index, index-1);//stavlja ga na poziciju ispred
		}
	}

	@Override
	public String toLogText() {
		return "Moved to back->"+s.toString();
	}

}
