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
		
		//create a query ... sort by last name
		Query<Customer> theQuery=
				session.createQuery("from Customer order by lastName",Customer.class);
		
		//get resultlist		
		List<Customer> customers=theQuery.getResultList();
		
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get the current hibernate session
		Session session=sessionFactory.getCurrentSession();
		
		//saveor update the customer
		session.saveOrUpdate(theCustomer);
		
		
	}


	@Override
	public Customer getCustomers(int theId) {
		
		//get the current hibernate session
		 Session session=sessionFactory.getCurrentSession();
		
		 //retrieve from database using primary key
		 Customer theCustomer=session.get(Customer.class, theId);
		 
		 //return the result		
		 return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		
		//get the current hibernate session
		 Session session=sessionFactory.getCurrentSession();
		
		 //delete object with primary key
		 Query theQuery=session.createQuery("delete from Customer where id=:theCustomerId");
		 
		 theQuery.setParameter("theCustomerId", theId);
		 
	     theQuery.executeUpdate();
		
	}


	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get the current hibernate session
        Session session = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =session.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
        
	}

}
