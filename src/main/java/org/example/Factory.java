package org.example;

import java.io.File;
public class Factory
{
    public static File createForD(Object Dirname)
    {
        String currDir= Meta.getCurrentDir();
        return new File(currDir+"/"+Dirname);
    }
};
