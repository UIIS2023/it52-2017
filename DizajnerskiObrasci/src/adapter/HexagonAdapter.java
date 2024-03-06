package adapter;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

import shapes.Point;
import shapes.Shape;
import shapes.SurfaceShape;

public class HexagonAdapter extends SurfaceShape{

	private Hexagon hexagon;
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public HexagonAdapter(Point startPoint, int radius) {
		
		hexagon = new Hexagon(startPoint.getX(), startPoint.getY(), radius);
	}
	
	public HexagonAdapter(Point center, int r, Color outsideColor, Color insideColor) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), r);
		this.hexagon.setBorderColor(outsideColor);
		this.hexagon.setAreaColor(insideColor);	
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		hexagon.setSelected(isSelected());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			Hexagon hex = ((HexagonAdapter) obj).hexagon;
			return hexagon.getX() == hex.getX() && hexagon.getY() == hex.getY() && hexagon.getR() == hex.getR();
		}
		return false;
	}
	
	@Override
	public int compareTo(Shape hex) {
		if (hex instanceof HexagonAdapter) return hexagon.getR() - ((HexagonAdapter) hex).getR();
		return 0;
	}

	@Override
	public String toString() {
		return "Hexagon: radius=" + hexagon.getR() + "; x=" + hexagon.getX() + "; y=" + hexagon.getY() + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInteriorColor().toString().substring(14).replace('=', '-');
	}
	
	@Override
	public void moveBy(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void selected(Graphics g) {}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	public HexagonAdapter clone() {
		Hexagon h = new Hexagon(getX(), getY(), getR());
		h.setBorderColor(getColor());
		h.setAreaColor(getInteriorColor());
		return new HexagonAdapter(h);
	}
	
	@Override
	public void fillUpShape(Graphics shapeForFillUp) {
		
	}

	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		super.setColor(color);
	}
	
	public Color getInteriorColor() {
		return hexagon.getAreaColor();
	}
	
	public void setInteriorColor(Color color) {
		hexagon.setAreaColor(color);
		super.setInteriorColor(color);
	}
	
	public int getR() {
		return hexagon.getR();
	}
	
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}
}