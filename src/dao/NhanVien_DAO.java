package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.Phuong;
import entity.Quan;
import entity.TaiKhoan;
import entity.Tinh;
import utils.Utils;

public class NhanVien_DAO {
	private TaiKhoan_DAO taiKhoan_DAO;

	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
		taiKhoan_DAO = new TaiKhoan_DAO();
	}

	/**
	 * Get nhân viên từ resultSet
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private NhanVien getNhanVien(ResultSet resultSet) throws SQLException {
		String maNhanVien = resultSet.getString("maNhanVien");
		String hoTen = resultSet.getString("hoTen");
		String cccd = resultSet.getString("cccd");
		String soDienThoai = resultSet.getString("soDienThoai");
		LocalDate ngaySinh = Utils.convertDateToLocalDate(resultSet.getDate("ngaySinh"));
		boolean gioiTinh = resultSet.getBoolean("gioiTinh");
		String tinh = resultSet.getString("tinh");
		String quan = resultSet.getString("quan");
		String phuong = resultSet.getString("phuong");
		String diaChiCuThe = resultSet.getString("diaChiCuThe");
		String chucVu = resultSet.getString("chucVu");
		double luong = resultSet.getDouble("luong");
		String taiKhoan = resultSet.getString("taiKhoan");
		String trangThai = resultSet.getString("trangThai");

		return new NhanVien(maNhanVien, hoTen, cccd, soDienThoai, ngaySinh, gioiTinh, new Tinh(tinh), new Quan(quan),
				new Phuong(phuong), diaChiCuThe, NhanVien.convertStringToChucVu(chucVu), luong, new TaiKhoan(taiKhoan),
				NhanVien.convertStringToTrangThai(trangThai));
	}

	/**
	 * Get tất cả nhân viên
	 * 
	 * @return danh sách nhân viên
	 */
	public List<NhanVien> getAllNhanVien() {
		List<NhanVien> list = new ArrayList<NhanVien>();

		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(("SELECT * FROM NhanVien"));

			while (resultSet.next())
				list.add(getNhanVien(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Get nhân viên theo họ tên, mã nhân viên và trạng thái làm việc
	 * 
	 * @param hoTen
	 * @param maNhanVien
	 * @param trangThai
	 * @return
	 */
	public List<NhanVien> filterNhanVien(String hoTen, String maNhanVien, String trangThai) {
		List<NhanVien> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"SELECT * FROM NhanVien WHERE hoTen LIKE ? and maNhanVien like ? and trangThai like ?");

			preparedStatement.setString(1, "%" + hoTen + "%");
			preparedStatement.setString(2, "%" + maNhanVien + "%");
			preparedStatement.setString(3, "%" + trangThai + "%");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getNhanVien(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Get nhân viên theo mã nhân viên
	 * 
	 * @param maNhanVien
	 * @return
	 */
	public NhanVien getNhanVienTheoMa(String maNhanVien) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM NhanVien WHERE maNhanVien = ?");
			preparedStatement.setString(1, maNhanVien);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getNhanVien(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Chuyển trạng thái của nhân viên có mã maNhanVien sang nghỉ làm
	 * 
	 * @param maNhanVien
	 * @return
	 */
	public boolean setNghiLam(String maNhanVien) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE NhanVien SET trangThai = N'Nghỉ làm' WHERE maNhanVien = ?");
			preparedStatement.setString(1, maNhanVien);

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Cập nhật thông tin nhân viên
	 * 
	 * @param nhanVien
	 * @return
	 */
	public boolean capNhatNhanVien(NhanVien nhanVien) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE NhanVien SET hoTen = ?, cccd = ?, soDienThoai = ?, ngaySinh = ?, gioiTinh = ?, tinh = ?, quan = ?, phuong = ?, diaChiCuThe = ?, chucVu = ?, luong = ?, trangThai = ? WHERE maNhanVien = ?");
			preparedStatement.setString(1, nhanVien.getHoTen());
			preparedStatement.setString(2, nhanVien.getCccd());
			preparedStatement.setString(3, nhanVien.getSoDienThoai());
			preparedStatement.setDate(4, Utils.convertLocalDateToDate(nhanVien.getNgaySinh()));
			preparedStatement.setBoolean(5, nhanVien.isGioiTinh());
			preparedStatement.setString(6, nhanVien.getTinh().getId());
			preparedStatement.setString(7, nhanVien.getQuan().getId());
			preparedStatement.setString(8, nhanVien.getPhuong().getId());
			preparedStatement.setString(9, nhanVien.getDiaChiCuThe());
			preparedStatement.setString(10, NhanVien.convertChucVuToString(nhanVien.getChucVu()));
			preparedStatement.setDouble(11, nhanVien.getLuong());
			preparedStatement.setString(12, NhanVien.convertTrangThaiToString(nhanVien.getTrangThai()));
			preparedStatement.setString(13, nhanVien.getMaNhanVien());

			return preparedStatement.executeUpdate() > 0 && taiKhoan_DAO.capNhatMatKhau(nhanVien.getTaiKhoan());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Get tất cả nhân viên theo trạng thái làm việc
	 * 
	 * @param trangThai
	 * @return
	 */
	public List<NhanVien> getNhanVienTheoTrangThai(String trangThai) {
		List<NhanVien> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM NhanVien WHERE trangThai = ?");
			preparedStatement.setString(1, trangThai);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getNhanVien(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
