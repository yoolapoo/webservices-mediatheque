package com.epsi.mediatheque.service.impl;

import com.epsi.mediatheque.domain.UserReport;
import com.epsi.mediatheque.mapper.UserReportsMapper;
import com.epsi.mediatheque.service.UserReportsService;

import java.util.List;

public class JdbcUserReportsServiceImpl implements UserReportsService {

	private UserReportsMapper userReportsMapper;

	public JdbcUserReportsServiceImpl(UserReportsMapper userReportsMapper) {
		super();
		this.userReportsMapper = userReportsMapper;
	}

	@Override
	public List<UserReport> findById(String id) {
		return this.userReportsMapper.findById(id);

	}

}
