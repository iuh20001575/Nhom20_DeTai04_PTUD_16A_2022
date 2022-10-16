package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.comboBox.ComboBox;
import components.scrollbarCustom.ScrollBarCustom;
import connectDB.ConnectDB;
import dao.DatPhong_DAO;
import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.LoaiPhong;
import entity.Phong;
import utils.Utils;

public class ChuyenPhong_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private LoaiPhong_DAO loaiPhong_DAO;
	private Phong_DAO phong_DAO;
	private List<LoaiPhong> dsLoaiPhong;
	private DatPhong_DAO datPhong_DAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChuyenPhong_GUI frame = new ChuyenPhong_GUI();
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
	public ChuyenPhong_GUI() {
		try {
			new ConnectDB().connect();
		} catch (Exception e) {
			// TODO: handle exception
		}

		loaiPhong_DAO = new LoaiPhong_DAO();
		phong_DAO = new Phong_DAO();
		datPhong_DAO = new DatPhong_DAO();

		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(0, 0, 600, 400);
		contentPane.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(0, 0, 600, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Chuyển phòng");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitle.setBounds(200, 0, 200, 50);
		pnlHeader.add(lblTitle);

		JPanel pnlPhongHienTai = new JPanel();
		pnlPhongHienTai.setBackground(Utils.secondaryColor);
		pnlPhongHienTai.setBounds(0, 50, 600, 50);
		pnlContainer.add(pnlPhongHienTai);
		pnlPhongHienTai.setLayout(null);

		JLabel lnlPhongHienTai = new JLabel("Phòng hiện tại");
		lnlPhongHienTai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lnlPhongHienTai.setBounds(16, 10, 130, 30);
		pnlPhongHienTai.add(lnlPhongHienTai);

		JLabel lblMaPhongHienTai = new JLabel("02.02");
		lblMaPhongHienTai.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblMaPhongHienTai.setBounds(150, 7, 70, 36);
		pnlPhongHienTai.add(lblMaPhongHienTai);

		JLabel lblIconNext = new JLabel("");
		lblIconNext.setIcon(new ImageIcon(
				"D:\\Code\\stt51_haAnhThao_20001575\\Nhom20_DeTai04_PTUD_16A_2022\\Icon\\next_32x32.png"));
		lblIconNext.setBounds(225, 9, 32, 32);
		pnlPhongHienTai.add(lblIconNext);

		JLabel lblmaPhongMoi = new JLabel("");
		lblmaPhongMoi.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblmaPhongMoi.setBounds(269, 7, 70, 36);
		pnlPhongHienTai.add(lblmaPhongMoi);

		JPanel pnlFilter = new JPanel();
		pnlFilter.setBackground(Utils.secondaryColor);
		pnlFilter.setBounds(0, 106, 600, 30);
		pnlContainer.add(pnlFilter);
		pnlFilter.setLayout(null);

		ComboBox<String> cmbMaPhong = new ComboBox<String>();
		cmbMaPhong.setBackground(Utils.primaryColor);
		cmbMaPhong.setModel(new DefaultComboBoxModel<String>(new String[] { "Mã phòng" }));
		cmbMaPhong.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cmbMaPhong.setBounds(16, 0, 127, 30);
		pnlFilter.add(cmbMaPhong);

		ComboBox<String> cmbLoaiPhong = new ComboBox<String>();
		cmbLoaiPhong.setBackground(Utils.primaryColor);
		cmbLoaiPhong.setModel(new DefaultComboBoxModel<String>(new String[] { "Loại phòng" }));
		cmbLoaiPhong.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cmbLoaiPhong.setBounds(163, 0, 127, 30);
		pnlFilter.add(cmbLoaiPhong);

		ComboBox<String> cmbSoKhach = new ComboBox<String>();
		cmbSoKhach.setBackground(Utils.primaryColor);
		cmbSoKhach.setModel(new DefaultComboBoxModel<String>(new String[] { "Số khách" }));
		cmbSoKhach.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cmbSoKhach.setBounds(310, 0, 127, 30);
		pnlFilter.add(cmbSoKhach);

		Button btnLamMoi = new Button("Làm mới");
		btnLamMoi.setBorderColor(Utils.secondaryColor);
		btnLamMoi.setRadius(4);
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setForeground(Color.BLACK);
		btnLamMoi.setColor(Color.WHITE);
		btnLamMoi.setColorOver(Utils.getOpacity(Color.BLACK, 0.2f));
		btnLamMoi.setColorClick(Utils.getOpacity(Color.BLACK, 0.4f));
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLamMoi.setBounds(457, -2, 127, 34);
		pnlFilter.add(btnLamMoi);

		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Utils.secondaryColor);
		pnlFooter.setBounds(0, 354, 600, 30);
		pnlContainer.add(pnlFooter);
		pnlFooter.setLayout(null);

		Button btnQuayLai = new Button("Quay lại");
		btnQuayLai.setBorderColor(Utils.secondaryColor);
		btnQuayLai.setRadius(4);
		btnQuayLai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnQuayLai.setFocusable(false);
		btnQuayLai.setForeground(Color.BLACK);
		btnQuayLai.setColor(Color.WHITE);
		btnQuayLai.setColorOver(Utils.getOpacity(Color.BLACK, 0.2f));
		btnQuayLai.setColorClick(Utils.getOpacity(Color.BLACK, 0.4f));
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuayLai.setBounds(16, -2, 127, 34);
		pnlFooter.add(btnQuayLai);

		Button btnChuyen = new Button("Chuyển");
		btnChuyen.setBorderColor(Utils.secondaryColor);
		btnChuyen.setRadius(4);
		btnChuyen.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChuyen.setFocusable(false);
		btnChuyen.setForeground(Color.WHITE);
		btnChuyen.setColor(Utils.primaryColor);
		btnChuyen.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnChuyen.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnChuyen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnChuyen.setBounds(457, -2, 127, 34);
		pnlFooter.add(btnChuyen);

//		Table danh sách
		JScrollPane scr = new JScrollPane();
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(16, 146, 568, 198);
		scr.setBackground(Utils.primaryColor);
		scr.getViewport().setBackground(Color.WHITE);
		ScrollBarCustom scp = new ScrollBarCustom();
//		Set color scrollbar thumb
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		pnlContainer.add(scr);
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
				new String[] { "Mã phòng", "Loại phòng", "Số người", "Giá tiền/ giờ", "Trạng thái" }, 0);

		tbl.setModel(tableModel);
		tbl.setFocusable(false);
//		Cam
//		tbl.getTableHeader().setBackground(new Color(255, 195, 174));
//		Xanh
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
//		tbl.getColumnModel().getColumn(1).setPreferredWidth(170);
//		tbl.getColumnModel().getColumn(2).setPreferredWidth(150);
//		tbl.getColumnModel().getColumn(3).setPreferredWidth(123);
//		tbl.getColumnModel().getColumn(4).setPreferredWidth(123);
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 24));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(24);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		tbl.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		scr.setViewportView(tbl);

//		Sự kiện window
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				dsLoaiPhong = loaiPhong_DAO.getAllLoaiPhong();
				emptyTable();
				addRow(datPhong_DAO.getPhongDatNgay());
			}
		});

//		Sự kiện JTable
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();

				if (row != -1) {
					String maPhong = (String) tableModel.getValueAt(row, 0);

					lblmaPhongMoi.setText(maPhong);
				}
			}
		});
	}

	private void addRow(List<Phong> list) {
		list.forEach(phong -> addRow(phong));
	}

	private void addRow(Phong phong) {
		LoaiPhong loaiPhong = null;

		for (LoaiPhong lp : dsLoaiPhong) {
			if (lp.equals(phong.getLoaiPhong())) {
				loaiPhong = lp;
				break;
			}
		}

		phong.setLoaiPhong(loaiPhong);

		tableModel.addRow(new String[] { phong.getMaPhong(), loaiPhong.getTenLoai(), phong.getSoLuongKhach() + "",
				Utils.formatTienTe(phong.getGiaTien()), Phong.convertTrangThaiToString(phong.getTrangThai()) });
	}

	private void emptyTable() {
		while (tbl.getRowCount() > 0)
			tableModel.removeRow(0);
	}
}
