package ru.job4j.loop;

public class Paint {

    /**
     * Метод формирует строку из Х в середине и пробелов
     * @param aster количество Х
     * @param maxaster длинна строки
     * @return строку из Х в середине и пробелов
     */
    private String PrintLine(int aster,int maxaster){
        String str="";
        int spaces=(maxaster-aster)/2;
        for (int i=1; (spaces>=0)&(i<=aster);i++){
           str=str+"X";
        }
        for(int j=1;(spaces>0)&(j<=spaces);j++){
         str=" " +str+" ";
        }
        return str;
    }


    public String piramid(int h){
        int max=(h-1)*2+1;
        String result="";
        StringBuilder builder = new StringBuilder();
        for(int i=1;(h>0)&(i<=h);i++){
            builder.append(this.PrintLine((i-1)*2+1,max));
            builder.append(System.getProperty("line.separator"));
        }
        result=builder.toString();
        return result;
    }
}
