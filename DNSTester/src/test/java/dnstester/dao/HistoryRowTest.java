/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnstester.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pera
 */
public class HistoryRowTest {

    HistoryRow hr;

    public HistoryRowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void date() {
        hr = new HistoryRow("AA", "BB", "CC");
        assertEquals("Date set/get fails", "AA", hr.getDate());
    }

    @Test
    public void duration() {
        hr = new HistoryRow("AA", "BB", "CC");
        assertEquals("Duration set/get fails", "BB", hr.getDuration());
    }

    @Test
    public void recursion() {
        hr = new HistoryRow("AA", "BB", "CC");
        assertEquals("Recursion set/get fail", "CC", hr.getRecursive());
    }
}
