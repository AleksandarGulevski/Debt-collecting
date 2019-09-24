package com.haselt.debtcollecting.util;

public interface ErrorCode {

    int FIELD_VALIDATION = 0;

    //USER
    int USER_ALREADY_EXISTS = 100;
    int USER_NOT_FOUND = 101;

    //DEBTOR
    int DEBTOR_NOT_FOUND = 200;
    int DEBTOR_ALREADY_EXISTS = 201;
    int DEBTOR_DELETING_UNSUCCESSFUL = 202;

    //INVOICE
    int INVOICE_NOT_FOUND = 300;
    int INVOICE_DELETING_UNSUCCESSFUL = 301;
}
