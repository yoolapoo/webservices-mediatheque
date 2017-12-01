package com.epsi.mediatheque.mapper;

import com.epsi.mediatheque.domain.Loan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface LoanMapper {

    @Select("SELECT * FROM loan WHERE id_loan = #{id}")
    Optional<Loan> findById(String id);

    @Select("SELECT * FROM loan ")
    List<Loan> findAll();

    @Insert("INSERT INTO loan(id_user, id_media) VALUES( #{id_user}, #{id_media})")
    void addLoan(long id_user, long id_media );
}
