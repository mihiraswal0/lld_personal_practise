package com.lld.practise.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






 class JsonTokenizer {
    private final String input;
    private int pos = 0;
    private Token nextToken = null;

    public JsonTokenizer(String input) {
        this.input = input;
    }

    public Token peekToken() {
        if (nextToken == null) {
            nextToken = readNextToken();
        }
        return nextToken;
    }

    public Token nextToken() {
        Token token = peekToken();
        nextToken = null;
        return token;
    }

    private Token readNextToken() {
        skipWhitespace();
        if (pos >= input.length()) return new Token(TokenType.EOF, null);

        char ch = input.charAt(pos);
        switch (ch) {
            case '{': pos++; return new Token(TokenType.LEFT_BRACE, "{");
            case '}': pos++; return new Token(TokenType.RIGHT_BRACE, "}");
            case '[': pos++; return new Token(TokenType.LEFT_BRACKET, "[");
            case ']': pos++; return new Token(TokenType.RIGHT_BRACKET, "]");
            case ',': pos++; return new Token(TokenType.COMMA, ",");
            case ':': pos++; return new Token(TokenType.COLON, ":");
            case '"': return readString();
            default:
                if (isDigit(ch) || ch == '-') return readNumber();
                else if (input.startsWith("true", pos)) {
                    pos += 4;
                    return new Token(TokenType.TRUE, "true");
                } else if (input.startsWith("false", pos)) {
                    pos += 5;
                    return new Token(TokenType.FALSE, "false");
                } else if (input.startsWith("null", pos)) {
                    pos += 4;
                    return new Token(TokenType.NULL, "null");
                } else {
                    throw new RuntimeException("Unexpected character at position " + pos + ": " + ch);
                }
        }
    }

    private Token readString() {
        pos++; // skip opening quote
        StringBuilder sb = new StringBuilder();
        while (pos < input.length()) {
            char ch = input.charAt(pos);
            if (ch == '"') {
                pos++;
                return new Token(TokenType.STRING, sb.toString());
            }
            if (ch == '\\') {
                pos++;
                if (pos >= input.length()) throw new RuntimeException("Unterminated escape in string");
                char esc = input.charAt(pos);
                switch (esc) {
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    case '/': sb.append('/'); break;
                    case 'b': sb.append('\b'); break;
                    case 'f': sb.append('\f'); break;
                    case 'n': sb.append('\n'); break;
                    case 'r': sb.append('\r'); break;
                    case 't': sb.append('\t'); break;
                    default: throw new RuntimeException("Invalid escape character: \\" + esc);
                }
            } else {
                sb.append(ch);
            }
            pos++;
        }
        throw new RuntimeException("Unterminated string");
    }

    private Token readNumber() {
        int start = pos;
        if (input.charAt(pos) == '-') pos++;
        while (pos < input.length() && isDigit(input.charAt(pos))) pos++;
        if (pos < input.length() && input.charAt(pos) == '.') {
            pos++;
            while (pos < input.length() && isDigit(input.charAt(pos))) pos++;
        }
        return new Token(TokenType.NUMBER, input.substring(start, pos));
    }

    private void skipWhitespace() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) pos++;
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }
}


 class JsonParser {
    private JsonTokenizer tokenizer;

    public Object parse(String json) {
        tokenizer = new JsonTokenizer(json);
        return parseValue();
    }

    private Object parseValue() {
        Token token = tokenizer.peekToken();
        switch (token.type) {
            case LEFT_BRACE: return parseObject();
            case LEFT_BRACKET: return parseArray();
            case STRING: return tokenizer.nextToken().value;
            case NUMBER: return Double.valueOf(tokenizer.nextToken().value);
            case TRUE: tokenizer.nextToken(); return true;
            case FALSE: tokenizer.nextToken(); return false;
            case NULL: tokenizer.nextToken(); return null;
            default: throw new RuntimeException("Unexpected token: " + token);
        }
    }

    private Map<String, Object> parseObject() {
        tokenizer.nextToken(); // consume '{'
        Map<String, Object> map = new HashMap<>();

        if (tokenizer.peekToken().type == TokenType.RIGHT_BRACE) {
            tokenizer.nextToken(); return map;
        }

        while (true) {
            Token keyToken = tokenizer.nextToken();
            tokenizer.nextToken(); // consume ':'
            Object value = parseValue();
            map.put(keyToken.value, value);

            Token next = tokenizer.peekToken();
            if (next.type == TokenType.RIGHT_BRACE) {
                tokenizer.nextToken(); break;
            } else if (next.type != TokenType.COMMA) {
                throw new RuntimeException("Expected ',' or '}', got: " + next);
            }
            tokenizer.nextToken(); // consume ','
        }

        return map;
    }

    private List<Object> parseArray() {
        tokenizer.nextToken(); // consume '['
        List<Object> list = new ArrayList<>();

        if (tokenizer.peekToken().type == TokenType.RIGHT_BRACKET) {
            tokenizer.nextToken(); return list;
        }

        while (true) {
            list.add(parseValue());
            Token next = tokenizer.peekToken();
            if (next.type == TokenType.RIGHT_BRACKET) {
                tokenizer.nextToken(); break;
            } else if (next.type != TokenType.COMMA) {
                throw new RuntimeException("Expected ',' or ']', got: " + next);
            }
            tokenizer.nextToken(); // consume ','
        }

        return list;
    }
}

class Token {
    public final TokenType type;
    public final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + (value != null ? "(" + value + ")" : "");
    }
}


enum TokenType {
    LEFT_BRACE,     // {
    RIGHT_BRACE,    // }
    LEFT_BRACKET,   // [
    RIGHT_BRACKET,  // ]
    COMMA,          // ,
    COLON,          // :
    STRING,         // "text"
    NUMBER,         // 123, -45.67
    TRUE,           // true
    FALSE,          // false
    NULL,           // null
    EOF             // End of input
}

