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

//inject no null
GlobalVariable.admin_buildingComplexID="1"
GlobalVariable.admin_buildingName="Building Name"
GlobalVariable.admin_buildingAddress="jalan"
GlobalVariable.admin_buildingDesc="desc"

def dump = GlobalVariable.admin_BuildingID

for(int i=0;i<8;i++)
{
	//not found ID
	if(i==0)
	{
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		GlobalVariable.admin_BuildingID="0"
		
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Update'))
		
		WS.verifyResponseStatusCode(rslt, 404)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'building not found')
		GlobalVariable.admin_BuildingID=dump
	}
	//unauth
	if(i==1)
	{
		GlobalVariable.token=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Update'))
		
		WS.verifyResponseStatusCode(rslt, 401)
	}
	else if(i==2)
	{
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		GlobalVariable.admin_buildingName=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
	}
	else if(i==3)
	{		
		GlobalVariable.admin_buildingName="BCA Tower"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Update'))
		
		WS.verifyResponseStatusCode(rslt, 500)
		
		WS.verifyElementPropertyValue(rslt, 'error', 'Internal Server Error')
		GlobalVariable.admin_buildingName="Building Name"
	}
	else if(i==4)
	{
		GlobalVariable.admin_buildingDesc=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
		GlobalVariable.admin_buildingDesc="Building Description"
	}
	else if(i==5)
	{
		GlobalVariable.admin_buildingAddress=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
		GlobalVariable.admin_buildingAddress="Building Address"
	}
	
	else if(i==6)
	{
		GlobalVariable.admin_buildingComplexID=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		//WS.verifyElementPropertyValue(rslt, 'error', 'city id must not be null')
	}
	
	else if(i==7)
	{
		
		GlobalVariable.admin_buildingComplexID="0"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Update'))
		
		WS.verifyResponseStatusCode(rslt, 404)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'complex not found')
		GlobalVariable.admin_buildingComplexID="1"
	}
	/*
	if(i==8)
	{
		GlobalVariable.admin_buildingComplexID="1"
		GlobalVariable.admin_buildingName="Building Name"
		GlobalVariable.admin_buildingAddress="jalan"
		GlobalVariable.admin_buildingDesc="desc"
		
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Update'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'building saved successfully')
	}*/
}