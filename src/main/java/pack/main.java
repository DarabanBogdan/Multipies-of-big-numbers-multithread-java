package pack;

import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main {





    private static Pair<List<Integer>,String> InmultireSecvential(Pair<List<Integer>, List<Integer>> pair){
        long startTime = System.nanoTime();

        List<Integer> buff;
        List<Integer> fin=new ArrayList<>();
        List<Integer> first=pair.getKey();
        List<Integer> second=pair.getValue();
        int carry = 0, inmTemp = 0, i = 0,j=0;
        for (i = 0; i<second.size(); i++) {

            buff = new ArrayList<Integer>(first);
            carry = 0;
            for (j = 0; j < buff.size(); j++)
            {
                inmTemp = buff.get(buff.size() - j - 1) * second.get(second.size() - i - 1) + carry;
                buff.set((buff.size() - j - 1),inmTemp % 10);
                carry = inmTemp / 10;

            }
            while (carry != 0 && j < buff.size()) {
                inmTemp = buff.get(buff.size() - j - 1) + carry;
                buff.set((buff.size() - j - 1),inmTemp % 10);
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



            ///idk reverse

        }
        long finishTime = System.nanoTime();
        return new Pair<List<Integer>, String>(fin, String.valueOf((finishTime - startTime) / 1000000));
    };



    private static Pair<List<Integer>,String> InmultireOptimizat(Pair<List<Integer>, List<Integer>> pair, int nrThread) throws InterruptedException {
        long startTime = System.nanoTime();



        List<Integer> first=pair.getKey();
        List<Integer> second=pair.getValue();



        List<Integer> t1=new ArrayList<>();
        List<Integer> t2=new ArrayList<>();
        List<Integer> t3=new ArrayList<>();
        List<Integer> t4=new ArrayList<>();
        List<Integer> t5=new ArrayList<>();
        List<Integer> t6=new ArrayList<>();
        List<Integer> t7=new ArrayList<>();
        List<Integer> t8=new ArrayList<>();
        int lenght = second.size() / nrThread + 1;
        int start = 0, finish;
        Thread th[] = new Thread[nrThread];
        List<List<Integer>> temp = new ArrayList<>();

        temp.add(t1);
        temp.add(t2);
        temp.add(t3);
        temp.add(t4);
        temp.add(t5);
        temp.add(t6);
        temp.add(t7);
        temp.add(t8);

        for (int i = 0; i < nrThread; i++) {
            finish = start + lenght;
            List<Integer> te=new ArrayList<>();
            switch (i) {
                case 0:{th[i] = new ThreadInm(first, second, t1, start, finish);break;}
                case 1:{th[i] = new ThreadInm(first, second, t2, start, finish);break;}
                case 2:{th[i] = new ThreadInm(first, second, t3, start, finish);break;}
                case 3:{th[i] = new ThreadInm(first, second, t4, start, finish);break;}
                case 4:{th[i] = new ThreadInm(first, second, t5, start, finish);break;}
                case 5:{th[i] = new ThreadInm(first, second, t6, start, finish);break;}
                case 6:{th[i] = new ThreadInm(first, second, t7, start, finish);break;}
                default:{th[i] = new ThreadInm(first, second, t8, start, finish);break;}
            }
            start = finish;
            th[i].start();

        }
        for (int i = 0; i < nrThread; i++) {
            th[i].join();
        }
        for (int i = 1; i < nrThread; i++) {
            Utils.SumaParalelOptimizat(temp.get(0), temp.get(i), 8);
            if(temp.get(0).size()<temp.get(i).size()) {
                int k = 0;
                for (k = 0; k < temp.get(0).size();k++)
                    temp.get(0).set(k,temp.get(i).get(k));
                for(;k<temp.get(i).size();k++)
                    temp.get(0).add(temp.get(i).get(k));
            }
      }
        long finishTime = System.nanoTime();
        return new Pair<List<Integer>, String>(temp.get(0), String.valueOf((finishTime - startTime) / 1000000));

    };

    private static Pair<List<Integer>,String> InmultireSimplificat(Pair<List<Integer>, List<Integer>> pair, int nrThread) throws InterruptedException {
        long startTime = System.nanoTime();



        List<Integer> first=pair.getKey();
        List<Integer> second=pair.getValue();



        List<Integer> t1=new ArrayList<>();
        List<Integer> t2=new ArrayList<>();
        List<Integer> t3=new ArrayList<>();
        List<Integer> t4=new ArrayList<>();
        List<Integer> t5=new ArrayList<>();
        List<Integer> t6=new ArrayList<>();
        List<Integer> t7=new ArrayList<>();
        List<Integer> t8=new ArrayList<>();
        int lenght = second.size() / nrThread + 1;
        int start = 0, finish;
        Thread th[] = new Thread[nrThread];
        List<List<Integer>> temp = new ArrayList<>();

        temp.add(t1);
        temp.add(t2);
        temp.add(t3);
        temp.add(t4);
        temp.add(t5);
        temp.add(t6);
        temp.add(t7);
        temp.add(t8);

        for (int i = 0; i < nrThread; i++) {
            finish = start + lenght;
            List<Integer> te=new ArrayList<>();
            switch (i) {
                case 0:{th[i] = new ThreadInm(first, second, t1, start, finish);break;}
                case 1:{th[i] = new ThreadInm(first, second, t2, start, finish);break;}
                case 2:{th[i] = new ThreadInm(first, second, t3, start, finish);break;}
                case 3:{th[i] = new ThreadInm(first, second, t4, start, finish);break;}
                case 4:{th[i] = new ThreadInm(first, second, t5, start, finish);break;}
                case 5:{th[i] = new ThreadInm(first, second, t6, start, finish);break;}
                case 6:{th[i] = new ThreadInm(first, second, t7, start, finish);break;}
                default:{th[i] = new ThreadInm(first, second, t8, start, finish);break;}
            }
            start = finish;
            th[i].start();

            th[i].join();
        }
        for (int i = 1; i < nrThread; i++) {
            Utils.SumaParalelOptimizat(temp.get(0), temp.get(i), 8);
            if(temp.get(0).size()<temp.get(i).size()) {
                int k = 0;
                for (k = 0; k < temp.get(0).size();k++)
                    temp.get(0).set(k,temp.get(i).get(k));
                for(;k<temp.get(i).size();k++)
                    temp.get(0).add(temp.get(i).get(k));
            }
        }
        long finishTime = System.nanoTime();
        return new Pair<List<Integer>, String>(temp.get(0), String.valueOf((finishTime - startTime) / 1000000));

    };

    public static void main(String[] args) throws IOException, InterruptedException {
        String output="inm";
        String input="numere.txt";
        Utils.createFileRandomNumbers(input,2,3,4);

        Pair<List<Integer>,List<Integer>> numbers=Utils.ReadFromFile(input);
        Pair<List<Integer>,String> secvential=InmultireSecvential(numbers);


        Utils.WriteInFile(output+"0.txt",secvential.getKey());
        Utils.writeFileExcel(Long.valueOf(secvential.getValue()),"0");

        numbers=Utils.ReadFromFile(input);
        Pair<List<Integer>,String> par1=InmultireOptimizat(numbers,1);
        numbers=Utils.ReadFromFile(input);
        Pair<List<Integer>,String> par2=InmultireOptimizat(numbers,2);
        numbers=Utils.ReadFromFile(input);
        Pair<List<Integer>,String> par4=InmultireOptimizat(numbers,4);
        numbers=Utils.ReadFromFile(input);
        Pair<List<Integer>,String> par6=InmultireOptimizat(numbers,6);
        numbers=Utils.ReadFromFile(input);
        Pair<List<Integer>,String> par8=InmultireOptimizat(numbers,8);
        Utils.WriteInFile(output+"O1.txt",par1.getKey());
        Utils.WriteInFile(output+"O2.txt",par2.getKey());
        Utils.WriteInFile(output+"O4.txt",par4.getKey());
        Utils.WriteInFile(output+"O6.txt",par6.getKey());
        Utils.WriteInFile(output+"O8.txt",par8.getKey());
        if( Utils.checkTwoFiles("inm0.txt","inmO1.txt"))
            Utils.writeFileExcel(Long.valueOf(par1.getValue()),"1O");
        if( Utils.checkTwoFiles("inm0.txt","inmO2.txt"))
            Utils.writeFileExcel(Long.valueOf(par2.getValue()),"2O");
        if(Utils.checkTwoFiles("inm0.txt","inmO4.txt"))
            Utils.writeFileExcel(Long.valueOf(par4.getValue()),"4O");
        if(Utils.checkTwoFiles("inm0.txt","inmO6.txt"))
            Utils.writeFileExcel(Long.valueOf(par6.getValue()),"6O");
        if(Utils.checkTwoFiles("inm0.txt","inmO8.txt"))
            Utils.writeFileExcel(Long.valueOf(par8.getValue()), "8O");




        numbers=Utils.ReadFromFile(input);
        par1=InmultireSimplificat(numbers,1);
        numbers=Utils.ReadFromFile(input);
        par2=InmultireSimplificat(numbers,2);
        numbers=Utils.ReadFromFile(input);
        par4=InmultireSimplificat(numbers,4);
        numbers=Utils.ReadFromFile(input);
        par6=InmultireSimplificat(numbers,6);
        numbers=Utils.ReadFromFile(input);
        par8=InmultireSimplificat(numbers,8);
        Utils.WriteInFile(output+"S1.txt",par1.getKey());
        Utils.WriteInFile(output+"S2.txt",par2.getKey());
        Utils.WriteInFile(output+"S4.txt",par4.getKey());
        Utils.WriteInFile(output+"S6.txt",par6.getKey());
        Utils.WriteInFile(output+"S8.txt",par8.getKey());
        if( Utils.checkTwoFiles("inm0.txt","inmS1.txt"))
            Utils.writeFileExcel(Long.valueOf(par2.getValue()),"1S");
        if( Utils.checkTwoFiles("inm0.txt","inmS2.txt"))
            Utils.writeFileExcel(Long.valueOf(par2.getValue()),"2S");
        if(Utils.checkTwoFiles("inm0.txt","inmS4.txt"))
            Utils.writeFileExcel(Long.valueOf(par4.getValue()),"4S");
        if(Utils.checkTwoFiles("inm0.txt","inmS6.txt"))
            Utils.writeFileExcel(Long.valueOf(par6.getValue()),"6S");
        if(Utils.checkTwoFiles("inm0.txt","inmS8.txt"))
            Utils.writeFileExcel(Long.valueOf(par8.getValue()), "8S");


    }
}
