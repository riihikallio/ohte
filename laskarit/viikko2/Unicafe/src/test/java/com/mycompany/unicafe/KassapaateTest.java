package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(0);
    }

    @Test
    public void luontiToimii() {
        assertTrue(kassa.kassassaRahaa() == 100000);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void edullisestiOnnistuuKateisellaVaihtorahaOikein() {
        int vaihtoraha = kassa.syoEdullisesti(500);
        assertTrue(vaihtoraha==260);
    }

    @Test
    public void edullisestiOnnistuuKateisellaKassaOikein() {
        int vaihtoraha = kassa.syoEdullisesti(500);
        assertTrue(kassa.kassassaRahaa() == 100240);
    }

    @Test
    public void edullisestiOnnistuuKateisellaAnnoksetOikein() {
        int vaihtoraha = kassa.syoEdullisesti(500);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void edullisestiEpannistuuKateisella() {
        int vaihtoraha = kassa.syoEdullisesti(200);
        assertTrue(vaihtoraha==200);
        assertTrue(kassa.kassassaRahaa() == 100000);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void maukkaastiOnnistuuKateisellaVaihtorahaOikein() {
        int vaihtoraha = kassa.syoMaukkaasti(500);
        assertTrue(vaihtoraha==100);
    }

    @Test
    public void maukkaastiOnnistuuKateisellaKassaOikein() {
        int vaihtoraha = kassa.syoMaukkaasti(500);
        assertTrue(kassa.kassassaRahaa() == 100400);
    }

    @Test
    public void maukkaastiOnnistuuKateisellaAnnoksetOikein() {
        int vaihtoraha = kassa.syoMaukkaasti(500);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void maukkaastiEpannistuuKateisella() {
        int vaihtoraha = kassa.syoMaukkaasti(200);
        assertTrue(kassa.kassassaRahaa() == 100000);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void lataaRahaaToimii(){
        kassa.lataaRahaaKortille(kortti, 500);
        assertTrue(kassa.kassassaRahaa() == 100500);
        assertTrue(kortti.saldo() == 500);
    }

    @Test
    public void negatiivinenLatausEiMuuta(){
        kassa.lataaRahaaKortille(kortti, -500);
        assertTrue(kassa.kassassaRahaa() == 100000);
        assertTrue(kortti.saldo() == 0);
    }
    
    @Test
    public void edullisestiOnnistuuKortilla() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisestiKortillaSaldoOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        kassa.syoEdullisesti(kortti);
        assertTrue(kortti.saldo()==260);
    }
    
    @Test
    public void edullisestiKortillaKassaOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100500);
    }
    
    @Test
    public void edullisestiKortillaAnnoksetOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void edullisestiEpaonnistuuKortilla() {
        kassa.lataaRahaaKortille(kortti, 200);
        assertFalse(kassa.syoEdullisesti(kortti));
        assertTrue(kortti.saldo()==200);
        assertTrue(kassa.kassassaRahaa() == 100200);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void maukkaastiOnnistuuKortilla() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void maukkaastiKortillaSaldoOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo()==100);
    }

    @Test
    public void maukkaastiKortillaKassaOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100500);
    }

    @Test
    public void maukkaastiKortillaAnnoksetOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void maukkaastiEpaonnistuuKortilla() {
        kassa.lataaRahaaKortille(kortti, 200);
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertTrue(kortti.saldo()==200);
        assertTrue(kassa.kassassaRahaa() == 100200);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

}
