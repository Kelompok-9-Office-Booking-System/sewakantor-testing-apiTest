<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Update</name>
   <tag></tag>
   <elementGuidId>bd507ea2-f49a-41b2-a4a4-f4a58f3f630f</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;parameters&quot;: [
    {
      &quot;name&quot;: &quot;name&quot;,
      &quot;value&quot;: &quot;${name}&quot;,
      &quot;type&quot;: &quot;Text&quot;,
      &quot;contentType&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;description&quot;,
      &quot;value&quot;: &quot;${desc}&quot;,
      &quot;type&quot;: &quot;Text&quot;,
      &quot;contentType&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;images&quot;,
      &quot;value&quot;: &quot;C:\\Users\\user\\Downloads\\whatsapp.png&quot;,
      &quot;type&quot;: &quot;File&quot;,
      &quot;contentType&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;buildingId&quot;,
      &quot;value&quot;: &quot;${buildingID}&quot;,
      &quot;type&quot;: &quot;Text&quot;,
      &quot;contentType&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;capacity&quot;,
      &quot;value&quot;: &quot;${capacity}&quot;,
      &quot;type&quot;: &quot;Text&quot;,
      &quot;contentType&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;height&quot;,
      &quot;value&quot;: &quot;${height}&quot;,
      &quot;type&quot;: &quot;Text&quot;,
      &quot;contentType&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;length&quot;,
      &quot;value&quot;: &quot;${length}&quot;,
      &quot;type&quot;: &quot;Text&quot;,
      &quot;contentType&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;width&quot;,
      &quot;value&quot;: &quot;${width}&quot;,
      &quot;type&quot;: &quot;Text&quot;,
      &quot;contentType&quot;: &quot;&quot;
    }
  ]
}</httpBodyContent>
   <httpBodyType>form-data</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <webElementGuid>cd654fcd-90e8-42f1-aa16-254886a57831</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.token}</value>
      <webElementGuid>a89f37d7-1a2c-456d-98c7-03f97ecafe6e</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.3.5</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>${url}/v1/admin/units/${unitID}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>GlobalVariable.url</defaultValue>
      <description></description>
      <id>be16effb-660d-472b-aa03-0987d13fb342</id>
      <masked>false</masked>
      <name>url</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.admin_unitName</defaultValue>
      <description></description>
      <id>e002900c-04b8-486e-b399-0c903b66afbb</id>
      <masked>false</masked>
      <name>name</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.admin_unitDesc</defaultValue>
      <description></description>
      <id>2be0cc74-f932-43c5-adf8-ba2118e97557</id>
      <masked>false</masked>
      <name>desc</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.admin_UnitBuildingID</defaultValue>
      <description></description>
      <id>92008791-3b0a-4e15-95ee-156bca296b11</id>
      <masked>false</masked>
      <name>buildingID</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.admin_unitCapacity</defaultValue>
      <description></description>
      <id>7a39c95b-33ad-4115-bdf0-d3c7ba199144</id>
      <masked>false</masked>
      <name>capacity</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.admin_unitLength</defaultValue>
      <description></description>
      <id>9c51d9ee-2fdd-4c46-9a80-812d329d3894</id>
      <masked>false</masked>
      <name>length</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.admin_unitWidth</defaultValue>
      <description></description>
      <id>5722f309-6d97-4e02-ae7b-59d29950f459</id>
      <masked>false</masked>
      <name>width</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.admin_unitHeight</defaultValue>
      <description></description>
      <id>738ccf89-aea0-46cf-a9f5-072b4965b7e9</id>
      <masked>false</masked>
      <name>height</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.admin_UnitID</defaultValue>
      <description></description>
      <id>2e640661-2ae7-4cd7-bbca-5f65bf2a2b5b</id>
      <masked>false</masked>
      <name>unitID</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
