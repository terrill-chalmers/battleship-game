package io.chalmers.domain;

import io.chalmers.domain.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTypeTest {

    @BeforeEach

    void setUp() {
    }

    @Test
    void getName() {
        assertEquals("Battleship", ShipType.BATTLESHIP.getName());
    }

    @Test
    void getSize() {
        assertEquals(4, ShipType.BATTLESHIP.getSize());
    }
}