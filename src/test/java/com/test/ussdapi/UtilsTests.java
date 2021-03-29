package com.test.ussdapi;

import com.test.ussdapi.ussd.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTests {

    @Test
    public void whenEntryIsNumber() {
        assertEquals(Utils.isNumeric("55"), true);
        assertEquals(Utils.isNumeric("55.50"), true);
    }

    @Test
    public void whenEntryISNotANumber() {
        assertEquals(Utils.isNumeric(""), false);
        assertEquals(Utils.isNumeric("R50"), false);
    }

    @Test
    public void whenEntryIsValidThirdStageValue() {
        assertEquals(Utils.isValidThirdStageEntry("1"), true);
    }

    @Test
    public void whenEntryIsInvalidThirdStageValue() {
        assertEquals(Utils.isValidThirdStageEntry(""), false);
        assertEquals(Utils.isValidThirdStageEntry("2"), false);
    }

    @Test
    public void whenEntryIsValidSecondStageValue() {
        assertEquals(Utils.isValidSecondStageEntry("1"), true);
        assertEquals(Utils.isValidSecondStageEntry("2"), true);
    }

    @Test
    public void whenEntryIsInvalidSecondStageValue() {
        assertEquals(Utils.isValidSecondStageEntry(""), false);
        assertEquals(Utils.isValidSecondStageEntry("Two"), false);
    }



}
