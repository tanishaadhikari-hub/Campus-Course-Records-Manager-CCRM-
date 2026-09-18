package edu.ccrm.util;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {



    public void backupDirectory(Path sourceDir, Path backupRootDir)  throws IOException {
        // Create timestamped backup folder
        String timestamp =   LocalDateTime.now().format(DateTimeFormatter.ofPattern(  "yyyy-MM-dd_HH-mm-ss" ))  ;


        Path backupDir =     backupRootDir.resolve("backup_"   + timestamp  ) ;



        if  (!Files.exists(backupRootDir   )) {


            Files.createDirectories(backupRootDir)  ;
        }

        Files.createDirectories(backupDir) ;



        copy_Diractory(sourceDir, backupDir);

        System.out.println(  "Backup created at: "    + backupDir.toString() )   ;


    }



    private void copy_Diractory(Path source, Path target) throws IOException

    {




        Files.walk(source)
                .forEach(sourcePath -> {
                    try {


                        Path targetPath = target.resolve(source.relativize(sourcePath));
                        if (Files.isDirectory(sourcePath)) {

                            Files.createDirectories(targetPath) ;
                        } else {
                            Files.copy(sourcePath, targetPath , StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException e ) {
                        throw new RuntimeException(e);




                    }

                });
    }
}
