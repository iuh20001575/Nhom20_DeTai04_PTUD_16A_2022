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

	public List<Phong> getAllPhongTheoMa(List<Phong> list) {
		List<Phong> phongs = new ArrayList<>();

		int length = list.size();
		if (length <= 0)
			return getAllPhong();
		String q = "?";
		for (int i = 1; i < length; i++)
			q += ", ?";

		String sql = String.format("SELECT * FROM Phong WHERE maPhong IN (%s)", q);

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			for (int i = 0; i < length; i++)
				preparedStatement.setString(i + 1, list.get(i).getMaPhong());

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				phongs.add(getPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return phongs;
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
	 * Get tất cả các phòng theo loại phòng
	 * 
	 * @param loaiPhong
	 * @return
	 */
	public List<Phong> getAllPhong(String loaiPhong) {
		List<Phong> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM Phong WHERE loaiPhong IN ("
							+ "SELECT [maLoai] FROM [dbo].[LoaiPhong] WHERE [tenLoai] LIKE ?)");
			preparedStatement.setString(1, "%" + loaiPhong + "%");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Get tất cả các phòng theo trạng thái
	 * 
	 * @param trangThai
	 * @return
	 */
	public List<Phong> getAllPhongTheoTrangThai(String trangThai) {
		List<Phong> list = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Phong WHERE trangThai LIKE ?";
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, "%" + trangThai + "%");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<Phong> getAllPhongDangThue() {
		List<Phong> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM Phong WHERE trangThai IN (N'Đang thuê', N'Phòng tạm')");
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
