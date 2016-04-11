package com.dotin.calculationOfIinterest;

import java.math.BigDecimal;

/**
 *
 * @author Ghandchi
 */
public class Deposit {

    private String customerNumber;
    private BigDecimal depositBalance;
    private Long durationDays;
    private BigDecimal interestValue;
    private Boolean isvalid = true;
    private DepositType depositType;

    public Boolean getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Boolean isvalid) {
        this.isvalid = isvalid;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public BigDecimal getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(BigDecimal interestValue) {
        this.interestValue = interestValue;
    }

    public void setDurationDays(Long durationDays) {
        this.durationDays = durationDays;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    public Long getDurationDays() {
        return durationDays;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }
}
