package com.example.server.Service.Account;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;

import java.util.Optional;

public interface IAccountService {
    public boolean isAccountExist(Long id);
    public boolean isSamePhone(String phone);
    public boolean updateNewestInfoForAccount(AccountDTO accountDTO);

}
