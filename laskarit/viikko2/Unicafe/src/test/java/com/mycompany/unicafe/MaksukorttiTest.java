package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void alkusaldoOikein() {
        assertTrue(kortti.saldo()==1000);      
    }

    @Test
    public void otaRahaaVahentaaSaldoa() {
        assertTrue(kortti.otaRahaa(500));
        assertTrue(kortti.saldo()==500);      
    }

    @Test
    public void otaLiikaaRahaaEiVahennaSaldoa() {
        assertFalse(kortti.otaRahaa(1100));
        assertTrue(kortti.saldo()==1000);      
    }

    @Test
    public void lataaRahaaLisaaSaldoa() {
        kortti.lataaRahaa(100);
        assertTrue(kortti.saldo()==1100);      
    }
    
    @Test
    public void toStringOikein() {
        assertTrue("saldo: 10.0".equals(kortti.toString()));      
    }

}
