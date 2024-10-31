package org.example;

import java.io.File;
public class Factory
{
    public static File createForD(String Dirname)
    {
        return new File(Dirname);
    }
};
