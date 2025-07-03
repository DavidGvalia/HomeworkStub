package org.example.homeworkstub.model.response.paymentResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class Contact {
    private String name;
    @JsonProperty("telecom")
    private List<String> telecoms;
}
