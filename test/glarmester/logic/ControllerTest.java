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
        List<Frame> frames = null; 
        Frame frame = null;
        
        try {
            frames = dao.getFrames();
        } catch (DataException ex) {
            //Logger.getLogger(ControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertNotNull(frames);
        assertFalse(frames.isEmpty());
        assertEquals(3, frames.size());
        
        frame = frames.get(0);
        assertEquals("Plain", frame.getName());
        assertEquals(100, frame.getPrice(), 0);
        
        frame = frames.get(1);
        assertEquals("Ornate", frame.getName());
        assertEquals(200, frame.getPrice(), 0);
        
        frame = frames.get(2);
        assertEquals("Lavish", frame.getName());
        assertEquals(350, frame.getPrice(), 0);
        
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
        

        /*
        System.out.println("getTotalPrice");
        int height_cm = 0;
        int width_cm = 0;
        String frameName = "";
        Controller instance = new ControllerImpl();
        double expResult = 0.0;
        double result = instance.getTotalPrice(height_cm, width_cm, frameName);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /*
    public class ControllerImpl implements Controller {

        public List<String> getFrameNames() {
            return null;
        }

        public double getTotalPrice(int height_cm, int width_cm, String frameName) {
            return 0.0;
        }
    }
    */
    
}
