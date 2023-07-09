package org.jcalc.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaticStringHandler
{
    public static String ParseListStringToString(String text)
    {
        String texto = text.replace("[", "");
        String texto2 = texto.replace("]","");
        return texto2;
    }

    public static List<Integer> ParseStringToList(String text)
    {
        List<String> newList = new ArrayList<>();
        newList = new ArrayList<String>(Arrays.asList(text.split(",")));
        List<Integer> intList = new ArrayList<>();
        for (String stri: newList)
        {
            String temp = stri.replace(" ","");
            String temp2 = temp.replace("[", "");
            String temp3 = temp2.replace("]", "");
            intList.add(Integer.parseInt(temp3));
        }
        return intList;
    }

    public static List<String> GetCommands(String text)
    {
        String texto = text.replace(" ","");
        List<String> newList = new ArrayList<String>(Arrays.asList(texto.split(",")));

        for(String comma: newList)
        {
            if(comma.equals("/delete"))
            {
                /**new Alert(Alert.AlertType.CONFIRMATION); NOT IMPLEMENTED**/
            }
        }

        return newList;
    }

    public static int GetDeletedItem(String text)
    {
        if(text != null)
        {
            String texto = text.replace(" ", "");
            List<String> newList = new ArrayList<String>(Arrays.asList(texto.split(",")));

            int index = 10000;

            for (String comma : newList) {
                if (comma.equals("/delete")) {
                    index = newList.indexOf(comma);
                }
            }

            return index;
        }

        return 10000;
    }
}
