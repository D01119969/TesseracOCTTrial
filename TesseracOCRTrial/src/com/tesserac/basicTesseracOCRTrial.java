// This file is a test of Tesserac OCR. Did nt work very well. 
//
package com.tesserac;

import java.awt.AWTException;
import java.awt.Robot;

import java.util.ArrayList;


import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;



public class basicTesseracOCRTrial {

	public static void main(String[] args) throws FindFailed, AWTException, InterruptedException {
		
		String[] requestedRoles = { "Store Marketing","Reporting", "Configuration", "Query Management", "Query", "Shipping Receiving", "Storefront-only Customer Service","Storefront-only", "Storefront"};
		//Map<String, String> env = System.getenv();
        //for (String envName : env.keySet()) {
        //    System.out.format("%s=%s%n",
        //                      envName,
        //                      env.get(envName));
        //}
		
		System.out.println("Setting up SIKULI Screen object...");
		Screen screen = new Screen();
		//String stringToFind = "Super User";
		
		
		//Find the coordinates of the string "Available Roles"
		int xCoord = -1;
		int yCoord= -1;
		try {
			xCoord = screen.findText("Avaiable Roles").getX();
			yCoord = screen.findText("Avaiable Roles").getY();
			//Location availableRolesLocation = screen.findText("Avaiable Roles").getTopLeft();
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		
		System.out.println("FOUND String [Available Roles]  at Coord [" + xCoord + "," + yCoord + "]");
		//Region selectList = screen.add(xCoord, xCoord + 50, yCoord, yCoord + 50);
		Region selectList = screen.newRegion(screen.findText("Avaiable Roles").getTopLeft(), 225, 150);	
		Location regionTopLeft = selectList.getTopLeft();
		//Location regionTopRight = selectList.getTopRight();
		//Location regionBottomLeft = selectList.getBottomLeft();
		Location regionBottomRight = selectList.getBottomRight();
		Settings sikSettings = new Settings();
		sikSettings.OcrTextRead = true; // to switch on the Region.text() function
		
		int regionTopLeftxCoord = regionTopLeft.getX();
		int regionTopLeftyCoord = regionTopLeft.getY();
		regionTopLeft.setLocation(regionTopLeftxCoord, regionTopLeftyCoord + 15);
		regionTopLeftyCoord = regionTopLeft.getY();
		
		Robot robot = new Robot();
		robot.mouseMove(regionTopLeftxCoord,regionTopLeftyCoord);
		
		System.out.println("Mouse Move Completed to Top left of region.");
		Thread.sleep(5000);
		
		int regionBottomRightxCoord = regionBottomRight.getX();
		int regionBottomRightyCoord = regionBottomRight.getY();
		
		robot.mouseMove(regionBottomRightxCoord,regionBottomRightyCoord);
		
		System.out.println("Mouse Move Completed to Bottom Right of region.");
		Thread.sleep(5000);
		
		String regionText = selectList.text();
		System.out.println("REGION TEXT [" + regionText + "]");	
		
		
		String availableRoles[] = regionText.split("\\r?\\n");
		ArrayList<String> availableRolesList = new ArrayList<String>();
		// initialize elements

		//availableRolesList.add(new Object());
		// This will add the element, resizing the ArrayList if necassary.
		for (String availableRole: availableRoles) {
			availableRolesList.add(availableRole.trim());
			//System.out.println("Role: [" + availableRole.trim() + "]");
		}
		//availableRolesList.forEach(action);
		for (String aRole:availableRolesList){
			System.out.println("AvRole: [" + aRole.trim() + "]");
			
		}
		
		
		System.out.println("Region TopLeftCorner at Coord [" + regionTopLeftxCoord + "," + regionTopLeftyCoord + "]");
		System.out.println("Region BottomRightCorner at Coord [" + regionBottomRightxCoord + "," + regionBottomRightyCoord + "]");
		
		
		int xCoordMatch = -1;
		int yCoordMatch = -1;
		for (String requestedRole: requestedRoles)
        {
        	try {
        		
        		selectList.findText(requestedRole).click();
        		xCoordMatch = selectList.findText(requestedRole).getX();
        		yCoordMatch = selectList.findText(requestedRole).getY();
				//screen.findText(requestedRole).click();
				System.out.println("FOUND String [" + requestedRole + "] at Coord [" + xCoordMatch + "," + yCoordMatch + "]");
			} catch (FindFailed e1) {

				System.out.println("NOT FOUND String [" + requestedRole + "]");
				//e1.printStackTrace();
			}        	

        }

		
		
		
		//File imageFile = new File("R:/QA_Automation/OKTA/Provisioning_DeProvissioning/Becker_ECommerce/ListBoxText3.png");
		//ITesseract instance = new Tesseract(); // JNA Interface Mapping
		//instance.setDatapath("C:/Users/D01119969/AppData/Roaming/Sikulix/SikulixTesseract");
		//try {
		//	String result = instance.doOCR(imageFile);
		//	System.out.println("Result: [" + result + "]");
		//} catch (TesseractException e){
		//	System.err.println(e.getMessage());
		//}
		
		System.out.println("Run To Completion");
	}

}
