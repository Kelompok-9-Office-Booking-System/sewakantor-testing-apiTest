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

for (i = 0; i < 7; i++) {
    
	//unauth
    if (i == 0) {
        GlobalVariable.token = ''

        rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Create One'))

        WS.verifyResponseStatusCode(rslt, 401)
    }
    
    //blank name
    else if (i == 1) {
		GlobalVariable.admin_complexName=""
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.CONTINUE_ON_FAILURE)
        rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Create One'))

        WS.verifyResponseStatusCode(rslt, 400)

        WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
    } 
    else if(i==2)
	{
		GlobalVariable.admin_complexName="Anggrek Permai"
		GlobalVariable.admin_complexDesc="ini default untuk test"
		GlobalVariable.admin_CityID="1"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 500)
		GlobalVariable.admin_complexName="Artapura"
	}

    //blank desc
	else if(i==3)
	{
		GlobalVariable.admin_complexDesc=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
		GlobalVariable.admin_complexDesc="Lorem ipsum dolor sit amet"
	}
    //blank city
	else if(i==4)
	{
		GlobalVariable.admin_CityID=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 400)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
	}
    //notfound city
    
    else if(i==5)
	{
		GlobalVariable.admin_CityID="0"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 404)
			
		WS.verifyElementPropertyValue(rslt, 'message', 'city not found')
	
	}

    //valid
	else if(i==6)
	{
		GlobalVariable.admin_complexName="Artapura"
		GlobalVariable.admin_complexDesc="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsu"
		GlobalVariable.admin_CityID="1"
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Complex/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'complex saved successfully')
	}

}

def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(rslt.getResponseBodyContent())

def value= result.data.id

GlobalVariable.admin_ComplexID= value

println(GlobalVariable.admin_ComplexID)



