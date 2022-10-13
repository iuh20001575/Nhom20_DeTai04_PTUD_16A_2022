package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import connectDB.ConnectDB;
import entity.KhachHang;
import utils.Utils;

public class KhachHang_DAO {
	private KhachHang getKhachHang(ResultSet resultSet) throws SQLException {
		String maKhachHang = resultSet.getString(1);
		String hoTen = resultSet.getString(2);
		String cccd = resultSet.getString(3);
		LocalDate ngaySinh = Utils.convertDateToLocalDate(resultSet.getDate(4));
		boolean gioiTinh = resultSet.getBoolean(5);
		String soDienThoai = resultSet.getString(6);
		String tinh = resultSet.getString(7);
		String quan = resultSet.getString(8);
		String phuong = resultSet.getString(9);
		String diaChiCuThe = resultSet.getString(10);

		return new KhachHang(maKhachHang, hoTen, cccd, ngaySinh, gioiTinh, soDienThoai, tinh, quan, phuong,
				diaChiCuThe);
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
}
