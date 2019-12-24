package com.energizeglobal.internship;

@FunctionalInterface
public interface AutoBoxer<T> {
     T box(T primitiveValue);
}
