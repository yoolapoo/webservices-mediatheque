package com.epsi.mediatheque.service;

import com.epsi.mediatheque.domain.UserReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserReportsService {

	List<UserReport> findById(String id);
	
}
