package dialogs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import shapes.Line;
import java.awt.event.*;


public class DialogLine extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel;
	private JTextField txtxCoordinateStartPoint;
	private JTextField txtyCoordinateStartPoint;
	private JLabel lblxCoordinateStartPoint;
	private JLabel lblyCoordinateStartPoint;
	private boolean confirmed;
	private JButton btnColor;
	private Color color = Color.BLACK;
	private Color lineColor = Color.BLACK;
	private JLabel lblxCoordinateEndPoint;
	private JLabel lblyCoordinateEndPoint;
	private JTextField txtxCoordinateEndPoint;
	private JTextField txtyCoordinateEndPoint;
	private int xCoordinateStartPoint;
	private int yCoordinateStartPoint;
	private int xCoordinateEndPoint;
	private int yCoordinateEndPoint;

	public static void main(String[] arrayOfStrings) {
		try {
			DialogLine dialog = new DialogLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DialogLine() {
		setModal(true);
		setResizable(false);
		setTitle("Line");
		setBounds(100, 100, 325, 373);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		{
			lblxCoordinateStartPoint = new JLabel("X coordinate of start point");
			lblxCoordinateStartPoint.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_lblxCoordinateStartPoint = new GridBagConstraints();
			gbc_lblxCoordinateStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblxCoordinateStartPoint.gridx = 1;
			gbc_lblxCoordinateStartPoint.gridy = 2;
			mainPanel.add(lblxCoordinateStartPoint, gbc_lblxCoordinateStartPoint);
		}
		{
			txtxCoordinateStartPoint = new JTextField();
			lblxCoordinateStartPoint.setLabelFor(txtxCoordinateStartPoint);
			txtxCoordinateStartPoint.setFont(new Font("Tahoma", Font.PLAIN, 11));
			GridBagConstraints gbc_txtxCoordinateStartPoint = new GridBagConstraints();
			gbc_txtxCoordinateStartPoint.gridwidth = 3;
			gbc_txtxCoordinateStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_txtxCoordinateStartPoint.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtxCoordinateStartPoint.gridx = 3;
			gbc_txtxCoordinateStartPoint.gridy = 2;
			mainPanel.add(txtxCoordinateStartPoint, gbc_txtxCoordinateStartPoint);
			txtxCoordinateStartPoint.setColumns(10);
		}
		{
			lblyCoordinateStartPoint = new JLabel("Y coordinate of start point");
			lblyCoordinateStartPoint.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_lblyCoordinateStartPoint = new GridBagConstraints();
			gbc_lblyCoordinateStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblyCoordinateStartPoint.gridx = 1;
			gbc_lblyCoordinateStartPoint.gridy = 4;
			mainPanel.add(lblyCoordinateStartPoint, gbc_lblyCoordinateStartPoint);
		}
		{
			txtyCoordinateStartPoint = new JTextField();
			lblyCoordinateStartPoint.setLabelFor(txtyCoordinateStartPoint);
			txtyCoordinateStartPoint.setFont(new Font("Tahoma", Font.PLAIN, 11));
			GridBagConstraints gbc_txtyCoordinateStartPoint = new GridBagConstraints();
			gbc_txtyCoordinateStartPoint.gridwidth = 3;
			gbc_txtyCoordinateStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_txtyCoordinateStartPoint.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtyCoordinateStartPoint.gridx = 3;
			gbc_txtyCoordinateStartPoint.gridy = 4;
			mainPanel.add(txtyCoordinateStartPoint, gbc_txtyCoordinateStartPoint);
			txtyCoordinateStartPoint.setColumns(10);
		}
		{
			lblxCoordinateEndPoint = new JLabel("X coordinate of end point");
			lblxCoordinateEndPoint.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_lblxCoordinateEndPoint = new GridBagConstraints();
			gbc_lblxCoordinateEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblxCoordinateEndPoint.gridx = 1;
			gbc_lblxCoordinateEndPoint.gridy = 6;
			mainPanel.add(lblxCoordinateEndPoint, gbc_lblxCoordinateEndPoint);
		}
		{
			txtxCoordinateEndPoint = new JTextField();
			lblxCoordinateEndPoint.setLabelFor(txtxCoordinateEndPoint);
			GridBagConstraints gbc_txtxCoordinateEndPoint = new GridBagConstraints();
			gbc_txtxCoordinateEndPoint.gridwidth = 3;
			gbc_txtxCoordinateEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_txtxCoordinateEndPoint.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtxCoordinateEndPoint.gridx = 3;
			gbc_txtxCoordinateEndPoint.gridy = 6;
			mainPanel.add(txtxCoordinateEndPoint, gbc_txtxCoordinateEndPoint);
			txtxCoordinateEndPoint.setColumns(10);
		}
		{
			lblyCoordinateEndPoint = new JLabel("Y coordinate of end point");
			lblyCoordinateEndPoint.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_lblyCoordinateEndPoint = new GridBagConstraints();
			gbc_lblyCoordinateEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblyCoordinateEndPoint.gridx = 1;
			gbc_lblyCoordinateEndPoint.gridy = 8;
			mainPanel.add(lblyCoordinateEndPoint, gbc_lblyCoordinateEndPoint);
		}
		{
			txtyCoordinateEndPoint = new JTextField();
			lblyCoordinateEndPoint.setLabelFor(txtyCoordinateEndPoint);
			GridBagConstraints gbc_txtyCoordinateEndPoint = new GridBagConstraints();
			gbc_txtyCoordinateEndPoint.gridwidth = 3;
			gbc_txtyCoordinateEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_txtyCoordinateEndPoint.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtyCoordinateEndPoint.gridx = 3;
			gbc_txtyCoordinateEndPoint.gridy = 8;
			mainPanel.add(txtyCoordinateEndPoint, gbc_txtyCoordinateEndPoint);
			txtyCoordinateEndPoint.setColumns(10);
		}
		
		btnColor = new JButton("Choose color");
		btnColor.setFont(new Font("Arial", Font.BOLD, 12));
		btnColor.setForeground(Color.WHITE);
		btnColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				color = JColorChooser.showDialog(null, "Colors pallete", lineColor);
				if (color != null) {
					if (color.equals(Color.WHITE)) JOptionPane.showMessageDialog(null, "Background is white");
					else {
						lineColor = color;
						btnColor.setBackground(lineColor);
					}
				}
			}
		});
	
		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor.gridx = 1;
		gbc_btnColor.gridy = 10;
		mainPanel.add(btnColor, gbc_btnColor);
		{
			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.setFont(new Font("Arial", Font.BOLD, 12));
				btnOK.setBackground(Color.LIGHT_GRAY);
				btnOK.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent click) {
						if (txtxCoordinateStartPoint.getText().isEmpty() || txtyCoordinateStartPoint.getText().isEmpty()  || txtxCoordinateEndPoint.getText().isEmpty() || txtyCoordinateEndPoint.getText().isEmpty()) JOptionPane.showMessageDialog(getParent(), "Values cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							try {	
								xCoordinateStartPoint = Integer.parseInt(txtxCoordinateStartPoint.getText());
								yCoordinateStartPoint = Integer.parseInt(txtyCoordinateStartPoint.getText());
								xCoordinateEndPoint = Integer.parseInt(txtxCoordinateEndPoint.getText());
								yCoordinateEndPoint = Integer.parseInt(txtyCoordinateEndPoint.getText());
								if (xCoordinateStartPoint <= 0 || yCoordinateStartPoint <= 0 || xCoordinateEndPoint <= 0 || yCoordinateEndPoint <= 0) JOptionPane.showMessageDialog(null, "X and Y coordinates of start and end point of line must be positive numbers!");
								else {
									confirmed = true;
									setVisible(false);
									dispose();
								}
							} catch (NumberFormatException nfe) {
								JOptionPane.showMessageDialog(getParent(),"X and Y coordinates of start and end point of line must be whole numbers!", "Error", JOptionPane.ERROR_MESSAGE);
							} 
						}  
					}
				});
				btnOK.setActionCommand("OK");
				buttonsPanel.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("Arial", Font.BOLD, 12));
				btnCancel.setForeground(Color.RED);
				btnCancel.setBackground(Color.LIGHT_GRAY);
				btnCancel.addMouseListener(new MouseAdapter() {
                	@Override
        			public void mouseClicked(MouseEvent click) {
                		setVisible(false);
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonsPanel.add(btnCancel);
			}
		}
	}

	public void write(Line line) {
		txtxCoordinateStartPoint.setText(String.valueOf(line.getStartPoint().getX()));
		txtyCoordinateStartPoint.setText(String.valueOf(line.getStartPoint().getY()));
		txtxCoordinateEndPoint.setText(String.valueOf(line.getEndPoint().getX()));
		txtyCoordinateEndPoint.setText(String.valueOf(line.getEndPoint().getY()));
		lineColor = line.getColor();
		btnColor.setBackground(lineColor);
	}

	public boolean isConfirmed() {
		return confirmed;
	}
	
	public Color getColor() {
		return lineColor;
	}

	public int getxCoordinateStartPoint() {
		return xCoordinateStartPoint;
	}
	public int getyCoordinateStartPoint() {
		return yCoordinateStartPoint;
	}

	public int getxCoordinateEndPoint() {
		return xCoordinateEndPoint;
	}

	public int getyCoordinateEndPoint() {
		return yCoordinateEndPoint;
	}
	
	public JTextField getTxtxCoordinateStartPoint() {
		return txtxCoordinateStartPoint;
	}

	public JTextField getTxtyCoordinateStartPoint() {
		return txtyCoordinateStartPoint;
	}

	public JTextField getTxtxCoordinateEndPoint() {
		return txtxCoordinateEndPoint;
	}

	public JTextField getTxtyCoordinateEndPoint() {
		return txtyCoordinateEndPoint;
	}
	
	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}
}