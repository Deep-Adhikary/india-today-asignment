package assignment.indiatoday.base.utils.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import assignment.indiatoday.base.BaseTest;
import assignment.indiatoday.base.utils.CustomReporter;

public class CustomTestngListener implements ITestListener {
    @Override
    public void onStart(ITestContext startContext) {
        CustomReporter.cleanResultFolder();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass=result.getInstance();
        CustomReporter reporter=((BaseTest) testClass).getCustomReporter();
        reporter.logError("Test " + result.getName()+ " is failed");
    }
}
