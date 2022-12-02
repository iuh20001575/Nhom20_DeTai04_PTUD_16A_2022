package dao;

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
import entity.Phong;

public class ChiTietDichVu_DAO {
	public boolean capNhatSoLuongDichVu(String maDV, String maDP, String maPhong, int soLuongMua) {
		boolean res = false;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE ChiTietDichVu SET soLuong = ? WHERE dichVu = ? and donDatPhong = ? and phong = ?");
			preparedStatement.setInt(1, soLuongMua);
			preparedStatement.setString(2, maDV);
			preparedStatement.setString(3, maDP);
			preparedStatement.setString(4, maPhong);
			res = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean capNhatSoLuongDichVuTang(String maDV, String maDP, String maPhong, int soLuongMua) {
		boolean res = false;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE ChiTietDichVu SET soLuong += ? WHERE dichVu = ? and donDatPhong = ? and phong = ?");
			preparedStatement.setInt(1, soLuongMua);
			preparedStatement.setString(2, maDV);
			preparedStatement.setString(3, maDP);
			preparedStatement.setString(4, maPhong);
			res = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

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
		String maDV = resultSet.getString(1);
		String maDP = resultSet.getString(2);
		String phong = resultSet.getString(3);
		Time gioVao = resultSet.getTime(4);
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
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT ChiTietDichVu VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setString(1, chiTietDichVu.getDichVu().getMaDichVu());
			preparedStatement.setString(2, chiTietDichVu.getChiTietDatPhong().getDonDatPhong().getMaDonDatPhong());
			preparedStatement.setString(3, chiTietDichVu.getChiTietDatPhong().getPhong().getMaPhong());
			preparedStatement.setTime(4, Time.valueOf(chiTietDichVu.getChiTietDatPhong().getGioVao()));
			preparedStatement.setInt(5, chiTietDichVu.getSoLuong());

			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res > 0;
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

}
