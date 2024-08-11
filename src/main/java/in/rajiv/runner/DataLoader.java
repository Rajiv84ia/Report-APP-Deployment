package in.rajiv.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.rajiv.entity.CitizenPlan;

import in.rajiv.repository.CitizenPlanRepository;
@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private CitizenPlanRepository repo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
repo.deleteAll();
		
		CitizenPlan c1 = new CitizenPlan();	
		c1.setName("john");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setStartDate(LocalDate.now());
		c1.setEndDate(LocalDate.now().plusMonths(6));
		
		CitizenPlan c2 = new CitizenPlan();	
		c2.setName("Raja");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		
		CitizenPlan c3 = new CitizenPlan();	
		c3.setName("Sita");
		c3.setGender("Female");
		c3.setPlanName("Food");
		c3.setPlanStatus("Approved");
		c3.setStartDate(LocalDate.now());
		c3.setEndDate(LocalDate.now().plusMonths(6));
		
		CitizenPlan c4 = new CitizenPlan();	
		c4.setName("Gita");
		c4.setGender("Female");
		c4.setPlanName("Food");
		c4.setPlanStatus("Denied");
		
		List<CitizenPlan> list= Arrays.asList(c1,c2,c3,c4);
		repo.saveAll(list);
		
	}

}
