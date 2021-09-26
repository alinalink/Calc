package com.company;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

 public class Main {


    public static void main(String[] args) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String oper, num1,num2;
            int result;
            int flag = 0;
            int num_1, num_2;
            while (true){
                String operation = reader.readLine();
                num1 = StringToNumber.StringToNumber(operation).get(0);
                num2 = StringToNumber.StringToNumber(operation).get(2);
                oper = StringToNumber.StringToNumber(operation).get(1);
                if (NumberCheck.numberCheckArab(num1,num2)){
                    num_1 = StringToNumber.RimToInt(num1);
                    num_2 = StringToNumber.RimToInt(num2);
                    flag = 1;
                }else if(NumberCheck.numberCheckInt(num1,num2)){
                    num_1 = Integer.parseInt(num1);
                    num_2 = Integer.parseInt(num2);
                }else{
                    throw new Exception("incorrect number");
                }
                if(num_1 > 10 || num_2 > 10){
                    throw new Exception("Number > 10");
                }
                if(oper.equals("+")){
                    result = num_1+num_2;
                }else if(oper.equals("-")){
                    result = num_1-num_2;
                }else if(oper.equals("*")){
                    result = num_1*num_2;
                }else if(oper.equals("/")){
                    result = num_1/num_2;
                }else{
                    throw new Exception("incorrect command");
                }
                if (flag == 1){
                    System.out.println(StringToNumber.IntToRim(result));
                }else{
                    System.out.println(result);
                }
            }


        }
    }
    class StringToNumber {
        public static ArrayList<String> StringToNumber(String str) {
            ArrayList<String> result = new ArrayList<String>(Arrays.asList(str.split(" ")));
            return result;
        }

        public static String IntToRim(int input) {
            String s = "";
            while (input >= 100) {
                s += "C";
                input -= 100;
            }
            while (input >= 90) {
                s += "XC";
                input -= 90;
            }
            while (input >= 50) {
                s += "L";
                input -= 50;
            }
            while (input >= 40) {
                s += "XL";
                input -= 40;
            }
            while (input >= 10) {
                s += "X";
                input -= 10;
            }
            while (input >= 9) {
                s += "IX";
                input -= 9;
            }
            while (input >= 5) {
                s += "V";
                input -= 5;
            }
            while (input >= 4) {
                s += "IV";
                input -= 4;
            }
            while (input >= 1) {
                s += "I";
                input -= 1;
            }
            return s;
        }

        public static int RimToInt(String s) {
            int num = 0;
            if (NumberCheck.checkKeyMap(s)) {
                num = NumberCheck.Dictionary().get(s);
            } else {
                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    int number = strToNum(ch);
                    if (i + 1 < s.length()) {
                        int nextNumber = strToNum(s.charAt(i + 1));
                        if (nextNumber > number) {
                            num += (nextNumber - number);
                            i++;
                        } else {
                            num += number;
                        }
                    }
                }
            }
            return num;
        }

        private static int strToNum(char ch) {
            switch (ch) {
                case 'I':
                    return 1;
                case 'V':
                    return 5;
                case 'X':
                    return 10;
                case 'L':
                    return 50;
                case 'C':
                    return 100;
                case 'D':
                    return 500;
                case 'M':
                    return 1000;
                default: return -1;
            }
        }
    }
    class NumberCheck {
        public static HashMap<String, Integer> Dictionary(){
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("I", 1);
            map.put("II", 2);
            map.put("III", 3);
            map.put("IV", 4);
            map.put("V", 5);
            map.put("VI", 6);
            map.put("VII", 7);
            map.put("VIII", 8);
            map.put("IX", 9);
            map.put("X", 10);
            return map;
        }

        public static Boolean numberCheckArab(String num1, String num2){
            Boolean status = false;
            Boolean rim1 = checkKeyMap(num1);
            Boolean rim2 = checkKeyMap(num2);
            if(rim1 == true && rim2 == true){
                status = true;
            }
            return status;
        }

        public static Boolean checkKeyMap(String str){
            for (String string: Dictionary().keySet()){
                if(string.contains(str)){
                    return true;
                }
            }
            return false;
        }

        public static boolean numberCheckInt(String num1, String num2){
            try {
                Integer.parseInt(num1);
                Integer.parseInt(num2);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

