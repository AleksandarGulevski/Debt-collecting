package com.haselt.debtcollecting.util;

public interface ErrorCode {

    int FIELD_VALIDATION = 0;

    //DEBTOR
    int DEBTOR_NOT_FOUND = 100;
    int DEBTOR_AREADY_EXISTS = 101;

    //INVOICE
    int INVOICE_NOT_FOUND = 200;
}
