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



for(int i=0;i<8;i++)
{
	
	if(i==0)
	{
		GlobalVariable.token=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Create One'))
		WS.verifyResponseStatusCode(rslt, 401)
	}
//long name[256b]
	else if(i==1)
	{
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		GlobalVariable.admin_facilityName = name255
		println(GlobalVariable.admin_facilityName )
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'facility saved successfully')
		
	}
//long name[257b]
	else if(i==2)
	{
		GlobalVariable.admin_facilityName=name256
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 500)
		
		WS.verifyElementPropertyValue(rslt, 'error', 'Internal Server Error')
	}
//blank data
	else if(i==3)
	{
		GlobalVariable.admin_facilityName=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'errors[0].error', 'name must not be empty')
	}

//duplicated name
	else if(i==4)
	{
		GlobalVariable.admin_facilityName="Parking Area"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 500)
		
		WS.verifyElementPropertyValue(rslt, 'error', 'Internal Server Error')
	}
	/*
//duplicated with uppercases
	else if(i==5)
	{
		GlobalVariable.admin_facilityName="PARKING AREA"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'facility saved successfully')
	}

//numerical case
	else if(i==6)
	{
		GlobalVariable.admin_facilityName="123"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'facility saved successfully')
	} */
//valid create one
	else if(i==7)
	{
		GlobalVariable.admin_facilityName="Toilet"
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Facility/Create One'))
			
		WS.verifyResponseStatusCode(rslt, 200)
			
		WS.verifyElementPropertyValue(rslt, 'message', 'facility saved successfully')
	}
}