package com.cestar.controller;

import java.util.List;
import java.util.Map;

import com.cestar.dao.*;
import com.cestar.model.BankAccount;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;





@SessionScoped
@ManagedBean(name="ctr")
public class Controller {

AccountDao dao=new AccountDao();
	
	int old_id=0;
	
	public List<BankAccount> displayRecs(){
		List<BankAccount> empz = dao.SelectAllEmployee();
		return empz;
	}
	
	public String insertRecs(BankAccount acc_from_insert_page) {
		dao.insertRec(acc_from_insert_page);
		return "display";
	}
	
	public String editRec(int id_from_display_page) {
		old_id = id_from_display_page;
		System.out.println("neeeleshs");
		System.out.println(old_id);		
		BankAccount emp_from_db=dao.getAccById(old_id);
		Map<String,Object> session_map=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();	
		session_map.put("acc_to_edit",emp_from_db);
		return "edit";
	
	}
	
	public String updateRec(BankAccount updated_emp_from_edit_page) {
		System.out.println(updated_emp_from_edit_page);
		dao.updateAcc(old_id, updated_emp_from_edit_page);
		return "display";
	}
	
	public String deleteRec(int id_from_display_page) {
		dao.delete(id_from_display_page);
		return "display";
	}
	
}
