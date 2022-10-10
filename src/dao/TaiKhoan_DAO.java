package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	private TaiKhoan getTaiKhoan(ResultSet resultSet) throws SQLException {
		String maTaiKhoan = resultSet.getString(1);
		String matKhau = resultSet.getString(2);

		return new TaiKhoan(maTaiKhoan, matKhau);
	}

	public boolean capNhatMatKhau(TaiKhoan taiKhoan) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE TaiKhoan SET matKhau = ? WHERE maTaiKhoan = ?");
			preparedStatement.setString(1, taiKhoan.getMatKhau());
			preparedStatement.setString(2, taiKhoan.getMaTaiKhoan());

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public TaiKhoan getTaiKhoan(String maNhanVien) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM TaiKhoan WHERE maTaiKhoan = ?");
			preparedStatement.setString(1, maNhanVien);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getTaiKhoan(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
