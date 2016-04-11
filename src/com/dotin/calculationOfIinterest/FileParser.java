package com.dotin.calculationOfIinterest;

import com.dotin.calculationOfIinterest.dotinExceptions.DotinNumberFormatException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FileParser {

      public ArrayList extractor() {
        ArrayList list = new ArrayList();
        try {
            
//            ClassLoader classLoader = getClass().getClassLoader();
//	    File inputFile = new File(classLoader.getResource("input.xml").getFile());


        URL url = getClass().getResource("input.xml");
        File inputFile = new File(url.toURI());
        
        //    File inputFile = new File("d://input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("deposit");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Deposit deposit = new Deposit();
                Node nNode = nList.item(temp);
                // System.out.println("\nCurrent Element :"
                //  + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String customerNumber = eElement.getElementsByTagName("customerNumber").item(0).getTextContent();
                    deposit.setCustomerNumber(customerNumber);

                    String depositType = eElement.getElementsByTagName("depositType").item(0).getTextContent();

                    DepositType dt = null;
                    if (depositType.equalsIgnoreCase("ShortTerm")) {
                        dt = Finder.find("com.dotin.calculationOfIinterest.Accounts", "short");
                    } else if (depositType.equalsIgnoreCase("LongTerm")) {
                        dt = Finder.find("com.dotin.calculationOfIinterest.Accounts", "long");
                    } else if (depositType.equalsIgnoreCase("Qarz")) {
                        dt = Finder.find("com.dotin.calculationOfIinterest.Accounts", "qarz");
                    } else {
                        deposit.setIsvalid(false);
                    }

                    deposit.setDepositType(dt);

                    String durationDays = eElement.getElementsByTagName("durationDays").item(0).getTextContent();
                    try {
                        deposit.setDurationDays(Long.parseLong(durationDays));
                        if (deposit.getDurationDays() < 1) {
                            throw new DotinNumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        deposit.setIsvalid(false);
                     
                    }

                    String depositBalance = eElement.getElementsByTagName("depositBalance").item(0).getTextContent();
                    try {
                        deposit.setDepositBalance(new BigDecimal(depositBalance));
                        if (deposit.getDepositBalance().compareTo(new BigDecimal(0)) < 0) {
                            throw new DotinNumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        deposit.setIsvalid(false);
                      
                    }

//                    int ir = 1;
//
//                    if (deposit.getDepositType().equalsIgnoreCase("LongTerm")) {
//                        // LongTerm lt = new LongTerm();
//                        ir = 2;
//                    }

                    if (deposit.getIsvalid()) {
                        BigDecimal profit = new BigDecimal(dt.getInterestVal()).
                                multiply(deposit.getDepositBalance().multiply(new BigDecimal(deposit.getDurationDays())));

                        deposit.setInterestValue(profit.divide(new BigDecimal(36500), 2, BigDecimal.ROUND_HALF_UP));

                        list.add(deposit);
                    }

                }
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        Collections.sort(list, new Comparator<Deposit>() {
            public int compare(Deposit lhs, Deposit rhs) {
                return rhs.getInterestValue().compareTo(lhs.getInterestValue());
            }
        });

        return list;
    }
}
