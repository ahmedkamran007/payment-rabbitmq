package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentProducer producer;
    private final PaymentRepository repository;

    public PaymentController(PaymentProducer producer, PaymentRepository repository) {
        this.producer = producer;
        this.repository = repository;
    }
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Payment payment) {
        producer.sendPayment(payment);
        return ResponseEntity.ok("Payment sent to queue.");
    }

    @GetMapping
    public List<Payment> getAll() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        return repository.findById(id)
                .map(payment -> {
                    repository.delete(payment);
                    return ResponseEntity.ok("Deleted");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getOne(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}