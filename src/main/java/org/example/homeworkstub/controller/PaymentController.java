package org.example.homeworkstub.controller;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.homeworkstub.model.request.paymentRequest.PaymentMadeRequest;
import org.example.homeworkstub.model.response.checkAccountResponse.CheckAccountResponse;
import org.example.homeworkstub.model.response.paymentResponse.PaymentMadeResponse;
import org.example.homeworkstub.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@Tag(name = "PaymentController", description = "Контроллер для обработки платежей")
public class PaymentController {
    @Value("${delay.seconds}")
    private int delaySeconds;

    public PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/v2/checkAccount/{acc}/{days}")
    @Operation(summary = "Проверить информацию о задолженностях по карте клиента")
    @Timed(value = "getInfoPayment")
    public ResponseEntity<?> getInfoPayment(@PathVariable("acc") String accNum, @PathVariable("days") int days) {
        log.info("getInfoPayment");
        CheckAccountResponse checkAccountResponse = paymentService.getCheckAccountInfo(accNum, days);
        return new ResponseEntity<>(checkAccountResponse, HttpStatus.ACCEPTED);
    }
    @PostMapping("/v2/payment")
    @Operation(summary = "Подтвердить платеж")
    @Parameter(name = "BankCode", description = "number", required = true, in = ParameterIn.HEADER)
    @Timed(value = "paymentMade")
    public ResponseEntity<PaymentMadeResponse> paymentMade(@RequestBody PaymentMadeRequest paymentMadeRequest,
                                                           @RequestHeader HttpHeaders headers) {
        log.info("paymentMadeMade");
        paymentMadeRequest.setTransaction_id("T-342-67777");
        paymentMadeRequest.setSum(133.12);
        paymentMadeRequest.setNeed_processing(true);

        int code = Integer.parseInt(headers.get("BankCode").getFirst().toString());

        PaymentMadeResponse paymentMadeResponse = paymentService.getPaymentMadeInfo(paymentMadeRequest.getTransaction_id(),
                code);

        String processingTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("ProcessingTime", processingTime);

        return new ResponseEntity<>(paymentMadeResponse, responseHeaders, HttpStatus.OK);
    }
    @DeleteMapping("/cleare/{id}")
    @Operation(summary = "Удалить транзакцию процессинга")
    @Timed(value = "deleteTransaction")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id) throws InterruptedException {
        log.info("deleteTransaction");
        // Эмуляция задержки
        Thread.sleep(delaySeconds * 1000L);

        // Ответ в формате text/plain
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body("deleted success");
    }
}
