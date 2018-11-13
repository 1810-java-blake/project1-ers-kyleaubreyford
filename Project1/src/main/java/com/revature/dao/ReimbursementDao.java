package com.revature.dao;

import java.util.List;

import com.revature.dto.ReimbursementTable;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ReimbursementDao {
	ReimbursementDao currentImplementation = new ReimbursementJdbc();

	//Table Methods
	List<ReimbursementTable> tableFindByUserId(int id);
	List<ReimbursementTable> tableFindAll();
	List<ReimbursementTable> tableFindByStatusType(int status);
	
	//Employee Methods
	List<Reimbursement> findByUserId(int id);
	boolean saveReimbursement(Reimbursement r);
	
	
	
	//Admin Methods
	List<Reimbursement> findAll();
	List<Reimbursement> findByStatusType(int status);
	boolean updateStatus(int id);
	boolean approveReimbursement(int index,int approver);
	boolean rejectReimbursement(int index, int approver);
}
