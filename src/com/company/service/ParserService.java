package com.company.service;

import com.company.lexeme.Lexeme;

import java.util.List;

public interface ParserService {
    List<Lexeme> parse(String line);
}
