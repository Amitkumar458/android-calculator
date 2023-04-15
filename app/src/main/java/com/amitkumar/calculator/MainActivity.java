package com.amitkumar.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


    public class MainActivity extends AppCompatActivity {
        String str = "";
        boolean change = true;
        String txt = "= Error";
        Button add,sub,mul,div,del,solve,clear,point,one,two,three,four,five,six,seven,eight,nine,zero,btn_c;
        TextView textView , textView2;

        /* algorithm for calculation of string value like "2+2*3" = 8; */
        public String calculate(String s){
            int len;
            if (s == null || (len = s.length()) == 0) return "0";
            if(len == 1 && s.charAt(0) == '-') return "";
            try{
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scope = context.initStandardObjects();
                String result = context.evaluateString(scope, s, "JavaScript", 1, null).toString();
                if(result.endsWith(".0")){
                    result = result.replace(".0" , "");
                }
                return result;
            }catch (Exception e){
                return String.valueOf(e);
            }
        }

        /* this function is use to add number and sign in string */
        public void num(char c){
            if(change){
                str = "";
                textView2.setTextSize(25);
                change = false;
            }
            str += c;
            textView.setText(str);

            /* this is use for live calculation */
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

            add = findViewById(R.id.button17);
            sub = findViewById(R.id.button13);
            mul = findViewById(R.id.button9);
            div = findViewById(R.id.button4);
            del = findViewById(R.id.button5);
            solve = findViewById(R.id.button21);
            clear = findViewById(R.id.button2);
            point = findViewById(R.id.button20);
            one = findViewById(R.id.button14);
            two = findViewById(R.id.button15);
            three = findViewById(R.id.button16);
            four = findViewById(R.id.button10);
            five = findViewById(R.id.button11);
            six = findViewById(R.id.button12);
            seven = findViewById(R.id.button6);
            eight = findViewById(R.id.button7);
            nine = findViewById(R.id.button8);
            zero = findViewById(R.id.button19);
            btn_c = findViewById(R.id.button18);

            textView = findViewById(R.id.textView2);
            textView2 = findViewById(R.id.textView);

            one.setOnClickListener(view -> num('1'));
            two.setOnClickListener(view -> num('2'));
            three.setOnClickListener(view -> num('3'));
            four.setOnClickListener(view -> num('4'));
            five.setOnClickListener(view -> num('5'));
            six.setOnClickListener(view -> num('6'));
            seven.setOnClickListener(view -> num('7'));
            eight.setOnClickListener(view -> num('8'));
            nine.setOnClickListener(view -> num('9'));
            zero.setOnClickListener(view -> num('0'));
            point.setOnClickListener(view -> num('.'));

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

            btn_c.setOnClickListener(view -> {
                str = "";
                textView.setText("");
                textView2.setText("");
            });

            del.setOnClickListener(view -> {
                if(change){
                    change = false;
                    textView2.setTextSize(25);
                }
                if(str.length() != 0){
                    str = str.substring(0 , str.length()-1);
                    try {
                        String sol;
                        if(str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/")){
                            sol = "= " + calculate(str.substring(0, str.length() - 1));
                        }else {
                            sol = "= " + calculate(str);
                        }
                        textView2.setText(sol);
                    }catch (Exception e){
                        textView2.setText(txt);
                    }
                    textView.setText(str);
                }
            });
        }
    }