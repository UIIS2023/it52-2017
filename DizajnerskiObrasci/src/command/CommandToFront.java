package command;

import java.util.Collections;
import model.DrawingModel;
import shapes.Shape;


public class CommandToFront implements Command {
	
	private DrawingModel drawModel;
	private Shape s;
	private int index;
	
	public CommandToFront(DrawingModel drawModel,Shape s) {	
		this.drawModel=drawModel;
		this.s=s;		
	}
	
	@Override
	public void execute() {
		index=drawModel.getIndexOfShape(s);
		
		if(index!=drawModel.getShapes().size()-1)/*ako nije poslednji index*/ {
			Collections.swap(drawModel.getShapes(), index+1, index);//proverava jel poslednji index ako jeste dodaje ga na poziciju ispred
		}	
	}

	@Override
	public void unexecute() {
		if(index!=drawModel.getShapes().size()-1) {
			Collections.swap(drawModel.getShapes(), index, index+1);
		}
	}

	@Override
	public String toLogText() {
		return "Moved to front->" + s.toString();
	}

}
