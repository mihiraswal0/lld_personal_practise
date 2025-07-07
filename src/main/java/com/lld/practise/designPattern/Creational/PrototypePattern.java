package com.lld.practise.designPattern.Creational;

interface Clone<T>{
    public T getClone();
}

class A implements Clone<A>{


    private final int val;
    public A(int val){
        this.val=val;
    }
    public int getVal() {
        return val;
    }

    @Override
    public A getClone() {
        return new A(val);
    }
}




public class PrototypePattern {
    public static void main(String args[]){
        A mainA=new A(10);
        A copyA=mainA.getClone();
        System.out.println(copyA.getVal());
    }
}
