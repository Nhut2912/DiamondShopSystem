package com.example.server.Service.Account;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository iAccountRepository;
    @Override
    public boolean isAccountExist(Long id) {
        System.out.println(id);
        Optional<Account> accountNeedToCheck = iAccountRepository.findById(id);
        System.out.println(accountNeedToCheck.isPresent());
        return accountNeedToCheck.isPresent();
    }
    public boolean isSamePhone(String phone){
        Optional<Account> account = iAccountRepository.findByNumberPhone(phone);
        if(account.isPresent()) return  true;
        else return false;
    }
    public boolean updateNewestInfoForAccount(AccountDTO accountDTO){
        try {
            Optional<Account> account = iAccountRepository.findById(accountDTO.getId());
            Account accountUpdate = account.get();
            accountUpdate.setName(accountDTO.getName());
            accountUpdate.setAddress(accountDTO.getAddress());
            accountUpdate.setBirthDay(accountDTO.getBirthDay());
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }

}
