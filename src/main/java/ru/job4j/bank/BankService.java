package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает модель банковского сервиса.
 * @author Ivan Tarasov
 * @version 0.1
 */
public class BankService {
    /**
     * Хранение пользователей и их счетов осуществляется в коллекции типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Этот метод позволяет зарегистрировать пользователя.
     * По умолчанию к этому user добавлен пустой список - new ArrayList<Account>().
     * @param user добавляемый пользователь.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Этот метод удаляет пользователя из системы.
     * Поиск пользователя осуществляется по паспорту.
     * @param passport паспорт пользователя.
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    /**
     * Этот метод добавляет пользователю банковский счет.
     * Пользователь может иметь несколько счетов.
     * @param passport паспорт пользователя.
     * @param account новый счёт пользователя.
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!(accounts.contains(account))) {
                accounts.add(account);
            }
        }
    }

    /**
     * Этот метод находит пользователя по паспорту.
     * @param passport паспорт пользователя.
     * @return возвращает пользователя, в случае если пользователь
     * не найден будет возращено null.
     */
    public User findByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Этот метод находит банковский счет пользователя.
     * @param passport паспорт пользователя.
     * @param requisite реквизиты пользователя.
     * @return возвращает счёт пользователя, в случае если счёт
     * не найден будет возращено null.
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        Account result = null;
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (requisite.equals(account.getRequisite())) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Этот метод позволяет переводить деньги с одного банковского счета на другой счет.
     * @param sourcePassport паспорт отправителя.
     * @param sourceRequisite реквизиты отправителя.
     * @param destinationPassport паспорт получателя.
     * @param destinationRequisite реквизиты получателя.
     * @param amount сумма перевода.
     * @return возвращает true, если перевод прошел успешно, либо false если не успешно.
     */
    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        boolean result = false;
        Account sourceAccount = findByRequisite(sourcePassport, sourceRequisite);
        Account destinationAccount = findByRequisite(destinationPassport, destinationRequisite);
        if ((sourceAccount != null) && (destinationAccount != null)) {
            double sourceBalance = sourceAccount.getBalance();
            double destinationBalance = destinationAccount.getBalance();
            if (sourceBalance >= amount) {
                sourceAccount.setBalance(sourceBalance - amount);
                destinationAccount.setBalance(destinationBalance + amount);
                result = true;
            }
        }
        return result;
    }

    /**
     * Этот метод позволяет получить все счета пользователя.
     * @param user паспорт отправителя.
     * @return список счетов пользователя в виде List<Account>.
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}