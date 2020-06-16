package web.application.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.application.project.dao.CustomerDAO;
import web.application.project.entity.Customer;
import web.application.project.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		List<Customer> theCustomers=customerService.getCustomers();
		
		theModel.addAttribute("customers",theCustomers);
		
		return "list-customers";
	}
}
