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
def dump=GlobalVariable.admin_CreateID
for (int i = 0; i < 9; i++) {
    //invalid token
    if (i == 0) {
        GlobalVariable.token = ''

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Update'))

        WS.verifyResponseStatusCode(rslt, 401) //long name[256b]
        //long name[257b]
        //blank data
        //duplicated name
        //not found ID
        //duplicated with uppercases
        //numerical case
        //valid create one
    } else if (i == 1) {
        WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.admin_CityName = fill255

        println(GlobalVariable.admin_CityName)

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Update'))

        WS.verifyResponseStatusCode(rslt, 200)

        WS.verifyElementPropertyValue(rslt, 'message', 'city updated successfully')
    } else if (i == 2) {
        GlobalVariable.admin_CityName = name256

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Update'))

        WS.verifyResponseStatusCode(rslt, 500)

        WS.verifyElementPropertyValue(rslt, 'error', 'Internal Server Error')
    } else if (i == 3) {
        GlobalVariable.admin_CityName = ''

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Update'))

        WS.verifyResponseStatusCode(rslt, 400)

        WS.verifyElementPropertyValue(rslt, 'message', 'name must not be empty')
    } else if (i == 4) {
        GlobalVariable.admin_CityName = 'Jakarta'

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Update'))

        WS.verifyResponseStatusCode(rslt, 500)

        WS.verifyElementPropertyValue(rslt, 'error', 'Internal Server Error')
    } else if (i == 5) {
        GlobalVariable.admin_CityName = 'BAWLI'

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Update'))

        WS.verifyResponseStatusCode(rslt, 200)

        WS.verifyElementPropertyValue(rslt, 'message', 'city updated successfully')
    } else if (i == 6) {
        GlobalVariable.admin_CityName = 'BAWLI1234'

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Update'))

        WS.verifyResponseStatusCode(rslt, 200)

        WS.verifyElementPropertyValue(rslt, 'message', 'city updated successfully')
    } else if (i == 7) {
        GlobalVariable.admin_CityName = 'Kab Bandung'

        WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)

        rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Update'))

        WS.verifyResponseStatusCode(rslt, 200)

        WS.verifyElementPropertyValue(rslt, 'message', 'city updated successfully')
    }
	else if (i == 8) {
		GlobalVariable.admin_CreateID = 0

		rslt = WS.sendRequest(findTestObject('Postman/Admin/City/Update'))

		WS.verifyResponseStatusCode(rslt, 404)

		WS.verifyElementPropertyValue(rslt, 'message', 'city not found')
	}
}

GlobalVariable.admin_CreateID=dump
println(GlobalVariable.admin_CreateID)