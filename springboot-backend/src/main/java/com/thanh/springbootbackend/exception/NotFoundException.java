package com.thanh.springbootbackend.exception;

/**
 * NotFoundException
 * Version 1.0
 *
 * Date: 01-09-2021
 *
 * Copyright
 *
 * Modification Logs:
 *  DATE                 AUTHOR          DESCRIPTION
 *  -----------------------------------------------------------------------
 *   01-09-2021         ThanhNV80            Create
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
