package com.ting.client.controller.spel;


import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Objects;

/**
 * spring el表达式
 *
 * @author ting
 * @version 1.0
 * @date 2021/01/12
 */
public class SpEL {

    public static void main(String[] args) {
        /*
        The Spring Expression Language 简称spEL
        是spring 官网出的一套表达式，独立于spring框架可以单独使用

        共有多种方法可供使用
        Literal expressions：文字表达式
        Boolean and relational operators:布尔运算符和关系运算符
        Regular expressions
        Class expressions
        Accessing properties, arrays, lists, maps
        Method invocation
        Relational operators
        Assignment
        Calling constructors
        Bean references
        Array construction
        Inline lists
        Inline maps
        Ternary operator
        Variables
        User defined functions
        Collection projection
        Collection selection
        Templated expressions
         */


        // Literal expressions：文字表达式
        ExpressionParser expression = new SpelExpressionParser();
        String name = "'hello word'.concat('!').replace('!','!!!!!!!!!!!!!!!!!!!!')";
        Expression parseExpression = expression.parseExpression(name);
        String string = Objects.requireNonNull(parseExpression.getValue()).toString();
        System.out.println(string);
    }
}
