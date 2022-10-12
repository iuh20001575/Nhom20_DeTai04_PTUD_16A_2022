package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import connectDB.ConnectDB;
import entity.ChiTietDatPhong;
import entity.DatPhong;
import entity.Phong;

public class ChiTietDatPhong_DAO {
	public ChiTietDatPhong getChiTietDatPhong(Phong phong) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"SELECT datPhong, phong, gioVao FROM ChiTietDatPhong WHERE phong = ? and gioRa is null");
			preparedStatement.setString(1, phong.getMaPhong());

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String datPhong = resultSet.getString(1);
				String maPhong = resultSet.getString(2);
				LocalTime gioVao = resultSet.getTime(3).toLocalTime();
//				Time.valueOf(gioVao);
				return new ChiTietDatPhong(new DatPhong(datPhong), new Phong(maPhong), gioVao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
