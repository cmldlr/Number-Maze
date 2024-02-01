package com.company;

import java.awt.*;

public class Number {
    private int x;
    private int y;
    private  int n_value;
    private Color n_color;
    private int lastdirection;
    private int lastdirection_red;
    private Stack findingpath_stack;
    public Number() {
    }

    public Number(Color n_color,int n_value,int x,int y) {
        this.x=x;
        this.y=y;
        this.n_color = n_color;
        this.n_value=n_value;
    }

    public Number(Color n_color,int n_value,int x,int y, int lastdirection) {
        this.x=x;
        this.y=y;
        this.n_color = n_color;
        this.n_value=n_value;
        this.lastdirection = lastdirection;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getN_value() {
        return n_value;
    }

    public void setN_value(int n_value) {
        this.n_value = n_value;
    }

    public Color getN_color() {
        return n_color;
    }

    public void setN_color(Color n_color) {
        this.n_color = n_color;
    }

    public int getLastdirection() {
        return lastdirection;
    }

    public void setLastdirection(int lastdirection) {
        this.lastdirection = lastdirection;
    }

    public int getLastdirection_red() {
        return lastdirection_red;
    }

    public void setLastdirection_red(int lastdirection_red) {
        this.lastdirection_red = lastdirection_red;
    }

    public Stack getFindingpath_stack() {
        return findingpath_stack;
    }

    public void setFindingpath_stack(Stack findingpath_stack) {
        this.findingpath_stack = findingpath_stack;
    }
}
