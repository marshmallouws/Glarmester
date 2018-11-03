/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glarmester.logic;

import glarmester.data.DBConnector;
import glarmester.data.DataAccessorDatabase;
import glarmester.data.DataException;
import glarmester.data.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Annika Ehlers
 */
public class ControllerTest {
    
    private static DataAccessorDatabase dao;
    private static ControllerImpl controller;
    private static PriceCalculator calc; 
    
    public ControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dao = new DataAccessorDatabase();
        calc = new PriceCalculator();
        controller = new ControllerImpl(dao, calc);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFrameNames method, of class Controller.
     */
    @Test
    public void testGetFrameNames() {
        List<String> frameNames = controller.getFrameNames();
        String frame = "";
        
        assertNotNull(frameNames);
        assertFalse(frameNames.isEmpty());
        assertEquals(3, frameNames.size());

        frame = frameNames.get(0);
        assertEquals("Plain", frame);
        
        frame = frameNames.get(1);
        assertEquals("Ornate", frame);
        
        frame = frameNames.get(2);
        assertEquals("Lavish", frame);    
    }

    /**
     * Test of getTotalPrice method, of class Controller.
     */
    @Test
    public void testGetTotalPrice() {
        
        double result = 0;
        
        result = controller.getTotalPrice(10, 10, "plain");
        assertEquals(43, result, 0);
        
        result = controller.getTotalPrice(15, 23, "ornate");
        assertEquals(162.35, result, 0);
        
        result = controller.getTotalPrice(100, 150, "lavish");
        assertEquals(2200, result, 0);
    }
    
}
