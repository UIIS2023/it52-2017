package controller;


import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import adapter.HexagonAdapter;
import command.CommandAddCircle;
import command.CommandAddDonut;
import command.CommandAddHexagon;
import command.CommandAddLine;
import command.CommandAddPoint;
import command.CommandAddRectangle;
import command.CommandBringToBack;
import command.CommandBringToFront;
import command.CommandRemoveCircle;
import command.CommandRemoveDonut;
import command.CommandRemoveHexagon;
import command.CommandRemoveLine;
import command.CommandRemovePoint;
import command.CommandRemoveRectangle;
import command.CommandShapeDeselect;
import command.CommandSelectShape;
import command.CommandToBack;
import command.CommandToFront;
import command.CommandUpdateCircle;
import command.CommandUpdateDonut;
import command.CommandUpdateHexagon;
import command.CommandUpdateLine;
import command.CommandUpdatePoint;
import command.CommandUpdateRectangle;
import command.Command;
import command.UndoRedo; 
import dialogs.DialogCircle;
import dialogs.DialogDonut;
import dialogs.DialogHexagon;
import dialogs.DialogLine;
import dialogs.DialogPoint;
import dialogs.DialogRectangle;
import hexagon.Hexagon;
import model.DrawingModel;
import observer.Observable;
import observer.Observer;
import dialogs.DialogDelete;
import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import Strategy.LogFile;
import Strategy.ManagerFile;
import Strategy.SaveDraw;
import Strategy.SerializableFile;
import view.DrawingFrame;


public class DrawingController {
	
	
	private DrawingFrame frame;
	private DrawingModel model;
	private List<Shape> selectedShapes = new ArrayList<Shape>();
	private Shape selected; 
	private Stack<Command> stackUndo = new Stack<Command>(); 
	private Stack<Command> stackRedo = new Stack<Command>(); 
	private PropertyChangeSupport propertyChangeSupport; 
	private DefaultListModel<String> log; 
	private ManagerFile manager;
	private int countSelectedShapes = 0; 
	private Observable observable = new Observable();
	private Observer observer;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		
		observer = new Observer(frame);
		observable.addPropertyChangeListener(observer);
		
		propertyChangeSupport = new PropertyChangeSupport(this); 
		log = frame.getDlmList();
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if(frame.getState() == 1) {
			Point point = new Point(e.getX(), e.getY(), frame.getBtnEdge().getBackground());
		
			CommandAddPoint cmdAddPoint = new CommandAddPoint(model, point);
			cmdAddPoint.execute();
			
			addCommandInStack(cmdAddPoint); 
			log.addElement(cmdAddPoint.toLogText());	
			stackRedo.removeAllElements();
			frame.getBtnRedo().setEnabled(false);	
		}
		
		if(frame.getState() == 2) {
			if(model.getStartPoint() == null) {
				model.setStartPoint(new Point(e.getX(), e.getY()));
			}
			else {
				 Line line = new Line(model.getStartPoint(), new Point(e.getX(), e.getY()), frame.getBtnEdge().getBackground());
				 
				 model.setStartPoint(null);
			
				 CommandAddLine cmdAddLine = new CommandAddLine(model, line);
				 cmdAddLine.execute();
				 
				 addCommandInStack(cmdAddLine); 
				 log.addElement(cmdAddLine.toLogText()); 
				 stackRedo.removeAllElements();
				 frame.getBtnRedo().setEnabled(false); 
			}
		}
		
		if(frame.getState() == 3) {
			DialogCircle dlgCircle = new DialogCircle();
			dlgCircle.getTxtXcoordinateOfCenter().setText(Integer.toString(e.getX())); 
			dlgCircle.getTxtXcoordinateOfCenter().disable();
			dlgCircle.getTxtYcoordinateOfCenter().setText(Integer.toString(e.getY()));
			dlgCircle.getTxtYcoordinateOfCenter().disable();
			dlgCircle.getBtnEdgeColor().setVisible(false);
			dlgCircle.getBtnInteriorColor().setVisible(false);
			dlgCircle.setVisible(true);
			
			if(dlgCircle.isConfirmed()) {
				try {
					Circle circle = new Circle(new Point(e.getX(), e.getY()), Integer.parseInt(dlgCircle.getTxtRadiusLength().getText()), frame.getBtnEdge().getBackground(), frame.getBtnInterior().getBackground());
					
					
					CommandAddCircle cmdAddCircle = new CommandAddCircle(model, circle);
					cmdAddCircle.execute();
					
					addCommandInStack(cmdAddCircle); 
					log.addElement(cmdAddCircle.toLogText());	
					stackRedo.removeAllElements();
					frame.getBtnRedo().setEnabled(false);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame,
							"Radius must be greater than 0!",
							"Illegal radius error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		
		if(frame.getState() == 4) {
			DialogRectangle dlgRectangle = new DialogRectangle();
			dlgRectangle.getTxtXcoordinate().setText(Integer.toString(e.getX())); 
			dlgRectangle.getTxtXcoordinate().disable();
			dlgRectangle.getTxtYcoordinate().setText(Integer.toString(e.getY()));
			dlgRectangle.getTxtYcoordinate().disable();	
			dlgRectangle.getBtnEdgeColor().setVisible(false);
			dlgRectangle.getBtnInteriorColor().setVisible(false);
			dlgRectangle.setVisible(true);
			
			if(dlgRectangle.isConfirmed()) {
				try {
					Rectangle rectangle = new Rectangle(new Point(e.getX(), e.getY()),Integer.parseInt(dlgRectangle.getTxtWidth().getText()),Integer.parseInt(dlgRectangle.getTxtHeight().getText()), frame.getBtnEdge().getBackground(), frame.getBtnInterior().getBackground());
			
					CommandAddRectangle cmdAddRectangle = new CommandAddRectangle(rectangle, model);
					cmdAddRectangle.execute();
					
					addCommandInStack(cmdAddRectangle); 
					log.addElement(cmdAddRectangle.toLogText());
					stackRedo.removeAllElements();//sto?
					frame.getBtnRedo().setEnabled(false);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame,
							"Width and height must be greater than 0!", null,
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		
		if(frame.getState() == 5) {
			DialogDonut dlgDonut = new DialogDonut();
			dlgDonut.getTxtXcoordinateOfCenter().setText(Integer.toString(e.getX())); 
			dlgDonut.getTxtXcoordinateOfCenter().disable();
			dlgDonut.getTxtYcoordinateOfCenter().setText(Integer.toString(e.getY()));
			dlgDonut.getTxtYcoordinateOfCenter().disable();
			dlgDonut.getBtnEdgeColor().setVisible(false);
			dlgDonut.getBtnInteriorColor().setVisible(false);
			dlgDonut.setVisible(true);
			
			if(dlgDonut.isConfirmed()) {
				try {
					Donut donut = new Donut(new Point(e.getX(), e.getY()), Integer.parseInt(dlgDonut.getTxtRadiusLength().getText()), Integer.parseInt(dlgDonut.getTxtInnerRadiusLength().getText()), frame.getBtnEdge().getBackground(), frame.getBtnInterior().getBackground());
					
					
					CommandAddDonut cmdAddDonut = new CommandAddDonut(donut, model);
					cmdAddDonut.execute();
					
					addCommandInStack(cmdAddDonut);
					log.addElement(cmdAddDonut.toLogText());
					stackRedo.removeAllElements();
					frame.getBtnRedo().setEnabled(false);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame,
							"Radius must be greater than 0!",
							"Illegal radius error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			
		}
		
		if(frame.getState() == 6) {
			DialogHexagon dlgHexagon = new DialogHexagon();
			dlgHexagon.getTxtXcoordinate().setText(Integer.toString(e.getX())); 
			dlgHexagon.getTxtXcoordinate().disable();
			dlgHexagon.getTxtYcoordinate().setText(Integer.toString(e.getY()));
			dlgHexagon.getTxtYcoordinate().disable();
			dlgHexagon.getBtnEdgeColor().setVisible(false);
			dlgHexagon.getBtnInteriorColor().setVisible(false);
			dlgHexagon.setVisible(true);
			
			if(dlgHexagon.isConfirmed()) {
				try {
					HexagonAdapter hexagon = new HexagonAdapter(new Point(e.getX(),e.getY()),Integer.parseInt(dlgHexagon.getTxtRadiusLength().getText()), frame.getBtnEdge().getBackground(), frame.getBtnInterior().getBackground());
					
					
					CommandAddHexagon cmdAddHexagon = new CommandAddHexagon(hexagon, model);
					cmdAddHexagon.execute();
					
					addCommandInStack(cmdAddHexagon); 
					log.addElement(cmdAddHexagon.toLogText());
					stackRedo.removeAllElements();
					frame.getBtnRedo().setEnabled(false);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame,
							"Width and height must be greater than 0!", null,
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		
		if(frame.getState() == 7){
			for (int i = 0; i<model.getShapes().size(); i++) {
				selected = model.getShapes().get(i);
				
				if(model.getShapes().get(i).contains(e.getX(), e.getY())) {
					if(selected.isSelected()) {
						CommandShapeDeselect cmdShapeDeselect = new CommandShapeDeselect(this,selected);
						cmdShapeDeselect.execute();
						
						addCommandInStack(cmdShapeDeselect);
						log.addElement(cmdShapeDeselect.toLogText());
						
						frame.getBtnUndo().setEnabled(true);
						frame.getBtnRedo().setEnabled(false);
						
						checkButtons();
						
					} 
					else {
						CommandSelectShape cmdShapeSelect = new CommandSelectShape(this,selected);
						cmdShapeSelect.execute();
						
						addCommandInStack(cmdShapeSelect); 
						log.addElement(cmdShapeSelect.toLogText());
						
						frame.getBtnUndo().setEnabled(true);
						frame.getBtnRedo().setEnabled(false);

						checkButtons();
					}
					
					
				}
				
			}
			
			checkShapes(e);
			if(checkShapes(e) == false) {
				unselectAll();
				checkButtons();
			}
		}
		
		frame.getView().repaint();
	}
	
	
	private boolean checkShapes(MouseEvent e) {
		for(int i = 0; i<model.getShapes().size(); i++) {
			if(model.getShapes().get(i).contains(e.getX(), e.getY())) {
				return true;
			}
		}
		return false;
	}
	
	public void unselectAll() {
		Iterator<Shape> it = selectedShapes.iterator();
		while(it.hasNext()) {
			it.next().setSelected(false);
			it.remove();
		}
	}
	
	public void delete() {
		DialogDelete dlgChoose = new DialogDelete();
		dlgChoose.setVisible(true);
		
		if(dlgChoose.confirmation) {
		
		for(int i=selectedShapes.size()-1; i >=0;i--) {
			if(selectedShapes.get(i) instanceof Point) {
				CommandRemovePoint cmdRemovePoint = new CommandRemovePoint((Point)selectedShapes.get(i),model);
				cmdRemovePoint.execute();
				
				addCommandInStack(cmdRemovePoint); 
				log.addElement(cmdRemovePoint.toLogText());
				
				frame.getView().repaint();
				selectedShapes.remove(i);
			}
			 else if(selectedShapes.get(i) instanceof Line) {
				CommandRemoveLine cmdRemoveLine = new CommandRemoveLine((Line)selectedShapes.get(i),model);
				cmdRemoveLine.execute();
				
				addCommandInStack(cmdRemoveLine); 
				log.addElement(cmdRemoveLine.toLogText());
			
				frame.getView().repaint();
				selectedShapes.remove(i);
			} 
			else if(selectedShapes.get(i) instanceof Rectangle) {
				CommandRemoveRectangle cmdRemoveRectangle = new CommandRemoveRectangle((Rectangle)selectedShapes.get(i),model);
				cmdRemoveRectangle.execute();
				
				addCommandInStack(cmdRemoveRectangle); 
				log.addElement(cmdRemoveRectangle.toLogText());
				
				frame.getView().repaint();
				selectedShapes.remove(i);
			} 
			else if(selectedShapes.get(i) instanceof Circle) {
				CommandRemoveCircle cmdRemoveCircle = new CommandRemoveCircle((Circle)selectedShapes.get(i),model);
				cmdRemoveCircle.execute();
				
				addCommandInStack(cmdRemoveCircle); 
				log.addElement(cmdRemoveCircle.toLogText());
				
				frame.getView().repaint();
				selectedShapes.remove(i);
			} 
			else if(selectedShapes.get(i) instanceof Donut) {
				CommandRemoveDonut cmdRemoveDonut  = new CommandRemoveDonut((Donut)selectedShapes.get(i),model);
				cmdRemoveDonut.execute();
				
				addCommandInStack(cmdRemoveDonut); 
				log.addElement(cmdRemoveDonut.toLogText());
				
				frame.getView().repaint();
				selectedShapes.remove(i);
			} 
			else if(selectedShapes.get(i) instanceof HexagonAdapter) {
				CommandRemoveHexagon cmdRemoveHexagon = new CommandRemoveHexagon((HexagonAdapter)selectedShapes.get(i),model);
				cmdRemoveHexagon.execute();
				
				addCommandInStack(cmdRemoveHexagon); 
				log.addElement(cmdRemoveHexagon.toLogText());
				
				frame.getView().repaint();
				selectedShapes.remove(i);
			}
		}
		}
		
		checkButtons();
	}

	public void updateShapeClicked() {
		Shape shape = getSelectedShape();
		if (shape instanceof Point) btnUpdatePointClicked((Point) shape); 
		else if (shape instanceof Line) btnUpdateLineClicked((Line) shape);
		else if (shape instanceof Rectangle) btnUpdateRectangleClicked((Rectangle) shape);
		else if (shape instanceof Donut) btnUpdateDonutClicked((Donut) shape);
		else if (shape instanceof Circle) btnUpdateCircleClicked((Circle) shape);	
		else if (shape instanceof HexagonAdapter) btnUpdateHexagonClicked((HexagonAdapter) shape);
		frame.getView().repaint();
	}

	private void btnUpdateHexagonClicked(HexagonAdapter oldHexagon) {
		DialogHexagon dlgHexagon = new DialogHexagon();
		dlgHexagon.getTxtXcoordinate().setText(Integer.toString(oldHexagon.getX()));
		dlgHexagon.getTxtYcoordinate().setText(Integer.toString(oldHexagon.getY()));
		dlgHexagon.getTxtRadiusLength().setText(Integer.toString(oldHexagon.getR()));
		dlgHexagon.getBtnEdgeColor().setBackground(oldHexagon.getColor());
		dlgHexagon.getBtnInteriorColor().setBackground(oldHexagon.getInteriorColor());
		
		dlgHexagon.setVisible(true);
		if(dlgHexagon.isConfirmed()) {
			Hexagon hex = new Hexagon(dlgHexagon.getXcoordinate(), dlgHexagon.getYcoordinate(), dlgHexagon.getRadiusLength());
			hex.setAreaColor(dlgHexagon.getBtnInteriorColor().getBackground());
			hex.setBorderColor(dlgHexagon.getBtnEdgeColor().getBackground());
			
			HexagonAdapter newHexagon = new HexagonAdapter(hex);
			
			CommandUpdateHexagon cmdUpdateHexagon = new CommandUpdateHexagon(oldHexagon, newHexagon);
			cmdUpdateHexagon.execute();
			
			addCommandInStack(cmdUpdateHexagon); 
			log.addElement(cmdUpdateHexagon.toLogText());
			stackRedo.removeAllElements();
			frame.getBtnRedo().setEnabled(false);
			
			
		}
		
	}

	private void btnUpdateDonutClicked(Donut oldDonut) {
		DialogDonut dlgDonut = new DialogDonut();
		dlgDonut.getTxtXcoordinateOfCenter().setText(Integer.toString(oldDonut.getCenter().getX())); 
		dlgDonut.getTxtYcoordinateOfCenter().setText(Integer.toString(oldDonut.getCenter().getY()));
		dlgDonut.getTxtRadiusLength().setText(Integer.toString(oldDonut.getRadius())); 
		dlgDonut.getTxtInnerRadiusLength().setText(Integer.toString(oldDonut.getInnerRadius()));
		dlgDonut.getBtnEdgeColor().setBackground(oldDonut.getColor());
		dlgDonut.getBtnInteriorColor().setBackground(oldDonut.getInteriorColor());
		
		dlgDonut.setVisible(true);
		if(dlgDonut.isConfirmed()) {
			Donut newDonut = new Donut(new Point(dlgDonut.getXcoordinateOfCenter(), dlgDonut.getYcoordinateOfCenter()), dlgDonut.getRadiusLength(), dlgDonut.getInnerRadiusLength(), dlgDonut.getBtnEdgeColor().getBackground(), dlgDonut.getBtnInteriorColor().getBackground());
			
			CommandUpdateDonut cmdUpdateDonut = new CommandUpdateDonut(oldDonut, newDonut);
			cmdUpdateDonut.execute();
			
			addCommandInStack(cmdUpdateDonut); 
			log.addElement(cmdUpdateDonut.toLogText());
			stackRedo.removeAllElements();
			frame.getBtnRedo().setEnabled(false);
			
			
		}
				
	}

	private void btnUpdateRectangleClicked(Rectangle oldRectangle) {
		DialogRectangle dlgRectangle = new DialogRectangle();
		dlgRectangle.getTxtXcoordinate().setText(Integer.toString(oldRectangle.getUpLeft().getX()));
		dlgRectangle.getTxtYcoordinate().setText(Integer.toString(oldRectangle.getUpLeft().getY()));
		dlgRectangle.getTxtWidth().setText(Integer.toString(oldRectangle.getWidth()));
		dlgRectangle.getTxtHeight().setText(Integer.toString(oldRectangle.getHeight()));
		dlgRectangle.getBtnEdgeColor().setBackground(oldRectangle.getColor());
		dlgRectangle.getBtnInteriorColor().setBackground(oldRectangle.getInteriorColor());
		
		dlgRectangle.setVisible(true);
		if(dlgRectangle.isConfirmed()) {
			Rectangle newRectangle = new Rectangle(new Point(dlgRectangle.getXcoordinate(), dlgRectangle.getYcoordinate()), dlgRectangle.getRectangleWidth(), dlgRectangle.getRectangleHeight(), dlgRectangle.getBtnEdgeColor().getBackground(), dlgRectangle.getBtnInteriorColor().getBackground());
			
			CommandUpdateRectangle cmdUpdateRectangle = new CommandUpdateRectangle(oldRectangle, newRectangle);
			cmdUpdateRectangle.execute();
			
			addCommandInStack(cmdUpdateRectangle); 
			log.addElement(cmdUpdateRectangle.toLogText());
			stackRedo.removeAllElements();
			frame.getBtnRedo().setEnabled(false);
			
		}
		
	}

	private void btnUpdateLineClicked(Line oldLine) {
		DialogLine dlgLine = new DialogLine();
		dlgLine.getTxtxCoordinateStartPoint().setText(Integer.toString(oldLine.getStartPoint().getX()));
		dlgLine.getTxtyCoordinateStartPoint().setText(Integer.toString(oldLine.getStartPoint().getY()));
		dlgLine.getTxtxCoordinateEndPoint().setText(Integer.toString(oldLine.getEndPoint().getX()));
		dlgLine.getTxtyCoordinateEndPoint().setText(Integer.toString(oldLine.getEndPoint().getY()));
		dlgLine.getBtnColor().setBackground(oldLine.getColor());
				
		dlgLine.setVisible(true);
		if(dlgLine.isConfirmed()) {
			Line newLine =  new Line(new Point(dlgLine.getxCoordinateStartPoint(), dlgLine.getyCoordinateStartPoint()), new Point(dlgLine.getxCoordinateEndPoint(), dlgLine.getyCoordinateEndPoint()), dlgLine.getBtnColor().getBackground());
			
			CommandUpdateLine cmdUpdateLine = new CommandUpdateLine(oldLine, newLine);
			cmdUpdateLine.execute();
			
			addCommandInStack(cmdUpdateLine); 
			log.addElement(cmdUpdateLine.toLogText());
			stackRedo.removeAllElements();
			frame.getBtnRedo().setEnabled(false);
			
			
		}
		
	}

	private void btnUpdatePointClicked(Point oldPoint) {
		DialogPoint dlgPoint = new DialogPoint();
		dlgPoint.getTxtXcoordinate().setText(Integer.toString(oldPoint.getX())); 
		dlgPoint.getTxtYcoordinate().setText(Integer.toString(oldPoint.getY()));
		dlgPoint.getBtnColor().setBackground(oldPoint.getColor());	
		
		dlgPoint.setVisible(true);
		if(dlgPoint.isConfirmed()) {
			Point newPoint = new Point(dlgPoint.getXcoordinate(), dlgPoint.getYcoordinate(), dlgPoint.getBtnColor().getBackground());
			
			CommandUpdatePoint cmdUpdatePoint = new CommandUpdatePoint(oldPoint, newPoint);
			cmdUpdatePoint.execute();
			
			addCommandInStack(cmdUpdatePoint); 
			log.addElement(cmdUpdatePoint.toLogText());
			stackRedo.removeAllElements();
			frame.getBtnRedo().setEnabled(false);
			
				
		}
	}

	private void btnUpdateCircleClicked(Circle oldCircle) {
		DialogCircle dlgCircle = new DialogCircle();
		dlgCircle.getTxtXcoordinateOfCenter().setText(Integer.toString(oldCircle.getCenter().getX())); 
		dlgCircle.getTxtYcoordinateOfCenter().setText(Integer.toString(oldCircle.getCenter().getY()));
		dlgCircle.getTxtRadiusLength().setText(Integer.toString(oldCircle.getRadius())); 
		dlgCircle.getBtnEdgeColor().setBackground(oldCircle.getColor());
		dlgCircle.getBtnInteriorColor().setBackground(oldCircle.getInteriorColor());
		
		dlgCircle.setVisible(true);
		if(dlgCircle.isConfirmed()) {
			Circle newCircle = new Circle(new Point(dlgCircle.getXcoordinateOfCenter(), dlgCircle.getYcoordinateOfCenter()), dlgCircle.getRadiusLength(), dlgCircle.getBtnEdgeColor().getBackground(), dlgCircle.getBtnInteriorColor().getBackground());			
			
			CommandUpdateCircle cmdUpdateCircle = new CommandUpdateCircle(oldCircle, newCircle);
			cmdUpdateCircle.execute();
			
			addCommandInStack(cmdUpdateCircle); 
			log.addElement(cmdUpdateCircle.toLogText());
			stackRedo.removeAllElements();
			frame.getBtnRedo().setEnabled(false);
			
			
		}
		
	}

	private Shape getSelectedShape() {
		Iterator<Shape> iterator = model.getAll().iterator();
		while(iterator.hasNext()) {
			Shape shapeForModification = iterator.next();
			if(shapeForModification.isSelected())
				return shapeForModification;
		}
		return null;
	}
	
	//uslov !!
	public void checkButtons() {
		if(selectedShapes.size() !=0)
		{
			if(selectedShapes.size()==1)
			{
				observable.setBtnUpdateActivated(true);
				btnUpdate();
				
			} 
			else {
				observable.setBtnUpdateActivated(false);
				observable.setBtnBringToBackActivated(false);
				observable.setBtnBringToFrontActivated(false);
				observable.setBtnToBackActivated(false);
				observable.setBtnToFrontActivated(false);
			}
			observable.setBtnDeleteActivated(true);
		} 
		else {
			observable.setBtnUpdateActivated(false);
			observable.setBtnDeleteActivated(false);
			observable.setBtnBringToBackActivated(false);
			observable.setBtnBringToFrontActivated(false);
			observable.setBtnToBackActivated(false);
			observable.setBtnToFrontActivated(false);
		
		}
	}
	
	public void btnUpdate() {
	
		ListIterator<Shape> it = model.getShapes().listIterator();
		while(it.hasNext())
		{
			selected = it.next();
			if(selected.isSelected()) {
				if(model.getShapes().size() !=1) {
					if(selected.equals(model.get(model.getShapes().size()-1))) { 
						observable.setBtnBringToBackActivated(true);
						observable.setBtnBringToFrontActivated(false);
						observable.setBtnToBackActivated(true);
						observable.setBtnToFrontActivated(false);
					} 
					else if (selected.equals(model.get(0))) { 
						observable.setBtnBringToBackActivated(false);
						observable.setBtnBringToFrontActivated(true);
						observable.setBtnToBackActivated(false);
						observable.setBtnToFrontActivated(true);
					} 
					else { 
						observable.setBtnBringToBackActivated(true);
						observable.setBtnBringToFrontActivated(true);
						observable.setBtnToBackActivated(true);
						observable.setBtnToFrontActivated(true);
					}
				}
			}
		}
	}
	
	public void bringToFront() {
		if (selectedShapes.size() != 1)
			return;
		Shape s= getSelectedShape();
		
		CommandBringToFront cmdBToF=new CommandBringToFront(model,s);
		cmdBToF.execute();
		
		addCommandInStack(cmdBToF); 
		log.addElement(cmdBToF.toLogText());

		frame.getView().repaint();
		checkButtons();
		
	}

	public void bringToBack() {
		if (selectedShapes.size() != 1)
			return;
		Shape s=getSelectedShape();
		
		CommandBringToBack cmdBToB=new CommandBringToBack(model,s);
		cmdBToB.execute();
		
		addCommandInStack(cmdBToB); 
		log.addElement(cmdBToB.toLogText());
	
		frame.getView().repaint();
		checkButtons();
	}

	public void toBack() {
		if (selectedShapes.size() != 1)
			return;
		Shape s=getSelectedShape();
		
		CommandToBack cmdToB=new CommandToBack(model,s);
		cmdToB.execute();
		
		addCommandInStack(cmdToB); 
		log.addElement(cmdToB.toLogText());
		
		frame.getView().repaint();	
		checkButtons();
	}

	public void toFront() {
		if (selectedShapes.size() != 1)
			return;
		Shape s=getSelectedShape();
		
		CommandToFront cmdToF=new CommandToFront(model,s);
		cmdToF.execute();
		
		addCommandInStack(cmdToF);
		log.addElement(cmdToF.toLogText());
		
		frame.getView().repaint();	
		checkButtons();
	}
	
	public void undo() {
		if(frame.getBtnUndo().isEnabled()) {
		
			log.addElement("Undo->"+stackUndo.peek().toLogText());
			
			UndoRedo.undo(stackUndo,stackRedo);
		
			if(stackUndo.isEmpty() && !stackRedo.isEmpty()) {
				frame.getBtnUndo().setEnabled(false);
				frame.getBtnRedo().setEnabled(true);
				} 
			else if(!stackUndo.isEmpty() && !stackRedo.isEmpty()) {
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(true);
				} 
			frame.getView().repaint();
			checkButtons();
		}	
	}
	
	public void redo() {
		if(frame.getBtnRedo().isEnabled()) {
			
			log.addElement("Redo->"+stackRedo.peek().toLogText());
			
			UndoRedo.redo(stackUndo,stackRedo);
			
			if(stackRedo.isEmpty() && !stackUndo.isEmpty()) {
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnRedo().setEnabled(false);
				
				} 
			else if(!stackRedo.isEmpty() && !stackUndo.isEmpty()) {
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(true);
				}
			frame.getView().repaint();
			checkButtons();
		}	
	}
	
	public void addCommandInStack(Command command) { 
		 stackUndo.push(command); 
		
		if(!stackUndo.isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}
	}

	public void save() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
		chooser.enableInputMethods(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setDialogTitle("Save");
		chooser.setAcceptAllFileFilterUsed(false);
		if (!model.getAll().isEmpty()) {
			chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
		}
		if (!stackUndo.isEmpty()) chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) manager = new ManagerFile(new SerializableFile(model));
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) manager = new ManagerFile(new LogFile(frame, model, this));
			else manager = new ManagerFile(new SaveDraw(frame));
			manager.save(chooser.getSelectedFile());
		}
		chooser.setVisible(false);
	}
	
	public void open() {
		JFileChooser chooser = new JFileChooser();
		chooser.enableInputMethods(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
		chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			model.removeAll();
			log.removeAllElements();
			stackRedo.clear();
			stackUndo.clear();
			frame.getView().repaint();
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) {
				manager = new ManagerFile(new SerializableFile(model));
				propertyChangeSupport.firePropertyChange("serialized draw opened", false, true);
			}
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) manager = new ManagerFile(new LogFile(frame, model, this));
			manager.open(chooser.getSelectedFile());
		}	
		chooser.setVisible(false);
	}
	
	public void newDraw() {
		if(JOptionPane.showConfirmDialog(null, "Are you sure that you want to start new draw?", "Warning", JOptionPane.YES_NO_OPTION) == 0) {	
			model.removeAll();
			log.removeAllElements();
			stackRedo.clear();
			stackUndo.clear();
			propertyChangeSupport.firePropertyChange("draw is empty", false, true);
			frame.getView().repaint();
		}
	}
	
	public void handleSelect(String s, String command) {
		if (command.equals("redo")) {
			if (s.equals("Selected")) ++countSelectedShapes;
			else --countSelectedShapes;
			handleSelectButtons();
		} else if (command.equals("undo")) {
			if (s.equals("Selected")) --countSelectedShapes;
			else ++countSelectedShapes;
			handleSelectButtons();
		} else if (command.equals("parser")) {
			if (s.equals("Selected")) ++countSelectedShapes;
			else --countSelectedShapes;
		}
	}
	
	public void handleSelectButtons() {
		if (countSelectedShapes == 0) propertyChangeSupport.firePropertyChange("unselected", false, true);
		else if (countSelectedShapes == 1)  {
			propertyChangeSupport.firePropertyChange("update turn on", false, true);
			propertyChangeSupport.firePropertyChange("selected", false, true);
		}  
		else if (countSelectedShapes > 1) propertyChangeSupport.firePropertyChange("update turn off", false, true);
	}
	
	public void executeCommand(Command command) {
		command.execute();
		stackUndo.push(command);
		
		if (!stackRedo.isEmpty()) {
			stackRedo.removeAllElements();
			propertyChangeSupport.firePropertyChange("redo turn off", false, true);
		}
		
		if (model.getAll().isEmpty()) propertyChangeSupport.firePropertyChange("don't exist", false, true);
		else if (model.getAll().size() == 1) propertyChangeSupport.firePropertyChange("exist", false, true);
		
		if (stackUndo.isEmpty()) propertyChangeSupport.firePropertyChange("draw is empty", false, true);
		else if (stackUndo.size() == 1) propertyChangeSupport.firePropertyChange("draw is not empty", false, true);
		frame.getView().repaint();
	}

	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}
}
