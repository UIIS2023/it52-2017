package command;
import java.util.Stack;


public class UndoRedo{
	
	public static void undo(Stack<Command> stackUndo,Stack<Command> stackRedo) {
		if (!stackUndo.isEmpty()) {			
				Command command = stackUndo.pop();
				command.unexecute();
				stackRedo.push(command);	
		} 			
	}
	
	public static void redo(Stack<Command> stackUndo,Stack<Command> stackRedo) {
		if (!stackRedo.isEmpty()) {
				Command command = stackRedo.pop();
				command.execute();
				stackUndo.push(command);	 
		} 
	}

}
