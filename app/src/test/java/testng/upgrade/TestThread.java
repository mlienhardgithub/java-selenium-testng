package testng.upgrade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestThread {
	protected static Log log = LogFactory.getLog(TestThread.class);
	
	public TestThread() {
		log.info("TestThread constructor");
	}
	
	private static final ThreadLocal<ITestApplication> THREADLOCAL_TEST_APPLICATION = new ThreadLocal<ITestApplication>();
	
	public void setUpThread(TestConfiguration testConfiguration) throws Exception{
		log.info("setting up instance thread...");
		ITestApplication testApplication = new TestApplication(testConfiguration);
		setUpThread(testApplication);
		log.info("set up instance thread.");
	}

	public static void setUpThread(ITestApplication testApplication) throws Exception{
		log.info("setting up thread: " + testApplication.toString());
		setTestThread(testApplication);
		getTestThread().setUp();
		log.info("set up thread.");
	}
	
	public static void tearDownThread() throws Exception{
		log.info("tearing down thread...");
		getTestThread().tearDown();
		log.info("tore down thread.");
	}
	
	private static void setTestThread(ITestApplication testApplication){
		log.info("setting the test thread: " + testApplication.toString());
		THREADLOCAL_TEST_APPLICATION.set(testApplication);
		log.info("set the test thread: " + testApplication.getTestName() + ": " + testApplication.toString());
	}
	
	public static ITestApplication getTestThread(){
		log.info("getting the test thread...");
		ITestApplication testApplication = THREADLOCAL_TEST_APPLICATION.get();
		log.info("got the test thread: " + testApplication.toString());
		return testApplication;
	}
	
	public static void removeTestThread(){
		log.info("removing the test thread...");
		@SuppressWarnings("unused")
		ITestApplication testApplication = getTestThread();
		testApplication = null;
		THREADLOCAL_TEST_APPLICATION.set(null);
		THREADLOCAL_TEST_APPLICATION.remove();
		log.info("removed the test thread.");
	}

	
	
	
	
	
	
	
	
	
	
	
}
