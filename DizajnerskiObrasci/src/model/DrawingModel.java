package model;


import java.util.ArrayList;
import java.util.List;
import shapes.Point;
import shapes.Shape;


public class DrawingModel {
	
	private List<Shape> shapes = new ArrayList<Shape>();
	
	private Point startPoint;
	private Point oldPointState;
		
	public int getIndexOfShape(Shape s) {
		int listSize = shapes.size() - 1;
		
		for (int i = 0; i <= listSize; i++) {
			if (shapes.get(i).equals(s)) {
				
				return i;
			}
		}
		return -1;
	}

	public Point getOldPointState() {
		return oldPointState;
	}

	public void setOldPointState(Point oldPointState) {
		this.oldPointState = oldPointState;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}

	public void add(Shape p) {
		shapes.add(p);
	}

	public void remove(Shape p) {
		shapes.remove(p);
	}

	public Shape get(int index) {
		return shapes.get(index);
	}
	
	public ArrayList<Shape> getAll() {
		return (ArrayList<Shape>) shapes;
	}
	
	public void removeAll() {
		shapes.clear();
	}
	
	public Shape getByIndex(int index) {
		return shapes.get(index);
	}
	
	public void addMultiple(ArrayList<Shape> shapes) {
		this.shapes.addAll(shapes);
	}

}
