package org.letter33.commerce.common.dto.response;

import lombok.Builder;
import lombok.Data;
import org.letter33.commerce.common.enumType.ErrorCode;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String name;
    private String code;
    private String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode e) {
        return ResponseEntity.status(e.getStatus())
                .body(ErrorResponse.builder()
                        .status(e.getStatus().value())
                        .name(e.name())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }
}
