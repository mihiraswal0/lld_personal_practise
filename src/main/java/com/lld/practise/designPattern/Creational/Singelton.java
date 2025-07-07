//package com.lld.practise.designPattern.Structural;
//
//
////1. Eager Initialization
//class Db{
//    private final static Db objDb=new Db();
//    private Db(){
////        so that no one can create object of it
//    }
//    public static Db getObject(){
//        return objDb;
//    }
//}
//
////2. Lazy Initiation: Problem concurrency issues But no multiple threads will be waiting even if object already exist
//class Db{
//    private static Db objDb;
//    private Db(){
////        so that no one can create object of it
//    }
//    public static synchronized Db getObject(){
//        if(objDb==null)
//            objDb=new Db();
//            return objDb;
//    }
//}
//
//
//class Db{
//    private static Db objDb;
//    private Db(){
////        so that no one can create object of it
//    }
//    public static synchronized Db getObject(){
//        if(objDb==null) {
//            synchronized (Db.class) {
//                if (objDb == null) {
//                    objDb = new Db();
//                }
//            }
//           }
//        return objDb;
//    }
//}
//
//
//
//
//
