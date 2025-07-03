package org.example.homeworkstub.service;

import org.example.homeworkstub.model.response.checkAccountResponse.CheckAccountResponse;
import org.example.homeworkstub.model.response.checkAccountResponse.Debt;
import org.example.homeworkstub.model.response.paymentResponse.Contact;
import org.example.homeworkstub.model.response.paymentResponse.PaymentMadeResponse;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PaymentService {
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    //Добавляем данные о задолженностях по карте клиента в наш ответ
    public CheckAccountResponse getCheckAccountInfo(String accNum, int days) {
        CheckAccountResponse checkAccountResponse = new CheckAccountResponse();

        checkAccountResponse.setAccountId(accNum);
        checkAccountResponse.setVipStatus(false);
        int lastDigit = Integer.parseInt(accNum) % 10;
        if (lastDigit % 2 == 0) {
            checkAccountResponse.setVipStatus(false);
        } else {
            checkAccountResponse.setBlockedStatus(true);
        }
        checkAccountResponse.setInn(accNum + "111");
        List<Debt> debtList = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            Random random = new Random();
            Debt debt = new Debt();
            debt.setSum(random.nextInt(5000-1000+1)+1000);
            if (i % 2 == 0) {
                debt.setDescription("parking");
            } else {
                debt.setDescription("gkh");
            }
            debtList.add(debt);
        }
        checkAccountResponse.setDebts(debtList);
        return checkAccountResponse;
    }
    //Добавляем подтверждение платежа в наш ответ
    public PaymentMadeResponse getPaymentMadeInfo(String transactionId, int bankCode) {
        PaymentMadeResponse paymentMadeResponse = new PaymentMadeResponse();

        paymentMadeResponse.setTransaction_id(transactionId);
        String bank_bik = generateRandomNumericString();
        paymentMadeResponse.setBank_bik(bank_bik);
        paymentMadeResponse.setStatus("accepted");
        List<Contact> contacts = new ArrayList<>();
        List<String> telecoms = new ArrayList<>();
        Contact contact = new Contact();
        contact.setName("HL pay company");
        String code = String.valueOf(bankCode);
        char[] charArray = code.toCharArray();
        int sum = 0;
        for (int i = 0; i < charArray.length; i++) {
            int a = Character.getNumericValue(charArray[i]);
            sum += a;
        }
        for (int i = 0; i < sum ; i++) {
            telecoms.add(generateRandomString());
        }
        contact.setTelecoms(telecoms);
        contacts.add(contact);
        paymentMadeResponse.setContacts(contacts);
        return paymentMadeResponse;
    }
    //Генерация числа для bank-bik
    public static String generateRandomNumericString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            // Генерируем случайную цифру от 0 до 9
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
    //Генерация строки для contact
    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(7); // длина строки — 7 символов

        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

}
