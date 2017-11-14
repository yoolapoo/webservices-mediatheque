package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.dto.DafDataResponse;
import com.epsi.mediatheque.dto.DafListResponse;
import com.epsi.mediatheque.dto.reports.ReportAvailableDto;
import com.epsi.mediatheque.dto.reports.VehiculeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportsController {

	@GetMapping("reports")
	private DafListResponse reportsAvailable() {
		DafListResponse response = new DafListResponse();

		DafDataResponse data = new DafDataResponse();
		data.setId("eco-report");
		data.setType("report");
		ReportAvailableDto reportAvailableDto = new ReportAvailableDto();
		reportAvailableDto.setName("EcoScore");
		reportAvailableDto.setRoute("reports.eco-score");
		data.setAttributes(reportAvailableDto);
		response.getData().add(data);

		data = new DafDataResponse();
		data.setId("plus-report");
		data.setType("report");
		reportAvailableDto = new ReportAvailableDto();
		reportAvailableDto.setName("Report 2");
		reportAvailableDto.setRoute("reports.plus-score");
		data.setAttributes(reportAvailableDto);
		response.getData().add(data);
		
		data = new DafDataResponse();
		data.setId("test-eco-report");
		data.setType("report");
		reportAvailableDto = new ReportAvailableDto();
		reportAvailableDto.setName("Report 3");
		reportAvailableDto.setRoute("reports.test-eco-score");
		data.setAttributes(reportAvailableDto);
		response.getData().add(data);

		return response;
	}

	@GetMapping("vehicules")
	private DafListResponse vehiculesAvailable() {
		DafListResponse response = new DafListResponse();

		String name = "All";
		DafDataResponse data = new DafDataResponse();
		data.setId(name);
		data.setType("vehicule");
		VehiculeDto vehiculeDto = new VehiculeDto();
		vehiculeDto.setValue(name);
		data.setAttributes(vehiculeDto);
		response.getData().add(data);
		
		for (int i = 0; i < 100; i++) {
			name = "Vehicule ID 0" + i;
			data = new DafDataResponse();
			data.setId(name);
			data.setType("vehicule");
			vehiculeDto = new VehiculeDto();
			vehiculeDto.setValue(name);
			data.setAttributes(vehiculeDto);
			response.getData().add(data);
		}

		return response;
	}
}
