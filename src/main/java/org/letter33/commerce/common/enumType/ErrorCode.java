package org.letter33.commerce.common.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /**
     * ### 문자열 기반 코드 체계
     * - **접두사**: 기능 영역별 구분 (AUTH: 인증, PAY: 결제, PRD: 상품, ORD: 주문 등)
     * - **번호**: 3자리 일련번호 (001, 002, 003...)
     *
     * ### 카테고리별 분류
     * - **공통**: CMN_xxx
     * - **인증/인가**: AUTH_xxx
     * - **사용자**: USER_xxx
     * - **상품**: PRD_xxx
     * - **주문**: ORD_xxx
     * - **결제**: PAY_xxx
     * - **장바구니**: CART_xxx
     * - **배송**: SHIP_xxx
     * - **리뷰**: REV_xxx
     * - **쿠폰**: CPN_xxx
     * - **재고**: INV_xxx
     * - **제한**: RATE_xxx
     */

    // 일반적인 오류
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "CMN_001", "잘못된 요청입니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "CMN_002", "서버 내부 오류가 발생했습니다"),

    // 인증/인가 관련
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH_001", "로그인이 필요합니다"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH_002", "접근 권한이 없습니다"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_003", "유효하지 않거나 만료된 토큰입니다"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "AUTH_004", "이메일 또는 비밀번호가 올바르지 않습니다"),

    // 사용자 관련
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_001", "사용자를 찾을 수 없습니다"),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER_002", "이미 가입된 사용자입니다"),

    // 상품 관련
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRD_001", "상품을 찾을 수 없습니다"),
    PRODUCT_OUT_OF_STOCK(HttpStatus.CONFLICT, "PRD_002", "상품이 품절되었습니다"),
    PRODUCT_DISCONTINUED(HttpStatus.GONE, "PRD_003", "단종된 상품입니다"),
    INVALID_PRODUCT_DATA(HttpStatus.UNPROCESSABLE_ENTITY, "PRD_004", "상품 정보가 올바르지 않습니다"),

    // 주문 관련
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORD_001", "주문을 찾을 수 없습니다"),
    ORDER_ALREADY_CANCELLED(HttpStatus.CONFLICT, "ORD_002", "이미 취소된 주문입니다"),
    ORDER_CANNOT_BE_MODIFIED(HttpStatus.CONFLICT, "ORD_003", "주문을 수정할 수 없습니다"),
    INSUFFICIENT_STOCK(HttpStatus.CONFLICT, "ORD_004", "재고가 부족합니다"),

    // 결제 관련
    PAYMENT_FAILED(HttpStatus.PAYMENT_REQUIRED, "PAY_001", "결제에 실패했습니다"),
    PAYMENT_METHOD_INVALID(HttpStatus.BAD_REQUEST, "PAY_002", "유효하지 않은 결제 수단입니다"),
    PAYMENT_AMOUNT_MISMATCH(HttpStatus.CONFLICT, "PAY_003", "결제 금액이 일치하지 않습니다"),
    PAYMENT_ALREADY_PROCESSED(HttpStatus.CONFLICT, "PAY_004", "이미 처리된 결제입니다"),

    // 장바구니 관련
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "CART_001", "장바구니를 찾을 수 없습니다"),
    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "CART_002", "장바구니에 해당 상품이 없습니다"),
    INVALID_QUANTITY(HttpStatus.BAD_REQUEST, "CART_003", "올바르지 않은 수량입니다"),
    CART_EMPTY(HttpStatus.BAD_REQUEST, "CART_004", "장바구니가 비어있습니다"),

    // 배송 관련
    DELIVERY_ADDRESS_INVALID(HttpStatus.BAD_REQUEST, "SHIP_001", "배송지 정보가 올바르지 않습니다"),
    DELIVERY_NOT_AVAILABLE(HttpStatus.CONFLICT, "SHIP_002", "해당 지역은 배송이 불가능합니다"),
    SHIPPING_METHOD_INVALID(HttpStatus.BAD_REQUEST, "SHIP_003", "유효하지 않은 배송 방법입니다"),

    // 리뷰/평점 관련
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REV_001", "리뷰를 찾을 수 없습니다"),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "REV_002", "이미 작성한 리뷰가 있습니다"),
    REVIEW_NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "REV_003", "리뷰 작성 권한이 없습니다"),

    // 쿠폰/할인 관련
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "CPN_001", "쿠폰을 찾을 수 없습니다"),
    COUPON_EXPIRED(HttpStatus.GONE, "CPN_002", "만료된 쿠폰입니다"),
    COUPON_ALREADY_USED(HttpStatus.CONFLICT, "CPN_003", "이미 사용한 쿠폰입니다"),
    COUPON_NOT_APPLICABLE(HttpStatus.BAD_REQUEST, "CPN_004", "이 주문에는 쿠폰을 적용할 수 없습니다"),

    // 재고 관리
    INVENTORY_INSUFFICIENT(HttpStatus.CONFLICT, "INV_001", "재고가 부족합니다"),
    INVENTORY_RESERVED(HttpStatus.CONFLICT, "INV_002", "다른 주문에서 예약된 재고입니다"),

    // 외부 서비스 연동
    EXTERNAL_SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "EXT_001", "외부 서비스가 일시적으로 이용 불가합니다"),
    PAYMENT_GATEWAY_ERROR(HttpStatus.BAD_GATEWAY, "EXT_002", "결제 처리 중 오류가 발생했습니다"),

    // 요청 제한
    RATE_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "RATE_001", "요청 횟수를 초과했습니다"),
    ORDER_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "RATE_002", "주문 가능 횟수를 초과했습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
