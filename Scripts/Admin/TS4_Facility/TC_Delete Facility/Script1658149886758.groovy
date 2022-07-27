import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//WebUI.callTestCase(findTestCase('Admin/Dummy Admin/CreateAndGetCityID'), [:], FailureHandling.STOP_ON_FAILURE)
def dump=GlobalVariable.admin_FacilityID
for(int i=0; i<3;i++)
{
	// no auth delete
	if(i==0)
	{
		GlobalVariable.token = ''
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Delete'))
		WS.verifyResponseStatusCode(rslt, 401)
	}
	// not found id delete
	if(i==1)
	{
		GlobalVariable.admin_FacilityID=-1
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Delete'))
		
		WS.verifyResponseStatusCode(rslt, 404)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'facility not found')
	}
	// valid delete
	if(i==2)
	{
		GlobalVariable.admin_FacilityID=dump
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Delete'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'facility deleted successfully')
	}
	// valid delete
}
