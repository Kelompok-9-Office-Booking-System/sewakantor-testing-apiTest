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
def dump=GlobalVariable.admin_UserID
GlobalVariable.admin_userUsername="forreset"
for(i=0;i<11;i++)
{

	//invalid token
	if(i==1)
	{
		GlobalVariable.token=''
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 401)

	}
	//invalid userID
	if(i==2)
	{
		GlobalVariable.admin_UserID=0
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 404)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'user not found')
		GlobalVariable.admin_UserID=dump
	}
	//blank name
	if(i==3)
	{
		GlobalVariable.admin_userFName=""
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'first name requuired')
		GlobalVariable.admin_userFName="its name"
		GlobalVariable.admin_userLName=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'last name requuired')
		GlobalVariable.admin_userLName="its name"
	}
	//longname
	if(i==4)
	{
		GlobalVariable.admin_userFName=GlobalVariable.Char256
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 500)
		
		WS.verifyElementPropertyValue(rslt, 'error', 'Internal Server Error')
		GlobalVariable.admin_userFName="its name"
	}
	//short password
	if(i==5)
	{
		GlobalVariable.admin_userPassword="passwor"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'password must contains at least 8 character')
	}
	//10space password
	if(i==6)
	{
		GlobalVariable.admin_userPassword="          "
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'password must not be blank')
		GlobalVariable.admin_userPassword="password"
	}
	//blankrole
	if(i==7)
		{
			GlobalVariable.admin_userRole=""
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
			
			WS.verifyResponseStatusCode(rslt, 400)
			
			//WS.verifyElementPropertyValue(rslt, 'message', 'role is required')
		}
	//invalidrole
	if(i==8)
	{
		GlobalVariable.admin_userRole="supervisor"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'role not valid')
		GlobalVariable.admin_userRole="SUPERVISOR"
	}
	//duplicateuname
	if(i==9)
	{
		GlobalVariable.admin_userUsername="supervisor"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'username already exist')
		GlobalVariable.admin_userUsername="admintestingUname"
	}
	//valid Creating
	if(i==10)
	{
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Users/Update'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'new user created successfully')
	}
}
