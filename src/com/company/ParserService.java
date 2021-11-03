package com.company;

import java.util.List;

public interface ParserService {
    List<Lexeme> parse(String line);
}
