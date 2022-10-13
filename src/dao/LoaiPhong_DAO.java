package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.LoaiPhong;

public class LoaiPhong_DAO {
	private LoaiPhong getLoaiPhong(ResultSet resultSet) throws SQLException {
		String maLoai = resultSet.getString("maLoai");
		String tenLoai = resultSet.getString("tenLoai");
		return new LoaiPhong(maLoai, tenLoai);
	}

	public LoaiPhong getLoaiPhong(String maLoai) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM LoaiPhong WHERE maLoai = ?");
			preparedStatement.setString(1, maLoai);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getLoaiPhong(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
