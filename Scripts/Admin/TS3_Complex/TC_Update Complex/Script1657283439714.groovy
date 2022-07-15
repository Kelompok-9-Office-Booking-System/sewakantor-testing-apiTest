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


def dump=GlobalVariable.admin_ComplexID

GlobalVariable.admin_complexName = 'Anggrek Permai'

GlobalVariable.admin_complexDesc = 'ini default untuk test'

GlobalVariable.admin_CityID = '1'

for (i = 0; i < 7; i++) {
    //unauth
    if (i == 0) {
        GlobalVariable.token = ''

        rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Update'))

        WS.verifyResponseStatusCode(rslt, 401)
    }
	// not found complex
	else if(i==1)
	{
		GlobalVariable.admin_ComplexID="0"
		
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.CONTINUE_ON_FAILURE)
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Update'))
		WS.verifyResponseStatusCode(rslt, 404)	
		WS.verifyElementPropertyValue(rslt, 'message', 'complex not found')
		GlobalVariable.admin_ComplexID=dump
	}
	//blank name
    else if (i == 2) {
		GlobalVariable.admin_complexName = ""

        rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Update'))

        WS.verifyResponseStatusCode(rslt, 400)

        WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
    }
	//duplicated 
	else if (i == 3) {
		GlobalVariable.admin_complexName = 'Anggrek Permai'
		
		GlobalVariable.admin_complexDesc = 'ini default untuk test'
		
		GlobalVariable.admin_CityID = '1'
        rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Update'))

        WS.verifyResponseStatusCode(rslt, 500)

        GlobalVariable.admin_complexName = 'Artapura'
    } else if (i == 4) {
        GlobalVariable.admin_complexDesc = ''

        rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Update'))

        WS.verifyResponseStatusCode(rslt, 400)

        WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')

        GlobalVariable.admin_complexDesc = 'Lorem ipsum dolor sit amet'
    } else if (i == 5) {
        GlobalVariable.admin_CityID = ''

        rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Update'))

        WS.verifyResponseStatusCode(rslt, 400)

        WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
    } else if (i == 6) {
        GlobalVariable.admin_CityID = '0'

        rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Update'))

        WS.verifyResponseStatusCode(rslt, 404)

        WS.verifyElementPropertyValue(rslt, 'message', 'city not found')
    } else if (i == 7) {
        GlobalVariable.admin_complexName = 'Artapura Baruu'

        GlobalVariable.admin_complexDesc = 'Desc Baru'

        GlobalVariable.admin_CityID = '1'

        rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Update'))

        WS.verifyResponseStatusCode(rslt, 200)

        WS.verifyElementPropertyValue(rslt, 'message', 'complex updated successfully')
    }
	
	
}

