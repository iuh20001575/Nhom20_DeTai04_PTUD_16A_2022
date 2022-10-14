package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.DatPhong;
import entity.DatPhong.TrangThai;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.Phong;
import utils.Utils;

public class DatPhong_DAO {
	private Phong getPhong(ResultSet resultSet) throws SQLException {
		String maPhong = resultSet.getString(1);
		String loaiPhong = resultSet.getString(2);
		int soLuongKhach = resultSet.getInt(3);
		String trangThai = resultSet.getString(4);
		return new Phong(maPhong, new LoaiPhong(loaiPhong), soLuongKhach, Phong.convertStringToTrangThai(trangThai));
	}

	/**
	 * Get những phòng trống hoặc phòng chờ giờ hiện tại + 3h <= giờ nhận phòng
	 * 
	 * @return danh sách các phòng có thể đặt ngay
	 */
	public List<Phong> getPhongDatNgay() {
		List<Phong> list = new ArrayList<>();

		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Phong WHERE trangThai = N'Trống'");

			while (resultSet.next())
				list.add(getPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<Phong> getPhongDatNgay(String maPhong, String loaiPhong, String soLuong) {
		List<Phong> list = new ArrayList<>();
		boolean isInteger = Utils.isInteger(soLuong);

		try {
			String sql = "SELECT maPhong, loaiPhong, soLuongKhach, trangThai FROM Phong P JOIN LoaiPhong LP ON P.loaiPhong = LP.maLoai WHERE trangThai = N'Trống' AND maPhong LIKE ? AND tenLoai LIKE ?";

			if (isInteger)
				sql += " AND soLuongKhach = ?";

			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, "%" + maPhong + "%");
			preparedStatement.setString(2, "%" + loaiPhong + "%");

			if (isInteger)
				preparedStatement.setInt(3, Integer.parseInt(soLuong));

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public boolean themPhieuDatPhongNgay(KhachHang khachHang, NhanVien nhanVien, List<Phong> phongs) {
		try {
			ConnectDB.getConnection().setAutoCommit(false);
			String maDatPhong = taoMaDatPhong();
			Time time = Time.valueOf(LocalTime.now());
			Date date = Utils.convertLocalDateToDate(LocalDate.now());

//			Tạo phiếu đặt phòng
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT DatPhong VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, maDatPhong);
			preparedStatement.setString(2, khachHang.getMaKhachHang());
			preparedStatement.setString(3, nhanVien.getMaNhanVien());
			preparedStatement.setDate(4, date);
			preparedStatement.setTime(5, time);
			preparedStatement.setDate(6, date);
			preparedStatement.setTime(7, time);
			preparedStatement.setString(8, DatPhong.convertTrangThaiToString(TrangThai.DangThue));
			int res = preparedStatement.executeUpdate();

			if (res <= 0) {
				ConnectDB.getConnection().rollback();
				return false;
			}

			for (Phong phong : phongs) {
//				Tạo chi tiết phiếu đặt phòng
				preparedStatement = ConnectDB.getConnection()
						.prepareStatement("INSERT ChiTietDatPhong(datPhong, phong, gioVao) VALUES(?, ?, ?)");
				preparedStatement.setString(1, maDatPhong);
				preparedStatement.setString(2, phong.getMaPhong());
				preparedStatement.setTime(3, time);

				res = preparedStatement.executeUpdate();

				if (res <= 0) {
					ConnectDB.getConnection().rollback();
					return false;
				}

//				Cập nhật trạng thái phòng
				preparedStatement = ConnectDB.getConnection()
						.prepareStatement("Update Phong SET trangThai = ? WHERE maPhong = ?");
				preparedStatement.setString(1, Phong.convertTrangThaiToString(Phong.TrangThai.DangThue));
				preparedStatement.setString(2, phong.getMaPhong());

				res = preparedStatement.executeUpdate();

				if (res <= 0) {
					ConnectDB.getConnection().rollback();
					return false;
				}
			}

			ConnectDB.getConnection().setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnectDB.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	public boolean themPhieuDatPhongTruoc(KhachHang khachHang, NhanVien nhanVien, List<Phong> phongs,
			LocalDate ngayNhanPhong, LocalTime gioNhanPhong) {
		try {
			ConnectDB.getConnection().setAutoCommit(false);
			String maDatPhong = taoMaDatPhong();
			Time time = Time.valueOf(LocalTime.now());
			Date date = Utils.convertLocalDateToDate(LocalDate.now());

//			Tạo phiếu đặt phòng
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT DatPhong VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, maDatPhong);
			preparedStatement.setString(2, khachHang.getMaKhachHang());
			preparedStatement.setString(3, nhanVien.getMaNhanVien());
			preparedStatement.setDate(4, date);
			preparedStatement.setTime(5, time);
			preparedStatement.setDate(6, Utils.convertLocalDateToDate(ngayNhanPhong));
			preparedStatement.setTime(7, Time.valueOf(gioNhanPhong));
			preparedStatement.setString(8, DatPhong.convertTrangThaiToString(TrangThai.DangCho));
			int res = preparedStatement.executeUpdate();

			if (res <= 0) {
				ConnectDB.getConnection().rollback();
				return false;
			}

			for (Phong phong : phongs) {
//				Tạo chi tiết phiếu đặt phòng
				preparedStatement = ConnectDB.getConnection()
						.prepareStatement("INSERT ChiTietDatPhong(datPhong, phong, gioVao) VALUES(?, ?, ?)");
				preparedStatement.setString(1, maDatPhong);
				preparedStatement.setString(2, phong.getMaPhong());
				preparedStatement.setTime(3, Time.valueOf(gioNhanPhong));

				res = preparedStatement.executeUpdate();

				if (res <= 0) {
					ConnectDB.getConnection().rollback();
					return false;
				}

//				Cập nhật trạng thái phòng
				preparedStatement = ConnectDB.getConnection()
						.prepareStatement("Update Phong SET trangThai = ? WHERE maPhong = ?");
				preparedStatement.setString(1, Phong.convertTrangThaiToString(Phong.TrangThai.DaDat));
				preparedStatement.setString(2, phong.getMaPhong());

				res = preparedStatement.executeUpdate();

				if (res <= 0) {
					ConnectDB.getConnection().rollback();
					return false;
				}
			}

			ConnectDB.getConnection().setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnectDB.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	private String taoMaDatPhong() {
		Statement statement;
		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT maDatPhong FROM DatPhong ORDER BY maDatPhong DESC");

			if (!resultSet.next()) {
				return "MDP0001";
			}
			String prevMaDatPhong = resultSet.getString(1);
			int prevSTT = Integer.parseInt(prevMaDatPhong.substring(3));

			String newMaDatPhong = prevSTT + 1 + "";
			while (newMaDatPhong.length() < 4)
				newMaDatPhong = "0" + newMaDatPhong;

			return "MDP" + newMaDatPhong;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "MDP0001";
	}

	public List<Phong> getPhongDatTruoc(LocalDate ngayNhanPhong, LocalTime gioNhanPhong) {
		List<Phong> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM PHONG " + "WHERE maPhong NOT IN (" + "	SELECT maPhong FROM Phong P"
							+ "	JOIN ChiTietDatPhong CTDP ON P.maPhong = CTDP.phong"
							+ "	JOIN DatPhong DP ON DP.maDatPhong = CTDP.datPhong"
							+ "	WHERE P.trangThai = N'Đã đặt' AND ngayNhanPhong = ? " // ngày nhận phòng
							+ "AND [dbo].[fnSubTime](gioNhanPhong, ?) < CONVERT(TIME(0), '3:00:00')" // giờ nhận phòng
							+ ") AND trangThai <> N'Đang thuê'");
			preparedStatement.setDate(1, Utils.convertLocalDateToDate(ngayNhanPhong));
			preparedStatement.setTime(2, Time.valueOf(gioNhanPhong));

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
