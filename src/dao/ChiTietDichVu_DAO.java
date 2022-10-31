package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietDichVu;

public class ChiTietDichVu_DAO {
	private DatPhong_DAO datPhong_DAO;
	private DichVu_DAO dichVu_DAO;

	public ChiTietDichVu_DAO() {
		datPhong_DAO = new DatPhong_DAO();
		dichVu_DAO = new DichVu_DAO();
	}

	private ChiTietDichVu getChiTietDichVu(ResultSet resultSet) throws SQLException {
		String maDV = resultSet.getString(1);
		String maDP = resultSet.getString(2);
		String soLuong = resultSet.getString(3);

		return new ChiTietDichVu(dichVu_DAO.getDichVuTheoMa(maDV), datPhong_DAO.getDatPhong(maDP),
				Integer.valueOf(soLuong));
	}

	public List<ChiTietDichVu> getDichVuTheoPhieuDatPhong(String datPhong) {
		List<ChiTietDichVu> list = new ArrayList<>();
		String sql = "SELECT * FROM [dbo].[ChiTietDichVu] WHERE [datPhong] = ?";

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, datPhong);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getChiTietDichVu(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public ChiTietDichVu getChiTietDichVuTheoMa(String maDichVu, String maDatPhong) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * " + "FROM   ChiTietDichVu INNER JOIN "
							+ "             DichVu ON ChiTietDichVu.dichVu = DichVu.maDichVu INNER JOIN "
							+ "             DatPhong ON ChiTietDichVu.datPhong = DatPhong.maDatPhong "
							+ "WHERE maDichVu = ? and maDatPhong = ?");
			preparedStatement.setString(1, maDichVu);
			preparedStatement.setString(2, maDatPhong);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getChiTietDichVu(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<ChiTietDichVu> getAllChiTietDichVuTheoMaDatPhong(String maDP) {
		List<ChiTietDichVu> list = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * " + "FROM   ChiTietDichVu INNER JOIN DatPhong ON  "
							+ "	   ChiTietDichVu.datPhong = DatPhong.maDatPhong " + "WHERE  maDatPhong = ?");

			preparedStatement.setString(1, maDP);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(getChiTietDichVu(resultSet));
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean themChiTietDichVu(ChiTietDichVu chiTietDichVu) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT ChiTietDichVu VALUES (?, ?, ?)");
			preparedStatement.setString(1, chiTietDichVu.getDichVu().getMaDichVu());
			preparedStatement.setString(2, chiTietDichVu.getDatPhong().getMaDatPhong());
			preparedStatement.setInt(3, chiTietDichVu.getSoLuong());

			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean capNhatSoLuongDichVu(String maDV, String maDP, int soLuongMua) {
		boolean res = false;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE ChiTietDichVu SET soLuong = ? WHERE dichVu = ? and datPhong = ?");
			preparedStatement.setInt(1, soLuongMua);
			preparedStatement.setString(2, maDV);
			preparedStatement.setString(3, maDP);
			res = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public boolean xoaChiTietDichVu(String maDichVu, String maDatPhong) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("DELETE ChiTietDichVu  " + "FROM   ChiTietDichVu INNER JOIN "
							+ "       DichVu ON ChiTietDichVu.dichVu = DichVu.maDichVu INNER JOIN "
							+ "       DatPhong ON ChiTietDichVu.datPhong = DatPhong.maDatPhong "
							+ "WHERE maDichVu = ? and maDatPhong = ?");
			preparedStatement.setString(1, maDichVu);
			preparedStatement.setString(2, maDatPhong);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

}
