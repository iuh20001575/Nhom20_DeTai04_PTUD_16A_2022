package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDateTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import layouts.DefaultLayout;

public class QuanLyNhanVien_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JLabel lblTime;
	private JTextField txtSearch;
	private JTable tbl;
//	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNhanVien_GUI frame = new QuanLyNhanVien_GUI();
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
	public QuanLyNhanVien_GUI() {
		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Quản lý nhân viên");
		contentPane = defaultLayout.getJPanel();

//		setTitle("Quản lý nhân viên");
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(0, 0, 1100, 610);
//		setLocationRelativeTo(null);
//
//		contentPane = new JPanel();
//		contentPane.setForeground(Color.GRAY);
//		contentPane.setBackground(Utils.secondaryColor);
//		setContentPane(contentPane);
//		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
//		contentPane.setLayout(null);
//
//		JPanel pnlHeader = new JPanel();
//		pnlHeader.setBackground(Utils.primaryColor);
//		pnlHeader.setBounds(0, 0, 1086, 65);
//		contentPane.add(pnlHeader);
//		pnlHeader.setLayout(null);
//
//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.setBounds(-20, -20, 0, 0);
//		contentPane.add(btnNewButton);
//
//		Button btnMenu = new Button("|||");
//		btnMenu.setBounds(23, 16, 38, 38);
//		btnMenu.setForeground(Utils.primaryColor);
//		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
//		btnMenu.setBorder(BorderFactory.createEmptyBorder());
//		btnMenu.setBackground(Color.WHITE);
//		btnMenu.setBorderColor(Utils.primaryColor);
//		btnMenu.setRadius(8);
//		btnMenu.setFocusable(false);
//		pnlHeader.add(btnMenu);
//
//		JLabel lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN");
//		lblTitle.setForeground(Color.WHITE);
//		lblTitle.setBounds(76, 17, 948, 32);
//		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
//		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
//		pnlHeader.add(lblTitle);
//		End default layout

		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(Utils.secondaryColor);
		pnlSearch.setBounds(16, 83, 1054, 24);
		contentPane.add(pnlSearch);
		pnlSearch.setLayout(null);

		JLabel lblSearch = new JLabel("TÌM KIẾM NHÂN VIÊN THEO TÊN:");
		lblSearch.setBounds(0, -1, 299, 27);
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
		pnlSearchInput.setRound(4);
		pnlSearchForm.add(pnlSearchInput);
		pnlSearchInput.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setBackground(Utils.secondaryColor);
		txtSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtSearch.setBounds(9, 1, 876, 34);
		pnlSearchInput.add(txtSearch);
		txtSearch.setColumns(10);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(16, 169, 1054, 36);
		contentPane.add(pnlActions);
		pnlActions.setLayout(null);

		Button btnEmployeeSearch = new Button("Xem");
		btnEmployeeSearch.setFocusable(false);
		btnEmployeeSearch.setIcon(new ImageIcon("Icon\\user 1.png"));
		btnEmployeeSearch.setBounds(0, 0, 150, 36);
		btnEmployeeSearch.setRadius(4);
		btnEmployeeSearch.setForeground(Color.WHITE);
		btnEmployeeSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeSearch.setColorOver(Utils.primaryColor);
		btnEmployeeSearch.setColorClick(new Color(161, 184, 186));
		btnEmployeeSearch.setColor(Utils.primaryColor);
		btnEmployeeSearch.setBorderColor(Utils.secondaryColor);
		btnEmployeeSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlActions.add(btnEmployeeSearch);

		Button btnEmployeeAdd = new Button("Thêm");
		btnEmployeeAdd.setFocusable(false);
		btnEmployeeAdd.setIcon(new ImageIcon("Icon\\add-user (2) 1.png"));
		btnEmployeeAdd.setRadius(4);
		btnEmployeeAdd.setForeground(Color.WHITE);
		btnEmployeeAdd.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeAdd.setColorOver(Utils.primaryColor);
		btnEmployeeAdd.setColorClick(new Color(161, 184, 186));
		btnEmployeeAdd.setColor(Utils.primaryColor);
		btnEmployeeAdd.setBorderColor(Utils.secondaryColor);
		btnEmployeeAdd.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEmployeeAdd.setBounds(165, 0, 150, 36);
		pnlActions.add(btnEmployeeAdd);

		Button btnEmployeeEdit = new Button("Sửa");
		btnEmployeeEdit.setFocusable(false);
		btnEmployeeEdit.setIcon(new ImageIcon("Icon\\edit 2.png"));
		btnEmployeeEdit.setRadius(4);
		btnEmployeeEdit.setForeground(Color.WHITE);
		btnEmployeeEdit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeEdit.setColorOver(Utils.primaryColor);
		btnEmployeeEdit.setColorClick(new Color(161, 184, 186));
		btnEmployeeEdit.setColor(Utils.primaryColor);
		btnEmployeeEdit.setBorderColor(Utils.secondaryColor);
		btnEmployeeEdit.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEmployeeEdit.setBounds(330, 0, 150, 36);
		pnlActions.add(btnEmployeeEdit);

		Button btnEmployeeRemove = new Button("Nghỉ việc");
		btnEmployeeRemove.setFocusable(false);
		btnEmployeeRemove.setIcon(new ImageIcon("Icon\\unemployed 1.png"));
		btnEmployeeRemove.setRadius(4);
		btnEmployeeRemove.setForeground(Color.WHITE);
		btnEmployeeRemove.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeRemove.setColorOver(Utils.primaryColor);
		btnEmployeeRemove.setColorClick(new Color(161, 184, 186));
		btnEmployeeRemove.setColor(Utils.primaryColor);
		btnEmployeeRemove.setBorderColor(Utils.secondaryColor);
		btnEmployeeRemove.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEmployeeRemove.setBounds(495, 0, 150, 36);
		pnlActions.add(btnEmployeeRemove);

		JComboBox<String> cboTrangThai = new JComboBox<String>();
		cboTrangThai.setModel(new DefaultComboBoxModel<String>(new String[] { "Trạng thái" }));
		cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboTrangThai.setBackground(Utils.primaryColor);
		cboTrangThai.setBounds(904, 0, 150, 36);
		pnlActions.add(cboTrangThai);

		JComboBox<String> cboMaNhanVien = new JComboBox<String>();
		cboMaNhanVien.setModel(new DefaultComboBoxModel<String>(new String[] { "Mã NV" }));
		cboMaNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboMaNhanVien.setBackground(Utils.primaryColor);
		cboMaNhanVien.setBounds(739, 0, 150, 36);
		cboMaNhanVien.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlActions.add(cboMaNhanVien);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(16, 223, 1054, 340);
		scr.setBackground(Utils.primaryColor);
		ScrollBarCustom scp = new ScrollBarCustom();
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
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (row % 2 == 0)
					c.setBackground(Color.WHITE);
				else
					c.setBackground(new Color(232, 232, 232));
				return c;
			}
		};
		tbl.setModel(new DefaultTableModel(new Object[][] {
				{ "NV001", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" },
				{ "NV001", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" },
				{ "NV001", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" },
				{ "NV001", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" },
				{ "NV001", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" },
				{ "NV001", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" },
				{ "NV001", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" },
				{ "NV001", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" },
				{ "NV001", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" },
				{ "NV002", "Nguy\u1EC5n Th\u1ECB Ng\u1ECDc Tuy\u1EBFt", "052202003245", "0123456789", "22/12/2000",
						"N\u1EEF", "Th\u00E0hh Ph\u1ED1 H\u1ED3 Ch\u00ED Minh Quan Go Vap", "\u0110ang l\u00E0m" } },
				new String[] { "M\u00E3 NV", "H\u1ECD T\u00EAn", "CCCD", "S\u0110T", "Ng\u00E0y sinh",
						"Gi\u1EDBi t\u00EDnh", "\u0110\u1ECBa ch\u1EC9", "Tr\u1EA1ng th\u00E1i" }));
//		Cam
		tbl.getTableHeader().setBackground(new Color(255, 195, 174));
//		Xanh
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Utils.secondaryColor);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(61);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(180);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(105);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(90);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(288);
		tbl.getColumnModel().getColumn(7).setPreferredWidth(100);
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);

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
}
