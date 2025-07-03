package org.example.homeworkstub.model.request.paymentRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PaymentMadeRequest {
    private String transaction_id;
    private double sum;
    private boolean need_processing;
}
