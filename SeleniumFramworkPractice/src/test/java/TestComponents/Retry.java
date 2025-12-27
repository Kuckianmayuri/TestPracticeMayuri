package TestComponents;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	//For flakyfail selenium code we use RetryAnalyzer (usually)
	int count = 0;
	int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry) {
			count++;
			return true; //code (listener) will rerun when we return true
		}
		return false;
	}

}
