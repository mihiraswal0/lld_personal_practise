package com.lld.practise.designPattern;



interface Datasource{

    public void readData();
    public void writeData(String data);
}

class FileDataSource implements Datasource{
    public String filename;

    public FileDataSource(String filename){
        this.filename=filename;
    }

    public void readData(){
        System.out.println("Reading data from source");
    }
    public void writeData(String data){
        System.out.println("writing data "+data);
    }
}

 abstract class  DatasourceDecorator implements Datasource{
    protected  Datasource datasource;

    public DatasourceDecorator(Datasource datasource){
        this.datasource=datasource;
    }
 }

 class EncryptDataSource extends DatasourceDecorator{

    public EncryptDataSource(Datasource dataSource){
        super(dataSource);
    }
     @Override
     public void readData() {

     }

     @Override
     public void writeData(String data) {

     }
 }



public class DecoratorPattern {
    Datasource fileDataSource=new FileDataSource("filename");
    Datasource dataSource=new EncryptDataSource(fileDataSource);
}

