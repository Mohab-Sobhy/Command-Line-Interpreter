import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileAction
{
    public static void touch (Object input)
    {
        String [] filename_s = Factory.Convert_to_array(input);
        assert(filename_s != null);
        
        for(String filename : filename_s)
        {
            File newfile = Factory.createForD(filename);
            try {
                if(newfile.createNewFile())
                    System.out.println("File: "+newfile.getName()+" is created successfully.");
                else    
                    System.err.println("File: "+newfile.getName()+ " is already exist besfore.");

            } catch (Exception e) {
                System.err.println("error creating a file: "+newfile.getName());
            }
        }

    }

    public static void rm(Object input)
    {
        String [] filename_s = Factory.Convert_to_array(input);
        assert(filename_s != null);
        
        for(String filename : filename_s)
        {
            File file = Factory.createForD(filename);
            if(file.exists())
            {
                if(file.delete())
                {
                    System.out.println("File: "+file.getName()+" deleted successfully");
                }
                else    
                    System.err.println("failed to delete the file: "+file.getName());
            }
            else
                System.err.println("File: "+file.getName()+" is not exists already");
        }

    }

    public static void cat(Object input) throws IOException
    {
        String[] filename_s = Factory.Convert_to_array(input);
        assert(filename_s != null);

        for(String filename : filename_s)
        {
            File file = Factory.createForD(filename);
            if(file.exists())
            {
                System.out.println("Content of the file : "+file.getName()+" :");
                Files.lines(Path.of(file.getPath())).forEach(System.out::println);
            }
            else    
                System.err.println("File: "+file.getName()+" is not exists .");
        }
    }

    public static void mv(Object input , String dir_or_file)
    {
        String[] filename_s = Factory.Convert_to_array(input);
        assert(filename_s != null);

        if(filename_s.length == 1)
        {
            File source_file = Factory.createForD(filename_s[0]);
            File DestFile = Factory.createForD(dir_or_file);

            if(source_file.exists())
            {
                if(source_file.renameTo(DestFile))
                    System.out.println("file : "+source_file.getName() + "move to: "+DestFile.getPath());
                else    
                    System.err.println("error to move: "+source_file.getName()+"to: "+DestFile.getPath());
            }
            else
                System.err.println("File: "+source_file.getName()+" is not exists");
            return ;
        }
        else
        {
            File Dir= Factory.createForD(dir_or_file);
            if(!Dir.exists())
                Dir.mkdirs();
            else if(!Dir.isDirectory())
            {
                System.err.println("no Directory name : "+dir_or_file);
                return;
            }
            
                for(String filename : filename_s)
                {
                    File sourceFile = Factory.createForD(filename);
                    if(sourceFile.exists())
                    {
                        File DestFile = new File(dir_or_file, sourceFile.getName());
                        if(sourceFile.renameTo(DestFile))
                            System.out.println("File: "+sourceFile.getName()+"moved  to Dir: "+Dir.getPath()+" successfully");
                        else        
                            System.err.println("error to move File: "+sourceFile.getName()+"to Dir: "+Dir.getPath());
                    }
                    else
                    {
                        System.err.println("failed to move File: "+sourceFile.getName());
                    }
                }
        }

        
    }
};
