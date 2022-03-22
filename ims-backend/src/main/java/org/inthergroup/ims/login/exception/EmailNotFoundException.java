package org.inthergroup.ims.login.exception;

import javax.persistence.EntityNotFoundException;

public class EmailNotFoundException extends EntityNotFoundException {

    public EmailNotFoundException(String email) {
        super(email + " is not found");
    }
}