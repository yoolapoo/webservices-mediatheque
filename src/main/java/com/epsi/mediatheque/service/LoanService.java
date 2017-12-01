package com.epsi.mediatheque.service;

import org.springframework.stereotype.Service;

@Service
public interface LoanService {

    void addLoan(long id_user, long id_media);

    void deleteLoan(long id_user, long id_media);

}
