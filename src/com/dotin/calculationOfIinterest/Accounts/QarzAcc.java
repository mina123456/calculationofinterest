/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dotin.calculationOfIinterest.Accounts;

import com.dotin.calculationOfIinterest.DepositType;

/**
 *
 * @author Mina
 */
public class QarzAcc implements DepositType {

    private static int longInterest = 0;

    public int getInterestVal() {

        return longInterest;

    }
}