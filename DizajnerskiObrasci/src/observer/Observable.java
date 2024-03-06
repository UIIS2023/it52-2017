package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class Observable {
	
	private boolean btnDeleteActivated;
	private boolean btnUpdateActivated;
	private boolean btnBringToFrontActivated;
	private boolean btnBringToBackActivated;
	private boolean btnToBackActivated;
	private boolean btnToFrontActivated;
	
	private PropertyChangeSupport propertyChangeSupport;
	
	public Observable() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}

	public void setBtnDeleteActivated(boolean btnDeleteActivated) {
		propertyChangeSupport.firePropertyChange("btnDelete",this.btnDeleteActivated,btnDeleteActivated);
		this.btnDeleteActivated=btnDeleteActivated;
	}

	public void setBtnUpdateActivated(boolean btnUpdateActivated) {
		propertyChangeSupport.firePropertyChange("btnUpdate",this.btnUpdateActivated,btnUpdateActivated);
		this.btnUpdateActivated=btnUpdateActivated;
	}
	
	public void setBtnBringToFrontActivated(boolean btnBringToFrontActivated) {
		propertyChangeSupport.firePropertyChange("btnBringToFront",this.btnBringToFrontActivated,btnBringToFrontActivated);
		this.btnBringToFrontActivated = btnBringToFrontActivated;
	}
	
	public void setBtnBringToBackActivated(boolean btnBringToBackActivated) {
		propertyChangeSupport.firePropertyChange("btnBringToBack",this.btnBringToBackActivated,btnBringToBackActivated);
		this.btnBringToBackActivated=btnBringToBackActivated; 
	}
	
	public void setBtnToFrontActivated(boolean btnToFrontActivated) {
		propertyChangeSupport.firePropertyChange("btnToFront",this.btnToFrontActivated,btnToFrontActivated);
		this.btnToFrontActivated=btnToFrontActivated;
	}
	
	public void setBtnToBackActivated(boolean btnToBackActivated) {
		propertyChangeSupport.firePropertyChange("btnToBack",this.btnToBackActivated,btnToBackActivated);
		this.btnToBackActivated=btnToBackActivated;
	}

	public boolean isBtnDeleteActivated() {
		return btnDeleteActivated;
	}

	public boolean isBtnUpdateActivated() {
		return btnUpdateActivated;
	}
	
}