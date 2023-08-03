package com.projeto.pessoal.repositories;

import com.projeto.pessoal.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.name LIKE LOWER(CONCAT ('%', :name, '%'))")
    Page<Account> findByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT a FROM Account a WHERE a.userName = :userName")
    Account findByUsername(@Param("userName") String username);
}
