package com.ndyudev.testng.lab4;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestVatCalculator {
    @Test
    public void testGetVatOnAmount() {
        VatCalculator vat = new VatCalculator();
        Assert.assertEquals(vat.getVatOnAmount(100), 10.0);
        Assert.assertNotEquals(vat.getVatOnAmount(100), 12.00);
    }
}
