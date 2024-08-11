package in.rajiv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.rajiv.dto.SearchRequest;
import in.rajiv.entity.CitizenPlan;


public interface RequestService {

	public List<String> getPlanName();
	public List<String> getPlanStatus();
	public List<CitizenPlan> Search(SearchRequest req);
	public boolean exportPdf();
	public boolean exportExcel();
}
