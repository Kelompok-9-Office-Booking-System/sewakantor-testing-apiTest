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

GlobalVariable.admin_buildingComplexID="1"
GlobalVariable.admin_buildingName="nama"
GlobalVariable.admin_buildingAddress="jalan"
GlobalVariable.admin_buildingDesc="desc"

for(int i=0;i<8;i++)
{
	//unauth
	if(i==0)
	{
		GlobalVariable.token=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 401)		
	}
	else if(i==1)
	{
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		GlobalVariable.admin_buildingName=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'name must not be empty')
	}
	else if(i==2)
	{
		GlobalVariable.admin_buildingName="BCA Tower"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 500)
		
		WS.verifyElementPropertyValue(rslt, 'error', 'Internal Server Error')
		GlobalVariable.admin_buildingName="Building Name"
	}
	else if(i==3)
	{
		GlobalVariable.admin_buildingDesc=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'description must not be empty')
		GlobalVariable.admin_buildingDesc="Building Description"
	}
	else if(i==4)
	{
		GlobalVariable.admin_buildingAddress=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'address must not be empty')
		GlobalVariable.admin_buildingAddress="Building Address"
	}
	
	else if(i==5)
	{
		GlobalVariable.admin_buildingComplexID=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'complex id must not be null')
	}
	
	else if(i==6)
	{
		GlobalVariable.admin_buildingComplexID="0"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 404)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'complex not found')
		GlobalVariable.admin_buildingComplexID="1"
	}
	if(i==7)
	{
		GlobalVariable.admin_buildingComplexID="1"
		GlobalVariable.admin_buildingName="Building Name"
		GlobalVariable.admin_buildingAddress="jalan"
		GlobalVariable.admin_buildingDesc="desc"
		
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Building/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'building saved successfully')
	}
	
}

def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(rslt.getResponseBodyContent())

def value= result.data.id

GlobalVariable.admin_BuildingID= value

println(GlobalVariable.admin_BuildingID)
