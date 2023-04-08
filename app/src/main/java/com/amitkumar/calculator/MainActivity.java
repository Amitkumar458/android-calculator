package com.amitkumar.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import java.util.Stack;


    public class MainActivity extends AppCompatActivity {
        String str = "";
        boolean change = true;
        String txt = "= Error";


        public int calculate(String s) {
            int len;
            if (s == null || (len = s.length()) == 0) return 0;
            Stack<Integer> stack = new Stack<>();
            int num = 0;
            char sign = '+';
            for (int i = 0; i < len; i++) {
                if (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                }
                if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
                    if (sign == '-') {
                        stack.push(-num);
                    }
                    if (sign == '+') {
                        stack.push(num);
                    }
                    if (sign == '*') {
                        stack.push(stack.pop() * num);
                    }
                    if (sign == '/') {
                        stack.push(stack.pop() / num);
                    }
                    sign = s.charAt(i);
                    num = 0;
                }
            }

            int re = 0;
            for (int i : stack) {
                re += i;
            }
            return re;
        }

        public void num(char c , TextView textView , TextView textView2){
            if(change){
                str = "";
                textView2.setTextSize(25);
                change = false;
            }
            str += c;
            textView.setText(str);
            try {
                String sol = "= " + calculate(str);
                textView2.setText(sol);
            }catch (Exception e){
                textView2.setText(txt);
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button add = findViewById(R.id.button17);
            Button sub = findViewById(R.id.button13);
            Button mul = findViewById(R.id.button9);
            Button div = findViewById(R.id.button4);
            Button del = findViewById(R.id.button5);
            Button solve = findViewById(R.id.button21);
            Button clear = findViewById(R.id.button2);
            Button point = findViewById(R.id.button20);
            Button one = findViewById(R.id.button14);
            Button two = findViewById(R.id.button15);
            Button three = findViewById(R.id.button16);
            Button four = findViewById(R.id.button10);
            Button five = findViewById(R.id.button11);
            Button six = findViewById(R.id.button12);
            Button seven = findViewById(R.id.button6);
            Button eight = findViewById(R.id.button7);
            Button nine = findViewById(R.id.button8);
            Button zero = findViewById(R.id.button19);

            TextView textView = findViewById(R.id.textView2);
            TextView textView2 = findViewById(R.id.textView);

            one.setOnClickListener(view -> num('1' , textView , textView2));
            two.setOnClickListener(view -> num('2' , textView , textView2));
            three.setOnClickListener(view -> num('3' , textView , textView2));
            four.setOnClickListener(view -> num('4' , textView , textView2));
            five.setOnClickListener(view -> num('5' , textView , textView2));
            six.setOnClickListener(view -> num('6' , textView , textView2));
            seven.setOnClickListener(view -> num('7' , textView , textView2));
            eight.setOnClickListener(view -> num('8' , textView , textView2));
            nine.setOnClickListener(view -> num('9' , textView , textView2));
            zero.setOnClickListener(view -> num('0' , textView , textView2));

            add.setOnClickListener(view -> {
                if(str.length() != 0){
                    char last = str.charAt(str.length()-1);
                    if(last == '+' || last == '-' || last == '*' || last == '/'){
                        str = str.substring(0, str.length()-1);
                        str += '+';
                        textView.setText(str);
                    }else{
                        str += "+";
                        textView.setText(str);
                    }
                }
            });
            div.setOnClickListener(view -> {
                if(str.length() != 0){
                    char last = str.charAt(str.length()-1);
                    if(last == '+' || last == '-' || last == '*' || last == '/'){
                        str = str.substring(0, str.length()-1);
                        str += '/';
                        textView.setText(str);
                    }else{
                        str += "/";
                        textView.setText(str);
                    }
                }
            });
            mul.setOnClickListener(view -> {
                if(str.length() != 0){
                    char last = str.charAt(str.length()-1);
                    if(last == '+' || last == '-' || last == '*' || last == '/'){
                        str = str.substring(0, str.length()-1);
                        str += '*';
                        textView.setText(str);
                    }else{
                        str += "*";
                        textView.setText(str);
                    }
                }
            });
            sub.setOnClickListener(view -> {
                if(change){
                    str = "";
                    change = false;
                }
                if(str.length() != 0){
                    char last = str.charAt(str.length()-1);
                    if(last == '+' || last == '-' || last == '*' || last == '/'){
                        str = str.substring(0, str.length()-1);
                        str += '-';
                        textView.setText(str);
                    }else{
                        str += "-";
                        textView.setText(str);
                    }
                }else{
                    str += '-';
                    textView.setText(str);
                }
            });
            solve.setOnClickListener(view -> {
                try {
                    String sol = "= " + calculate(str);
                    textView2.setText(sol);
                }catch (Exception e){
                    str = "Error";
                    textView2.setText(str);
                }
                textView.setText("");
                textView2.setTextSize(50);
                change = true;
            });
            clear.setOnClickListener(view -> {
                str = "";
                textView.setText("");
                textView2.setText("");
            });
            del.setOnClickListener(view -> {
                if(str.length() != 0){
                    str = str.substring(0 , str.length()-1);
                    try {
                        String sol = "= " + calculate(str);
                        textView2.setText(sol);
                    }catch (Exception e){
                        textView2.setText(txt);
                    }
                    textView.setText(str);
                }
            });
        }
    }