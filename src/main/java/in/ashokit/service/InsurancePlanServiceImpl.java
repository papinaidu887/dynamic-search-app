package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.InsurancePlan;
import in.ashokit.repository.InsurancePlanRepository;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.PlanResponse;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {
	
	
	@Autowired
	private InsurancePlanRepository repo;

	@Override
	public List<PlanResponse> searchPlans(SearchRequest req) {
		
		InsurancePlan entity = new InsurancePlan();

		if (null != req && req.getPlanName() != null && !req.getPlanName().equals("")) {
			entity.setPlanName(req.getPlanName());
		}
		if (null != req && req.getPlanStatus() != null && !req.getPlanStatus().equals("")) {
			entity.setPlanStatus(req.getPlanStatus());
		}

		Example<InsurancePlan> example = Example.of(entity);
		List<InsurancePlan> findAll = repo.findAll(example);

		List<PlanResponse> plansData = new ArrayList<>();
		for (InsurancePlan ip : findAll) {
			PlanResponse plan = new PlanResponse();
			BeanUtils.copyProperties(ip, plan);
			plansData.add(plan);
		}
		return plansData;
	}

	@Override
	public List<String> getUniquePlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatuses() {
		return repo.getPlanStatuses();
	}

}
