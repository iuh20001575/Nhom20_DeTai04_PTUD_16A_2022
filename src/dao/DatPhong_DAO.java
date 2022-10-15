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
	/**
	 * Get phòng từ resultSet
	 * 
	 * @param resultSet
	 * @return phòng
	 * @throws SQLException if the columnIndex is not valid;if a database access
	 *                      error occurs or this method iscalled on a closed result
	 *                      set
	 */
	private Phong getPhong(ResultSet resultSet) throws SQLException {
		String maPhong = resultSet.getString(1);
		String loaiPhong = resultSet.getString(2);
		int soLuongKhach = resultSet.getInt(3);
		String trangThai = resultSet.getString(4);
		return new Phong(maPhong, new LoaiPhong(loaiPhong), soLuongKhach, Phong.convertStringToTrangThai(trangThai));
	}

	/**
	 * Get đặt phòng từ resultSet
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private DatPhong getDatPhong(ResultSet resultSet) throws SQLException {
		String maDatPhong = resultSet.getString("maDatPhong");
		KhachHang khachHang = new KhachHang(resultSet.getString("khachHang"));
		NhanVien nhanVien = new NhanVien(resultSet.getString("nhanVien"));
		LocalDate ngayDatPhong = Utils.convertDateToLocalDate(resultSet.getDate("ngayDatPhong"));
		LocalTime gioDatPhong = resultSet.getTime("gioDatPhong").toLocalTime();
		LocalDate ngayNhanPhong = Utils.convertDateToLocalDate(resultSet.getDate("ngayNhanPhong"));
		LocalTime gioNhanPhong = resultSet.getTime("gioNhanPhong").toLocalTime();
		TrangThai trangThai = DatPhong.convertStringToTrangThai(resultSet.getString("trangThai"));
		return new DatPhong(maDatPhong, khachHang, nhanVien, ngayDatPhong, gioDatPhong, ngayNhanPhong, gioNhanPhong,
				trangThai);
	}

	public DatPhong getDatPhong(String maDatPhong) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM DatPhong WHERE maDatPhong = ?");
			preparedStatement.setString(1, maDatPhong);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getDatPhong(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get những phòng trống hoặc phòng chờ giờ hiện tại + 3h <= giờ nhận phòng
	 * 
	 * @return danh sách các phòng có thể đặt ngay
	 */
	public List<Phong> getPhongDatNgay() {
		List<Phong> list = new ArrayList<>();

		try {
			Date dateNow = Utils.convertLocalDateToDate(LocalDate.now());
			Time timeNow = Time.valueOf(LocalTime.now());
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM [dbo].[Phong] "
							+ "WHERE [trangThai] = N'Trống' OR ([trangThai] = N'Đã đặt' AND maPhong NOT IN ("
							+ "	SELECT maPhong FROM [dbo].[DatPhong] DP "
							+ "	JOIN [dbo].[ChiTietDatPhong] CTDP ON DP.[maDatPhong] = CTDP.[datPhong] "
							+ "	JOIN [dbo].[Phong] P ON P.[maPhong] = CTDP.[phong] "
							+ "	WHERE P.[trangThai] = N'Đã đặt' AND [ngayNhanPhong] = ?"
							+ "	AND [dbo].[fnSubTime]([gioNhanPhong], ?) <= CONVERT(TIME(0), '3:00:00')" + "))");
			preparedStatement.setDate(1, dateNow);
			preparedStatement.setTime(2, timeNow);

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
	 * Get danh sách các phòng có thể đặt ngay
	 * 
	 * @param maPhong   mã phòng
	 * @param loaiPhong loại phòng
	 * @param soLuong   số lượng
	 * @return danh sách các phòng
	 */
	public List<Phong> getPhongDatNgay(String maPhong, String loaiPhong, String soLuong) {
		List<Phong> list = new ArrayList<>();
		boolean isInteger = Utils.isInteger(soLuong);

		try {
			String sql = "SELECT maPhong, loaiPhong, soLuongKhach, trangThai FROM [dbo].[Phong] P "
					+ "JOIN [dbo].[LoaiPhong] LP ON P.loaiPhong = LP.maLoai "
					+ "WHERE ([trangThai] = N'Trống' OR ([trangThai] = N'Đã đặt' AND maPhong NOT IN ("
					+ "	SELECT maPhong FROM [dbo].[DatPhong] DP "
					+ "	JOIN [dbo].[ChiTietDatPhong] CTDP ON DP.[maDatPhong] = CTDP.[datPhong] "
					+ "	JOIN [dbo].[Phong] P ON P.[maPhong] = CTDP.[phong] "
					+ "	WHERE P.[trangThai] = N'Đã đặt' AND [ngayNhanPhong] = ? "
					+ "	AND [dbo].[fnSubTime]([gioNhanPhong], ?) <= CONVERT(TIME(0), '3:00:00'))"
					+ ")) AND maPhong LIKE ? AND tenLoai LIKE ?";

			if (isInteger)
				sql += " AND soLuongKhach = ?";

			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			Date dateNow = Utils.convertLocalDateToDate(LocalDate.now());
			Time timeNow = Time.valueOf(LocalTime.now());
			preparedStatement.setDate(1, dateNow);
			preparedStatement.setTime(2, timeNow);
			preparedStatement.setString(3, "%" + maPhong + "%");
			preparedStatement.setString(4, "%" + loaiPhong + "%");

			if (isInteger)
				preparedStatement.setInt(5, Integer.parseInt(soLuong));

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
	 * Thêm phiếu đặt phòng ngay mới
	 * 
	 * @param khachHang khách hàng đặt phòng
	 * @param nhanVien  nhân viên tạo hóa đơn
	 * @param phongs    danh sách các phòng mà khách thuê
	 * @return true nếu thêm thành công
	 */
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

	/**
	 * Thêm phiếu đặt phòng trước mới
	 * 
	 * @param khachHang     khách hàng đặt phòng
	 * @param nhanVien      nhân viên tạo hóa đơn
	 * @param phongs        danh sách các phòng mà khách đặt
	 * @param ngayNhanPhong ngày nhận phòng
	 * @param gioNhanPhong  giờ nhận phòng
	 * @return true nếu thêm thành công
	 */
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

	/**
	 * Tạo mã đặt phòng
	 * 
	 * @return mã đặt phòng
	 */
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

	/**
	 * Get tất cả các phòng có thể đặt trước theo ngày và giờ được chọn
	 * 
	 * @param ngayNhanPhong ngày nhận phòng
	 * @param gioNhanPhong  giờ nhận phòng
	 * @return tất cả các phòng có thể đặt trước
	 */
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

	/**
	 * Get giờ vào phòng của phòng đang thuê hoặc phòng đặt trước gần nhất
	 * 
	 * @param phong phòng cần lấy giờ vào
	 * @return giờ vào phòng
	 */
	public LocalTime getGioVao(Phong phong) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT TOP 1 [gioNhanPhong] FROM [dbo].[DatPhong] DP "
							+ "JOIN [dbo].[ChiTietDatPhong] CTDP ON DP.[maDatPhong] = CTDP.[datPhong] "
							+ "WHERE phong = ? " + "ORDER BY [trangThai] DESC, [ngayNhanPhong], [gioNhanPhong]");
			preparedStatement.setString(1, phong.getMaPhong());

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getTime(1).toLocalTime();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get đặt phòng ngay theo số điện thoại
	 * 
	 * @param soDienThoai
	 * @return
	 */
	public DatPhong getDatPhongNgayTheoSoDienThoai(String soDienThoai) {
		String sql = "SELECT [maDatPhong], [khachHang], [nhanVien], [ngayDatPhong],"
				+ "	[gioDatPhong], [ngayNhanPhong], [gioNhanPhong], [trangThai] " + "FROM [dbo].[DatPhong] DP\r\n"
				+ "	JOIN [dbo].[KhachHang] KH ON DP.[khachHang] = KH.[maKhachHang]"
				+ "WHERE [trangThai] = N'Đang thuê' AND soDienThoai = ?";
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, soDienThoai);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getDatPhong(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<DatPhong> getAllDatPhongDangThue() {
		List<DatPhong> list = new ArrayList<>();

		String sql = "SELECT * FROM [dbo].[DatPhong] WHERE [trangThai] = N'Đang thuê'";
		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next())
				list.add(getDatPhong(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Get tất cả các mã đặt phòng của phiếu đặt phòng có trạng thái đang thuê
	 * 
	 * @return danh sách các mã đặt phòng
	 */
	public List<String> getAllMaDatPhongDangThue() {
		List<String> list = new ArrayList<>();

		String sql = "SELECT maDatPhong FROM [dbo].[DatPhong] WHERE [trangThai] = N'Đang thuê'";
		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next())
				list.add(resultSet.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
