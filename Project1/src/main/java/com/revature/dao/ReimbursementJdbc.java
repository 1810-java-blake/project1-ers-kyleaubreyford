package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dto.ReimbursementTable;
import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementJdbc implements ReimbursementDao {

	private Logger log = Logger.getRootLogger();
	
	private String select = "SELECT id, author.username AS ausername,amount,description, type,submitted,receipt,status,resolver.username AS rusername,resolved "
			+ 				"FROM reimbursement INNER JOIN reimbursement_status USING (statusid) "
			+ 				"INNER JOIN reimbursement_type USING (typeid) INNER JOIN users AS author ON (reimbursement.author = author.usersid) "
			+ 				"LEFT JOIN users AS resolver ON (reimbursement.resolver = resolver.usersid)";
	
	
	private Reimbursement extractFromResultSet(ResultSet rs) throws SQLException {
		return new Reimbursement(rs.getInt("id"),rs.getInt("amount"), rs.getTimestamp("submitted"),
				rs.getTimestamp("resolved"), rs.getString("description"),rs.getString("receipt"),
				rs.getInt("author"),rs.getInt("resolver"),rs.getInt("statusid"),rs.getInt("typeid"));
	}
	
	private ReimbursementTable extractFromResultSetTable(ResultSet rs) throws SQLException {
		String submitted = "";
		String resolved = "";
		try {
			submitted = rs.getTimestamp("submitted").toString();
		}catch(NullPointerException e){
			log.debug("Submitted timestamp not found");
		}
		
		try {
			resolved = rs.getTimestamp("resolved").toString();
		}catch(NullPointerException e){
			//log.debug("Submitted timestamp not found");
		}
		return new ReimbursementTable(rs.getInt("id"),rs.getString("ausername"),rs.getInt("amount"),
				rs.getString("description"),rs.getString("type"),submitted,rs.getString("receipt"),
				rs.getString("status"),rs.getString("rusername"),resolved);
	}
	
	
	@Override
	public List<Reimbursement> findByUserId(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reimbursement WHERE author = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractFromResultSet(rs));
			}
			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveReimbursement(Reimbursement r) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			Statement s = conn.createStatement();
			s.execute("Select MAX(id) FROM reimbursement");
			ResultSet rsId = s.getResultSet(); //

			if (rsId.next()) {
				r.setId(rsId.getInt(1) + 1);
			}

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO reimbursement("
					+ "id,amount,submitted,"
					+ "resolved,description,"
					+ "receipt,author,"
					+ "statusid,"
					+ "typeid) VAlUES (?,?,?,?,?,?,?,?,?);");
			
			
			ps.setInt(1, r.getId());
			ps.setInt(2, r.getAmount());
			ps.setTimestamp(3, r.getSubmitted());
			ps.setTimestamp(4, r.getResolved());
			ps.setString(5, r.getDescription());
			ps.setString(6, r.getReceipt());
			ps.setInt(7, r.getAuthor());
			ps.setInt(8, r.getStatusId());
			ps.setInt(9, r.getTypeId());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reimbursement> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reimbursement");
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractFromResultSet(rs));
			}
			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> findByStatusType(int status) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reimbursement WHERE statusid = ?");
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractFromResultSet(rs));
			}
			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateStatus(int id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<ReimbursementTable> tableFindByUserId(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(select + "WHERE author = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<ReimbursementTable> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractFromResultSetTable(rs));
			}
			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ReimbursementTable> tableFindAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(select + " ORDER BY id");
			ResultSet rs = ps.executeQuery();
			List<ReimbursementTable> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractFromResultSetTable(rs));
			}
			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ReimbursementTable> tableFindByStatusType(int status) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(select + " WHERE statusid = ?");
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			List<ReimbursementTable> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractFromResultSetTable(rs));
			}
			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean approveReimbursement(int index,int approver) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET statusid = 2,resolved = ?,resolver = ? WHERE id = ?");
			ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			ps.setInt(2, approver);
			ps.setInt(3, index);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean rejectReimbursement(int index,int approver) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET statusid = 3,resolved = ?,resolver = ? WHERE id = ?");
			ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			ps.setInt(2, approver);
			ps.setInt(3, index);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
