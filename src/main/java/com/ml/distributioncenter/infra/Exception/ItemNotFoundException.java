package com.ml.distributioncenter.infra.Exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message) {
        super(message);
    }
}
