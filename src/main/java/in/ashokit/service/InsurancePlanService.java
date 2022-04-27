package in.ashokit.service;

import java.util.List;

import in.ashokit.request.SearchRequest;
import in.ashokit.response.PlanResponse;

public interface InsurancePlanService {

	public List<PlanResponse> searchPlans(SearchRequest searchRequest);

	public List<String> getUniquePlanNames();

	public List<String> getUniquePlanStatuses();

}
