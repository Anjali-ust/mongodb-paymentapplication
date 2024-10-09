package com.example.payments.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomRepository {
    Double sumAllAmounts();
}
