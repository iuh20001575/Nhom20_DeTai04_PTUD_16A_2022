package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietDatPhong;
import entity.ChiTietDichVu;
import entity.DichVu;
import entity.DonDatPhong;
import entity.LoaiDichVu;
import entity.Phong;

public class ChiTietDichVu_DAO extends DAO {


	public List<ChiTietDichVu> getAllChiTietDichVu() {
		List<ChiTietDichVu> list = new ArrayList<>();
		Statement statement;

		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ChiTietDichVu");
			while (resultSet.next())
				list.add(getChiTietDichVu(resultSet));
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ChiTietDichVu> getAllChiTietDichVu(String maDonDatPhong, String maPhong) {
		List<ChiTietDichVu> list = new ArrayList<>();
		String sql = "SELECT CTDV.*, DV.*, CTDV.soLuong AS SOLUONGBAN FROM [dbo].[ChiTietDichVu] CTDV "
				+ "JOIN [dbo].[ChiTietDatPhong] CTDP ON CTDV.donDatPhong = CTDP.donDatPhong "
				+ "AND CTDV.PHONG = CTDP.phong AND CTDV.gioVao = CTDP.gioVao "
				+ "JOIN [dbo].[DichVu] DV ON DV.maDichVu = CTDV.dichVu "
				+ "WHERE CTDV.[phong] = ? AND CTDV.[donDatPhong] = ? AND [gioRa] IS NULL";

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, maPhong);
			preparedStatement.setString(2, maDonDatPhong);
			ResultSet resultSet = preparedStatement.executeQuery();
			ChiTietDichVu chiTietDichVu;
			DichVu dichVu;
			LoaiDichVu loaiDichVu;
			while (resultSet.next()) {
				chiTietDichVu = getChiTietDichVu(resultSet);

				loaiDichVu = new LoaiDichVu(resultSet.getString(10));
				dichVu = new DichVu(resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8),
						resultSet.getString(9), loaiDichVu, resultSet.getDouble(11));
				chiTietDichVu.setDichVu(dichVu);

				list.add(chiTietDichVu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<ChiTietDichVu> getAllChiTietDichVuTheoMaDatPhong(String maDP, String maPhong) {
		List<ChiTietDichVu> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * " + "FROM   ChiTietDichVu INNER JOIN DonDatPhong ON  "
							+ "	   ChiTietDichVu.donDatPhong = DonDatPhong.maDonDatPhong "
							+ "WHERE  maDonDatPhong = ? and phong = ?");

			preparedStatement.setString(1, maDP);
			preparedStatement.setString(2, maPhong);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getChiTietDichVu(resultSet));
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private ChiTietDichVu getChiTietDichVu(ResultSet resultSet) throws SQLException {
		String maDV = resultSet.getString("dichVu");
		String maDP = resultSet.getString("donDatPhong");
		String phong = resultSet.getString("phong");
		Time gioVao = resultSet.getTime("gioVao");
		int soLuong = resultSet.getInt(5);

		return new ChiTietDichVu(new DichVu(maDV),
				new ChiTietDatPhong(new DonDatPhong(maDP), new Phong(phong), gioVao.toLocalTime()), soLuong);
	}

	public ChiTietDichVu getChiTietDichVuTheoMa(String maDichVu, String maDatPhong, String maPhong) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"SELECT * FROM ChiTietDichVu INNER JOIN DichVu ON ChiTietDichVu.dichVu = DichVu.maDichVu INNER JOIN "
							+ "DonDatPhong ON ChiTietDichVu.donDatPhong = DonDatPhong.maDonDatPhong "
							+ "WHERE maDichVu = ? and maDonDatPhong = ? and phong = ?");
			preparedStatement.setString(1, maDichVu);
			preparedStatement.setString(2, maDatPhong);
			preparedStatement.setString(3, maPhong);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next())
				return getChiTietDichVu(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<ChiTietDichVu> getDichVuTheoPhieuDatPhong(String datPhong) {
		List<ChiTietDichVu> list = new ArrayList<>();
		String sql = "SELECT * FROM [dbo].[ChiTietDichVu] WHERE [donDatPhong] = ?";

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, datPhong);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getChiTietDichVu(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<ChiTietDichVu> getDichVuTheoPhieuDatPhongVaPhong(String datPhong, String maPhong) {
		List<ChiTietDichVu> list = new ArrayList<>();
		String sql = "SELECT * FROM ChiTietDichVu WHERE donDatPhong = ? and phong = ? ";

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, datPhong);
			preparedStatement.setString(2, maPhong);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getChiTietDichVu(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean themChiTietDichVu(ChiTietDichVu chiTietDichVu) {
		int res = 0;
		PreparedStatement preparedStatement;
		Connection connection = ConnectDB.getConnection();
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("UPDATE [dbo].[DichVu] SET [soLuong] -= ? WHERE [maDichVu] = ?");
			preparedStatement.setInt(1, chiTietDichVu.getSoLuong());
			preparedStatement.setString(2, chiTietDichVu.getDichVu().getMaDichVu());
			res = preparedStatement.executeUpdate();
			if (res <= 0)
				return rollback();

			preparedStatement = connection.prepareStatement("INSERT ChiTietDichVu VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setString(1, chiTietDichVu.getDichVu().getMaDichVu());
			preparedStatement.setString(2, chiTietDichVu.getChiTietDatPhong().getDonDatPhong().getMaDonDatPhong());
			preparedStatement.setString(3, chiTietDichVu.getChiTietDatPhong().getPhong().getMaPhong());
			preparedStatement.setTime(4, Time.valueOf(chiTietDichVu.getChiTietDatPhong().getGioVao()));
			preparedStatement.setInt(5, chiTietDichVu.getSoLuong());
			res = preparedStatement.executeUpdate();
			if (res <= 0)
				return rollback();

			preparedStatement.close();
			return commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean xoaChiTietDichVu(String maDichVu, String maDatPhong, String maPhong) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("DELETE ChiTietDichVu "
					+ "FROM   ChiTietDatPhong INNER JOIN ChiTietDichVu ON ChiTietDatPhong.donDatPhong = ChiTietDichVu.donDatPhong "
					+ "AND ChiTietDatPhong.phong = ChiTietDichVu.phong AND ChiTietDatPhong.gioVao = ChiTietDichVu.gioVao "
					+ "INNER JOIN DichVu ON ChiTietDichVu.dichVu = DichVu.maDichVu "
					+ "WHERE dichVu =  ? and ChiTietDichVu.donDatPhong = ? and ChiTietDichVu.phong = ?");
			preparedStatement.setString(1, maDichVu);
			preparedStatement.setString(2, maDatPhong);
			preparedStatement.setString(3, maPhong);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean capNhatSoLuongDichVu(ChiTietDichVu chiTietDichVu, boolean isSoLuongTang) {
		Connection connection = ConnectDB.getConnection();
		PreparedStatement preparedStatement;
		boolean res;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"UPDATE [dbo].[DichVu] SET [soLuong] " + (isSoLuongTang ? "-" : "+") + "= ? WHERE [maDichVu] = ?");
			preparedStatement.setInt(1, chiTietDichVu.getSoLuong());
			preparedStatement.setString(2, chiTietDichVu.getDichVu().getMaDichVu());
			res = preparedStatement.executeUpdate() > 0;
			if (!res)
				return rollback();

			ChiTietDatPhong chiTietDatPhong = chiTietDichVu.getChiTietDatPhong();
			preparedStatement = connection
					.prepareStatement("UPDATE [dbo].[ChiTietDichVu] SET [soLuong] " + (isSoLuongTang ? "+" : "-")
							+ "= ? WHERE [dichVu] = ? AND [donDatPhong] = ? AND [phong] = ? AND [gioVao] = ?");
			preparedStatement.setInt(1, chiTietDichVu.getSoLuong());
			preparedStatement.setString(2, chiTietDichVu.getDichVu().getMaDichVu());
			preparedStatement.setString(3, chiTietDatPhong.getDonDatPhong().getMaDonDatPhong());
			preparedStatement.setString(4, chiTietDatPhong.getPhong().getMaPhong());
			preparedStatement.setString(5, Time.valueOf(chiTietDatPhong.getGioVao()).toString());
			res = preparedStatement.executeUpdate() > 0;
			if (!res)
				return rollback();
			return commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
