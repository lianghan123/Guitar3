package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.*;
import dao.IGuitar;
import util.DBUtil;

public class GuitarImpl implements IGuitar {

	@Override
	public List<Guitar> getAllGuitar(){
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Guitar";
		List<Guitar> inventory = new ArrayList<Guitar>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String, Enum> properties = new HashMap<String, Enum>();
				properties.put("builder",  Builder.fromString(rs.getString("builder")));
				properties.put("backWood", Wood.fromString(rs.getString("backWood")));
				properties.put("topWood", Wood.fromString(rs.getString("topWood")));
				properties.put("style", Style.fromString(rs.getString("style")));
				properties.put("type", Type.fromString(rs.getString("type")));
				GuitarSpec spec = new GuitarSpec(properties);
				
				Guitar guitar = new Guitar(rs.getString("serialNumber"),rs.getDouble("price"),spec);
				inventory.add(guitar);				
			}			
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e){
		    e.printStackTrace();	
		}finally{
			try{
		         if(Conn!=null)
		        	 Conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return inventory;
	}
	@Override
	public void addGuitar(Guitar guitar){
		String serialNumber=guitar.getSerialNumber();
		double price=guitar.getPrice();
		GuitarSpec spec=guitar.getSpec();
		
		String sql="insert into Guitar (serialNumber,price,";
		for (Iterator i = spec.getProperties().keySet().iterator(); i.hasNext();) {
			String propertyName = (String) i.next();
			sql+=propertyName+",";
		}
		sql=sql.substring(0, sql.length()-1);
		sql+=") values('"+serialNumber+"',"+price+",";
		for (Iterator i = spec.getProperties().keySet().iterator(); i.hasNext();) {
			String propertyName = (String) i.next();
			String propertyValue = spec.getProperty(propertyName).toString();
			sql+="'"+propertyValue+"',";
		}
		sql=sql.substring(0, sql.length()-1);
		sql+=")";

		Connection conn = DBUtil.getSqliteConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Override
	public void deleteGuitar(String serialNumber){
		Connection conn = DBUtil.getSqliteConnection();
		String sql = "delete from Guitar where serialNumber='"+serialNumber+"'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
	}
}

