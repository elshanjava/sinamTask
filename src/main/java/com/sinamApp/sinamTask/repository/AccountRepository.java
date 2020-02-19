package com.sinamApp.sinamTask.repository;

import com.sinamApp.sinamTask.model.AccountEntity;
import com.sinamApp.sinamTask.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findAllByUserId(@Param("active") UserEntity userId);
    AccountEntity findByAccountNumber(Long accountNumber);

}
