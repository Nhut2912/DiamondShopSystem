package com.example.server.Repository;

import com.example.server.Pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
 * author : Tran Viet Hoang
 * date : 23/5/2024
 * purpose : tạo repository để xử lí account
 *
 */
@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {
    Account findByEmail(String email);
}
