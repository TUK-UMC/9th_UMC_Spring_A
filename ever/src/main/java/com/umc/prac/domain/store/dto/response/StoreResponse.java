package com.umc.prac.domain.store.dto.response;

public record StoreResponse(
        Long storeId,
        String name,
        Long locationId,
        String managerNumber
) {
}

