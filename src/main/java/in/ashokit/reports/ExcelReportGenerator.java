package in.ashokit.reports;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.ashokit.response.PlanResponse;

public class ExcelReportGenerator {

	public void export(List<PlanResponse> plans, HttpServletResponse response) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Insurance Plans");

		XSSFRow headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("Plan ID");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Benefit Amount");

		for (int i = 0; i < plans.size(); i++) {

			XSSFRow dataRow = sheet.createRow(i + 1);

			PlanResponse plan = plans.get(i);

			dataRow.createCell(0).setCellValue(plan.getPlanId());
			dataRow.createCell(1).setCellValue(plan.getHolderName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getBenefitAmount());
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
