package CrudApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class BankingOperations {
	private static final long balance = 0;
	private static String host = "jdbc:mysql://localhost:3306/bankdata";
	private static String userName = "root";
	private static String userPassword = "Agni@2001";

	private static Connection toCreateConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(host, userName, userPassword);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static void toCreateAnAccount(Bank1 obj) {
		try { //AccountName, MobileNumber, AccountBalance, TypeOfAccount
			Connection con = toCreateConnection();
			String query = "Insert into bankdata (AccountName, MobileNumber, AccountBalance, TypeOfAccount) values (?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, obj.getAccountName());
			stmt.setLong(2, obj.getMobileNumber());
			stmt.setFloat(3, obj.getAccountBalance());
			stmt.setString(4, obj.getTypeOfAccount());
			stmt.executeUpdate();
			con.close();
			System.out.println("Account Created successfully\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toRemoveAnAccount(int id) {
		try {
			if (BankExists(id)) {
				Connection con = toCreateConnection();
				PreparedStatement stmt = con.prepareStatement("Delete from bankdata where AccountID = ?");
				stmt.setInt(1, id);
				stmt.executeUpdate();
				con.close();
				System.out.println("Account is Removed Successfully ");
			} else {
				System.out.println("Account does not exist with this id !!!!!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean BankExists(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from bankdata  where AccountID = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			return rowData.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void toUpdateAccountName(String name, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update bankdata set AccountName = ? where AccountID = ?");
			stmt.setString(1, name);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateAccountBalance(long balance2, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con
					.prepareStatement("Update bankdata set AccountBalance = ? where AccountID = ?");
			stmt.setLong(1, balance2);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateMobileNumber(long number, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update bankdata set MobileNumber = ? where AccountID = ?");
			stmt.setLong(1, number);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateTypeOfAccount(String Type, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con
					.prepareStatement("Update bankdata set TypeOfAccount = ? where AccountID = ?");
			stmt.setString(1, Type);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Bank1 toGetbankData(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from bankdata where AccountID = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			rowData.next();
			Bank1 obj = new Bank1();
			obj.setAccountID(rowData.getInt(1));
			obj.setAccountName(rowData.getString(2));
			obj.setMobileNumber(rowData.getLong(3));
			obj.setAccountBalance(rowData.getFloat(4));
			obj.setTypeOfAccount(rowData.getString(5));
			con.close();
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HashMap<Integer, Bank1> toDisplayAccountData() {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from bankdata");

			ResultSet rowData = stmt.executeQuery();
			HashMap<Integer, Bank1> bankData = new HashMap<>();
			while (rowData.next()) {
				Bank1 obj = new Bank1();
				obj.setAccountID(rowData.getInt(1));
				obj.setAccountName(rowData.getString(2));
				obj.setMobileNumber(rowData.getLong(3));
				obj.setAccountBalance(rowData.getFloat(4));
				obj.setTypeOfAccount(rowData.getString(5));
				bankData.put(obj.getAccountID(), obj);
			}
			con.close();
			return bankData;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
