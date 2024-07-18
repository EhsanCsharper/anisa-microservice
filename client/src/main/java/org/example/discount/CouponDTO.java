package org.example.discount;


import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponDTO {
    private String code;
    private BigDecimal discount;
    //@JsonProperty("date")
    private LocalDate localDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
