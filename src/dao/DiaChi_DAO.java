package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Phuong;
import entity.Quan;
import entity.Tinh;

public class DiaChi_DAO {
	public List<Phuong> getPhuong(Quan quan) {
		List<Phuong> list = new ArrayList<>();

		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement("SELECT * FROM Phuong WHERE quan = ?");
			preparedStatement.setString(1, quan.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(new Phuong(resultSet.getString(1), resultSet.getString(2), quan));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<Quan> getQuan(Tinh tinh) {
		List<Quan> list = new ArrayList<>();

		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement("SELECT * FROM Phuong WHERE quan = ?");
			preparedStatement.setString(1, tinh.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				list.add(new Quan(resultSet.getString(1), resultSet.getString(2), tinh));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<Tinh> getTinh() {
		List<Tinh> list = new ArrayList<>();

		try {
			Statement statement =  ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Tinh");
			
			while (resultSet.next())
				list.add(new Tinh(resultSet.getString(1), resultSet.getString(2)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
