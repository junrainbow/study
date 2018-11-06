package com.jun.study.algorithm.classic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author junrainbow
 * @Description 四则运算器（逆波兰算法）
 * @Date:Create in 2018-11-02 15:56
 */
public class Calc {


    public String calc(String midExp){
        //转化为后缀表达式
        String tailExp = new Convertor(midExp).convert();
        System.out.println("后缀表达式："+tailExp);
        //解析运算
        String result = new Analyzer(tailExp).analyzer();
        return result;
    }


    /**
     * 解析器，将后缀表达式，解析运算
     */
    public class Analyzer{

        public Analyzer(String tailExp){
            this.tailExp = tailExp;
            operateStack = new Stack<String>();
            expStack = new Stack<String>();
        }

        /**
         * 后缀表达式
         */
        private String tailExp;
        /**
         * 操作运算栈
         */
        private Stack<String> operateStack;
        /**
         * 表达式栈
         */
        private Stack<String> expStack;
        /**
         * 分隔符
         */
        private static final String SPLIT = " ";

        /**
         * 解析后缀表达式
         * @return
         */
        public String analyzer(){
            //切割
            String[] keys = tailExp.split(SPLIT);
            for (int i = keys.length-1; i >= 0; i--) {
                expStack.push(keys[i]);
            }
            //计算
            while(!expStack.empty()){
                String s = expStack.pop();
                //符号则，弹出两个数字运算
                if(checkExp("\\+|\\-|\\*|\\/",s)){
                    //结果压栈
                    String result = calc(operateStack.pop(),operateStack.pop(),s);
                    operateStack.push(result);
                    continue;
                }
                //数字则压栈
                operateStack.push(s);
            }
            //返回运算结果
            return operateStack.pop();
        }

        /**
         * 两数运算
         * @param s1
         * @param s2
         * @param symbol
         * @return
         */
        private String calc(String s1,String s2,String symbol){
            BigDecimal num1 = new BigDecimal(s1);
            BigDecimal num2 = new BigDecimal(s2);
            switch (symbol){
                case "+":
                    return num2.add(num1).toString();
                case "-":
                    return num2.subtract(num1).toString();
                case "*":
                    return num2.multiply(num1).toString();
                case "/":
                    return num2.divide(num1,2,RoundingMode.DOWN).toString();
            }
            throw new RuntimeException();
        }


    }

    /**
     * 转化器，将运算表达式，转化为后缀表达式
     */
    public class Convertor {

        public Convertor(String midExp){
            this.midExp = midExp;
        }

        /**
         * 中缀表达式
         */
        private String midExp;
        /**
         * 符号栈
         */
        private Stack<String> symbolStack = new Stack<String>();
        /**
         * 结果队列
         */
        private Deque<String> resultQueue = new LinkedBlockingDeque<>();
        /**
         * 连续数字追加标识
         */
        private boolean addFlag = true;
        /**
         * 分隔符
         */
        private final String SPLIT = " ";


        /**
         * 中缀转化后缀表达式
         *
         * @return
         */
        public String convert() {
            //校验运算式有效性
            String regex = "(\\d|\\(|\\)|\\*|\\/|\\+|\\-)+";
            boolean pass = checkExp(regex, midExp);
            if (!pass) {
                throw new RuntimeException("输入表达式错误，只支持括号，四则运算");
            }
            //转化
            int idx = 0;
            int length = midExp.length();
            while (idx < length) {
                String symbol = midExp.charAt(idx) + "";
                //处理符号
                handle(symbol);
                idx++;
            }
            StringBuilder sb = new StringBuilder();
            while (!resultQueue.isEmpty()) {
                sb.append(resultQueue.poll()).append(SPLIT);
            }
            while (!symbolStack.isEmpty()) {
                sb.append(symbolStack.pop()).append(SPLIT);
            }
            //返回后缀表达式
            return sb.toString();
        }

        /**
         * 比较当前操作符号与栈顶符号，并处理
         *
         * @param symbol
         * @return
         */
        private void handle(String symbol) {
            //处理数字
            boolean isNum = checkExp("^-?\\d$", symbol);
            //处理数字
            if (isNum) {
                if(addFlag){
                    String tmp = resultQueue.peekLast()==null?"":resultQueue.pollLast();
                    resultQueue.add(tmp+symbol);
                }else{
                    resultQueue.add(symbol);
                    addFlag = !addFlag;//追加模式
                }
                return ;
            }
            addFlag = addFlag?!addFlag:addFlag;//新增模式
            //处理符号
            //右括号，则弹栈，直到遇到左括号
            if (checkExp("\\)", symbol)) {
                while (!symbolStack.empty()) {
                    String topSymbol = symbolStack.pop();//栈顶
                    if (checkExp("\\(", topSymbol)) {
                        break;
                    }
                    resultQueue.add(topSymbol);
                }
                return ;
            }
            //若运算优先级低，则全部弹栈
            while (!symbolStack.empty()) {
                String topSymbol = symbolStack.peek();//获取栈顶（非弹栈）
                boolean highLevel = checkExp("\\*|\\/", symbol);
                boolean lowLevel = checkExp("\\-|\\+", topSymbol);
                if(checkExp("\\(",symbol)||checkExp("\\(",topSymbol)){
                    break;
                }
                if(!(highLevel&&lowLevel)){
                    topSymbol = symbolStack.pop();
                    resultQueue.add(topSymbol);
                }else{
                    break;
                }
            }
            //当前操作符压栈
            symbolStack.push(symbol);
        }
    }

    /**
     * 指定表达式，匹配字符串，返回boolean
     * @param regex
     * @param str
     * @return
     */
    private boolean checkExp(String regex , String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }




}
