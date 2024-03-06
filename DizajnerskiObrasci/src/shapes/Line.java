package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	private Point startPoint;
    private Point endPoint;

    public Line() {
    	
    }

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line(Point startPoint, Point endPoint, Color color) {
        this(startPoint, endPoint);
        setColor(color);
    }
    
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
        if (isSelected()) 
        	selected(g);
    }

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line) obj;
			return startPoint.equals(line.startPoint) && endPoint.equals(line.endPoint);
		}
		return false;
	}

	@Override
	public int compareTo(Shape shape) {
		if (shape instanceof Line) return (int) distance() - (int) ((Line) shape).distance();
		return 0;
	}

	 @Override
	 public String toString() {
		 return "Line: start point x=" + startPoint.getX() + "; start point y=" + startPoint.getY() + "; end point x=" + endPoint.getX() + "; end point y=" + endPoint.getY() + "; color=" + getColor().toString().substring(14).replace('=', '-');
	    }

    public void moveBy(int x, int y) {
    	startPoint.moveBy(x, y);
    	endPoint.moveBy(x, y);
    }
    
    public void selected(Graphics g) {
        g.setColor(Color.BLUE);
        startPoint.selected(g);
        endPoint.selected(g);
        centerOfLine().selected(g);
    }

    public boolean contains(int x, int y) {
        if ((startPoint.distance(new Point(x, y)) + endPoint.distance(new Point(x, y))) - distance() <= 1) 
        	return true;
        return false;
    }
    
    public Line clone() {
		return new Line(startPoint.clone(), endPoint.clone(), getColor());
	}

    public double distance() {
        return startPoint.distance(endPoint);
    }

    public Point centerOfLine() {
        return new Point((startPoint.getX() + endPoint.getX()) / 2, (startPoint.getY() + endPoint.getY()) / 2);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
    
}