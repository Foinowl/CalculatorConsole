package com.company.service;

import com.company.exeptions.NotFoundOperationException;
import com.company.exeptions.SyntaxErrorCalculator;
import com.company.lexeme.*;

import java.util.*;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class CalculatorServiceImpl implements CalculatorService {

    private final String line;
    private final Map<Lexeme, Integer> operatorPrecedenceMap = ofEntries(
            entry(Brackets.OPEN_BRACKET, 4),
            entry(Brackets.CLOSE_BRACKET, 4),
            entry(BinaryOperator.ARITHMETIC_ADDITION, 2),
            entry(BinaryOperator.ARITHMETIC_SUBTRACTION, 2),
            entry(BinaryOperator.ARITHMETIC_MULTIPLICATION, 3),
            entry(BinaryOperator.ARITHMETIC_DIVISION, 3),
            entry(UnaryOperator.ARITHMETIC_UNARY_MINUS, 1)
            );
    private ParserService parserService = new ParserServiceImpl();

    public CalculatorServiceImpl(String line) {
        this.line = line;
    }

    public String execute() {
        List<Lexeme> listLexeme = parserService.parse(this.line);

        List<Lexeme> listPostfixNotation = buildPostfixNotation(listLexeme);
        return String.valueOf(calculatePostfixNotation(listPostfixNotation));
    }

    private List<Lexeme> buildPostfixNotation(final List<Lexeme> expressionLexeme) {
//        Let's start a stack, initially it is empty. We will move from left to right according to the expression in reverse Polish notation;
//        if the current element is a number or variable, then we put its value on the top of the stack;
//        if the current element is an operation, then we take out the top two elements from the stack (or one if the operation is unary),
//        apply the operation to them, and put the result back on the stack.
//        In the end, there will be exactly one element left in the stack - the value of the expression.
        List<Lexeme> result = new ArrayList<>();
        Deque<Lexeme> stack = new ArrayDeque<>();

        for (Lexeme lexeme : expressionLexeme) {
            if (lexeme instanceof Operator) {
                while (!stack.isEmpty()) {
                    Lexeme headStackElement = stack.getFirst();
                    if (headStackElement instanceof Operator) {
                        Operator headStackOperator = (Operator) headStackElement;
                        if (headStackOperator instanceof UnaryOperator) {
                            break;
                        } else {
                            int currentOperatorPrecedence = getPrecedence((Operator) lexeme);
                            int headStackOperatorPrecedence = getPrecedence(headStackOperator);
                            if (currentOperatorPrecedence <= headStackOperatorPrecedence) {
                                result.add(stack.removeFirst());
                            } else {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
                stack.addFirst(lexeme);
            } else if (lexeme instanceof Brackets) {
                if (((Brackets) lexeme).isOpen()) {
                    stack.addFirst(lexeme);
                } else {
                    // pop from stack until first open bracket
                    Lexeme token = stack.removeFirst();
                    while (token != Brackets.OPEN_BRACKET) {
                        result.add(token);
                        token = stack.removeFirst();
                    }
                }
            } else {
                result.add(lexeme);
            }
        }

        while (!stack.isEmpty()) {
            final Lexeme headStackElement = stack.removeFirst();
            if (headStackElement instanceof Operator) {
                result.add(headStackElement);
            } else {
                throw new SyntaxErrorCalculator("Missing )");
            }
        }
        return result;
    }

    private double calculatePostfixNotation(List<Lexeme> listExpression) {
        return 0.0;
    }

    private int getPrecedence(final Operator operator) {
        final Integer precedence = operatorPrecedenceMap.get(operator);
        if (precedence == null) {
            throw new NotFoundOperationException("Precedence not defined for " + operator.getCode());
        }
        return precedence;
    }
}
