package components.controlPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import components.jDialog.JDialogCustom;
import utils.Utils;

public class ControlPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTrangHienThai;
	private int soTrang;
	private int trangHienTai;
	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;
	private JTable tbl;
	private JLabel lblSoTrang;

	public ControlPanel(int x, int y, JFrame jFrame) {
		setBounds(x, y, 286, 34);
		setBackground(Utils.secondaryColor);
		setLayout(null);

		btnFirst = new JButton("");
		btnFirst.setIcon(new ImageIcon("Icon\\first.png"));
		btnFirst.setBounds(0, 0, 34, 34);
		add(btnFirst);

		btnPrev = new JButton("");
		btnPrev.setIcon(new ImageIcon("Icon\\prev.png"));
		btnPrev.setBounds(54, 0, 34, 34);
		add(btnPrev);

		btnNext = new JButton("");
		btnNext.setIcon(new ImageIcon("Icon\\next.png"));
		btnNext.setBounds(198, 0, 34, 34);
		add(btnNext);

		btnLast = new JButton("");
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

		lblSoTrang = new JLabel("55");
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
				System.out.println(trangHienTai);
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
		if (trangHienTai == 0 && soTrang > 0)
			trangHienTai = 1;
		if (soTrang > 0)
			tbl.setRowSelectionInterval(trangHienTai - 1, trangHienTai - 1);
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
}
