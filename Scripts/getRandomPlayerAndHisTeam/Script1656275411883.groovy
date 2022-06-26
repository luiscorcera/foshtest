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

//Author: Luis Andrade Lorenzo Corcera
//Email: luis.andradelc@gmail.com
//Date: 26/06/2022

//Generate random id number to select a player
GlobalVariable.id = (((Math.random() * (3092 - 1)) + 1) as int)

//print the random value
println(GlobalVariable.id)

//GET request with the random id generated and storing the result on the variable responseRest
def responseRest = WS.sendRequestAndVerify(findTestObject('getSpecificPlayer', [('id') : GlobalVariable.id]))

//print the API response
println(responseRest.getResponseText())

//json text parsing into groovy data
def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(responseRest.getResponseText())

//storing the team id from the response in a variable
def getTeamId = result.team.id

//print the team id
println(getTeamId)

//storing at the global variable the team id
GlobalVariable.teamId = getTeamId

//GET request with the team id stored at the global variable
def responseTeam = WS.sendRequestAndVerify(findTestObject('getPlayersTeam', [('teamId') : GlobalVariable.teamId]))

//print of the team information
println(responseTeam.getResponseText())

//json text parsing into groovy data of the response
def resultTeam = slurper.parseText(responseTeam.getResponseText())

//storing the team name of the response in a variable
def teamName = resultTeam.full_name

//printing the name of the team of the random player
println("The team of the random player is: "+teamName)
