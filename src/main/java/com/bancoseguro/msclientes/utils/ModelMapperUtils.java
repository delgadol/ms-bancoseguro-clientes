package com.bancoseguro.msclientes.utils;

import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;

public class ModelMapperUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S, T> Mono<T> mapToMono(Mono<S> source, Class<T> targetClass) {
        return source.map(s -> map(s, targetClass));
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}