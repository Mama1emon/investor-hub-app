package com.mama1emon.api.model.converter

/**
 * Интерфейс конвертер
 *
 * @param From объект из которого конвертирует
 * @param To объект в который конвертируют
 *
 * @author Andrey Khokhlov on 27.03.21
 */
interface Converter<From, To> {

    /**
     * Конвертировать из [From] в [To]
     */
    fun convert(from: From): To
}