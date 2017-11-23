package com.epsi.mediatheque.service;

import org.springframework.stereotype.Service;

@Service
public interface LoanService {

    String addLoan(long id_user, long id_media);

}
