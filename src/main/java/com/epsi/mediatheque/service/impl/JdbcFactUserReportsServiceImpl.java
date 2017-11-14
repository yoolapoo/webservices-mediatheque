package com.epsi.mediatheque.service.impl;

import com.epsi.mediatheque.domain.FactUserReports;
import com.epsi.mediatheque.mapper.FactUserReportsMapper;
import com.epsi.mediatheque.service.FactUserReportsService;

import java.util.List;

public class JdbcFactUserReportsServiceImpl implements FactUserReportsService {

	private FactUserReportsMapper factUserReportsMapper;

	public JdbcFactUserReportsServiceImpl(FactUserReportsMapper factUserReportsMapper) {
		super();
		this.factUserReportsMapper = factUserReportsMapper;
	}

	@Override
	public List<FactUserReports> findById(String id) {
		return this.factUserReportsMapper.findById(id);
	}

}
