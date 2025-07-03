package org.example.homeworkstub.model.response.checkAccountResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CheckAccountResponse {
    @JsonProperty("account")
    private String accountId;
    @JsonProperty("vip-client")
    private boolean vipStatus;
    @JsonProperty("blocked")
    private boolean blockedStatus;
    private String inn;
    @JsonProperty("debt")
    private List<Debt> debts;

}
