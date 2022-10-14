package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.Phuong;
import entity.Quan;
import entity.Tinh;
import utils.Utils;

public class KhachHang_DAO {
	private KhachHang getKhachHang(ResultSet resultSet) throws SQLException {
		String maKhachHang = resultSet.getString(1);
		String hoTen = resultSet.getString(2);
		String cccd = resultSet.getString(3);
		LocalDate ngaySinh = Utils.convertDateToLocalDate(resultSet.getDate(4));
		boolean gioiTinh = resultSet.getBoolean(5);
		String soDienThoai = resultSet.getString(6);
		Tinh tinh = new Tinh(resultSet.getString(7));
		Quan quan = new Quan(resultSet.getString(8));
		Phuong phuong = new Phuong(resultSet.getString(9));
		String diaChiCuThe = resultSet.getString(10);

		return new KhachHang(maKhachHang, hoTen, cccd, ngaySinh, gioiTinh, soDienThoai, tinh, quan, phuong,
				diaChiCuThe);
	}

	public List<KhachHang> getAllKhachHang() {
		List<KhachHang> list = new ArrayList<KhachHang>();

		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(("SELECT * FROM KhachHang"));

			while (resultSet.next())
				list.add(getKhachHang(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public KhachHang getKhachHangTheoMa(String maKhachHang) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM KhachHang WHERE maKhachHang = ?");
			preparedStatement.setString(1, maKhachHang);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getKhachHang(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public KhachHang getKhachHang(String soDienThoai) {

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM KhachHang WHERE soDienThoai = ?");
			preparedStatement.setString(1, soDienThoai);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getKhachHang(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean themKhachHang(KhachHang khachHang) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT KhachHang VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, khachHang.getMaKhachHang());
			preparedStatement.setString(2, khachHang.getHoTen());
			preparedStatement.setString(3, khachHang.getCccd());
			preparedStatement.setDate(4, Utils.convertLocalDateToDate(khachHang.getNgaySinh()));
			preparedStatement.setBoolean(5, khachHang.isGioiTinh());
			preparedStatement.setString(6, khachHang.getSoDienThoai());
			preparedStatement.setString(7, khachHang.getTinh().getId());
			preparedStatement.setString(8, khachHang.getQuan().getId());
			preparedStatement.setString(9, khachHang.getPhuong().getId());
			preparedStatement.setString(10, khachHang.getDiaChiCuThe());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean suaKhachHang(KhachHang khachHang) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE KhachHang SET hoTen = ?, cccd = ?, ngaySinh = ?, gioiTinh = ?, soDienThoai = ?, tinh = ?, quan = ?, phuong = ?, diaChiCuThe = ? WHERE maKhachHang = ?");
			preparedStatement.setString(1, khachHang.getHoTen());
			preparedStatement.setString(2, khachHang.getCccd());
			preparedStatement.setDate(3, Utils.convertLocalDateToDate(khachHang.getNgaySinh()));
			preparedStatement.setBoolean(4, khachHang.isGioiTinh());
			preparedStatement.setString(5, khachHang.getSoDienThoai());
			preparedStatement.setString(6, khachHang.getTinh().getId());
			preparedStatement.setString(7, khachHang.getQuan().getId());
			preparedStatement.setString(8, khachHang.getPhuong().getId());
			preparedStatement.setString(9, khachHang.getDiaChiCuThe());
			preparedStatement.setString(10, khachHang.getMaKhachHang());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean xoaKhachHang(String maKhachHang) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("DELETE KhachHang WHERE maKhachHang = ?");
			preparedStatement.setString(1, maKhachHang);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public List<KhachHang> filterKhachHang(String hoTen) {
		List<KhachHang> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM KhachHang WHERE hoTen LIKE ?");

			preparedStatement.setString(1, "%" + hoTen + "%");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getKhachHang(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}