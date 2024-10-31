package org.example;

import java.io.File;
public class Factory
{
    public static File createForD(Object Dirname)
    {
        String currDir= Meta.getCurrentDir();
        return new File(currDir+"/"+Dirname);
    }

    public static String[] Convert_to_array(Object Input)
    {
        if(Input instanceof String)
            return new String[]{(String) Input};
        else if(Input instanceof String[])
            return (String[])Input ;
        else{
            System.err.println("invailed input must be string or string[] only");
            return null;
        }

    }
};