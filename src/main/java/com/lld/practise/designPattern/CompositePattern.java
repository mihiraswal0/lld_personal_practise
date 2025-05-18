package com.lld.practise.designPattern;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


interface FileSytem{
    public void show();
}
@Getter
@AllArgsConstructor
class File implements FileSytem{

    private final String name;

    public void show(){
        System.out.println("Name of the file"+name);
    }
}
@Getter
@AllArgsConstructor
class Folder implements FileSytem{
    private final String name;
    List<FileSytem> folderPath;

    public Folder(String name){
        this.name=name;
        folderPath=new ArrayList<>();
    }
    public void addFile(FileSytem fileSytem){
        folderPath.add(fileSytem);
    }

    public void show(){
        folderPath.forEach(FileSytem::show);
    }

}


public class CompositePattern {
    public void implementComposite(){
        File file1=new File("file1");
        File file2=new File("file2");
        Folder folder=new Folder("root");
        Folder subfolder=new Folder("subfolder");
        folder.addFile(file1);
        folder.addFile(file2);
        folder.addFile(subfolder);
        subfolder.addFile(new File("file3"));
        folder.show();


    }
}
