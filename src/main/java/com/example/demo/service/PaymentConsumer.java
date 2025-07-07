package com.example.demo.service;

import com.example.demo.config.RabbitConfig;
import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {
    private final PaymentRepository repository;

    public PaymentConsumer(PaymentRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receivePayment(Payment payment) {
        repository.save(payment);
        System.out.println("Saved payment: " + payment);
        System.out.println("Sending confirmation to " + payment.getPayer());

    }




}