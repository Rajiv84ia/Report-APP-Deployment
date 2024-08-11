package in.rajiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.rajiv.dto.SearchRequest;
import in.rajiv.entity.CitizenPlan;
import in.rajiv.repository.CitizenPlanRepository;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private CitizenPlanRepository repo;
	
	@Override
	public List<String> getPlanName() {
		// TODO Auto-generated method stub
		
		return repo.getPlanName();
	}

	@Override
	public List<String> getPlanStatus() {
		// TODO Auto-generated method stub
		return repo.getPlanStatus();
	}

	public List<CitizenPlan> Search(SearchRequest req) {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}


}
