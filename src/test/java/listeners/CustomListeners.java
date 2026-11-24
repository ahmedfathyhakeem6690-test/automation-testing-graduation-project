package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListeners implements ITestListener, IExecutionListener , IInvokedMethodListener {
    String deleteJsonCommand = "cmd /c if exist allure-results del /q allure-results\\*.json";
    String allureReportGenerate = "cmd /c allure generate allure-results --clean -o allure-report";
    public static final Logger log = LogManager.getLogger(CustomListeners.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("START: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASS: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("FAIL: {} - {}", result.getMethod().getMethodName(), result.getThrowable());
    }
    public void onExecutionStart() {
        // not implemented
        executeCommand(deleteJsonCommand);
    }


    /** Invoked once all the suites have been run. */
    public void onExecutionFinish() {
        // not implemented
        executeCommand(allureReportGenerate);
    }
    private static void executeCommand(String CMD) {

        try {
            Process process = Runtime.getRuntime().exec(CMD);
            int exitcode = process.waitFor();
            if (exitcode == 0) {
                System.out.println("Process has been executed successfully");
            }else {
                System.out.println("Process has been failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
