package view;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import controller.DrawingController;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;



public class DrawingFrame extends JFrame{
	
	private JButton btnEdge;
	private JButton btnInterior;
		
	private JToggleButton btnUndo; 
	private JToggleButton btnRedo; 
	
	private JToggleButton btnSelect;
	private JToggleButton btnUpdate;
	private JToggleButton btnDelete;
	
	private JButton btnBringToFront;
	private JButton btnToFront;
	private JButton btnBringToBack;
	private JButton btnToBack;

	private DrawingController controller;
	private DrawingView view = new DrawingView();
	private int state = 0;
	
	private JList<String> activityLog;
	private DefaultListModel <String> dlmList;
	private JScrollPane scrollPane;

	public DrawingFrame() {
		setTitle("Milica Santrac");

		dlmList = new DefaultListModel<String>();
		scrollPane = new JScrollPane();
		
		
		//NORTH
		JPanel north = new JPanel();
		north.setBackground(Color.PINK);
		getContentPane().add(north, BorderLayout.NORTH);
								
		btnEdge = new JButton("Edge color");
		btnEdge.setBackground(Color.BLACK);
		btnEdge.setForeground(Color.WHITE);
		btnEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEdge.setBackground(JColorChooser.showDialog(null, "Choose edge color", Color.GREEN));
				if(btnEdge.getBackground().equals(Color.BLACK)) {
					btnEdge.setForeground(Color.WHITE);
				}
				
			}
		});
		north.add(btnEdge);
		
		btnInterior = new JButton("Interior color");
		btnInterior.setBackground(Color.WHITE);
		btnInterior.setForeground(Color.BLACK);
		btnInterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInterior.setBackground(JColorChooser.showDialog(null, "Choose interior color", Color.pink));
				if(btnInterior.getBackground().equals(Color.WHITE)) {
					btnInterior.setForeground(Color.BLACK);
				}
			}
		});
		north.add(btnInterior);
		
		JRadioButton btnPoint = new JRadioButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(1);
			}
		});
		north.add(btnPoint);
				
		JRadioButton btnLine = new JRadioButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(2);
			}
		});
		north.add(btnLine);
		
		JRadioButton btnCircle = new JRadioButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(3);
			}
		});
		north.add(btnCircle);
		
		JRadioButton btnRectangle = new JRadioButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(4);
			}
		});
		north.add(btnRectangle);
		
		JRadioButton btnDonut = new JRadioButton("Donut");
		btnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(5);
			}
		});
		north.add(btnDonut);
		
		JRadioButton btnHexagon = new JRadioButton("Hexagon");
		btnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(6);
			}
		});
		north.add(btnHexagon);
		
		
		//WEST
		JPanel west = new JPanel();
		west.setBackground(Color.PINK);
		getContentPane().add(west, BorderLayout.WEST);
		
		
		activityLog = new JList<String>();
		activityLog.setEnabled(false);
		activityLog.setModel(dlmList);
		activityLog.setFont(new Font("Lucida Console", Font.BOLD, 12));
		scrollPane.setViewportView(activityLog);

		JButton btnNewDraw = new JButton("New Draw");
		btnNewDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.newDraw();
			}
		});
		west.add(btnNewDraw, "cell 0 0,alignx left,aligny top");
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});
		west.add(btnSave, "cell 0 1");
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.open();
			}
		});
		west.add(btnOpen, "cell 0 2");
		
		JButton btnLog = new JButton("Log");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnLog.getText().equals("Log")) {
					getContentPane().remove(view);
					getContentPane().add(scrollPane, BorderLayout.CENTER);
					btnLog.setText("Draw");
									
					} else if (btnLog.getText().equals("Draw")) {
						getContentPane().remove(scrollPane);
						getContentPane().add(view, BorderLayout.CENTER);
						btnLog.setText("Log");
					}			
					repaint();
			}
		});
		west.add(btnLog, "cell 0 3");
		
		btnUndo = new JToggleButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo(); 
			}
		});
		west.add(btnUndo, "cell 0 8");
		
		btnRedo = new JToggleButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo(); 
			}
		});
		west.add(btnRedo, "cell 0 9");
		
		
		//SOUTH
		JPanel south = new JPanel();
		south.setBackground(Color.PINK);
		getContentPane().add(south, BorderLayout.SOUTH);
			
		btnSelect = new JToggleButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(7);		
			}
		});
		south.add(btnSelect);
		
		btnUpdate = new JToggleButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.updateShapeClicked(); 
			}
		});
		south.add(btnUpdate);
		
		btnDelete = new JToggleButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		south.add(btnDelete);
		
		btnBringToFront = new JButton("BringToFront");
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		btnBringToFront.setEnabled(false);
		south.add(btnBringToFront);
		
		btnToFront = new JButton("ToFront");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront(); 
			}
		});
		btnToFront.setEnabled(false);
		south.add(btnToFront);
		
		btnBringToBack = new JButton("BringToBack");
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			
			}
		});
		btnBringToBack.setEnabled(false);
		south.add(btnBringToBack);
		
		btnToBack = new JButton("ToBack");
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		btnToBack.setEnabled(false);
		south.add(btnToBack);

		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
		view.setBackground(Color.WHITE);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnDonut);
		group.add(btnHexagon);
		group.add(btnCircle);
		group.add(btnPoint);
		group.add(btnLine);
		group.add(btnRectangle);
		
		group.add(btnDelete);
		group.add(btnUpdate);
		group.add(btnSelect);
		
		group.add(btnUndo); 
		group.add(btnRedo); 	

	}
	
	public DefaultListModel<String> getDlmList() {
		return dlmList;
	}
	
	public JToggleButton getBtnUndo() {
		return btnUndo;
	}
	
	public void setBtnUndo(JToggleButton btnUndo) {
		this.btnUndo = btnUndo;
	}
	
	public JToggleButton getBtnRedo() {
		return btnRedo;
	}
	
	public void setBtnRedo(JToggleButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public DrawingController getController() {
		return controller;
	}
	
	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	public DrawingView getView() {
		return view;
	}
	
	public void setView(DrawingView view) {
		this.view = view;
	}
	
	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}
	
	public JToggleButton getBtnSelect() {
		return btnSelect;
	}
	
	public void setBtnSelect(JToggleButton btnSelect) {
		this.btnSelect = btnSelect;
	}

	public JToggleButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(JToggleButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public JToggleButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JToggleButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnEdge() {
		return btnEdge;
	}

	public void setBtnEdge(JButton btnEdge) {
		this.btnEdge = btnEdge;
	}

	public JButton getBtnInterior() {
		return btnInterior;
	}

	public void setBtnInterior(JButton btnInterior) {
		this.btnInterior = btnInterior;
	}
}
