package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.LoaiDichVu;

public class DichVu_DAO {
	private LoaiDichVu_DAO loaiDichVu_DAO;

	public DichVu_DAO() {
		loaiDichVu_DAO = new LoaiDichVu_DAO();
	}

	public boolean capNhatSoLuongDichVuTang(String maDV, int soLuong) {
		boolean res = false;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE DichVu SET soLuong += ? WHERE maDichVu = ?");
			preparedStatement.setInt(1, soLuong);
			preparedStatement.setString(2, maDV);
			res = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public List<DichVu> filterDichVu(String tenDichVu, String tenLoaiDichVu, String soLuong) {
		List<DichVu> list = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		try {
			if (soLuong == "") {
				preparedStatement = ConnectDB.getConnection()
						.prepareStatement("SELECT * FROM   DichVu INNER JOIN "
								+ "       LoaiDichVu ON DichVu.loaiDichVu = LoaiDichVu.maLoaiDichVu "
								+ "WHERE tenDichVu like ? and tenLoaiDichVu like ?");

				preparedStatement.setString(1, "%" + tenDichVu + "%");
				preparedStatement.setString(2, "%" + tenLoaiDichVu + "%");
				ResultSet resultSet = preparedStatement.executeQuery();
				DichVu dichVu;
				while (resultSet.next()) {
					dichVu = getDichVu(resultSet);
					list.add(dichVu);
				}

				resultSet.close();
			} else {
				if (soLuong.equals("<50")) {
					preparedStatement = ConnectDB.getConnection()
							.prepareStatement("SELECT * FROM   DichVu INNER JOIN "
									+ "       LoaiDichVu ON DichVu.loaiDichVu = LoaiDichVu.maLoaiDichVu "
									+ "WHERE tenDichVu like ? and tenLoaiDichVu like ? and soLuong <= 50");
				} else if (soLuong.equals("50-100")) {
					preparedStatement = ConnectDB.getConnection().prepareStatement("SELECT * FROM   DichVu INNER JOIN "
							+ "       LoaiDichVu ON DichVu.loaiDichVu = LoaiDichVu.maLoaiDichVu "
							+ "WHERE tenDichVu like ? and tenLoaiDichVu like ? and soLuong > 50 and soLuong <= 100");
				} else if (soLuong.equals("100-200")) {
					preparedStatement = ConnectDB.getConnection().prepareStatement("SELECT * FROM   DichVu INNER JOIN "
							+ "       LoaiDichVu ON DichVu.loaiDichVu = LoaiDichVu.maLoaiDichVu "
							+ "WHERE tenDichVu like ? and tenLoaiDichVu like ?  and soLuong > 100 and soLuong <= 200");
				} else if (soLuong.equals(">200")) {
					preparedStatement = ConnectDB.getConnection()
							.prepareStatement("SELECT * FROM   DichVu INNER JOIN "
									+ "       LoaiDichVu ON DichVu.loaiDichVu = LoaiDichVu.maLoaiDichVu "
									+ "WHERE tenDichVu like ? and tenLoaiDichVu like ? and soLuong > 200");
				}
				preparedStatement.setString(1, "%" + tenDichVu + "%");
				preparedStatement.setString(2, "%" + tenLoaiDichVu + "%");
				ResultSet resultSet = preparedStatement.executeQuery();
				DichVu dichVu;
				while (resultSet.next()) {
					dichVu = getDichVu(resultSet);
					list.add(dichVu);
				}

				resultSet.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<DichVu> getAllDichVu() {
		List<DichVu> list = new ArrayList<>();
		Statement statement;

		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM DichVu");
			DichVu dichVu;
			while (resultSet.next()) {
				dichVu = getDichVu(resultSet);
				list.add(dichVu);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<DichVu> getAllDichVuCoSoLuongLonHon0() {
		List<DichVu> list = new ArrayList<>();

		Statement statement;
		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM DichVu  WHERE soLuong > 0 ");
			DichVu dichVu;
			while (resultSet.next()) {
				dichVu = getDichVu(resultSet);
				list.add(dichVu);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private DichVu getDichVu(ResultSet resultSet) throws SQLException {
		String maDV = resultSet.getString(1);
		String tenDV = resultSet.getString(2);
		String soLuong = resultSet.getString(3);
		String donViTinh = resultSet.getString(4);
		String loai = resultSet.getString(5);
		String giaMua = resultSet.getString(6);
		LoaiDichVu loaiDichVu = loaiDichVu_DAO.getLoaiDichVu(loai);

		return new DichVu(maDV, tenDV, Integer.valueOf(soLuong), donViTinh, loaiDichVu, Double.valueOf(giaMua));
	}

	public DichVu getDichVuTheoMa(String maDichVu) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM DichVu WHERE maDichVu = ?");
			preparedStatement.setString(1, maDichVu);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getDichVu(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<DichVu> getDichVuTheoMaVaLoai(String maDichVu, String loaiDichVu) {
		List<DichVu> list = new ArrayList<>();

		try {

			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * " + "FROM   DichVu INNER JOIN "
							+ " LoaiDichVu ON DichVu.loaiDichVu = LoaiDichVu.maLoaiDichVu "
							+ "WHERE tenDichVu like ? and tenLoaiDichVu like ? and soLuong > 0");

			preparedStatement.setString(1, "%" + maDichVu + "%");
			preparedStatement.setString(2, "%" + loaiDichVu + "%");

			ResultSet resultSet = preparedStatement.executeQuery();
			DichVu dichVu;
			while (resultSet.next()) {
				dichVu = getDichVu(resultSet);
				list.add(dichVu);
			}

			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public DichVu getDichVuTheoTen(String tenDichVu) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM DichVu WHERE tenDichVu = ?");
			preparedStatement.setString(1, tenDichVu);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getDichVu(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean suaDichVu(DichVu dichVu) {

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE DichVu SET tenDichVu = ?, soLuong = ?, donViTinh = ?, loaiDichVu = ?, giaMua= ? WHERE maDichVu = ?");
			preparedStatement.setString(1, dichVu.getTenDichVu());
			preparedStatement.setInt(2, dichVu.getSoLuong());
			preparedStatement.setString(3, dichVu.getDonViTinh());
			preparedStatement.setString(4, dichVu.getLoaiDichVu().getMaLoaiDichVu());
			preparedStatement.setDouble(5, dichVu.getGiaMua());
			preparedStatement.setString(6, dichVu.getMaDichVu());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean themDichVu(DichVu dichVu) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT DichVu VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, dichVu.getMaDichVu());
			preparedStatement.setString(2, dichVu.getTenDichVu());
			preparedStatement.setInt(3, dichVu.getSoLuong());
			preparedStatement.setString(4, dichVu.getDonViTinh());
			preparedStatement.setString(5, dichVu.getLoaiDichVu().getMaLoaiDichVu());
			preparedStatement.setDouble(6, dichVu.getGiaMua());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean xoaDichVu(String maDichVu) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("DELETE DichVu WHERE maDichVu = ?");
			preparedStatement.setString(1, maDichVu);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

}
