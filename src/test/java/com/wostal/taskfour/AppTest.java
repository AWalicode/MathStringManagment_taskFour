package com.wostal.taskfour;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit tests for consumer and producer.
 * @author Aleksander Wostal
 */
public class AppTest {
   
	/**
	 * Tests math Regex and producer of tasks
	 */
	@Test
    public void testProduceTask()
    {
        TaskProducer tp = new TaskProducer(null,0);
        
        assertTrue("-3242-423*23".matches("^-?\\d+([-+/*]\\d+)*"));
        assertTrue("3242/4324-423*23+2123".matches("^-?\\d+([-+/*]\\d+)*"));
        assertTrue("3+424-423/23+2123".matches("^-?\\d+([-+/*]\\d+)*"));
        assertTrue(!"3242/4324-423*23+2123+".matches("^-?\\d+([-+/*]\\d+)*"));
        assertTrue("3242/434-423*23+2123".matches("^-?\\d+([-+/*]\\d+)*"));
        assertTrue(!"32d42/4324-423*23+2123".matches("^-?\\d+([-+/*]\\d+)*"));
        assertTrue(!"-3242324-423*23.2123".matches("^-?\\d+([-+/*]\\d+)*"));
        assertTrue(!"-*34+8".matches("^-?\\d+([-+/*]\\d+)*"));
        assertTrue(!"*43".matches("^-?\\d+([-+/*]\\d+)*"));
        
        for(int i = 0; i <99 ; i++) {
        	assertTrue(tp.generateTask().matches("^-?\\d+([-+/*]\\d+)*"));
        }
    }
	
	/**
	 * Tests consumer of tasks
	 */
	@Test
    public void testSolveTask()
    {
        TaskConsumer tc = new TaskConsumer(null);
		//tc.solveTask("60/20*3-6u");
        assertTrue(tc.solveTask("4-8") == -4);
        assertTrue(tc.solveTask("60/20*3-6") == 3);
        assertTrue(tc.solveTask("11+40-1/2") == 50.5);
        assertTrue(tc.solveTask("11+40-1/2*2") == 50);
        assertTrue(tc.solveTask("11+40-1/2*2+10") == 60);
        assertTrue(tc.solveTask("11+40-1/2*2+10*4") == 90);
        assertTrue(tc.solveTask("4-3*5") == -11);
       
    }
}
