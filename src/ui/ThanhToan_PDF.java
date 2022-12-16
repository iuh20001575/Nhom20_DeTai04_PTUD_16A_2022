package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class ThanhToan_PDF extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblGioNhanPhong;
	private JLabel lblTenKhach;
	private JLabel lblTenNhanVien;
	private JLabel lblTienDichVu;
	private JLabel lblTienPhong;
	private JLabel lblTienThanhToan;
	private JLabel lblTongThoiLuong;
	private JPanel pnlContent;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private JPanel pnlContainer;
	private int heightTable;

	/**
	 * Create the frame.
	 *
	 * @param quanLyDatPhongGUI
	 */
	public ThanhToan_PDF(String maDatPhong, String maNhanVien, String tenKhach, String gioNhanPhong, String soDienThoai,
			String tongThoiLuong, TableModel jTable, String tienDV, String tienPhong, String tienThanhToan) {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("Thanh toán");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		pnlContainer = new JPanel();
		pnlContainer.setBackground(Color.WHITE);
		pnlContent.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Color.WHITE);
		pnlHeader.setBounds(0, 0, 616, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("KARAOKE ROME");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitle.setBounds(40, 0, 536, 50);
		pnlHeader.add(lblTitle);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(Color.WHITE);
		pnlContainer.add(pnlBody);
		pnlBody.setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Color.WHITE);
		pnlRow1.setBounds(0, 0, 596, 24);
		pnlBody.add(pnlRow1);
		pnlRow1.setLayout(null);

		JPanel pnlMaDatPhong = new JPanel();
		pnlMaDatPhong.setBackground(Color.WHITE);
		pnlMaDatPhong.setBounds(0, 0, 288, 24);
		pnlRow1.add(pnlMaDatPhong);
		pnlMaDatPhong.setLayout(null);

		JLabel lblLabelMaDatPhong = new JLabel("Mã đặt phòng");
		lblLabelMaDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelMaDatPhong.setBounds(0, 0, 105, 24);
		pnlMaDatPhong.add(lblLabelMaDatPhong);

		JLabel lblMaPhongValue = new JLabel(maDatPhong);
		lblMaPhongValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMaPhongValue.setBounds(105, 0, 183, 24);
		pnlMaDatPhong.add(lblMaPhongValue);

		JPanel pnlTenNhanVien = new JPanel();
		pnlTenNhanVien.setBackground(Color.WHITE);
		pnlTenNhanVien.setLayout(null);
		pnlTenNhanVien.setBounds(308, 0, 288, 24);
		pnlRow1.add(pnlTenNhanVien);

		JLabel lblLabelTenNhanVien = new JLabel("Tên nhân viên");
		lblLabelTenNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelTenNhanVien.setBounds(0, 0, 120, 24);
		pnlTenNhanVien.add(lblLabelTenNhanVien);

		lblTenNhanVien = new JLabel(maNhanVien);
		lblTenNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTenNhanVien.setBounds(125, 0, 162, 24);
		pnlTenNhanVien.add(lblTenNhanVien);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Color.WHITE);
		pnlRow2.setLayout(null);
		pnlRow2.setBounds(0, 32, 596, 24);
		pnlBody.add(pnlRow2);

		JPanel pnlTenKhach = new JPanel();
		pnlTenKhach.setBackground(Color.WHITE);
		pnlTenKhach.setLayout(null);
		pnlTenKhach.setBounds(0, 0, 288, 24);
		pnlRow2.add(pnlTenKhach);

		JLabel lblLabelTenKhach = new JLabel("Tên khách");
		lblLabelTenKhach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelTenKhach.setBounds(0, 0, 105, 24);
		pnlTenKhach.add(lblLabelTenKhach);

		lblTenKhach = new JLabel(tenKhach);
		lblTenKhach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTenKhach.setBounds(105, 0, 183, 24);
		pnlTenKhach.add(lblTenKhach);

		JPanel pnlGioNhanPhong = new JPanel();
		pnlGioNhanPhong.setBackground(Color.WHITE);
		pnlGioNhanPhong.setLayout(null);
		pnlGioNhanPhong.setBounds(308, 0, 288, 24);
		pnlRow2.add(pnlGioNhanPhong);

		JLabel lbllabelGioNhanPhong = new JLabel("Giờ nhận phòng");
		lbllabelGioNhanPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lbllabelGioNhanPhong.setBounds(0, 0, 120, 24);
		pnlGioNhanPhong.add(lbllabelGioNhanPhong);

		lblGioNhanPhong = new JLabel(gioNhanPhong);
		lblGioNhanPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGioNhanPhong.setBounds(125, 0, 162, 24);
		pnlGioNhanPhong.add(lblGioNhanPhong);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Color.WHITE);
		pnlRow3.setLayout(null);
		pnlRow3.setBounds(0, 64, 596, 24);
		pnlBody.add(pnlRow3);

		JPanel pnlSoDienThoai = new JPanel();
		pnlSoDienThoai.setBackground(Color.WHITE);
		pnlSoDienThoai.setLayout(null);
		pnlSoDienThoai.setBounds(0, 0, 288, 24);
		pnlRow3.add(pnlSoDienThoai);

		JLabel lblLabelSoDienThoai = new JLabel("SĐT khách");
		lblLabelSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelSoDienThoai.setBounds(0, 0, 105, 24);
		pnlSoDienThoai.add(lblLabelSoDienThoai);

		JLabel lblSoDienThoaiValue = new JLabel(soDienThoai);
		lblSoDienThoaiValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSoDienThoaiValue.setBounds(105, 0, 183, 24);
		pnlSoDienThoai.add(lblSoDienThoaiValue);

		JPanel pnlTongThoiLuong = new JPanel();
		pnlTongThoiLuong.setBackground(Color.WHITE);
		pnlTongThoiLuong.setLayout(null);
		pnlTongThoiLuong.setBounds(308, 0, 288, 24);
		pnlRow3.add(pnlTongThoiLuong);

		JLabel lblLabelTongThoiLuong = new JLabel("Tổng thời lượng");
		lblLabelTongThoiLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelTongThoiLuong.setBounds(0, 0, 120, 24);
		pnlTongThoiLuong.add(lblLabelTongThoiLuong);

		lblTongThoiLuong = new JLabel(tongThoiLuong);
		lblTongThoiLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongThoiLuong.setBounds(125, 0, 162, 24);
		pnlTongThoiLuong.add(lblTongThoiLuong);

		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Color.WHITE);
		pnlBody.add(pnlFooter);
		pnlFooter.setLayout(null);

		JPanel pnlFooterRow1 = new JPanel();
		pnlFooterRow1.setLayout(null);
		pnlFooterRow1.setBackground(Color.WHITE);
		pnlFooterRow1.setBounds(0, 0, 596, 24);
		pnlFooter.add(pnlFooterRow1);

		JPanel pnlTienDichVu = new JPanel();
		pnlTienDichVu.setLayout(null);
		pnlTienDichVu.setBackground(Color.WHITE);
		pnlTienDichVu.setBounds(0, 0, 288, 24);
		pnlFooterRow1.add(pnlTienDichVu);

		JLabel lblLabelTienDichVu = new JLabel("Tiền dịch vụ");
		lblLabelTienDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelTienDichVu.setBounds(0, 0, 105, 24);
		pnlTienDichVu.add(lblLabelTienDichVu);

		lblTienDichVu = new JLabel(tienDV);
		lblTienDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTienDichVu.setBounds(105, 0, 197, 24);
		pnlTienDichVu.add(lblTienDichVu);

		JPanel pnlTienPhong = new JPanel();
		pnlTienPhong.setLayout(null);
		pnlTienPhong.setBackground(Color.WHITE);
		pnlTienPhong.setBounds(308, 0, 288, 24);
		pnlFooterRow1.add(pnlTienPhong);

		JLabel lblLabelTienPhong = new JLabel("Tiền phòng");
		lblLabelTienPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelTienPhong.setBounds(0, 0, 115, 24);
		pnlTienPhong.add(lblLabelTienPhong);

		lblTienPhong = new JLabel(tienPhong);
		lblTienPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTienPhong.setBounds(125, 0, 162, 24);
		pnlTienPhong.add(lblTienPhong);

		JPanel pnlFooterRow2 = new JPanel();
		pnlFooterRow2.setLayout(null);
		pnlFooterRow2.setBackground(Color.WHITE);
		pnlFooterRow2.setBounds(0, 32, 596, 24);
		pnlFooter.add(pnlFooterRow2);

		JPanel pnlThueVAT = new JPanel();
		pnlThueVAT.setLayout(null);
		pnlThueVAT.setBackground(Color.WHITE);
		pnlThueVAT.setBounds(0, 0, 288, 24);
		pnlFooterRow2.add(pnlThueVAT);

		JLabel lblLabelThueVAT = new JLabel("Thuế VAT");
		lblLabelThueVAT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelThueVAT.setBounds(0, 0, 105, 24);
		pnlThueVAT.add(lblLabelThueVAT);

		JLabel lblThueVAT = new JLabel("10%");
		lblThueVAT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblThueVAT.setBounds(105, 0, 197, 24);
		pnlThueVAT.add(lblThueVAT);

		JPanel pnlTienThanhToan = new JPanel();
		pnlTienThanhToan.setLayout(null);
		pnlTienThanhToan.setBackground(Color.WHITE);
		pnlTienThanhToan.setBounds(308, 0, 288, 24);
		pnlFooterRow2.add(pnlTienThanhToan);

		JLabel lblLabelTienThanhToan = new JLabel("Tiền thanh toán");
		lblLabelTienThanhToan.setForeground(Color.BLACK);
		lblLabelTienThanhToan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelTienThanhToan.setBounds(0, 0, 115, 24);
		pnlTienThanhToan.add(lblLabelTienThanhToan);

		lblTienThanhToan = new JLabel(tienThanhToan);
		lblTienThanhToan.setForeground(Color.BLACK);
		lblTienThanhToan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTienThanhToan.setBounds(125, 0, 162, 24);
		pnlTienThanhToan.add(lblTienThanhToan);

		JPanel pnlFooterRow3 = new JPanel();
		pnlFooterRow3.setLayout(null);
		pnlFooterRow3.setBackground(Color.WHITE);
		pnlFooterRow3.setBounds(0, 64, 596, 24);
		pnlFooter.add(pnlFooterRow3);

		JPanel pnlThueVAT_1 = new JPanel();
		pnlThueVAT_1.setLayout(null);
		pnlThueVAT_1.setBackground(Color.WHITE);
		pnlThueVAT_1.setBounds(0, 0, 288, 24);
		pnlFooterRow3.add(pnlThueVAT_1);

		JLabel lblTinNhn = new JLabel("Tiền nhận");
		lblTinNhn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTinNhn.setBounds(0, 0, 105, 24);
		pnlThueVAT_1.add(lblTinNhn);

		JLabel lblThueVAT_1 = new JLabel("10%");
		lblThueVAT_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblThueVAT_1.setBounds(105, 0, 197, 24);
		pnlThueVAT_1.add(lblThueVAT_1);

		JPanel pnlTienThanhToan_1 = new JPanel();
		pnlTienThanhToan_1.setLayout(null);
		pnlTienThanhToan_1.setBackground(Color.WHITE);
		pnlTienThanhToan_1.setBounds(308, 0, 288, 24);
		pnlFooterRow3.add(pnlTienThanhToan_1);

		JLabel lblTinTha = new JLabel("Tiền thừa");
		lblTinTha.setForeground(Color.BLACK);
		lblTinTha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTinTha.setBounds(0, 0, 115, 24);
		pnlTienThanhToan_1.add(lblTinTha);

		JLabel lblTienThanhToan_1 = new JLabel("");
		lblTienThanhToan_1.setForeground(Color.BLACK);
		lblTienThanhToan_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTienThanhToan_1.setBounds(125, 0, 162, 24);
		pnlTienThanhToan_1.add(lblTienThanhToan_1);

		tbl = new JTable() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean getShowVerticalLines() {
				return false;
			}
		};

		tableModel = new DefaultTableModel(
				new String[] { "STT", "Tên dịch vụ", "Số lượng/Thời gian", "Đơn giá", "Thành tiền" }, 0);
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();

		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Color.white);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblHeader.setPreferredSize(new Dimension(596, 24));
		tblHeader.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(24);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tableColumnModel.getColumn(0).setCellRenderer(dtcr);
		tableColumnModel.getColumn(2).setCellRenderer(dtcr);
		tableColumnModel.getColumn(3).setCellRenderer(dtcr);
		tableColumnModel.getColumn(4).setCellRenderer(dtcr);

		int rowCount = jTable.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			tableModel.addRow(new String[] { jTable.getValueAt(i, 0).toString(), jTable.getValueAt(i, 1).toString(),
					jTable.getValueAt(i, 2).toString(), jTable.getValueAt(i, 3).toString(),
					jTable.getValueAt(i, 4).toString() });
		}

		heightTable = 24 * (rowCount + 1) + 50;
		tbl.setBounds(0, 98, 596, heightTable);
		pnlFooter.setBounds(0, 118 + heightTable, 596, 88);
		pnlBody.setBounds(10, 65, 596, 291 + heightTable);
		pnlContainer.setBounds(0, 0, 616, 291 + heightTable);
		setBounds(0, 0, 616, 291 + heightTable);
		pnlBody.add(tbl);
	}
	
	public int getHeightPanel() {
		return 291 + heightTable;
	}

	public JPanel getPanel() {
		return pnlContainer;
	}
}