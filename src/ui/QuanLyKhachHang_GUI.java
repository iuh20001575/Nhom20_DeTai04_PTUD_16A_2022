package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.jDialog.JDialogCustom;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import connectDB.ConnectDB;
import dao.DiaChi_DAO;
import dao.KhachHang_DAO;
import entity.DichVu;
import entity.Phuong;
import entity.Quan;
import entity.Tinh;
import entity.KhachHang;
import entity.NhanVien;
import layouts.DefaultLayout;
import utils.StackFrame;
import utils.Utils;

public class QuanLyKhachHang_GUI extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JLabel lblTime;
	private JTextField txtSearch;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private KhachHang_DAO khachHang_DAO;
	private DiaChi_DAO diaChi_DAO;

	private ControlPanel pnlControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyKhachHang_GUI frame = new QuanLyKhachHang_GUI();
					StackFrame.push(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuanLyKhachHang_GUI() {
		JFrame _this = this;

		try {
			new ConnectDB().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		khachHang_DAO = new KhachHang_DAO();
		diaChi_DAO = new DiaChi_DAO();

		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Quản lý khách hàng");
		contentPane = defaultLayout.getJPanel();

//		Search
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(Utils.secondaryColor);
		pnlSearch.setBounds(16, 83, 1054, 24);
		contentPane.add(pnlSearch);
		pnlSearch.setLayout(null);

		JLabel lblSearch = new JLabel("TÌM KIẾM KHÁCH HÀNG THEO TÊN:");
		lblSearch.setBounds(0, -1, 500, 27);
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlSearch.add(lblSearch);

		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTime.setBounds(874, 0, 180, 24);
		pnlSearch.add(lblTime);
		clock();

		JPanel pnlSearchForm = new JPanel();
		pnlSearchForm.setBackground(Utils.secondaryColor);
		pnlSearchForm.setBounds(16, 117, 1054, 36);
		contentPane.add(pnlSearchForm);
		pnlSearchForm.setLayout(null);

		Button btnSearch = new Button("Tìm");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filterKhachHang();
			}
		});
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("Icon\\searching.png"));
		btnSearch.setRadius(4);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setColor(new Color(134, 229, 138));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSearch.setBounds(904, -2, 150, 40);
		btnSearch.setBorderColor(Utils.secondaryColor);
		btnSearch.setColorOver(new Color(134, 229, 138));
		btnSearch.setColorClick(new Color(59, 238, 66));
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlSearchForm.add(btnSearch);

		PanelRound pnlSearchInput = new PanelRound();
		pnlSearchInput.setRounded(4);
		pnlSearchInput.setBackground(Utils.secondaryColor);
		pnlSearchInput.setBounds(0, 0, 894, 36);
		pnlSearchInput.setBorder(new LineBorder(Color.BLACK));
		pnlSearchInput.setRounded(4);
		pnlSearchForm.add(pnlSearchInput);
		pnlSearchInput.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtSearch.setBackground(Utils.secondaryColor);
		txtSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtSearch.setBounds(9, 1, 876, 34);
		pnlSearchInput.add(txtSearch);
		txtSearch.setColumns(10);

//		Actions
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(16, 169, 1054, 36);
		contentPane.add(pnlActions);
		pnlActions.setLayout(null);

		Button btnKhachHangView = new Button("Xem");
		btnKhachHangView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(_this, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui lòng chọn khách hàng muốn xem");
				} else {
					JFrame jFrame = new XemKhachHang_GUI(new KhachHang((String) tbl.getValueAt(row, 0)));
					StackFrame.push(jFrame);
					jFrame.setVisible(true);
					_this.setVisible(false);
				}
			}
		});
		btnKhachHangView.setFocusable(false);
		btnKhachHangView.setIcon(new ImageIcon("Icon\\user 1.png"));
		btnKhachHangView.setBounds(0, 0, 150, 36);
		btnKhachHangView.setRadius(4);
		btnKhachHangView.setForeground(Color.WHITE);
		btnKhachHangView.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnKhachHangView.setColorOver(Utils.primaryColor);
		btnKhachHangView.setColorClick(new Color(161, 184, 186));
		btnKhachHangView.setColor(Utils.primaryColor);
		btnKhachHangView.setBorderColor(Utils.secondaryColor);
		btnKhachHangView.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlActions.add(btnKhachHangView);

		Button btnKhachHangAdd = new Button("Thêm");
		btnKhachHangAdd.setFocusable(false);
		btnKhachHangAdd.setIcon(new ImageIcon("Icon\\add-user (2) 1.png"));
		btnKhachHangAdd.setRadius(4);
		btnKhachHangAdd.setForeground(Color.WHITE);
		btnKhachHangAdd.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnKhachHangAdd.setColorOver(Utils.primaryColor);
		btnKhachHangAdd.setColorClick(new Color(161, 184, 186));
		btnKhachHangAdd.setColor(Utils.primaryColor);
		btnKhachHangAdd.setBorderColor(Utils.secondaryColor);
		btnKhachHangAdd.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnKhachHangAdd.setBounds(165, 0, 150, 36);
		pnlActions.add(btnKhachHangAdd);

		btnKhachHangAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JFrame jFrame = new ThemKhachHang_GUI();
				StackFrame.push(jFrame);
				jFrame.setVisible(true);
				_this.setVisible(false);

			}
		});

		Button btnKhachHangEdit = new Button("Sửa");
		btnKhachHangEdit.setFocusable(false);
		btnKhachHangEdit.setIcon(new ImageIcon("Icon\\edit 2.png"));
		btnKhachHangEdit.setRadius(4);
		btnKhachHangEdit.setForeground(Color.WHITE);
		btnKhachHangEdit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnKhachHangEdit.setColorOver(Utils.primaryColor);
		btnKhachHangEdit.setColorClick(new Color(161, 184, 186));
		btnKhachHangEdit.setColor(Utils.primaryColor);
		btnKhachHangEdit.setBorderColor(Utils.secondaryColor);
		btnKhachHangEdit.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnKhachHangEdit.setBounds(330, 0, 150, 36);
		pnlActions.add(btnKhachHangEdit);

		btnKhachHangEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(_this, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui lòng chọn khách hàng muốn cập nhật");
				} else {
					JFrame jFrame = new CapNhatKhachHang_GUI(new KhachHang((String) tbl.getValueAt(row, 0)));
					StackFrame.push(jFrame);
					jFrame.setVisible(true);
					_this.setVisible(false);
				}
			}
		});

		Button btnKhachHangRemove = new Button("Xóa");
		btnKhachHangRemove.setFocusable(false);
		btnKhachHangRemove.setIcon(new ImageIcon("Icon\\unemployed 1.png"));
		btnKhachHangRemove.setRadius(4);
		btnKhachHangRemove.setForeground(Color.WHITE);
		btnKhachHangRemove.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnKhachHangRemove.setColorOver(Utils.primaryColor);
		btnKhachHangRemove.setColorClick(new Color(161, 184, 186));
		btnKhachHangRemove.setColor(Utils.primaryColor);
		btnKhachHangRemove.setBorderColor(Utils.secondaryColor);
		btnKhachHangRemove.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnKhachHangRemove.setBounds(495, 0, 150, 36);
		pnlActions.add(btnKhachHangRemove);

		btnKhachHangRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(_this, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui lòng chọn khách hàng muốn xóa");
				} else {
					int res = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa khách hàng này",
							"Xóa khách hàng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (res == JOptionPane.OK_OPTION) {
						String maKH = tbl.getValueAt(row, 0).toString();
						khachHang_DAO.xoaKhachHang(maKH);
					}
				}
			}
		});

//		Table danh sách khách hàng
		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(16, 223, 1054, 300);
		scr.setBackground(Utils.primaryColor);
		ScrollBarCustom scp = new ScrollBarCustom();
//		Set color scrollbar thumb
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		contentPane.add(scr);
		tbl = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			/**
			 * Set màu từng dòng cho Table
			 */
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (isRowSelected(row))
					c.setBackground(Utils.getOpacity(Utils.primaryColor, 0.3f));
				else if (row % 2 == 0)
					c.setBackground(Color.WHITE);
				else
					c.setBackground(new Color(232, 232, 232));
				return c;
			}

			@Override
			public boolean getShowVerticalLines() {
				// TODO Auto-generated method stub
				return false;
			}
		};

		tableModel = new DefaultTableModel(
				new String[] { "Mã KH", "Họ tên", "CCCD", "Ngày sinh", "Giới tính", "SĐT", "Địa chỉ" }, 0);

		tbl.setModel(tableModel);
		tbl.setFocusable(false);
//		Cam
		tbl.getTableHeader().setBackground(new Color(255, 195, 174));
//		Xanh
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(61);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(190);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(105);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(130);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(330);
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
//		tbl.setShowGrid(false);
		scr.setViewportView(tbl);

		pnlControl = new ControlPanel(400, 529, this);
		contentPane.add(pnlControl);

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbl.isEnabled()) {
					pnlControl.setTrangHienTai(tbl.getSelectedRow() + 1);
				}
			}
		});

		this.addWindowListener(this);
	}

	public static void clock() {
		Thread clock = new Thread() {
			@Override
			public void run() {
				for (;;) {
					try {
						LocalDateTime currTime = LocalDateTime.now();
						int day = currTime.getDayOfMonth();
						int month = currTime.getMonthValue();
						int year = currTime.getYear();
						int hour = currTime.getHour();
						int minute = currTime.getMinute();
						int second = currTime.getSecond();
						lblTime.setText(String.format("%s/%s/%s | %s:%s:%s", day < 10 ? "0" + day : day,
								month < 10 ? "0" + month : month, year, hour < 10 ? "0" + hour : hour,
								minute < 10 ? "0" + minute : minute, second < 10 ? "0" + second : second));
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		clock.start();
	}

	private void filterKhachHang() {
		String hoTen = txtSearch.getText();
		List<KhachHang> list = khachHang_DAO.filterKhachHang(hoTen);
		setEmptyTable();
		addRow(list);
		pnlControl.setTbl(tbl);

		if (list.size() == 0)
//			System.out.println("Rỗng");
			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng cần tìm");
			
	}
	private void addRow(KhachHang khachHang) {
		Tinh tinh = diaChi_DAO.getTinh(khachHang.getTinh());
		Quan quan = diaChi_DAO.getQuan(tinh, khachHang.getQuan());
		Phuong phuong = diaChi_DAO.getPhuong(quan, khachHang.getPhuong());
		tableModel.addRow(new String[] { khachHang.getMaKhachHang(), khachHang.getHoTen(), khachHang.getCccd(),
				Utils.formatDate(khachHang.getNgaySinh()), khachHang.isGioiTinh() ? "Nam" : "Nữ",
				khachHang.getSoDienThoai(), String.format("%s, %s, %s, %s", tinh.getTinh(), quan.getQuan(),
						phuong.getPhuong(), khachHang.getDiaChiCuThe()), });
	}

	private List<KhachHang> addRow(List<KhachHang> list) {
		list.forEach(khachHang -> addRow(khachHang));
		return list;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		setEmptyTable();
		List<KhachHang> listKH = (List<KhachHang>) khachHang_DAO.getAllKhachHang();
		addRow(listKH);
		pnlControl.setTbl(tbl);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	private void setEmptyTable() {
		while (tbl.getRowCount() > 0)
			tableModel.removeRow(0);
	}
}
