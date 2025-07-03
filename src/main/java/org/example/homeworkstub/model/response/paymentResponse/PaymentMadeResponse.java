package org.example.homeworkstub.model.response.paymentResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class PaymentMadeResponse {
    private String transaction_id;
    private String bank_bik;
    private String status;
    @JsonProperty("contact")
    private List<Contact> contacts;
}
