package it.uniroma2.dicii.isw2.jcs.paramTests;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(Parameterized.class)
public class MyJCSLightLoadUnitTest {
	
	private static int items;
	private static String testName;
	
	private void configure(){
        JCS.setConfigFilename("/TestSimpleLoad.ccf");
        try {
            JCS.getInstance("testCache1");
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }

	
	@Before
	public void setUp() { 
		configure();
	}
	
	public MyJCSLightLoadUnitTest(int items, String testName) {
		this.items=items;
		this.testName=testName;
	}
	
	@Parameters
	//capisci come passare i parametri
	
	
	@Test
	public void testSimpleLoad() throws Exception
		    {
		        JCS jcs = JCS.getInstance( "testCache1" );
		        

		        for ( int i = 1; i <= items; i++ )
		        {
		            jcs.put( i + ":key", "data" + i );
		        }

		        for ( int i = items; i > 0; i-- )
		        {
		            String res = (String) jcs.get( i + ":key" );
		            if ( res == null )
		            {
		                assertNotNull( "[" + i + ":key] should not be null", res );
		            }
		        }

		        // test removal
		        jcs.remove( testName );
		        assertNull( jcs.get( "300:key" ) );

		    }

	
	

}
