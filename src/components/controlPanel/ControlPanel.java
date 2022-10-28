package components.controlPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.jDialog.JDialogCustom;
import utils.Utils;

public class ControlPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button btnFirst;
	private Button btnLast;
	private Button btnNext;
	private Button btnPrev;
	private JLabel lblSoTrang;
	private int soTrang;
	private JTable tbl;
	private int trangHienTai;
	private JTextField txtTrangHienThai;

	public ControlPanel(int x, int y, JFrame jFrame) {
		setBounds(x, y, 286, 34);
		setBackground(Utils.secondaryColor);
		setLayout(null);

		btnFirst = new Button("");
		btnFirst.setFocusable(false);
		btnFirst.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnFirst.setRadius(4);
		btnFirst.setBorderColor(Utils.secondaryColor);
		btnFirst.setColor(Color.WHITE);
		btnFirst.setColorOver(Utils.getOpacity(Color.BLACK, 0.1f));
		btnFirst.setColorClick(Utils.getOpacity(Color.BLACK, 0.2f));
		btnFirst.setIcon(new ImageIcon("Icon\\first.png"));
		btnFirst.setBounds(0, 0, 34, 34);
		add(btnFirst);

		btnPrev = new Button("");
		btnPrev.setFocusable(false);
		btnPrev.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnPrev.setRadius(4);
		btnPrev.setBorderColor(Utils.secondaryColor);
		btnPrev.setColor(Color.WHITE);
		btnPrev.setColorOver(Utils.getOpacity(Color.BLACK, 0.1f));
		btnPrev.setColorClick(Utils.getOpacity(Color.BLACK, 0.2f));
		btnPrev.setIcon(new ImageIcon("Icon\\prev.png"));
		btnPrev.setBounds(54, 0, 34, 34);
		add(btnPrev);

		btnNext = new Button("");
		btnNext.setFocusable(false);
		btnNext.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNext.setRadius(4);
		btnNext.setBorderColor(Utils.secondaryColor);
		btnNext.setColor(Color.WHITE);
		btnNext.setColorOver(Utils.getOpacity(Color.BLACK, 0.1f));
		btnNext.setColorClick(Utils.getOpacity(Color.BLACK, 0.2f));
		btnNext.setIcon(new ImageIcon("Icon\\next.png"));
		btnNext.setBounds(198, 0, 34, 34);
		add(btnNext);

		btnLast = new Button("");
		btnLast.setFocusable(false);
		btnLast.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLast.setRadius(4);
		btnLast.setBorderColor(Utils.secondaryColor);
		btnLast.setColor(Color.WHITE);
		btnLast.setColorOver(Utils.getOpacity(Color.BLACK, 0.1f));
		btnLast.setColorClick(Utils.getOpacity(Color.BLACK, 0.2f));
		btnLast.setIcon(new ImageIcon("Icon\\last.png"));
		btnLast.setBounds(252, 0, 34, 34);
		add(btnLast);

		JPanel pnlLabel = new JPanel();
		pnlLabel.setBackground(Utils.secondaryColor);
		pnlLabel.setBounds(108, 0, 70, 34);
		add(pnlLabel);
		pnlLabel.setLayout(null);

		JLabel lbl = new JLabel("/");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lbl.setBounds(30, 0, 10, 34);
		pnlLabel.add(lbl);

		lblSoTrang = new JLabel("0");
		lblSoTrang.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSoTrang.setBounds(40, 0, 30, 34);
		pnlLabel.add(lblSoTrang);

		JDialogCustom jDialog = new JDialogCustom(jFrame, JDialogCustom.Type.warning);

		txtTrangHienThai = new JTextField();
		txtTrangHienThai.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtTrangHienThai.setText(trangHienTai + "");
			}
		});
		txtTrangHienThai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					txtTrangHienThai.setEditable(true);
				} else {
					txtTrangHienThai.setEditable(false);
				}
			}
		});
		txtTrangHienThai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String trangHienTai = txtTrangHienThai.getText();
				int trangHienTaiInt = Integer.parseInt(trangHienTai);
				if (trangHienTaiInt > 0 && trangHienTaiInt <= soTrang)
					setTrangHienTai(trangHienTaiInt);
				else {
					if (trangHienTaiInt > soTrang) {
						jDialog.showMessage("Lỗi", "Số trang phải bé hơn " + soTrang);
					} else if (trangHienTaiInt < 1) {
						jDialog.showMessage("Lỗi", "Số trang phải lớn hơn 0");
					}
					txtTrangHienThai.selectAll();
					txtTrangHienThai.setEditable(true);
					txtTrangHienThai.setFocusable(true);
				}
			}
		});
		txtTrangHienThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtTrangHienThai.setBounds(0, 0, 30, 34);
		pnlLabel.add(txtTrangHienThai);
		txtTrangHienThai.setColumns(10);

		btnFirst.addActionListener(this);
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		btnLast.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnFirst)) {
			this.setTrangHienTai(1);
		} else if (o.equals(btnPrev))
			this.setTrangHienTai(trangHienTai - 1);
		else if (o.equals(btnNext))
			this.setTrangHienTai(trangHienTai + 1);
		else if (o.equals(btnLast))
			this.setTrangHienTai(soTrang);
	}

	public JTable getTbl() {
		return tbl;
	}

	public void setTbl(JTable tbl) {
		this.tbl = tbl;
		soTrang = tbl.getRowCount();
		lblSoTrang.setText(soTrang + "");
		setTrangHienTai(tbl.getSelectedRow() + 1);
	}

	public void setTrangHienTai(int trangHienTai) {
		soTrang = tbl.getRowCount();
		if (trangHienTai == 0 && soTrang > 0)
			trangHienTai = 1;
		if (soTrang > 0) {
			tbl.setRowSelectionInterval(trangHienTai - 1, trangHienTai - 1);
			tbl.scrollRectToVisible(tbl.getCellRect(trangHienTai-1, 0, true));
		}
		this.trangHienTai = trangHienTai;
		txtTrangHienThai.setText(trangHienTai + "");
		xuLiBtnPhanTrang();
	}

	private void xuLiBtnPhanTrang() {
		if (soTrang <= 1) {
			btnFirst.setEnabled(false);
			btnPrev.setEnabled(false);
			btnNext.setEnabled(false);
			btnLast.setEnabled(false);
		} else if (trangHienTai == 1) {
			btnFirst.setEnabled(false);
			btnPrev.setEnabled(false);
			btnNext.setEnabled(true);
			btnLast.setEnabled(true);
		} else if (trangHienTai == soTrang) {
			btnFirst.setEnabled(true);
			btnPrev.setEnabled(true);
			btnNext.setEnabled(false);
			btnLast.setEnabled(false);
		} else {
			btnFirst.setEnabled(true);
			btnPrev.setEnabled(true);
			btnNext.setEnabled(true);
			btnLast.setEnabled(true);
		}
	}
}
