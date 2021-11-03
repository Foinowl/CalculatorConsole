package com.company.service.impl;

import com.company.exeptions.NotFoundOperationException;
import com.company.exeptions.SyntaxErrorCalculator;
import com.company.lexeme.*;
import com.company.service.PostfixNotationService;

import java.util.*;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class ExpressionPostfixNotationImpl implements PostfixNotationService {
    private final Map<Lexeme, Integer> priorityOperatorMap = ofEntries(
            entry(Brackets.OPEN_BRACKET, 4),
            entry(Brackets.CLOSE_BRACKET, 4),
            entry(BinaryOperator.ARITHMETIC_ADDITION, 2),
            entry(BinaryOperator.ARITHMETIC_SUBTRACTION, 2),
            entry(BinaryOperator.ARITHMETIC_MULTIPLICATION, 3),
            entry(BinaryOperator.ARITHMETIC_DIVISION, 3)
    );

    @Override
    public List<Lexeme> buildPostfixNotation(final List<Lexeme> expressionLexeme) {
        List<Lexeme> result = new ArrayList<>();
        Deque<Lexeme> stack = new ArrayDeque<>();

        for (Lexeme lexeme : expressionLexeme) {
            if (lexeme instanceof Operator) {
                popOperatorFromStackUntilPrecedenceOperator(result, stack, lexeme);
                stack.addFirst(lexeme);
            } else if (lexeme instanceof Brackets) {
                if (((Brackets) lexeme).isOpen()) {
                    stack.addFirst(lexeme);
                } else {
                    popUntilOpenBracket(result, stack);
                }
            } else {
                result.add(lexeme);
            }
        }

        popOperationsFromStack(result, stack);
        return result;
    }


    @Override
    public Double getAndCalculateResultByPostfixNotation(List<Lexeme> postfixExpression) {
        Deque<ConstantValueLexeme> stack = new ArrayDeque<>();

        for (Lexeme lexeme : postfixExpression) {
            if (lexeme instanceof BinaryOperator) {
                calculateBinaryOperator(stack, (BinaryOperator) lexeme);
            } else if (lexeme instanceof ValueLexeme) {
                stack.addLast((ConstantValueLexeme) lexeme);
            }
        }

        Double resultValue = (Double) stack.getFirst().getValue();

        if (resultValue.equals(Double.POSITIVE_INFINITY) || resultValue.equals(Double.NEGATIVE_INFINITY)) {
            throw new ArithmeticException("На ноль делить нельзя");
        }
        return resultValue;
    }

    private void calculateBinaryOperator(Deque<ConstantValueLexeme> stack, BinaryOperator operator) {
        ValueLexeme operandOne = (ValueLexeme) stack.removeLast();
        ValueLexeme operandTwo = (ValueLexeme) stack.removeLast();
        ValueLexeme value = calculate(operandTwo, operator, operandOne);
        stack.addLast(value);
    }

    private ValueLexeme calculate(ValueLexeme operand, BinaryOperator operator, ValueLexeme operand2) {
        return switch (operator) {
            case ARITHMETIC_MULTIPLICATION -> ValueLexeme.build(operand.getValue() * operand2.getValue());
            case ARITHMETIC_DIVISION -> ValueLexeme.build(operand.getValue() / operand2.getValue());
            case ARITHMETIC_ADDITION -> ValueLexeme.build(operand.getValue() + operand2.getValue());
            case ARITHMETIC_SUBTRACTION -> ValueLexeme.build(operand.getValue() - operand2.getValue());
        };
    }

    private void popOperatorFromStackUntilPrecedenceOperator(List<Lexeme> result, Deque<Lexeme> stack, Lexeme lexeme) {
        while (!stack.isEmpty()) {
            Lexeme headStackElement = stack.getFirst();
            if (headStackElement instanceof Operator headStackOperator) {
                int currentOperatorPrecedence = getPriority((Operator) lexeme);
                int headStackOperatorPrecedence = getPriority(headStackOperator);
                if (currentOperatorPrecedence <= headStackOperatorPrecedence) {
                    result.add(stack.removeFirst());
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }

    private void popUntilOpenBracket(List<Lexeme> result, Deque<Lexeme> stack) {
        Lexeme token;
        while ((token = stack.removeFirst()) != Brackets.OPEN_BRACKET) {
            result.add(token);
        }
    }

    private void popOperationsFromStack(List<Lexeme> result, Deque<Lexeme> stack) {
        while (!stack.isEmpty()) {
            Lexeme headStackElement = stack.removeFirst();
            if (headStackElement instanceof Operator) {
                result.add(headStackElement);
            } else {
                throw new SyntaxErrorCalculator("Пропущена )");
            }
        }
    }

    private int getPriority(final Operator operator) {
        final Integer precedence = priorityOperatorMap.get(operator);
        if (precedence == null) {
            throw new NotFoundOperationException("Приоритет не определен для" + operator.getCode());
        }
        return precedence;
    }
}
