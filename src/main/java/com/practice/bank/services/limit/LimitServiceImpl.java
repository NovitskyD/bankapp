package com.practice.bank.services.limit;

import com.practice.bank.dto.LimitDto;
import com.practice.bank.entity.LimitEntity;
import com.practice.bank.mapper.EntityToDto;
import com.practice.bank.repository.LimitRepository;
import com.practice.bank.services.limit.LimitService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {
    private final LimitRepository limitRepository;
    @Override
    public LimitDto getDataById(String id) {
        return EntityToDto.limitEntityToDto(limitRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Limit was not found")));
    }

    @Override
    public void insertData(LimitDto element) {

    }

    @Override
    public void updateData(LimitDto element) {

    }

    @Override
    public List<LimitDto> getAllLimits() {
        List<LimitEntity> limitEntities = limitRepository.findAll();
        return EntityToDto.limitToDtoList(limitEntities);
    }

    @Override
    public LimitEntity getLimitEntityById(String id) {
        return limitRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Limit was not found"));
    }
}
