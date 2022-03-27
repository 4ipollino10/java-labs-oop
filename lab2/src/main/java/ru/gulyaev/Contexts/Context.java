package ru.gulyaev.Contexts;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private final Stack<Double> _stack;
    private final Map<String, Double> _vars;

    public Context(){
        _stack = new Stack<>();
        _vars = new HashMap<>();
    }

    public String getStackPeek(){
        return _stack.peek().toString();
    }

    public Map<String, Double> getVars(){
        return _vars;
    }

    public Double pop(){
        return _stack.pop();
    }

    public void addValue(String define, Double val) {
        _vars.put(define, val);
        _stack.push(Double.parseDouble(define));
    }

    public void addValue(Double val){
        _stack.push(val);
    }

}
