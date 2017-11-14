package com.epsi.mediatheque.controller;

import com.epsi.mediatheque.dto.DafDataResponse;
import com.epsi.mediatheque.dto.DafListResponse;
import com.epsi.mediatheque.service.UserReportsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.epsi.mediatheque.security.SecurityConstants.HEADER_STRING;

@RestController
public class UserReportsController extends ParentController {

	private UserReportsService userReportsService;

	public UserReportsController(UserReportsService userReportsService) {
		super();
		this.userReportsService = userReportsService;
	}

	@GetMapping("userReports")
	private DafListResponse userReports(@RequestHeader(value = HEADER_STRING) String token) {
		DafListResponse response = new DafListResponse();
		String id = retrieveSubFromToken(token);

		this.userReportsService.findById(id).forEach(c -> {
			DafDataResponse dafDataResponse = new DafDataResponse();
			dafDataResponse.setId(c.getReport());
			dafDataResponse.setType("user-report");
			dafDataResponse.setAttributes(c);
			response.getData().add(dafDataResponse);
		});
		
		return response;
	}
}
