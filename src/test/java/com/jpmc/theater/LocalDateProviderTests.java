package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentDate() {
        new LocalDateProvider();
        LocalDateProvider provider = LocalDateProvider.singleton();
        assertEquals(provider.currentDate(), LocalDate.of(2023, 6, 15));
    }
}
