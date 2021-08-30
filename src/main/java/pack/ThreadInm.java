package pack;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ThreadInm extends Thread {
    private int start;
    private int finish;
    List<Integer> first,second,fin, buff;


    public ThreadInm(List<Integer> first,List<Integer> second,List<Integer> fin, int start, int finish) {
        this.start=start;
        this.finish=finish;
        this.first=first;
        this.second=second;
        this.fin=fin;


    }
    @Override
    public void run(){
        int carry = 0, inmTemp = 0, i = 0, j = 0;

        for (i = start; i < finish && i < second.size(); i++) {
            buff = new ArrayList<Integer>(first);
            carry = 0;
            for (j = 0; j < buff.size(); j++)
            {
                inmTemp = buff.get(buff.size() - j - 1) * second.get(second.size() - i - 1) + carry;
                buff.set((buff.size() - j - 1) , inmTemp % 10);
                carry = inmTemp / 10;

            }
            while (carry != 0 && j < buff.size()) {
                inmTemp = buff.get(buff.size() - j - 1) + carry;
                buff.set((buff.size() - j - 1) , inmTemp % 10);
                carry = inmTemp / 10;
                j++;
            }
            if (carry != 0)
                buff.add(0, carry);

            for (int k = 0; k < i; k++) {
                buff.add(0);
            }



            Utils.SumaParalelOptimizat(fin, buff, 8);
            if(fin.size()<buff.size()) {
                int k = 0;
                for (k = 0; k < fin.size();k++)
                    fin.set(k,buff.get(k));
                for(;k<buff.size();k++)
                    fin.add(buff.get(k));
            }


        }


    }
}
