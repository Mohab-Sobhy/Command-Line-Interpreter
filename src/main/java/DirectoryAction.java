import java.io.File;

public class DirectoryAction
{
    
    public static void mkdir(Object input)
    {
        String[] Dirname_s = Factory.Convert_to_array(input);
        assert(Dirname_s!=null);

        for(String Dirname : Dirname_s)
        {
            File newdir = Factory.createForD(Dirname);
        
            if(!newdir.exists())
            {
                if(newdir.mkdirs()) 
                    System.out.println("Dir: "+newdir.getName()+" created successfully");
                else    
                    System.err.println("failed to create:"+newdir.getName());
            }
            else    
                System.err.println("Dir: "+newdir.getName()+" is already exists before");
        }
    }

    public static void rmdir(Object input)
    {
        String[] Dirname_s = Factory.Convert_to_array(input);
        assert(Dirname_s!=null);

        for( String Dirname : Dirname_s )
        {
            File Dir = Factory.createForD(Dirname);

            if(Dir.exists()&&Dir.isDirectory())
            {
                if(Dir.delete())
                    System.out.println("Dir: "+Dir.getName()+" deleted successfully");
                else    
                    System.err.println("failed to remove Dir: "+Dir.getName());
            }
            else
                System.err.println("Dir: "+Dir.getName()+" is not exists to remove it");
        }
    }
    

};
