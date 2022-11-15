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
import entity.DonDatPhong;
import entity.Phong;
import entity.Phong.TrangThai;

public class ChiTietDatPhong_DAO extends DAO {
	private Phong_DAO phong_DAO;

	/**
	 * Constructor
	 */
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
		DonDatPhong donDatPhong = new DonDatPhong(resultSet.getString("donDatPhong"));
		Phong phong = new Phong(resultSet.getString("phong"));
		LocalTime gioVao = resultSet.getTime("gioVao").toLocalTime();
		Time time = resultSet.getTime("gioRa");
		LocalTime gioRa = time == null ? null : resultSet.getTime("gioRa").toLocalTime();
		return new ChiTietDatPhong(donDatPhong, phong, gioVao, gioRa);
	}

	/**
	 * Get tất cả các chi tiết đặt phòng theo đặt phòng
	 * 
	 * @param datPhong
	 * @return
	 */
	public List<ChiTietDatPhong> getAllChiTietDatPhong(DonDatPhong datPhong) {
		List<ChiTietDatPhong> list = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM ChiTietDatPhong WHERE donDatPhong = ?");
			preparedStatement.setString(1, datPhong.getMaDonDatPhong());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getChiTietDatPhong(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement, resultSet);
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
		ChiTietDatPhong chiTietDatPhong = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement(
					"SELECT donDatPhong, phong, gioVao FROM ChiTietDatPhong WHERE phong = ? and gioRa is null");
			preparedStatement.setString(1, phong.getMaPhong());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String datPhong = resultSet.getString(1);
				String maPhong = resultSet.getString(2);
				LocalTime gioVao = resultSet.getTime(3).toLocalTime();
				chiTietDatPhong = new ChiTietDatPhong(new DonDatPhong(datPhong), new Phong(maPhong), gioVao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement, resultSet);
		}

		return chiTietDatPhong;
	}

	/**
	 * Get giờ vào phòng của danh sách phòng các phòng chờ
	 * 
	 * @param dsPhong danh sách phòng
	 * @return
	 */
	public List<ChiTietDatPhong> getGioVaoPhongCho(List<Phong> dsPhong) {
		List<ChiTietDatPhong> list = new ArrayList<>();
		String q = "?";
		int length = dsPhong.size();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		for (int i = 1; i < length; ++i)
			q += ", ?";

		String sql = String.format("SELECT [donDatPhong], [phong], [gioVao], [gioRa] FROM [dbo].[DonDatPhong] DP "
				+ "JOIN [dbo].[ChiTietDatPhong] CTDP ON DP.maDonDatPhong = CTDP.donDatPhong "
				+ "WHERE [ngayNhanPhong] = CONVERT(DATE, GETDATE()) "
				+ "AND [trangThai] = N'Đang chờ' AND [phong] in (%s)", q);

		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			for (int i = 0; i < length; ++i)
				preparedStatement.setString(i + 1, dsPhong.get(i).getMaPhong());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getChiTietDatPhong(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement, resultSet);
		}

		return list;
	}

	/**
	 * Thanh toán đơn đặt phòng
	 * 
	 * @param maDatPhong
	 * @param gioRa
	 * @return
	 */
	public boolean thanhToanDatPhong(String maDatPhong, LocalTime gioRa) {
		boolean res = false;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE ChiTietDatPhong SET gioRa = ? WHERE donDatPhong = ? AND gioRa is null");
			preparedStatement.setTime(1, Time.valueOf(gioRa));
			preparedStatement.setString(2, maDatPhong);

			res = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}

		return res;
	}

	/**
	 * Set giờ ra của phòng
	 * 
	 * @param maDonDatPhong
	 * @param maPhong
	 * @param gioRa
	 * @return
	 */
	public boolean setGioRa(String maDonDatPhong, String maPhong, Time gioRa) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE [dbo].[ChiTietDatPhong] SET [gioRa] = ? "
							+ "WHERE [phong] IN (SELECT [phong] FROM [dbo].[DonDatPhong] DP"
							+ "	JOIN [dbo].[ChiTietDatPhong] CTDP ON DP.[maDonDatPhong] = CTDP.[donDatPhong]"
							+ "	WHERE DP.[trangThai] = N'Đang thuê' AND [phong] = ? AND maDonDatPhong = ?) AND donDatPhong = ?");
			preparedStatement.setTime(1, gioRa);
			preparedStatement.setString(2, maPhong);
			preparedStatement.setString(3, maDonDatPhong);
			preparedStatement.setString(4, maDonDatPhong);
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Thêm chi tiết đặt phòng
	 * 
	 * @param maDatPhong
	 * @param dsPhong
	 * @param gioVao
	 * @return
	 */
	public boolean themChiTietDatPhong(String maDatPhong, List<Phong> dsPhong, Time gioVao) {
		try {
			ConnectDB.getConnection().setAutoCommit(false);

			for (Phong phong : dsPhong) {
//				[ChiTietDatPhong] - Tạo chi tiết phiếu đặt phòng
				if (!themChiTietDatPhong(maDatPhong, phong, gioVao))
					return rollback();

//				[Phong] - Cập nhật trạng thái phòng
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
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Thêm chi tiết đặt phòng
	 * 
	 * @param maDatPhong
	 * @param phong
	 * @param gioVao
	 * @return
	 */
	public boolean themChiTietDatPhong(String maDatPhong, Phong phong, Time gioVao) {
		PreparedStatement preparedStatement = null;
		boolean res = false;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT ChiTietDatPhong(donDatPhong, phong, gioVao) VALUES(?, ?, ?)");
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
