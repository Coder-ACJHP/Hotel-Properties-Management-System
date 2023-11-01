package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Payment;

public interface PaymentConversionDao {

    public String getTotalCashDollarForOneDay(String date);

    public String getTotalCashLiraPaymentsForOneDay(String date);

    public String getTotalCashEuroPaymentsForOneDay(String date);

    public String getTotalCashPoundPaymentsForOneDay(String date);

    public String getTotalCreditLiraPaymentsForOneDay(String date);

    public String getTotalCreditDollarPaymentsForOneDay(String date);

    public String getTotalCreditEuroPaymentsForOneDay(String date);

    public String getTotalCreditPoundPaymentsForOneDay(String date);

}
