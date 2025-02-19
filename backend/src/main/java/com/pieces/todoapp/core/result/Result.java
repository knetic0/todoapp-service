package com.pieces.todoapp.core.result;

import lombok.Data;

@Data
public final class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result() {}

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public Result(boolean success, T data) {
        this(success);
        this.data = data;
    }

    public Result(boolean success, String message, T data) {
        this(success, message);
        this.data = data;
    }
}
