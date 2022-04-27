package in.ashokit.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.reports.ExcelReportGenerator;
import in.ashokit.reports.PdfReportGenerator;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.PlanResponse;
import in.ashokit.service.InsurancePlanService;


@RestController
public class InsuranceRestController {
	
	@Autowired
	private InsurancePlanService insuranceService;
	
	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> planNames = insuranceService.getUniquePlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}

	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getPlanStatuses() {
		List<String> statusList = insuranceService.getUniquePlanStatuses();
		return new ResponseEntity<>(statusList, HttpStatus.OK);
	}

	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> searchPlans(@RequestBody SearchRequest searchRequest) {
		List<PlanResponse> plans = insuranceService.searchPlans(searchRequest);
		return new ResponseEntity<>(plans, HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.xlsx";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> plans = insuranceService.searchPlans(null);

		ExcelReportGenerator generator = new ExcelReportGenerator();
		generator.export(plans, response);
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.pdf";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> plans = insuranceService.searchPlans(null);

		PdfReportGenerator generator = new PdfReportGenerator();
		generator.export(plans, response);
	}

}
