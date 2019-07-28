package com.adjose.bank.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@RedisHash("TransactionDto")
public class TransactionDto implements Serializable {

    @Id
    private String transactionId;

    // todo try store java.util.Date data type to redis
    private String transactionDate;

    private String transactionType;

    private String accountNumber;

    private BigDecimal amount;

}
