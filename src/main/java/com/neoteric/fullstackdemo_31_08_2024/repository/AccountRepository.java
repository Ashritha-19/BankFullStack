package com.neoteric.fullstackdemo_31_08_2024.repository;

import com.neoteric.fullstackdemo_31_08_2024.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {


   @Query("select a from AccountEntity a where a.accountNumber=: accountNumber and a.pan=: pan")
   AccountEntity  getAccountNumber(@Param("accountnumber")String accountNumber,
                                   @Param("pan")String pan);

    AccountEntity findByAccountNumberAndPan(@Param("accountNumber") String accountNumber,
                                            @Param("pan")String pan);



}
