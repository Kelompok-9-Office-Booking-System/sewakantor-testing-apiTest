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


for(int i=0;i<4;i++)
{
	//no auth
	if(i==0)
	{
		GlobalVariable.token=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Get One'))
		
		WS.verifyResponseStatusCode(rslt, 401)
	}
	//valid
	else if(i==1)
	{
		GlobalVariable.admin_FacilityID=1
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Get One'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		'this validation must to change to building name for verify, \r\nthe case just can run in regression \r\n'
		WS.verifyElementPropertyValue(rslt, 'message', 'list facility')
	}
	//invalidID
	else if(i==2)
	{
		GlobalVariable.admin_FacilityID=notFound
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Get One'))
		
		WS.verifyResponseStatusCode(rslt, 404)
		
		'this validation must to change to building name for verify, \r\nthe case just can run in regression \r\n'
		WS.verifyElementPropertyValue(rslt, 'message', 'facility not found')
		
	}
	//malformed
	else
	{
		GlobalVariable.admin_FacilityID=string
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Get One'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		'this validation must to change to building name for verify, \r\nthe case just can run in regression \r\n'
		WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
	}
	
	
}


