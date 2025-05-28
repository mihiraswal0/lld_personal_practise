package com.lld.practise.designPattern;

class RoundHole{
    private final int radiusLimit;

    public RoundHole(int radiusLimit){
        this.radiusLimit=radiusLimit;
    }

    public void checkPeg(RoundPeg roundPeg){
        if(roundPeg.getSize()<=this.radiusLimit){
            System.out.println("Accepted for Round Hole");
        }
        else
        {
            System.out.println("Not Accpeted For RoundHole");
        }
    }
}

class RoundPeg{
    private final int size;
    public RoundPeg(int size){
        this.size=size;
    }
    public int getSize() {
        return size;
    }

}


class SquarePeg{
    public final int width;
    public SquarePeg(int width){
        this.width=width;
    }
}

class SquarePegAdapter extends RoundPeg{
    private final SquarePeg squarePeg;
    public SquarePegAdapter(SquarePeg squarePeg){
        super(0);
        this.squarePeg=squarePeg;
    }
    @Override
    public int getSize() {
       return squarePeg.width*2;
    }
}


public class AdapterPattern {

    public void implementAdapterPattern(){
        SquarePeg squarePeg=new SquarePeg(10);
        SquarePegAdapter squarePegAdapter=new SquarePegAdapter(squarePeg);
        RoundHole roundHole=new RoundHole(20);
        roundHole.checkPeg(squarePegAdapter);
    }
}

//SquarePegAdapter extends RoundPeg
//RoundHole has a RoundPeg
//SquarePegAdapter has SquarePeg

