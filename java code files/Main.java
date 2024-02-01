package com.company;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import enigma.core.Enigma;
import enigma.console.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


import enigma.console.TextAttributes;


public class Main {
    public static Random rnd=new Random();
    public static  enigma.console.Console cn = Enigma.getConsole("NUMBER MAZE",75,30,15);
    public static  KeyListener klis;
    // ------ Standard variables for and keyboard ------
    public static  int keypr;   // key pressed
    public static  int rkey;    // key   (for press/release)
    private static Object InputStream;
    private static  Console s_console;
    static {
        s_console=Enigma.getConsole();
        cn=Enigma.getConsole();
    }

    public static void pathfinding(char [][] gameboard,Number [] redNumbers,int px,int py){
        Stack findthepath=new Stack(100);
        //yolu bulma
        for (int i = 0; i <redNumbers.length; i++) {
            int count=1;
            Stack findthepath1=new Stack(100);
            if(redNumbers[i]!=null){
                //1 = up --> y-1;   2 = right --> x+1
                //3 = down --> y+1;   4 = left --> x-1
                int x=redNumbers[i].getX();
                int x1=x;
                int y=redNumbers[i].getY();
                int y1=y;
                boolean priority=true;
                boolean flag=true;
                while (flag){
                    if(py>y && priority){
                        if(gameboard[y+1][x]==' ' && redNumbers[i].getLastdirection_red()!=1){
                            findthepath1.push(3);
                            redNumbers[i].setLastdirection_red(3);
                            y++;
                            y1++;
                        }
                        else if(gameboard[y+1][x]!=' '&&(gameboard[y][x+1]==' '|| gameboard[y][x-1]==' ')){
                            if(px>x){
                                if(gameboard[y][x+1]==' '&& redNumbers[i].getLastdirection_red()!=4){
                                    findthepath1.push(2);
                                    redNumbers[i].setLastdirection_red(2);
                                    x++;
                                    x1++;
                                }
                                else if(gameboard[y][x+1]!=' ' && gameboard[y-1][x]==' '&& redNumbers[i].getLastdirection_red()!=3){
                                    findthepath1.push(1);
                                    redNumbers[i].setLastdirection_red(1);
                                    y--;
                                    y1--;
                                }
                                else if(gameboard[y][x+1]!=' ' && gameboard[y-1][x]!=' '){
                                    redNumbers[i].setLastdirection_red(4);
                                    findthepath1.push(4);
                                    x--;
                                    x1--;
                                }
                                else{
                                    redNumbers[i].setLastdirection_red(4);
                                    findthepath1.push(4);
                                    x--;
                                    x1--;
                                }

                            }
                            else if(px<x){
                                if(gameboard[y][x-1]==' '&& redNumbers[i].getLastdirection_red()!=2){
                                    findthepath1.push(4);
                                    redNumbers[i].setLastdirection_red(4);
                                    x--;
                                    x1--;
                                }
                                else if(gameboard[y-1][x]==' '&& gameboard[y][x-1]!=' '&& redNumbers[i].getLastdirection_red()!=3){
                                    findthepath1.push(1);
                                    redNumbers[i].setLastdirection_red(1);
                                    y--;
                                    y1--;
                                }
                                else  if(gameboard[y][x-1]!=' ' && gameboard[y-1][x]!=' '){
                                    redNumbers[i].setLastdirection_red(2);
                                    findthepath1.push(2);
                                    x++;
                                    x1++;


                                }
                                else{
                                    redNumbers[i].setLastdirection_red(2);
                                    findthepath1.push(2);
                                    x++;
                                    x1++;
                                }
                            }

                        }
                        else if(gameboard[y+1][x]!=' ' &&gameboard[y][x+1]!=' '&&gameboard[y][x-1]!=' '){
                            redNumbers[i].setLastdirection_red(1);
                            findthepath1.push(1);
                            y--;
                            y1--;
                        }
                        if(py==y){
                            priority=false;
                        }
                    }
                    else if(!priority){
                        if(px>x){
                            if(gameboard[y][x+1]==' ' && redNumbers[i].getLastdirection_red()!=4){
                                findthepath1.push(2);
                                redNumbers[i].setLastdirection_red(2);
                                x++;
                                x1++;
                            }
                            else if(gameboard[y][x+1]!=' '&& (gameboard[y-1][x]==' '|| gameboard[y+1][x]==' ')){
                                if(gameboard[y+1][x]!=' ' && gameboard[y-1][x]==' ' &&redNumbers[i].getLastdirection_red()!=3){
                                    findthepath1.push(1);
                                    redNumbers[i].setLastdirection_red(1);
                                    y--;
                                    y1--;
                                }
                                else if(gameboard[y-1][x]!=' '&&gameboard[y+1][x]==' ' &&redNumbers[i].getLastdirection_red()!=1){
                                    findthepath1.push(3);
                                    redNumbers[i].setLastdirection_red(3);
                                    y++;
                                    y1++;
                                }
                                else if(gameboard[y-1][x]==' '&&gameboard[y+1][x]!=' ' &&redNumbers[i].getLastdirection_red()!=3){
                                    findthepath1.push(1);
                                    redNumbers[i].setLastdirection_red(1);
                                    y--;
                                    y1--;
                                }
                                else if(gameboard[y-1][x]==' '&&gameboard[y+1][x]==' '){
                                    if(redNumbers[i].getLastdirection_red()!=1){
                                        findthepath1.push(3);
                                        redNumbers[i].setLastdirection_red(3);
                                        y++;
                                        y1++;
                                    }
                                    else if(redNumbers[i].getLastdirection_red()!=3){
                                        findthepath1.push(1);
                                        redNumbers[i].setLastdirection_red(1);
                                        y--;
                                        y1--;
                                    }

                                }
                                else if(gameboard[y-1][x]!=' ' && gameboard[y+1][x]!=' '){
                                        redNumbers[i].setLastdirection_red(4);
                                        findthepath1.push(4);
                                        x--;
                                        x1--;

                                }
                                else if(gameboard[y-1][x]!=' ' && gameboard[y][x+1]!=' '&&gameboard[y][x-1]!=' '&&redNumbers[i].getLastdirection_red()==1){
                                    redNumbers[i].setLastdirection_red(3);
                                    findthepath1.push(3);
                                    y++;
                                    y1++;
                                }
                                else if(gameboard[y+1][x]!=' ' && gameboard[y][x+1]!=' '&&gameboard[y][x-1]!=' '&&redNumbers[i].getLastdirection_red()==3){
                                    redNumbers[i].setLastdirection_red(1);
                                    findthepath1.push(1);
                                    y--;
                                    y1--;
                                }

                            }

                        }
                        else if(px<x){
                            if(gameboard[y][x-1]==' ' && redNumbers[i].getLastdirection_red()!=2){
                                findthepath1.push(4);
                                redNumbers[i].setLastdirection_red(4);
                                x--;
                                x1--;
                            }
                            else if(gameboard[y][x-1]!=' '&& (gameboard[y-1][x]==' '|| gameboard[y+1][x]==' ')){
                                if(gameboard[y+1][x]!=' ' && gameboard[y-1][x]==' ' &&redNumbers[i].getLastdirection_red()!=3){
                                    findthepath1.push(1);
                                    redNumbers[i].setLastdirection_red(1);
                                    y--;
                                    y1--;
                                }
                                else if(gameboard[y-1][x]!=' '&&gameboard[y+1][x]==' ' &&redNumbers[i].getLastdirection_red()!=1){
                                    findthepath1.push(3);
                                    redNumbers[i].setLastdirection_red(3);
                                    y++;
                                    y1++;
                                }
                                else if(gameboard[y-1][x]==' '&&gameboard[y+1][x]!=' ' &&redNumbers[i].getLastdirection_red()!=3){
                                    findthepath1.push(1);
                                    redNumbers[i].setLastdirection_red(1);
                                    y--;
                                    y1--;
                                }
                                else if(gameboard[y-1][x]==' '&&gameboard[y+1][x]==' '){
                                    if(redNumbers[i].getLastdirection_red()!=1){
                                        findthepath1.push(3);
                                        redNumbers[i].setLastdirection_red(3);
                                        y++;
                                        y1++;
                                    }
                                    else if(redNumbers[i].getLastdirection_red()!=3){
                                        findthepath1.push(1);
                                        redNumbers[i].setLastdirection_red(1);
                                        y--;
                                        y1--;
                                    }

                                }
                                else if(gameboard[y-1][x]!=' ' && gameboard[y+1][x]!=' '){
                                    redNumbers[i].setLastdirection_red(4);
                                    findthepath1.push(4);
                                    x--;
                                    x1--;

                                }
                                else if(gameboard[y-1][x]!=' ' && gameboard[y][x+1]!=' '&&gameboard[y][x-1]!=' '&&redNumbers[i].getLastdirection_red()==1){
                                    redNumbers[i].setLastdirection_red(3);
                                    findthepath1.push(3);
                                    y++;
                                    y1++;
                                }
                                else if(gameboard[y+1][x]!=' ' && gameboard[y][x+1]!=' '&&gameboard[y][x-1]!=' '&&redNumbers[i].getLastdirection_red()==3){
                                    redNumbers[i].setLastdirection_red(1);
                                    findthepath1.push(1);
                                    y--;
                                    y1--;
                                }

                            }

                        }
                        else if(px==x){
                            if(py==y){
                                redNumbers[i].setFindingpath_stack(findthepath1);
                                flag=false;
                            }
                            else  if(py>y){

                            }
                            else if(py<y){

                            }

                        }


                    }
                    else if(py<y){
                        if(gameboard[y-1][x]==' '){
                            findthepath1.push(1);
                            redNumbers[i].setLastdirection_red(1);
                            y--;
                            y1--;
                        }
                        else{
                            if(px>x){
                                if(gameboard[y][x+1]==' '){
                                    findthepath1.push(2);
                                    redNumbers[i].setLastdirection_red(2);
                                    x++;
                                    x1++;
                                }
                                else if(gameboard[y-1][x]==' '){
                                    findthepath1.push(1);
                                    redNumbers[i].setLastdirection_red(1);
                                    y--;
                                    y1--;
                                }
                                else {
                                    if(redNumbers[i].getLastdirection_red()==2){
                                        findthepath1.push(4);
                                        gameboard[y][x]='x';

                                    }

                                }

                            }
                            else if(px<x){
                                if(gameboard[y][x-1]==' '){
                                    findthepath1.push(4);
                                    x1--;
                                }
                            }
                            else if(px==x){
                                 flag=false;
                            }
                        }
                    }

                }

            }
        }
        //ilerletme

    }

    public static void printstack(Stack leftpack,Stack rightpack){
        TextAttributes attrs3 = new TextAttributes(Color.white, Color.BLACK);
        cn.setTextAttributes(attrs3);
        for(int i = 8; i <= 15; i++) {
            cn.getTextWindow().setCursorPosition(60,i);
            System.out.print("|   | |   |");
        }
        Stack leftpacktemp=new Stack(8);
        Stack rightpacktemp=new Stack(8);
        int x=15;
        while (!leftpack.isEmpty()){
            leftpacktemp.push(leftpack.pop());
        }
        int y=15;
        while (!rightpack.isEmpty()){
            rightpacktemp.push(rightpack.pop());
        }
        while(!leftpacktemp.isEmpty()){
            cn.getTextWindow().setCursorPosition(60,x);
            System.out.print("| "+leftpacktemp.peek()+" |");
            x--;
            leftpack.push(leftpacktemp.pop());
        }
        while (!rightpacktemp.isEmpty()){
            cn.getTextWindow().setCursorPosition(66,y);
            System.out.print("| "+rightpacktemp.peek()+" |");
            y--;
            rightpack.push(rightpacktemp.pop());
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new FileReader("maze.txt"));
        BufferedReader br1=new BufferedReader(new FileReader("maze.txt"));
        char [][]gameboard=new char[23][55];
        int column=0;
        String str0;
        while ((str0=br1.readLine())!=null){
            for (int i = 0; i <str0.length(); i++) {
                char a=str0.charAt(i);
                gameboard[column][i]=a;

            }
            column++;
        }
        Queue input=new Queue(5000);
        Stack leftpack=new Stack(8);
        Stack rightpack=new Stack(8);
        Stack leftpacktemp=new Stack(8);
        Stack rightpacktemp=new Stack(8);
        Number[] yellowNumbers = new Number[30];
        Number [] redNumbers=new Number[30];
        int yellowcounter = 0;
        int redcounter=0;

        for (int i = 0; i <10; i++) {
            int change = rnd.nextInt(100)+1;
            if(change <= 75) {
                int a= rnd.nextInt(3)+1;
                input.enqueue(a);
            }
            else if(change > 75 && change <= 95) {
                int a= rnd.nextInt(3)+4;
                input.enqueue(a);
            }
            else {
                int a= rnd.nextInt(3)+7;
                input.enqueue(a);
            }
        }
        System.out.println();

        klis=new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(keypr==0) {
                    keypr=1;
                    rkey=e.getKeyCode();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };

        cn.getTextWindow().addKeyListener(klis);
        // ----------------------------------------------------
        int px=38,py=12;
        int tempx = 0; int tempy=0;
        boolean onlyonce=true;
        int millisecond=0;
        int minute=0;
        int second=0;
        int leftpack_count = 15;
        int rightpack_count = 15;
        boolean eaten = false;
        int userscore = 0;

        Number user_number=new Number(Color.BLUE,5,px,py);
        gameboard[py][px] = (char)user_number.getN_value();

        int count1=0;
        while(true) {
            if(onlyonce){
                for (int i = 0; i < gameboard.length; i++) {
                    for (int j = 0; j < gameboard[i].length; j++) {
                        System.out.print(gameboard[i][j]);
                    }
                    System.out.println(" ");
                }
                for(int i =0; i < 25; i++) {
                    int chance = rnd.nextInt(100)+1;
                    if(chance <= 75) {
                        int number = rnd.nextInt(3)+1;
                        int random_x = rnd.nextInt(54)+1;
                        int random_y = rnd.nextInt(22)+1;
                        while(gameboard[random_y][random_x] != ' ') {
                            random_x = rnd.nextInt(54)+1;
                            random_y = rnd.nextInt(22)+1;
                        }
                        Number n = new Number(Color.green,number,random_x,random_y);
                        gameboard[random_y][random_x] = (char)n.getN_value();
                        cn.getTextWindow().setCursorPosition(random_x,random_y);
                        TextAttributes attrs3 = new TextAttributes(n.getN_color(), Color.BLACK);
                        cn.setTextAttributes(attrs3);
                        System.out.println(number);
                    }

                    else if(chance >= 75 && chance <= 95) {
                        int number = rnd.nextInt(3)+4;
                        int random_x = rnd.nextInt(54)+1;
                        int random_y = rnd.nextInt(22)+1;
                        while(gameboard[random_y][random_x] != ' ') {
                            random_x = rnd.nextInt(54)+1;
                            random_y = rnd.nextInt(22)+1;
                        }
                        int randomdirection;
                        while(true) {
                            randomdirection = rnd.nextInt(4)+1;
                            if(randomdirection == 1 && gameboard[random_y-1][random_x] == ' ') {
                                break;
                            }
                            else if(randomdirection == 2 && gameboard[random_y][random_x+1] == ' ') {
                                break;
                            }
                            else if (randomdirection == 3 && gameboard[random_y+1][random_x] == ' ') {
                                break;
                            }
                            else if(randomdirection == 4 && gameboard[random_y][random_x-1] == ' ') {
                                break;
                            }
                            else {
                                continue;
                            }
                        }
                        Number n = new Number(Color.yellow,number,random_x,random_y,randomdirection);
                        yellowNumbers[yellowcounter] = n;
                        yellowcounter++;
                        gameboard[random_y][random_x] = (char)n.getN_value();
                        cn.getTextWindow().setCursorPosition(random_x,random_y);
                        TextAttributes attrs3 = new TextAttributes(n.getN_color(), Color.BLACK);
                        cn.setTextAttributes(attrs3);
                        System.out.println(number);

                    }
                    else {
                        int number = rnd.nextInt(3)+7;
                        int random_x = rnd.nextInt(54)+1;
                        int random_y = rnd.nextInt(22)+1;
                        while(gameboard[random_y][random_x] != ' ') {
                            random_x = rnd.nextInt(54)+1;
                            random_y = rnd.nextInt(22)+1;
                        }
                        Number n = new Number(Color.red,number,random_x,random_y);
                        gameboard[random_y][random_x] = (char)n.getN_value();
                        cn.getTextWindow().setCursorPosition(random_x,random_y);
                        TextAttributes attrs3 = new TextAttributes(n.getN_color(), Color.BLACK);
                        cn.setTextAttributes(attrs3);
                        System.out.println(number);
                        redNumbers[redcounter]=n;
                        redcounter++;
                    }
                }
                TextAttributes attrs4 = new TextAttributes(Color.MAGENTA, Color.BLACK);
                cn.setTextAttributes(attrs4);
                cn.getTextWindow().setCursorPosition(px,py);
                cn.getOutputStream().println(user_number.getN_value());
                TextAttributes attrs3 = new TextAttributes(Color.white, Color.BLACK);
                cn.setTextAttributes(attrs3);
                cn.getTextWindow().setCursorPosition(60,2);
                cn.getOutputStream().println("Input");
                cn.getTextWindow().setCursorPosition(60,3);
                cn.getOutputStream().println("<<<<<<<<<<");
                cn.getTextWindow().setCursorPosition(60,4);
                for (int i = 0; i <10; i++) {
                    System.out.print(input.peek());
                    input.enqueue(input.dequeue());
                }
                int y=input.size();
                for (int i = 0; i <y-10; i++) {
                    input.enqueue(input.dequeue());
                }
                cn.getTextWindow().setCursorPosition(60,5);
                cn.getOutputStream().println("<<<<<<<<<<");
                cn.getTextWindow().setCursorPosition(60,22);
                System.out.print("Time: ");
                cn.getTextWindow().setCursorPosition(60,20);
                System.out.print("Score: ");
                cn.getTextWindow().setCursorPosition(67,20);
                System.out.print(userscore);
                cn.getTextWindow().setCursorPosition(60,7);
                System.out.print("Backpacks ");
                for(int i = 8; i <= 15; i++) {
                    cn.getTextWindow().setCursorPosition(60,i);
                    System.out.print("|   | |   |");
                }
                cn.getTextWindow().setCursorPosition(60,16);
                System.out.print("+---+ +---+");
                cn.getTextWindow().setCursorPosition(60,17);
                System.out.print("Left  Right");
                cn.getTextWindow().setCursorPosition(60,18);
                System.out.print("  Q     W");
                onlyonce=false;
            }

            if(keypr==1) {    // if keyboard button pressed
                if (rkey == KeyEvent.VK_LEFT && py<22  && px<54&& px>0 &py>0 ){
                    if(gameboard[py][px-1]!='#') {
                        tempx = px;
                        tempy = py;
                        if((int)gameboard[py][px-1]<=user_number.getN_value()) {
                            if((int)gameboard[py][px-1]==4 ||(int)gameboard[py][px-1]==5||(int)gameboard[py][px-1]==6){
                                int x=px-1;
                                int y=py;
                                for (int i = 0; i <yellowNumbers.length; i++) {
                                    if(yellowNumbers[i]!=null&&x==yellowNumbers[i].getX()&&y==yellowNumbers[i].getY()){
                                        yellowNumbers[i].setN_value(-1);
                                    }
                                }
                            }
                            if(leftpack.size() == 8) {
                                leftpack.pop();
                                leftpack.push((int)gameboard[py][px-1]);
                                gameboard[py][px-1] = ' ';
                                px--;
                            }
                            else {
                                leftpack.push((int)gameboard[py][px-1]);
                                gameboard[py][px-1] = ' ';
                                px--;
                            }
                        }
                        else if((int)gameboard[py][px-1]>user_number.getN_value() && gameboard[py][px-1] != ' '){
                            cn.getTextWindow().setCursorPosition(2,24);
                            TextAttributes attrs3 = new TextAttributes(Color.red, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print("                        GAME OVER!");
                            break;
                        }
                        else {
                            px--;
                        }

                    }
                    printstack(leftpack,rightpack);
                    if(leftpack.size()!=0 && rightpack.size()!=0 &&leftpack.size()!=8 &&rightpack.size()!=8){
                        cn.getTextWindow().setCursorPosition(2,24);
                        TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                        cn.setTextAttributes(attrs3);
                        System.out.print("                                                 ");

                    }

                }
                if (rkey == KeyEvent.VK_RIGHT &&  py<22  && px<54&& px>0 &py>0  ){
                    if(gameboard[py][px+1]!='#') {
                        tempx = px;
                        tempy = py;
                        if((int)gameboard[py][px+1]<=user_number.getN_value()) {
                            if((int)gameboard[py][px+1]==4 ||(int)gameboard[py][px+1]==5||(int)gameboard[py][px+1]==6){
                                int x=px+1;
                                int y=py;
                                for (int i = 0; i <yellowNumbers.length; i++) {
                                    if(yellowNumbers[i]!=null&&x==yellowNumbers[i].getX()&&y==yellowNumbers[i].getY()){
                                        yellowNumbers[i].setN_value(-1);
                                    }
                                }
                            }
                            if(leftpack.size() == 8) {
                                leftpack.pop();
                                leftpack.push((int)gameboard[py][px+1]);
                                gameboard[py][px+1] = ' ';
                                px++;
                            }

                            else {
                                leftpack.push((int)gameboard[py][px+1]);
                                gameboard[py][px+1] = ' ';
                                px++;
                            }

                        }
                        else if((int)gameboard[py][px+1]>user_number.getN_value() && gameboard[py][px+1] != ' '){
                            cn.getTextWindow().setCursorPosition(2,24);
                            TextAttributes attrs3 = new TextAttributes(Color.red, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print("                        GAME OVER!");
                            break;
                        }
                        else {
                            px++;
                        }
                    }
                    printstack(leftpack,rightpack);
                    if(leftpack.size()!=0 && rightpack.size()!=0 &&leftpack.size()!=8 &&rightpack.size()!=8){
                        cn.getTextWindow().setCursorPosition(2,24);
                        TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                        cn.setTextAttributes(attrs3);
                        System.out.print("                                                 ");

                    }
                }
                if (rkey == KeyEvent.VK_UP &&   py<22  && px<54&& px>0 &py>0){
                    if(gameboard[py-1][px]!='#') {
                        tempx = px;
                        tempy = py;
                        if((int)gameboard[py-1][px]<=user_number.getN_value()) {
                            if((int)gameboard[py-1][px]==4 ||(int)gameboard[py-1][px]==5||(int)gameboard[py-1][px]==6){
                                int x=px;
                                int y=py-1;
                                for (int i = 0; i <yellowNumbers.length; i++) {
                                    if(yellowNumbers[i]!=null&&x==yellowNumbers[i].getX()&&y==yellowNumbers[i].getY()){
                                        yellowNumbers[i].setN_value(-1);
                                    }
                                }
                            }
                            if(leftpack.size() == 8) {
                                leftpack.pop();
                                leftpack.push((int)gameboard[py-1][px]);
                                gameboard[py-1][px] = ' ';
                                py--;
                            }

                            else {
                                leftpack.push((int)gameboard[py-1][px]);
                                gameboard[py-1][px] = ' ';
                                py--;
                            }
                        }
                        else if((int)gameboard[py-1][px]>user_number.getN_value() && gameboard[py-1][px] != ' '){
                            cn.getTextWindow().setCursorPosition(2,24);
                            TextAttributes attrs3 = new TextAttributes(Color.red, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print("                        GAME OVER!");
                            break;
                        }
                        else {
                            py--;
                        }
                    }
                    printstack(leftpack,rightpack);
                    if(leftpack.size()!=0 && rightpack.size()!=0 &&leftpack.size()!=8 &&rightpack.size()!=8){
                        cn.getTextWindow().setCursorPosition(2,24);
                        TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                        cn.setTextAttributes(attrs3);
                        System.out.print("                                                 ");

                    }
                }
                if (rkey == KeyEvent.VK_DOWN &&  py<22  && px<54&& px>0 &py>0){
                    if(gameboard[py+1][px]!='#') {
                        tempx = px;
                        tempy = py;
                        if((int)gameboard[py+1][px]<=user_number.getN_value()) {
                            if((int)gameboard[py+1][px]==4 ||(int)gameboard[py+1][px]==5||(int)gameboard[py+1][px]==6){
                                int x=px;
                                int y=py+1;
                                for (int i = 0; i <yellowNumbers.length; i++) {
                                    if(yellowNumbers[i]!=null&&x==yellowNumbers[i].getX()&&y==yellowNumbers[i].getY()){
                                        yellowNumbers[i].setN_value(-1);
                                    }
                                }
                            }
                            if(leftpack.size() == 8) {
                                leftpack.pop();
                                leftpack.push((int)gameboard[py+1][px]);
                                gameboard[py+1][px] = ' ';
                                py++;
                            }
                            else {
                                leftpack.push((int)gameboard[py+1][px]);
                                gameboard[py+1][px] = ' ';
                                py++;
                            }

                        }
                        else if((int)gameboard[py+1][px]>user_number.getN_value() && gameboard[py+1][px] != ' ' ){
                            cn.getTextWindow().setCursorPosition(2,24);
                            TextAttributes attrs3 = new TextAttributes(Color.red, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print("                        GAME OVER!");
                            break;
                        }
                        else {
                            py++;
                        }
                    }
                    printstack(leftpack,rightpack);
                    if(leftpack.size()!=0 && rightpack.size()!=0 &&leftpack.size()!=8 &&rightpack.size()!=8){
                        cn.getTextWindow().setCursorPosition(2,24);
                        TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                        cn.setTextAttributes(attrs3);
                        System.out.print("                                                 ");

                    }
                }
                if(rkey==KeyEvent.VK_Q){
                    if(!rightpack.isEmpty()) {
                        if(leftpack.size() == 8) {
                            cn.getTextWindow().setCursorPosition(2,24);
                            TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print("Left backpack is full!");
                        }
                        else {
                            leftpack.push(rightpack.pop());
                        }

                    }
                    else {
                        cn.getTextWindow().setCursorPosition(2,24);
                        TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                        cn.setTextAttributes(attrs3);
                        System.out.print("Right backpack is empty!");
                    }
                    printstack(leftpack,rightpack);

                }
                if(rkey==KeyEvent.VK_W){
                    if(!leftpack.isEmpty()) {
                        if(rightpack.size() == 8) {
                            cn.getTextWindow().setCursorPosition(2,24);
                            TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print("Right backpack is full!");
                        }
                        else {
                            rightpack.push(leftpack.pop());
                        }
                    }
                    else {
                        cn.getTextWindow().setCursorPosition(2,24);
                        TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                        cn.setTextAttributes(attrs3);
                        System.out.print("Left backpack is empty!");
                    }
                    printstack(leftpack,rightpack);

                }
                char rckey = (char) rkey;
                TextAttributes attrs = new TextAttributes(Color.MAGENTA, Color.BLACK);
                cn.setTextAttributes(attrs);
                //        left          right          up            down
                if ((rckey == '%' || rckey == '\'' || rckey == '&' || rckey == '(' ) && gameboard[py][px]!='#') {
                    cn.setTextAttributes(attrs);
                    cn.getTextWindow().setCursorPosition(tempx,tempy);
                    System.out.println(" ");
                    gameboard[tempy][tempx] = ' ';
                    cn.getTextWindow().setCursorPosition(px,py);
                    System.out.println(user_number.getN_value());
                    gameboard[py][px] = (char)user_number.getN_value();


                }

                keypr = 0;    // last action
            }
            TextAttributes attrs5 = new TextAttributes(Color.white, Color.BLACK);
            cn.setTextAttributes(attrs5);
            if(!leftpack.isEmpty() && !rightpack.isEmpty() &&rkey==KeyEvent.VK_P) {
                if(leftpack.size() < rightpack.size()) {
                    int count=0;
                    int a=rightpack.size()-leftpack.size();
                    while (!rightpack.isEmpty()){
                        if(a==count){
                            break;
                        }
                        rightpacktemp.push(rightpack.pop());
                        count++;

                    }
                    while (!leftpack.isEmpty()){
                        if(rightpack.peek() == leftpack.peek()) {
                            if((int)rightpack.peek() < 4) {
                                userscore += (int)rightpack.peek() * 1;
                                rightpack.pop();
                                leftpack.pop();
                                cn.getTextWindow().setCursorPosition(67,20);
                                System.out.print(userscore);
                                if(user_number.getN_value() < 9) {
                                    user_number.setN_value(user_number.getN_value()+1);
                                }
                                else {
                                    user_number.setN_value(1);
                                }

                            }
                            else if((int)rightpack.peek() >= 4 && (int)rightpack.peek() < 7) {
                                userscore += (int)rightpack.peek() * 5;
                                rightpack.pop();
                                leftpack.pop();
                                cn.getTextWindow().setCursorPosition(67,20);
                                System.out.print(userscore);
                                if(user_number.getN_value() < 9) {
                                    user_number.setN_value(user_number.getN_value()+1);
                                }
                                else {
                                    user_number.setN_value(1);
                                }
                            }
                            else if((int)rightpack.peek() >= 7 && (int)rightpack.peek() <=9) {
                                userscore += (int)rightpack.peek() * 25;
                                rightpack.pop();
                                leftpack.pop();
                                cn.getTextWindow().setCursorPosition(67,20);
                                System.out.print(userscore);
                                if(user_number.getN_value() < 9) {
                                    user_number.setN_value(user_number.getN_value()+1);
                                }
                                else {
                                    user_number.setN_value(1);
                                }
                            }
                        }
                        else{
                            rightpacktemp.push(rightpack.pop());
                            leftpacktemp.push(leftpack.pop());
                        }

                    }
                    while(!leftpacktemp.isEmpty()){
                        leftpack.push(leftpacktemp.pop());
                    }
                    while (!rightpacktemp.isEmpty()){
                        rightpack.push(rightpacktemp.pop());
                    }
                    printstack(leftpack,rightpack);

                }
                else if(leftpack.size() > rightpack.size()) {
                    int count=0;
                    int a=leftpack.size()-rightpack.size();
                    while (!leftpack.isEmpty()){
                        if(a==count){
                            break;
                        }
                        leftpacktemp.push(leftpack.pop());
                        count++;

                    }
                    while (!rightpack.isEmpty()){
                        if(rightpack.peek() == leftpack.peek()) {
                            if((int)rightpack.peek() < 4) {
                                userscore += (int)rightpack.peek()*1;
                                rightpack.pop();
                                leftpack.pop();
                                cn.getTextWindow().setCursorPosition(67,20);
                                System.out.print(userscore);
                                if(user_number.getN_value() < 9) {
                                    user_number.setN_value(user_number.getN_value()+1);
                                }
                                else {
                                    user_number.setN_value(1);
                                }
                            }
                            else if((int)rightpack.peek() >= 4 && (int)rightpack.peek() < 7) {
                                userscore += (int)rightpack.peek() * 5;
                                rightpack.pop();
                                leftpack.pop();
                                cn.getTextWindow().setCursorPosition(67,20);
                                System.out.print(userscore);
                                if(user_number.getN_value() < 9) {
                                    user_number.setN_value(user_number.getN_value()+1);
                                }
                                else {
                                    user_number.setN_value(1);
                                }
                            }
                            else if((int)rightpack.peek() >= 7 && (int)rightpack.peek() <= 9) {
                                userscore += (int)rightpack.peek() * 25;
                                leftpack.pop();
                                rightpack.pop();
                                cn.getTextWindow().setCursorPosition(67,20);
                                System.out.print(userscore);
                                if(user_number.getN_value() < 9) {
                                    user_number.setN_value(user_number.getN_value()+1);
                                }
                                else {
                                    user_number.setN_value(1);
                                }
                            }
                        }
                        else{
                            rightpacktemp.push(rightpack.pop());
                            leftpacktemp.push(leftpack.pop());
                        }
                    }
                    while(!leftpacktemp.isEmpty()){
                        leftpack.push(leftpacktemp.pop());
                    }
                    while (!rightpacktemp.isEmpty()){
                        rightpack.push(rightpacktemp.pop());
                    }
                    printstack(leftpack,rightpack);


                }
                else {
                    while (!rightpack.isEmpty()){
                        if(rightpack.peek() == leftpack.peek()) {
                            if((int)rightpack.peek() < 4) {
                                userscore += (int)rightpack.peek() * 1;
                                rightpack.pop();
                                leftpack.pop();
                                cn.getTextWindow().setCursorPosition(67,20);
                                System.out.print(userscore);
                                if(user_number.getN_value() < 9) {
                                    user_number.setN_value(user_number.getN_value()+1);
                                }
                                else {
                                    user_number.setN_value(1);
                                }
                            }
                            else if((int)rightpack.peek() >= 4 && (int)rightpack.peek() < 7) {
                                userscore += (int)rightpack.peek() * 5;
                                leftpack.pop();
                                rightpack.pop();
                                cn.getTextWindow().setCursorPosition(67,20);
                                System.out.print(userscore);
                                if(user_number.getN_value() < 9) {
                                    user_number.setN_value(user_number.getN_value()+1);
                                }
                                else {
                                    user_number.setN_value(1);
                                }
                            }
                            else if((int)rightpack.peek() >= 7 && (int)rightpack.peek() <=9) {
                                userscore += (int)rightpack.peek() * 25;
                                leftpack.pop();
                                rightpack.pop();
                                cn.getTextWindow().setCursorPosition(67,20);
                                System.out.print(userscore);
                                if(user_number.getN_value() < 9) {
                                    user_number.setN_value(user_number.getN_value()+1);
                                }
                                else {
                                    user_number.setN_value(1);
                                }
                            }

                        }
                        else{
                            rightpacktemp.push(rightpack.pop());
                            leftpacktemp.push(leftpack.pop());
                        }
                    }

                    while(!leftpacktemp.isEmpty()){
                        leftpack.push(leftpacktemp.pop());
                    }
                    while (!rightpacktemp.isEmpty()){
                        rightpack.push(rightpacktemp.pop());
                    }
                    printstack(leftpack,rightpack);


                }
            }


            Thread.sleep(450);

            //1 = up --> y-1;   2 = right --> x+1
            //3 = down --> y+1;   4 = left --> x-1

            for(int i = 0; i < yellowNumbers.length; i++) {
                if(eaten == true) {
                    break;
                }
                if(yellowNumbers[i]!=null &&yellowNumbers[i].getN_value()!=-1){
                    while(true) {
                        if(yellowNumbers[i].getLastdirection() == 1 && gameboard[yellowNumbers[i].getY()-1][yellowNumbers[i].getX()] == ' ') {
                            gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()] = ' ';
                            cn.getTextWindow().setCursorPosition(yellowNumbers[i].getX(),yellowNumbers[i].getY());
                            System.out.println(" ");
                            yellowNumbers[i].setY(yellowNumbers[i].getY()-1);
                            gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()] = (char)yellowNumbers[i].getN_value();
                            cn.getTextWindow().setCursorPosition(yellowNumbers[i].getX(),yellowNumbers[i].getY());
                            TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print(yellowNumbers[i].getN_value());
                            break;
                        }
                        else if(yellowNumbers[i].getLastdirection() == 2 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()+1] == ' ') {
                            gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()] = ' ';
                            cn.getTextWindow().setCursorPosition(yellowNumbers[i].getX(),yellowNumbers[i].getY());
                            System.out.println(" ");
                            yellowNumbers[i].setX(yellowNumbers[i].getX()+1);
                            gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()] = (char)yellowNumbers[i].getN_value();
                            cn.getTextWindow().setCursorPosition(yellowNumbers[i].getX(),yellowNumbers[i].getY());
                            TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print(yellowNumbers[i].getN_value());
                            break;
                        }
                        else if(yellowNumbers[i].getLastdirection() == 3 && gameboard[yellowNumbers[i].getY()+1][yellowNumbers[i].getX()] == ' ') {
                            gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()] = ' ';
                            cn.getTextWindow().setCursorPosition(yellowNumbers[i].getX(),yellowNumbers[i].getY());
                            System.out.println(" ");
                            yellowNumbers[i].setY(yellowNumbers[i].getY()+1);
                            gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()] = (char)yellowNumbers[i].getN_value();
                            cn.getTextWindow().setCursorPosition(yellowNumbers[i].getX(),yellowNumbers[i].getY());
                            TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print(yellowNumbers[i].getN_value());
                            break;
                        }
                        else if(yellowNumbers[i].getLastdirection() == 4 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()-1] == ' ') {
                            gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()] = ' ';
                            cn.getTextWindow().setCursorPosition(yellowNumbers[i].getX(),yellowNumbers[i].getY());
                            System.out.println(" ");
                            yellowNumbers[i].setX(yellowNumbers[i].getX()-1);
                            gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()] = (char)yellowNumbers[i].getN_value();
                            cn.getTextWindow().setCursorPosition(yellowNumbers[i].getX(),yellowNumbers[i].getY());
                            TextAttributes attrs3 = new TextAttributes(Color.yellow, Color.BLACK);
                            cn.setTextAttributes(attrs3);
                            System.out.print(yellowNumbers[i].getN_value());
                            break;
                        }
                        else if(gameboard[yellowNumbers[i].getY()-1][yellowNumbers[i].getX()] != ' ' && gameboard[yellowNumbers[i].getY()+1][yellowNumbers[i].getX()] != ' ' && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()-1] != ' ' && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()+1] != ' ') {
                            //if 4 directions are not both blank, the number dont move
                            break;
                        }

                        else if (yellowNumbers[i].getLastdirection() == 1 && gameboard[yellowNumbers[i].getY()-1][yellowNumbers[i].getX()] != ' ') {
                            if(yellowNumbers[i].getY()-1 == py && yellowNumbers[i].getX() == px && yellowNumbers[i].getN_value() > user_number.getN_value()) {
                                cn.getTextWindow().setCursorPosition(2,24);
                                TextAttributes attrs3 = new TextAttributes(Color.red, Color.BLACK);
                                cn.setTextAttributes(attrs3);
                                System.out.print("                        GAME OVER!");
                                eaten = true;
                                break;
                            }
                            while(true) {
                                int randomdirection = rnd.nextInt(4)+1;
                                if(randomdirection == 1) {
                                    while(randomdirection != 1) {
                                        randomdirection = rnd.nextInt(4)+1;
                                    }
                                }
                                if(randomdirection == 2 || randomdirection == 4) {
                                    if(randomdirection == 2 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()+1] == ' ') {
                                        yellowNumbers[i].setLastdirection(2);
                                        break;
                                    }
                                    else if(randomdirection == 4 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()-1] == ' ') {
                                        yellowNumbers[i].setLastdirection(4);
                                        break;
                                    }
                                }
                                if(randomdirection == 3 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()+1] != ' ' && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()-1] != ' ') {
                                    yellowNumbers[i].setLastdirection(3);
                                    break;
                                }
                            }
                        }

                        else if (yellowNumbers[i].getLastdirection() == 2 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()+1] != ' ') {
                            if(yellowNumbers[i].getY() == py && yellowNumbers[i].getX()+1 == px && yellowNumbers[i].getN_value() > user_number.getN_value()) {
                                cn.getTextWindow().setCursorPosition(2,24);
                                TextAttributes attrs3 = new TextAttributes(Color.red, Color.BLACK);
                                cn.setTextAttributes(attrs3);
                                System.out.print("                        GAME OVER!");
                                eaten = true;
                                break;
                            }
                            while(true) {
                                int randomdirection = rnd.nextInt(4)+1;
                                if(randomdirection == 2) {
                                    while(randomdirection != 2) {
                                        randomdirection = rnd.nextInt(4)+1;
                                    }
                                }
                                if(randomdirection == 1 || randomdirection == 3) {
                                    if(randomdirection == 1 && gameboard[yellowNumbers[i].getY()-1][yellowNumbers[i].getX()] == ' ') {
                                        yellowNumbers[i].setLastdirection(1);
                                        break;
                                    }
                                    else if(randomdirection == 3 && gameboard[yellowNumbers[i].getY()+1][yellowNumbers[i].getX()] == ' ') {
                                        yellowNumbers[i].setLastdirection(3);
                                        break;
                                    }
                                }
                                if(randomdirection == 4 && gameboard[yellowNumbers[i].getY()-1][yellowNumbers[i].getX()] != ' ' && gameboard[yellowNumbers[i].getY()+1][yellowNumbers[i].getX()] != ' ') {
                                    yellowNumbers[i].setLastdirection(4);
                                    break;
                                }
                            }
                        }

                        else if (yellowNumbers[i].getLastdirection() == 3 && gameboard[yellowNumbers[i].getY()+1][yellowNumbers[i].getX()] != ' ') {
                            if(yellowNumbers[i].getY()+1 == py && yellowNumbers[i].getX() == px && yellowNumbers[i].getN_value() > user_number.getN_value()) {
                                cn.getTextWindow().setCursorPosition(2,24);
                                TextAttributes attrs3 = new TextAttributes(Color.red, Color.BLACK);
                                cn.setTextAttributes(attrs3);
                                System.out.print("                        GAME OVER!");
                                eaten = true;
                                break;
                            }
                            while(true) {
                                int randomdirection = rnd.nextInt(4)+1;
                                if(randomdirection == 3) {
                                    while(randomdirection != 3) {
                                        randomdirection = rnd.nextInt(4)+1;
                                    }
                                }
                                if(randomdirection == 2 || randomdirection == 4) {
                                    if(randomdirection == 2 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()+1] == ' ') {
                                        yellowNumbers[i].setLastdirection(2);
                                        break;
                                    }
                                    else if(randomdirection == 4 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()-1] == ' ') {
                                        yellowNumbers[i].setLastdirection(4);
                                        break;
                                    }
                                }
                                if(randomdirection == 1 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()+1] != ' ' && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()-1] != ' ') {
                                    yellowNumbers[i].setLastdirection(1);
                                    break;
                                }
                            }
                        }

                        else if (yellowNumbers[i].getLastdirection() == 4 && gameboard[yellowNumbers[i].getY()][yellowNumbers[i].getX()-1] != ' ') {
                            if(yellowNumbers[i].getY() == py && yellowNumbers[i].getX()-1 == px && yellowNumbers[i].getN_value() > user_number.getN_value()) {
                                cn.getTextWindow().setCursorPosition(2,24);
                                TextAttributes attrs3 = new TextAttributes(Color.red, Color.BLACK);
                                cn.setTextAttributes(attrs3);
                                System.out.print("                        GAME OVER!");
                                eaten = true;
                                break;
                            }
                            while(true) {
                                int randomdirection = rnd.nextInt(4)+1;
                                if(randomdirection == 4) {
                                    while(randomdirection != 4) {
                                        randomdirection = rnd.nextInt(4)+1;
                                    }
                                }
                                if(randomdirection == 1 || randomdirection == 3) {
                                    if(randomdirection == 1 && gameboard[yellowNumbers[i].getY()-1][yellowNumbers[i].getX()] == ' ') {
                                        yellowNumbers[i].setLastdirection(1);
                                        break;
                                    }
                                    else if(randomdirection == 3 && gameboard[yellowNumbers[i].getY()+1][yellowNumbers[i].getX()] == ' ') {
                                        yellowNumbers[i].setLastdirection(3);
                                        break;
                                    }
                                }
                                if(randomdirection == 2 && gameboard[yellowNumbers[i].getY()-1][yellowNumbers[i].getX()] != ' ' && gameboard[yellowNumbers[i].getY()+1][yellowNumbers[i].getX()] != ' ') {
                                    yellowNumbers[i].setLastdirection(2);
                                    break;
                                }
                            }
                        }
                        else {
                            continue;
                        }

                    }
                }
            }

            if(eaten == true) {
                break;
            }

            cn.getTextWindow().setCursorPosition(67,22);
            TextAttributes attrs1 = new TextAttributes(Color.white, Color.BLACK);
            millisecond= millisecond+500;
            /*pathfinding(gameboard,redNumbers,px,py);*/
            if( millisecond==1000){
                if(user_number.getN_value()==1){
                    count1++;
                    if(count1==4){
                        user_number.setN_value(2);
                        count1=0;
                    }
                }
                second++;
                millisecond=0;
                if(second==60){
                    minute++;
                    second=0;
                }
                if(second<10){
                    cn.setTextAttributes(attrs1);
                    System.out.println( minute+":0"+second);

                }
                else{
                    cn.setTextAttributes(attrs1);
                    System.out.println( minute+":"+second);
                }
                if(second%5==0 &&input.size()!=0){
                    Number n=new Number();
                    int number =(int)input.dequeue();
                    int random_x = rnd.nextInt(54)+1;
                    int random_y = rnd.nextInt(22)+1;
                    while(gameboard[random_y][random_x] != ' ') {
                        random_x = rnd.nextInt(54)+1;
                        random_y = rnd.nextInt(22)+1;
                    }
                    if(number==1 ||number==2||number==3){
                        n.setN_color(Color.GREEN);
                    }
                    else if(number==4 ||number==5||number==6){
                        n.setN_color(Color.YELLOW);
                    }
                    else if(number==7 ||number==8||number==9){
                        n.setN_color(Color.RED);
                    }
                    int randomdirection;
                    while(true) {
                        randomdirection = rnd.nextInt(4)+1;
                        if(randomdirection == 1 && gameboard[random_y-1][random_x] == ' ') {
                            break;
                        }
                        else if(randomdirection == 2 && gameboard[random_y][random_x+1] == ' ') {
                            break;
                        }
                        else if (randomdirection == 3 && gameboard[random_y+1][random_x] == ' ') {
                            break;
                        }
                        else if(randomdirection == 4 && gameboard[random_y][random_x-1] == ' ') {
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                    Number n1 = new Number(n.getN_color(),number,random_x,random_y);
                    if(n1.getN_value()==4 ||n1.getN_value()==5 || n1.getN_value()==6){
                        n1.setLastdirection(randomdirection);
                        yellowNumbers[yellowcounter]=n1;
                        yellowcounter++;
                    }
                    if(n1.getN_value()==7 ||n1.getN_value()==8 || n1.getN_value()==9){
                        redNumbers[redcounter]=n1;
                        redcounter++;
                    }
                    gameboard[random_y][random_x] = (char)n1.getN_value();
                    cn.getTextWindow().setCursorPosition(random_x,random_y);
                    TextAttributes attrs3 = new TextAttributes(n.getN_color(), Color.BLACK);
                    cn.setTextAttributes(attrs3);
                    System.out.println(number);
                    cn.getTextWindow().setCursorPosition(60,4);
                    TextAttributes attrs6 = new TextAttributes(Color.white, Color.BLACK);
                    cn.setTextAttributes(attrs6);
                    int change = rnd.nextInt(100)+1;
                    if(change <= 75) {
                        int a= rnd.nextInt(3)+1;
                        input.enqueue(a);
                    }
                    else if(change > 75 && change <= 95) {
                        int a= rnd.nextInt(3)+4;
                        input.enqueue(a);
                    }
                    else {
                        int a= rnd.nextInt(3)+7;
                        input.enqueue(a);
                    }
                    for (int i = 0; i <10; i++) {
                        System.out.print(input.peek());
                        input.enqueue(input.dequeue());
                    }

                }


            }


        }

    }
}
