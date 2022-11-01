package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietDatPhong;
import entity.DatPhong;
import entity.Phong;

public class PhieuDatPhong_DAO {
	/**
	 * Get chi tiết đặt phòng resultSet
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private ChiTietDatPhong getChiTietDatPhong(ResultSet resultSet) throws SQLException {
		DatPhong datPhong = new DatPhong(resultSet.getString("datPhong"));
		return new ChiTietDatPhong(datPhong);
	}

	/**
	 * Get chi tiết đặt phòng của phòng đang chờ
	 * 
	 * @param phong
	 * @return
	 */
	public ChiTietDatPhong getChiTietDatPhongTheoMa(DatPhong datPhong) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM ChiTietDatPhong WHERE datPhong = ? ");
			preparedStatement.setString(1, datPhong.getMaDatPhong());

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String maDatPhong = resultSet.getString(1);
				String maPhong = resultSet.getString(2);
				return new ChiTietDatPhong(new DatPhong(maDatPhong), new Phong(maPhong));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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

			while (resultSet.next())
				list.add(getChiTietDatPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

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
					"SELECT * FROM  ChiTietDatPhong INNER JOIN DatPhong ON ChiTietDatPhong.datPhong = DatPhong.maDatPhong \r\n"
							+ "INNER JOIN KhachHang ON DatPhong.khachHang = KhachHang.maKhachHang\r\n"
							+ "WHERE ChiTietDatPhong.datPhong LIKE ? and DatPhong.trangThai like ? and KhachHang.soDienThoai like ?");

			preparedStatement.setString(1, "%" + maDatPhong + "%");
			preparedStatement.setString(2, "%" + trangThai + "%");
			preparedStatement.setString(3, soDienThoai);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getChiTietDatPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
