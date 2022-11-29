package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietDatPhong;
import entity.DonDatPhong;
import entity.Phong;

public class PhieuDatPhong_DAO {
	/**
	 * Get chi tiết phiếu đặt phòng theo mã phiếu đặt, trạng thái và số điện thoại
	 * 
	 * @param maPhieuDat
	 * @param soDienThoai
	 * @param trangThai
	 * @return
	 */
	public List<ChiTietDatPhong> filterPhieuDatPhong(String maDatPhong, String soDienThoai, String trangThai) {
		List<ChiTietDatPhong> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"SELECT * FROM  ChiTietDatPhong INNER JOIN DonDatPhong ON ChiTietDatPhong.donDatPhong = DonDatPhong.maDonDatPhong \r\n"
							+ "INNER JOIN KhachHang ON DonDatPhong.khachHang = KhachHang.maKhachHang\r\n"
							+ "WHERE ChiTietDatPhong.donDatPhong LIKE ? and DonDatPhong.trangThai like ? and KhachHang.soDienThoai like ?");

			preparedStatement.setString(1, "%" + maDatPhong + "%");
			preparedStatement.setString(2, "%" + trangThai + "%");
			preparedStatement.setString(3, soDienThoai);

			ResultSet resultSet = preparedStatement.executeQuery();
			ChiTietDatPhong chiTietDatPhong;
			while (resultSet.next()) {
				chiTietDatPhong = getChiTietDatPhong(resultSet);
				list.add(chiTietDatPhong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Get tất cả các chi tiết đặt phòng
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public List<ChiTietDatPhong> getAllChiTietDatPhong() {
		List<ChiTietDatPhong> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM ChiTietDatPhong");

			ResultSet resultSet = preparedStatement.executeQuery();
			ChiTietDatPhong chiTietDatPhong;
			while (resultSet.next()) {
				chiTietDatPhong = getChiTietDatPhong(resultSet);
				list.add(chiTietDatPhong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Get chi tiết đặt phòng resultSet
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private ChiTietDatPhong getChiTietDatPhong(ResultSet resultSet) throws SQLException {
		DonDatPhong donDatPhong = new DonDatPhong(resultSet.getString("donDatPhong"));
		Phong phong = new Phong(resultSet.getString("phong"));
		LocalTime gioVao = resultSet.getTime("gioVao").toLocalTime();
		return new ChiTietDatPhong(donDatPhong, phong, gioVao);
	}

	/**
	 * Get chi tiết đặt phòng của phòng đang chờ
	 * 
	 * @param phong
	 * @return
	 */
	public ChiTietDatPhong getChiTietDatPhongTheoMa(DonDatPhong datPhong) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM ChiTietDatPhong WHERE donDatPhong = ? ");
			preparedStatement.setString(1, datPhong.getMaDonDatPhong());

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getChiTietDatPhong(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
