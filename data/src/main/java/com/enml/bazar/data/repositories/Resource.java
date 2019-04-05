package com.enml.bazar.data.repositories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable public final Exception exception;
    private Resource(@NonNull Status status, @Nullable T data, @Nullable Exception exception) {
        this.status = status;
        this.data = data;
        this.exception = exception;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(Exception exception, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, exception);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }
}