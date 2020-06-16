package web.application.project.dao.impl;

import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.application.project.dao.CustomerDAO;
import web.application.project.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session session=sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> theQuery=
				session.createQuery("from Customer",Customer.class);
		
		//get resultlist		
		List<Customer> customers=theQuery.getResultList();
		
		return customers;
	}

}
