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


for(i=0;i<16;i++)
{
	//No token creating
	if(i==0)
	{
		
		GlobalVariable.token=""
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 401)
	}
	//255 char name
	else if(i==1)
	{
		WebUI.callTestCase(findTestCase('Admin/Dummy Admin/LoginGetToken'), [:], FailureHandling.STOP_ON_FAILURE)
		
		GlobalVariable.admin_unitName=GlobalVariable.Char255
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
		
		def result = slurper.parseText(rslt.getResponseBodyContent())
		
		def value = result.data.id
		
		GlobalVariable.admin_UnitID = value
		
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
	}
		
	//256 char name
	else if(i==2)
		{
			
			GlobalVariable.admin_unitName=GlobalVariable.Char256
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
			
			WS.verifyResponseStatusCode(rslt, 500)
			
			WS.verifyElementPropertyValue(rslt, 'message', 'Internal Server Error')
		}
	//blanking building name
	else if(i==3)
		{	
			GlobalVariable.admin_unitName=""
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
				
			WS.verifyResponseStatusCode(rslt, 400)
				
			WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
		}
	//duplicated name in one id
	else if(i==3)
		{
			GlobalVariable.admin_unitName="Unit	404"
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
					
			WS.verifyResponseStatusCode(rslt, 200)
					
			WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
			
			def result = slurper.parseText(rslt.getResponseBodyContent())
			
			def value = result.data.id
			
			GlobalVariable.admin_UnitID = value
			
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
		}
	//not found building
	else if(i==4)
		{
			GlobalVariable.admin_UnitBuildingID="0"
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
						
			WS.verifyResponseStatusCode(rslt, 404)
						
			WS.verifyElementPropertyValue(rslt, 'message', 'building not found')
			
		}
	//blanking building ID
	else if(i==5)
		{
			GlobalVariable.admin_UnitBuildingID=""
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
					
			WS.verifyResponseStatusCode(rslt, 400)
					
			WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
			GlobalVariable.admin_UnitBuildingID="1"
		}
	//negative capacity
	else if(i==6)
		{
			GlobalVariable.admin_unitCapacity="-10"
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
						
			WS.verifyResponseStatusCode(rslt, 200)
						
			WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
			
			def result = slurper.parseText(rslt.getResponseBodyContent())
			
			def value = result.data.id
			
			GlobalVariable.admin_UnitID = value
			
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
		}
	//decimmal capacity
	else if(i==7)
		{
			GlobalVariable.admin_unitCapacity="1.56"
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
						
			WS.verifyResponseStatusCode(rslt, 400)
						
			WS.verifyElementPropertyValue(rslt, 'message', 'Malformed request')
		}
	//blanking capacity
	else if(i==8)
		{
			GlobalVariable.admin_unitCapacity=""
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
							
			WS.verifyResponseStatusCode(rslt, 200)
							
			WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
			GlobalVariable.admin_unitCapacity="20"
			
			def result = slurper.parseText(rslt.getResponseBodyContent())
			
			def value = result.data.id
			
			GlobalVariable.admin_UnitID = value
			
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
		}
	//negative height
	else if(i==9)
		{
			GlobalVariable.admin_unitCapacity="-10"
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
								
			WS.verifyResponseStatusCode(rslt, 200)
								
			WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
			
			def result = slurper.parseText(rslt.getResponseBodyContent())
			
			def value = result.data.id
			
			GlobalVariable.admin_UnitID = value
			
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
		}
	//blanking height
	else if(i==10)
		{
			GlobalVariable.admin_unitHeight=""
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
								
			WS.verifyResponseStatusCode(rslt, 200)
								
			WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
			GlobalVariable.admin_unitHeight="2"
			
			def result = slurper.parseText(rslt.getResponseBodyContent())
			
			def value = result.data.id
			
			GlobalVariable.admin_UnitID = value
			
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
		}
	//negative width
	else if(i==11)
		{
			GlobalVariable.admin_unitWidth="-10"
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
									
			WS.verifyResponseStatusCode(rslt, 200)
									
			WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
			
			def result = slurper.parseText(rslt.getResponseBodyContent())
			
			def value = result.data.id
			
			GlobalVariable.admin_UnitID = value
			
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
		}
	//blanking width
	else if(i==12)
		{
			GlobalVariable.admin_unitWidth=""
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
								
			WS.verifyResponseStatusCode(rslt, 200)
								
			WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
			GlobalVariable.admin_unitWidth="1"
			
			def result = slurper.parseText(rslt.getResponseBodyContent())
			
			def value = result.data.id
			
			GlobalVariable.admin_UnitID = value
			
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
		}
	//negative length
	else if(i==13)
		{
			GlobalVariable.admin_unitLength="-10"
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
									
			WS.verifyResponseStatusCode(rslt, 200)
									
			WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
			
			def result = slurper.parseText(rslt.getResponseBodyContent())
			
			def value = result.data.id
			
			GlobalVariable.admin_UnitID = value
			
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
		}
	//blanking length
	else if(i==14)
		{
			GlobalVariable.admin_unitLength=""
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
								
			WS.verifyResponseStatusCode(rslt, 200)
								
			WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
			GlobalVariable.admin_unitLength="2"
			
			def result = slurper.parseText(rslt.getResponseBodyContent())
			
			def value = result.data.id
			
			GlobalVariable.admin_UnitID = value
			
			rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
		}
	//normal
	else if(i==15)
	{
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Create One'))
		
		WS.verifyResponseStatusCode(rslt, 200)
		
		WS.verifyElementPropertyValue(rslt, 'message', 'unit saved successfully')
		
		def result = slurper.parseText(rslt.getResponseBodyContent())
		
		def value = result.data.id
		
		GlobalVariable.admin_UnitID = value
		
		rslt = WS.sendRequest(findTestObject('Postman/Admin/Unit/Delete'))
	}
	
}
