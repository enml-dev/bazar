package com.enml.bazar.data;

/**
 * Created by edel on 06/03/17.
 */

public interface GetCallback<T> {
  void done(T object, Exception e);
}
