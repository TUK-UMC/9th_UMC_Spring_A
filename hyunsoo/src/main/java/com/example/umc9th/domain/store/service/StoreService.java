package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.store.dto.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.StoreResponseDTO;

public interface StoreService {
    StoreResponseDTO.CreateResultDTO createStore(StoreRequestDTO.CreateDTO request);
}
