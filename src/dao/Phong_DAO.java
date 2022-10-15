package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;
import entity.Phong.TrangThai;

public class Phong_DAO {
	private Phong getPhong(ResultSet resultSet) throws SQLException {
		String maPhong = resultSet.getString(1);
		String loaiPhong = resultSet.getString(2);
		int soLuongKhach = resultSet.getInt(3);
		String trangThai = resultSet.getString(4);
		return new Phong(maPhong, new LoaiPhong(loaiPhong), soLuongKhach, Phong.convertStringToTrangThai(trangThai));
	}

	/**
	 * Get tất cả các phòng
	 * 
	 * @return
	 */
	public List<Phong> getAllPhong() {
		List<Phong> list = new ArrayList<>();

		Statement statement;
		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Phong");

			while (resultSet.next())
				list.add(getPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Get phòng theo mã phòng
	 * 
	 * @param maPhong
	 * @return
	 */
	public Phong getPhong(String maPhong) {
		try {
			String sql = "SELECT * FROM Phong WHERE maPhong = ?";
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, maPhong);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getPhong(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get tất cả các phòng theo trạng thái
	 * 
	 * @param trangThai
	 * @return
	 */
	public List<Phong> getAllPhongTheoTrangThai(TrangThai trangThai) {
		List<Phong> list = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Phong WHERE trangThai = ?";
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, Phong.convertTrangThaiToString(trangThai));

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
