package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DialogDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public boolean confirmation;
	
	public static void main(String[] args) {
		try {
			DialogDelete dialog = new DialogDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DialogDelete() {
		setTitle("Warning!");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{ 
			JLabel lbl = new JLabel("Are you sure that you want to delete selected shape?");
			lbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lbl, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnYes = new JButton("Yes");
				btnYes.setBackground(Color.LIGHT_GRAY);
				btnYes.setFont(new Font("Arial", Font.BOLD, 12));
				btnYes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						confirmation = true;
						setVisible(false);
					}
				});
				btnYes.setActionCommand("Yes");
				buttonPane.add(btnYes);
				getRootPane().setDefaultButton(btnYes);
			}
			{
				JButton btnNo = new JButton("No");
				btnNo.setBackground(Color.LIGHT_GRAY);
				btnNo.setForeground(Color.RED);
				btnNo.setFont(new Font("Arial", Font.BOLD, 12));
				btnNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnNo.setActionCommand("No");
				buttonPane.add(btnNo);
			}
		}
	}

}
