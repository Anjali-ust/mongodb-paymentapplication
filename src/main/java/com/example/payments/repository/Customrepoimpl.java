package com.example.payments.repository;

import com.example.payments.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

@Repository
public class Customrepoimpl implements CustomRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    public Double sumAllAmounts() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group().sum("amount").as("totalAmount")
        );

        AggregationResults<SumResult> results = mongoTemplate.aggregate(aggregation, Payment.class, SumResult.class);
        return results.getMappedResults().stream()
                .map(SumResult::getTotalAmount)
                .findFirst()
                .orElse(0.0);
    }

    private static class SumResult {
        private Double totalAmount;

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }
    }
}
