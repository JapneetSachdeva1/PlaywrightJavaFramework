package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.microsoft.playwright.Page;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static factory.PlaywrightFactory.takeScreenshot;
import static utils.ExtentReportHelper.getReportObject;

public class ReportListeners implements ITestListener
{
        private final ExtentReports extentReports = getReportObject();
        ExtentTest logger;
        //ThreadLocal to generate the report for parallel execution
        ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");

    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Test Suite is ending!"));
        extentTestThreadLocal.get().log(Status.INFO, "Execution end time:  "+ dtf.format(LocalDateTime.now()));
        extentReports.flush();
        extentTestThreadLocal.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");
        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        extentTestThreadLocal.set(extentTest);
        extentTestThreadLocal.get().getModel();
        extentTestThreadLocal.get().log(Status.INFO, "Execution start time: "+ dtf.format(LocalDateTime.now()));


    }

    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        extentTestThreadLocal.get().pass("Test passed");
       extentTestThreadLocal.get().getModel();
    }

    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        extentTestThreadLocal.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(result.getMethod().getMethodName()),result.getMethod().getMethodName()).build());

        extentTestThreadLocal.get().fail(result.getThrowable());
        //extentTestThreadLocal.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(),result.getMethod().getMethodName()).build());
        extentTestThreadLocal.get().getModel();
    }

    public synchronized void onTestSkipped(ITestResult result) {
//        System.out.println((result.getMethod().getMethodName() + " skipped!"));
//        extentTestThreadLocal.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(), result.getMethod().getMethodName()).build());
//        extentTestThreadLocal.get().getModel();
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
    }

