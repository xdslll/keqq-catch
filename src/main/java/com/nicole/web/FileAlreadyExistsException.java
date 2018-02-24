package com.nicole.web;

import java.io.IOException;

/**
 * @author xiads
 * @date 11/01/2018
 * @since
 */
public class FileAlreadyExistsException extends IOException {

    public FileAlreadyExistsException(String message) {
        super(message);
    }
}
