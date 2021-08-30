package pack;

import sun.awt.Symbol;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Integer.min;
import static jdk.nashorn.internal.objects.NativeMath.max;

public class ThreadClass extends Thread{
    private int start;
    private int finish;
    List<Integer> first,second,suma;
    private final Lock _mutex = new ReentrantLock(true);


    public ThreadClass(List<Integer> first, List<Integer> second, List<Integer> suma, int start, int finish){
        this.finish=finish;
        this.start=start;
        this.first=first;
        this.second=second;
        this.suma=suma;
    }
    @Override
    public void run(){
        //_mutex.lock();
        int carry = 0, sumaTemp = 0, i;
        for (i = start; i < finish && i < min(first.size(), second.size()); i++)
        {
           // System.out.println(i+" "+ first.size()+" "+second.size());
            sumaTemp = first.get(first.size() - i - 1) + second.get(second.size() - i - 1) + carry;
            suma.set((suma.size() - i - 1) , sumaTemp % 10);
            carry = sumaTemp / 10;


        }

        while (carry != 0 && i < suma.size()) {

            sumaTemp = suma.get(suma.size() - i - 1) + 1;
            suma.set((suma.size() - i - 1) , sumaTemp % 10);
            carry = sumaTemp / 10;
            i++;

        }
        //Sleep(500);
        if (carry != 0)
            suma.add(0, 1);
       // _mutex.unlock();
    }


}
