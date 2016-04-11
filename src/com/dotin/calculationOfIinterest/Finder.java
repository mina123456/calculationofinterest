/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dotin.calculationOfIinterest;

import sun.misc.Launcher;

import java.io.File;
import java.net.URL;

/**
 *
 * @author Mina Ghandchi
 */
public class Finder {

    public static DepositType find(String pckgname, String depType) {
        DepositType retVal = null;
        String name = new String(pckgname);
        if (!name.startsWith("/")) {
            name = "/" + name;
        }
        name = name.replace('.', '/');


        URL url = Launcher.class.getResource(name);
        File directory = new File(url.getFile());

        if (directory.exists()) {

            String[] files = directory.list();
            for (int i = 0; i < files.length; i++) {


                if (files[i].endsWith(".class")) {

                    String classname = files[i].substring(0, files[i].length() - 6);
                    try {

                        Object o = Class.forName(pckgname + "." + classname).newInstance();



                        if (o instanceof DepositType && depType.equals("short") && classname.contains("Short")) {
                            retVal = (DepositType) o;
                            return retVal;
                        } else if (o instanceof DepositType && depType.equals("long") && classname.contains("Long")) {
                            retVal = (DepositType) o;
                            return retVal;
                        } else if (o instanceof DepositType && depType.equals("qarz") && classname.contains("Qarz")) {
                            retVal = (DepositType) o;
                            return retVal;
                        }



                    } catch (ClassNotFoundException cnfex) {
                        System.err.println(cnfex);
                    } catch (InstantiationException iex) {
                    } catch (IllegalAccessException iaex) {
                    }
                }
            }
        }
        return retVal;
    }
}