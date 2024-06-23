package com.example.server.Service.Account;

import com.example.server.Model.AccountDTO;
import com.example.server.Pojo.Account;
import com.example.server.Pojo.Order;
import com.example.server.Repository.IAccountRepository;
import com.example.server.Service.Email.IEmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {
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

    @Override
    public String createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        account.setName(accountDTO.getName());
        account.setNumberPhone(accountDTO.getNumberPhone());
        account.setRole(accountDTO.getRole());
        account.setBirthDay(accountDTO.getBirthDay());
        account.setAddress(accountDTO.getAddress());
        try {
            iAccountRepository.save(account);
            return account.getRole();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "Cannot Create Account";
    }

    @Override
    public String isStaffOrAdmin(AccountDTO accountDTO) {

        Optional<Account> account = iAccountRepository.findByNameAndPassword(accountDTO.getName(), accountDTO.getPassword());;
        try {
            account.orElseThrow(() -> new ClassNotFoundException("Account is not valid!!"));
            return account.get().getRole();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return "Not Valid";
        }

    }

    public boolean isSamePhone(String phone) {
        Optional<Account> account = iAccountRepository.findByNumberPhone(phone);
        if (account.isPresent()) return true;
        else return false;
    }

    public boolean updateNewestInfoForAccount(AccountDTO accountDTO) {
        try {
            Optional<Account> account = iAccountRepository.findById(accountDTO.getId());
            Account accountUpdate = account.get();
            accountUpdate.setName(accountDTO.getName());
            accountUpdate.setAddress(accountDTO.getAddress());
            accountUpdate.setBirthDay(accountDTO.getBirthDay());
            accountUpdate.setNumberPhone(accountDTO.getNumberPhone());
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean registerAccount(AccountDTO accountDTO) {
        String otp = generateOtp();
        sendEmailVerification(accountDTO.getEmail(), otp);
        try {
            System.out.println(accountDTO);
            httpSession.setAttribute(otp, accountDTO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String loginAccount(AccountDTO accountDTO) {
        Optional<Account> account = iAccountRepository.findByEmail(accountDTO.getEmail());
        if (account.isPresent()) return account.get().getRole();
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
        String body = "Your verification otp is: " + otp;
        emailService.sendEmail(email, subject, body);
    }

    @Override
    public AccountDTO verifyOtp(String otp, AccountDTO accountDTO) {
        Account account = new Account();
        Optional<Account> checkAccountExist = iAccountRepository.findByEmail(accountDTO.getEmail());
        if (checkAccountExist.isEmpty()) {
            account.setEmail(accountDTO.getEmail());
            account.setRole(accountDTO.getRole());
            try {
                Account accountValid = iAccountRepository.save(account);

                AccountDTO accountReturn = new AccountDTO();
                accountReturn.setId(accountValid.getId());
                accountReturn.setEmail(accountValid.getEmail());
                accountReturn.setRole(accountValid.getRole());
                return accountReturn;
            } catch (Exception e) {
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


    public AccountDTO updateAccount(Long accountId, AccountDTO accountDetails) {
        Optional<Account> optionalAccount = iAccountRepository.findById(accountId).filter(Account::isActive);
        if (optionalAccount.isPresent()) {
            Account accountToUpdate = optionalAccount.get();
            accountToUpdate.setName(accountDetails.getName());
            accountToUpdate.setPassword(accountDetails.getPassword());
            accountToUpdate.setNumberPhone(accountDetails.getNumberPhone());
            accountToUpdate.setAddress(accountDetails.getAddress());
            Account updatedAccount = iAccountRepository.save(accountToUpdate);
            return convertToDto(updatedAccount);
        }
        return null;
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
        accountDto.setActive(account.isActive());
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

    public Map<LocalDate, Long> getNewAccountStatisticByWeek() throws ParseException{
        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.minusDays(7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());
        List<Account> accountList = iAccountRepository.getAccountByStartDateAndEndDate(startDate, endDate);
        Map<String, Long> accountCountMap = accountList.stream()
                .collect(Collectors.groupingBy(
                        account -> {
                            try {
                                return formatter.format(formatter.parse(account.getDateAdd().toString()));
                            } catch (ParseException e) {
                                System.out.println(e.getMessage() + "Error format");
                                throw new RuntimeException(e);
                            }
                        }, // Chuyển đổi Date thành String
                        Collectors.counting()
                ));
        Map<LocalDate, Long> resultDate = new HashMap<>();

        for (LocalDate date = beforeDate; !date.isAfter(currentDate); date = date.plusDays(1)) {
            long count = accountCountMap.getOrDefault(formatter.format(formatter.parse(date.toString())), 0L);
            resultDate.put(date, count);
        }
        System.out.println(resultDate.size());
        return resultDate;
    }

    public Map<LocalDate, Long> getNewAccountStatisticByMonth() throws ParseException {

        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate.getMonth().getValue());
        LocalDate beforeDate;
        if (currentDate.getMonth().getValue() == 4 || currentDate.getMonth().getValue() == 6 ||
                currentDate.getMonth().getValue() == 9 || currentDate.getMonth().getValue() == 11) {
            beforeDate = currentDate.minusDays(30);
        } else if (Year.of(currentDate.getYear()).isLeap() && currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(29);
        } else if (currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(28);
        } else {
            beforeDate = currentDate.minusDays(31);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());

        List<Account> accountList = iAccountRepository.getAccountByStartDateAndEndDate(startDate, endDate);
        Map<String, Long> accountCountMap = accountList.stream()
                .collect(Collectors.groupingBy(
                        account -> {
                            try {
                                return formatter.format(formatter.parse(account.getDateAdd().toString()));
                            } catch (ParseException e) {
                                System.out.println(e.getMessage() + "Error format");
                                throw new RuntimeException(e);
                            }
                        }, // Chuyển đổi Date thành String
                        Collectors.counting()
                ));
        Map<LocalDate, Long> resultDate = new HashMap<>();

        for (LocalDate date = beforeDate; !date.isAfter(currentDate); date = date.plusDays(1)) {
            long count = accountCountMap.getOrDefault(formatter.format(formatter.parse(date.toString())), 0L);
            resultDate.put(date, count);
        }
        System.out.println(resultDate.size());
        return resultDate;
    }

    public long getSumNewAccountStatisticByWeek() throws ParseException{
        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.minusDays(7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());
        List<Account> accountList = iAccountRepository.getAccountByStartDateAndEndDate(startDate, endDate);
        Map<String, Long> accountCountMap = accountList.stream()
                .collect(Collectors.groupingBy(
                        account -> {
                            try {
                                return formatter.format(formatter.parse(account.getDateAdd().toString()));
                            } catch (ParseException e) {
                                System.out.println(e.getMessage() + "Error format");
                                throw new RuntimeException(e);
                            }
                        }, // Chuyển đổi Date thành String
                        Collectors.counting()
                ));
        long sumToTalOrders = 0;
        for (Map.Entry<String, Long> entry : accountCountMap.entrySet()) {
            sumToTalOrders += entry.getValue();
        }

        return sumToTalOrders;
    }
    public long getSumNewAccountStatisticByMonth() throws ParseException{
        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate;
        if (currentDate.getMonth().getValue() == 4 || currentDate.getMonth().getValue() == 6 ||
                currentDate.getMonth().getValue() == 9 || currentDate.getMonth().getValue() == 11) {
            beforeDate = currentDate.minusDays(30);
        } else if (Year.of(currentDate.getYear()).isLeap() && currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(29);
        } else if (currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(28);
        } else {
            beforeDate = currentDate.minusDays(31);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());
        List<Account> accountList = iAccountRepository.getAccountByStartDateAndEndDate(startDate, endDate);
        Map<String, Long> accountCountMap = accountList.stream()
                .collect(Collectors.groupingBy(
                        account -> {
                            try {
                                return formatter.format(formatter.parse(account.getDateAdd().toString()));
                            } catch (ParseException e) {
                                System.out.println(e.getMessage() + "Error format");
                                throw new RuntimeException(e);
                            }
                        }, // Chuyển đổi Date thành String
                        Collectors.counting()
                ));
        long sumToTalOrders = 0;
        for (Map.Entry<String, Long> entry : accountCountMap.entrySet()) {
            sumToTalOrders += entry.getValue();
        }

        return sumToTalOrders;
    }

}
