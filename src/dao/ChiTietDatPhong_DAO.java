package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietDatPhong;
import entity.DatPhong;
import entity.Phong;

public class ChiTietDatPhong_DAO {
	/**
	 * Get chi tiết đặt phòng resultSet
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private ChiTietDatPhong getChiTietDatPhong(ResultSet resultSet) throws SQLException {
		DatPhong datPhong = new DatPhong(resultSet.getString("datPhong"));
		Phong phong = new Phong(resultSet.getString("phong"));
		LocalTime gioVao = resultSet.getTime("gioVao").toLocalTime();
		Time time = resultSet.getTime("gioRa");
		LocalTime gioRa = time == null ? null : resultSet.getTime("gioRa").toLocalTime();
		return new ChiTietDatPhong(datPhong, phong, gioVao, gioRa);
	}

	/**
	 * Get tất cả các chi tiết đặt phòng theo đặt phòng
	 * 
	 * @param datPhong
	 * @return
	 */
	public List<ChiTietDatPhong> getAllChiTietDatPhong(DatPhong datPhong) {
		List<ChiTietDatPhong> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM ChiTietDatPhong WHERE datPhong = ?");
			preparedStatement.setString(1, datPhong.getMaDatPhong());

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
	 * Get chi tiết đặt phòng của phòng đang thuê
	 * 
	 * @param phong
	 * @return
	 */
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
