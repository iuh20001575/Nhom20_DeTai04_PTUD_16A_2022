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
import entity.Phong.TrangThai;

public class ChiTietDatPhong_DAO extends DAO {
	private Phong_DAO phong_DAO;

	public ChiTietDatPhong_DAO() {
		phong_DAO = new Phong_DAO();
	}

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
			e.printStackTrace();
		}
		return null;
	}

	public List<ChiTietDatPhong> getGioVaoPhongCho(List<Phong> dsPhong) {
		List<ChiTietDatPhong> list = new ArrayList<>();

		String q = "?";
		int length = dsPhong.size();
		for (int i = 1; i < length; i++)
			q += ", ?";
		String sql = String.format("SELECT [datPhong], [phong], [gioVao], [gioRa] FROM [dbo].[DatPhong] DP "
				+ "JOIN [dbo].[ChiTietDatPhong] CTDP ON DP.maDatPhong = CTDP.datPhong "
				+ "WHERE [ngayNhanPhong] = CONVERT(DATE, GETDATE()) "
				+ "AND [trangThai] = N'Đang chờ' AND [phong] in (%s)", q);
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			for (int i = 0; i < length; i++) {
				preparedStatement.setString(i + 1, dsPhong.get(i).getMaPhong());
			}

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getChiTietDatPhong(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean thanhToanDatPhong(String maDatPhong, LocalTime gioRa) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE ChiTietDatPhong SET gioRa = ? WHERE datPhong = ? AND gioRa is null");
			preparedStatement.setTime(1, Time.valueOf(gioRa));
			preparedStatement.setString(2, maDatPhong);

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean themChiTietDatPhong(String maDatPhong, List<Phong> dsPhong, Time gioVao) {
		try {
			ConnectDB.getConnection().setAutoCommit(false);

			for (Phong phong : dsPhong) {
//			[ChiTietDatPhong] - Tạo chi tiết phiếu đặt phòng
				if (!themChiTietDatPhong(maDatPhong, phong, gioVao))
					return rollback();

//			[Phong] - Cập nhật trạng thái phòng
				Phong phongFull = phong_DAO.getPhong(phong.getMaPhong());
				TrangThai trangThaiNew;

				if (phongFull.getTrangThai().equals(entity.Phong.TrangThai.DaDat))
					trangThaiNew = Phong.TrangThai.PhongTam;
				else
					trangThaiNew = Phong.TrangThai.DangThue;

				if (!phong_DAO.capNhatTrangThaiPhong(phongFull, Phong.convertTrangThaiToString(trangThaiNew)))
					return rollback();
			}

			return commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean themChiTietDatPhong(String maDatPhong, Phong phong, Time gioVao) {
		PreparedStatement preparedStatement = null;
		boolean res = false;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT ChiTietDatPhong(datPhong, phong, gioVao) VALUES(?, ?, ?)");
			preparedStatement.setString(1, maDatPhong);
			preparedStatement.setString(2, phong.getMaPhong());
			preparedStatement.setTime(3, gioVao);

			res = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}

		return res;
	}
}
