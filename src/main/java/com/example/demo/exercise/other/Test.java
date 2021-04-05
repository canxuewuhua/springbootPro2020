package com.example.demo.exercise.other;

public class Test {

    public static void main(String[] args) {

    }

    public static int countConsistentStrings(String allowed, String[] words) {
        int result=words.length;
        for (int i=0;i<words.length;i++){
            String ss = words[i];
            for (int j=0;j<ss.length();j++){
                char ch = ss.charAt(j);
                String str =  String.valueOf(ch);
                if (!allowed.contains(str)){
                    result--;
                    break;
                }
            }
        }
        return result;
    }
}
