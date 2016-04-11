package com.dotin.calculationOfIinterest;

import java.util.ArrayList;

/**
 *
 * @author Ghandchi
 */
public class Main {

    public static void main(String[] args) {
        FileParser fp = new FileParser();
        ArrayList ls = fp.extractor();
        //ArrayList ls = new ArrayList();
        //ls= fp.extractor();
        for (int i = 0; i < ls.size(); i++) {
            Deposit ba = new Deposit();
            ba = (Deposit) ls.get(i);

            System.out.println(ba.getCustomerNumber() + " # " + ba.getInterestValue());

        }
    }
}
