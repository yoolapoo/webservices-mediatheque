package com.epsi.mediatheque.service;

import com.epsi.mediatheque.domain.FactUserReports;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FactUserReportsService {

	List<FactUserReports> findById(String id);
}
