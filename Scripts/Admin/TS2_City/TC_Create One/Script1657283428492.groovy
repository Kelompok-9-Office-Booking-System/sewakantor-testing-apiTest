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

for (int i = 0; i < 8; i++) {
    //invalid token
    if (i == 0) {
        GlobalVariable.token = ''

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Create One'))

        WS.verifyResponseStatusCode(rslt, 401)
    } 
	//255 CHAR NAME
	else if (i == 1) {
        WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.admin_CityName = name255

        println(GlobalVariable.admin_CityName)

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Create One'))

        WS.verifyResponseStatusCode(rslt, 200)

        WS.verifyElementPropertyValue(rslt, 'message', 'city saved successfully')
		
		//deleting data
		def slurper = new groovy.json.JsonSlurper()
		def result = slurper.parseText(rslt.getResponseBodyContent())
		
		def value= result.data.id
		
		GlobalVariable.admin_CreateID=value
		rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Delete'))
		WS.verifyResponseStatusCode(rslt, 200)
    } 
	//256 Char Name
	else if (i == 2) {
        GlobalVariable.admin_CityName = name256

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Create One'))

        WS.verifyResponseStatusCode(rslt, 500)

        WS.verifyElementPropertyValue(rslt, 'error', 'Internal Server Error')
    } 
	//Blank City Name
	else if (i == 3) {
        GlobalVariable.admin_CityName = ''

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Create One'))

        WS.verifyResponseStatusCode(rslt, 400)

        WS.verifyElementPropertyValue(rslt, 'errors[0].error', 'name must not be empty')
    } 
	//Duplicated Name
	else if (i == 4) {
        GlobalVariable.admin_CityName = 'Jakarta'

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Create One'))

        WS.verifyResponseStatusCode(rslt, 500)

        WS.verifyElementPropertyValue(rslt, 'error', 'Internal Server Error')
    } 
	
	//Duplicated Uppercases Name
	else if (i == 5) {
        GlobalVariable.admin_CityName = 'JAKARTA'

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Create One'))

        WS.verifyResponseStatusCode(rslt, 200)

        WS.verifyElementPropertyValue(rslt, 'message', 'city saved successfully')
		
		
		//deleting data
		def slurper = new groovy.json.JsonSlurper()
		def result = slurper.parseText(rslt.getResponseBodyContent())
		
		def value= result.data.id
		
		GlobalVariable.admin_CreateID=value
		rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Delete'))
    } 
	//Numeric City Name
	else if (i == 6) {
        GlobalVariable.admin_CityName = '123'

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Create One'))

        WS.verifyResponseStatusCode(rslt, 200)

        WS.verifyElementPropertyValue(rslt, 'message', 'city saved successfully')
		
		
		//deleting data
		def slurper = new groovy.json.JsonSlurper()
		def result = slurper.parseText(rslt.getResponseBodyContent())
		
		def value= result.data.id
		
		GlobalVariable.admin_CreateID=value
		rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Delete'))
    } 
	//Valid City Name
	else if (i == 7) {
        GlobalVariable.admin_CityName = 'Bawli'

        WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Create One'))

        WS.verifyResponseStatusCode(rslt, 200)

        WS.verifyElementPropertyValue(rslt, 'message', 'city saved successfully')
    }
}

def slurper = new groovy.json.JsonSlurper()

def result = slurper.parseText(rslt.getResponseBodyContent())

def value = result.data.id

GlobalVariable.admin_CreateID = value

println(GlobalVariable.admin_CreateID)


