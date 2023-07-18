package com.practice.bank.services;

import com.practice.bank.dto.PaymentSystemDto;
import com.practice.bank.entity.PaymentSystemEntity;
import com.practice.bank.mapper.EntityToDto;
import com.practice.bank.repository.PaymentSystemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSystemServiceImpl implements PaymentSystemService{
    private final PaymentSystemRepository paymentSystemRepository;
    @Override
    public PaymentSystemDto getDataById(String id) {
        return EntityToDto.paymentSystemEntityToDto(paymentSystemRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Payment System was not found")));
    }

    @Override
    public PaymentSystemEntity getPaymentSystemEntityById(String id) {
        return paymentSystemRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Payment System was not found"));
    }

    @Override
    public void insertData(PaymentSystemDto element) {

    }

    @Override
    public void updateData(PaymentSystemDto element) {

    }

    @Override
    public List<PaymentSystemDto> getAllPaymentSystems() {
        List<PaymentSystemEntity> paymentSystemEntities = paymentSystemRepository.findAll();
        return EntityToDto.paymentSystemEntityToDtoList(paymentSystemEntities);
    }

}
