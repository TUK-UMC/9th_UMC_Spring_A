package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.StoreResponseDTO;
import com.example.umc9th.domain.store.service.StoreService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ApiResponse<StoreResponseDTO.CreateResultDTO> createStore(
            @RequestBody StoreRequestDTO.CreateDTO request
    ) {
        StoreResponseDTO.CreateResultDTO result = storeService.createStore(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }
}
