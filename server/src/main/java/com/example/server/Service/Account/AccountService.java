package com.example.server.Service.Account;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;
import com.example.server.Repository.IAccountRepository;
import com.example.server.Service.Email.IEmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository iAccountRepository;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private HttpSession httpSession;
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
            accountUpdate.setNumberPhone(accountDTO.getNumberPhone());
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean registerAccount(AccountDTO accountDTO) {
        String otp = generateOtp();
        sendEmailVerification(accountDTO.getEmail(), otp);
       try{
           System.out.println(accountDTO);
           httpSession.setAttribute(otp,accountDTO);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public String loginAccount(AccountDTO accountDTO) {
        Optional<Account> account = iAccountRepository.findByEmail(accountDTO.getEmail());
        if(account.isPresent()) return account.get().getRole();
        return "Account does not exsit!!";
    }

    @Override
    public String generateOtp() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    @Override
    public void sendEmailVerification(String email, String otp) {
        String subject = "Email verification";
        String body ="Your verification otp is: " + otp;
        emailService.sendEmail(email,subject,body);
    }

    @Override
    public AccountDTO verifyOtp(String otp, AccountDTO accountDTO) {
        Account account = new Account();
        Optional<Account> checkAccountExist = iAccountRepository.findByEmail(accountDTO.getEmail());
        if(checkAccountExist.isEmpty()){
            account.setEmail(accountDTO.getEmail());
            account.setRole(accountDTO.getRole());
            try {
                Account accountValid = iAccountRepository.save(account);

                AccountDTO accountReturn = new AccountDTO();
                accountReturn.setId(accountValid.getId());
                accountReturn.setEmail(accountValid.getEmail());
                accountReturn.setRole(accountValid.getRole());
                return accountReturn;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }

        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(checkAccountExist.get().getId());
        accountDTO1.setEmail(checkAccountExist.get().getEmail());
        accountDTO1.setRole(checkAccountExist.get().getRole());
        return accountDTO1;
    }


    public List<AccountDTO> findAllActive() {
        return iAccountRepository.findByActive(true).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<AccountDTO> findByEmail(String email) {
        return iAccountRepository.findByEmail(email).filter(Account::isActive).map(this::convertToDto);
    }

    public Optional<AccountDTO> findById(Long id) {
        return iAccountRepository.findById(id).filter(Account::isActive).map(this::convertToDto);
    }

    public AccountDTO save(AccountDTO accountDto) {
        Account account = convertToEntity(accountDto);
        Account savedAccount = iAccountRepository.save(account);
        return convertToDto(savedAccount);
    }

    public boolean deactivateByEmail(String email) {
        Optional<Account> account = iAccountRepository.findByEmail(email);
        if (account.isPresent()) {
            Account accountToDeactivate = account.get();
            accountToDeactivate.setActive(false);
            iAccountRepository.save(accountToDeactivate);
            return true;
        } else {
            return false;
        }
    }



    private AccountDTO convertToDto(Account account) {
        AccountDTO accountDto = new AccountDTO();
        accountDto.setId(account.getId());
        accountDto.setName(account.getName());
        accountDto.setEmail(account.getEmail());
        accountDto.setPassword(account.getPassword());
        accountDto.setNumberPhone(account.getNumberPhone());
        accountDto.setAddress(account.getAddress());
        accountDto.setBirthDay(account.getBirthDay());
        accountDto.setRole(account.getRole());
        return accountDto;
    }

    private Account convertToEntity(AccountDTO accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setName(accountDto.getName());
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());
        account.setNumberPhone(accountDto.getNumberPhone());
        account.setAddress(accountDto.getAddress());
        account.setBirthDay(accountDto.getBirthDay());
        account.setRole(accountDto.getRole());
        account.setActive(true); // đảm bảo account đc active
        return account;
    }

}
