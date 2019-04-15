import java.util.*;
import java.math.*;

class cube
{
    int x,y,op;
    int[][] mp=new int[5][5];
    void rotate()
    {
        int[][] tmp=new int[5][5];
        if(this.op==0||this.op==3)
        {
            for(int i=1;i<=4;i++)
            {
                for(int j=1;j<=4;j++)
                    tmp[i][j]=this.mp[j][4-i+1];
            }
            for(int i=1;i<=4;i++)
            {
                for(int j=1;j<=4;j++)
                    this.mp[i][j]=tmp[i][j];
            }
        }
        else
        {
            for(int i=1;i<=3;i++)
            {
                for(int j=1;j<=3;j++)
                    tmp[i][j]=this.mp[j][3-i+1];
            }
            for(int i=1;i<=3;i++)
            {
                for(int j=1;j<=3;j++)
                    this.mp[i][j]=tmp[i][j];
            }
        }

    }
    static cube make()
    {
        cube a=new cube();
        Random random=new Random();
        int op=random.nextInt(7);
        if(op==0){a.mp[2][1]=1;a.mp[2][2]=1;a.mp[2][3]=1;a.mp[2][4]=1;}
        if(op==1){a.mp[1][1]=1;a.mp[2][1]=1;a.mp[2][2]=1;a.mp[2][3]=1;}
        if(op==2){a.mp[1][3]=1;a.mp[2][1]=1;a.mp[2][2]=1;a.mp[2][3]=1;}
        if(op==3){a.mp[2][2]=1;a.mp[2][3]=1;a.mp[3][2]=1;a.mp[3][3]=1;}
        if(op==4){a.mp[1][2]=1;a.mp[1][3]=1;a.mp[2][1]=1;a.mp[2][2]=1;}
        if(op==5){a.mp[1][2]=1;a.mp[2][1]=1;a.mp[2][2]=1;a.mp[2][3]=1;}
        if(op==6){a.mp[1][1]=1;a.mp[1][2]=1;a.mp[2][2]=1;a.mp[2][3]=1;}
        a.x=1;a.y=11;a.op=op;
        return a;
    }
    void print()
    {
        for(int i=1;i<=4;i++)
        {
            for(int j=1;j<=4;j++)
            {
                if(this.mp[i][j]==1)System.out.printf("*");
                else System.out.printf(" ");
            }
            System.out.println();
        }
    }
}

public class tr2
{
    static int[][] mp=new int[101][101];
    static cube now,nxt;
    static int score;
    static void printstart()
    {
        for(int i=1;i<=25;i++)System.out.printf("-");System.out.printf("\n");
        System.out.printf("|");for(int i=1;i<=8;i++)System.out.printf(" ");System.out.printf("Tetris");for(int i=1;i<=9;i++)System.out.printf(" ");System.out.printf("|");System.out.printf("\n");
        System.out.printf("|");for(int i=1;i<=23;i++)System.out.printf(" ");System.out.printf("|");System.out.printf("\n");
        System.out.printf("|");for(int i=1;i<=9;i++)System.out.printf(" ");System.out.printf("input");for(int i=1;i<=9;i++)System.out.printf(" ");System.out.printf("|");System.out.printf("\n");
        System.out.printf("|");for(int i=1;i<=5;i++)System.out.printf(" ");System.out.printf("s: Start Game");for(int i=1;i<=5;i++)System.out.printf(" ");System.out.printf("|");System.out.printf("\n");
        System.out.printf("|");for(int i=1;i<=5;i++)System.out.printf(" ");System.out.printf("q: Quit Game");for(int i=1;i<=6;i++)System.out.printf(" ");System.out.printf("|");System.out.printf("\n");
        for(int i=1;i<=25;i++)System.out.printf("-");System.out.printf("\n");
    }
    static void printmap()
    {
        System.out.println("下一个方块：");
        nxt.print();
        for(int i=1;i<=25;i++)
        {
            System.out.printf("|");
            for(int j=1;j<=25;j++)
            {
                if(mp[i][j]==1)System.out.printf("*");
                else if(mp[i][j]==2)System.out.printf("+");
                else System.out.printf(" ");
            }
            System.out.printf("|\n");
        }
        for(int i=1;i<=27;i++)System.out.printf("-");
        System.out.printf("\n");
        System.out.printf("你当前的分数为：%d\n",score);
        System.out.printf(" w:旋转 s:维持 a:左坠 d:右坠 x:下坠 q:退出\n");
        System.out.printf("请输入你的指令：\n");
    }
    static void apply(cube a)
    {
        for(int i=1;i<=4;i++)
            for(int j=1;j<=4;j++)
                if(a.mp[i][j]==1)
                    mp[a.x+i-1][a.y+j-1]=1;
    }
    static boolean predict(cube a)
    {
        int p=a.x;
        while(true)
        {
            int flag=0;
            for(int i=1;i<=4;i++)
            {
                for(int j=1;j<=4;j++)
                {
                    if(a.mp[i][j]==1&&mp[p+i-1][a.y+j-1]==1)
                    {
                        flag=1;break;
                    }
                    if(a.mp[i][j]==1&&p+i-1>25)
                    {
                        flag=1;break;
                    }
                }
            }
            if(flag==1){p--;break;}
            else p++;
        }
        if(p==a.x)return false;
        for(int i=1;i<=4;i++)
        {
            for(int j=1;j<=4;j++)
                if(a.mp[i][j]==1)
                    mp[p+i-1][a.y+j-1]=2;
        }
        return true;
    }
    static void uapply(cube a)
    {
        for(int i=1;i<=4;i++)
            for(int j=1;j<=4;j++)
                if(a.mp[i][j]==1)
                    mp[a.x+i-1][a.y+j-1]=0;
    }
    static void upredict(cube a)
    {
        for(int i=1;i<=25;i++)
            for(int j=1;j<=25;j++)
                if(mp[i][j]==2)
                    mp[i][j]=0;
    }
    static boolean check(cube a)
    {
        boolean flag=true;
        for(int i=1;i<=4;i++)
        {
            for(int j=1;j<=4;j++)
                if(a.mp[i][j]==1)
                {
                    if(mp[a.x+i-1][a.y+j-1]==1)flag=false;
                    if(a.x+i-1>25)flag=false;
                    if(a.y+j-1>25||a.y+j-1<1)flag=false;
                }
        }
        return flag;
    }
    static void clear()
    {
        int p=25,hav=0;
        while(true)
        {
            int flag=0;
            for(int i=1;i<=25;i++)
            {
                if(mp[p][i]==0)
                {
                    flag=1;
                    break;
                }
            }
            if(flag==0){p--;hav++;}
            else break;
        }
        if(hav>0)
        {
            System.out.printf("你得分了！\n");
            int tmp=10;
            for(int i=1;i<hav;i++)tmp=tmp*2;
            score+=tmp*hav;
            for(int i=25;i>=hav+1;i--)
            {
                for(int j=1;j<=25;j++)
                    mp[i][j]=mp[i-hav][j];
            }
            for(int i=1;i<=hav;i++)
                for(int j=1;j<=25;j++)
                    mp[i][j]=0;
            printmap();
        }
    }
    static void work()
    {
        Scanner sca=new Scanner(System.in);
        String s=sca.next();
        now=cube.make();nxt=cube.make();
        predict(now);apply(now);
        printmap();uapply(now);upredict(now);
        while(true)
        {
            s=sca.next();
            boolean end=false;
            switch(s)
            {
                case "s":
                {
                    now.x++;
                    if(check(now)==false)
                    {
                        System.out.printf("illegal operation!\n");
                        now.x--;if(now.x==1)end=true;
                        break;
                    }
                    if(predict(now)==false)
                    {
                        apply(now);printmap();
                        now=nxt;nxt=cube.make();
                    }
                    else
                    {
                        apply(now);
                        printmap();
                        uapply(now);upredict(now);
                    }
                    break;
                }
                case "q":
                {
                    end=true;
                    break;
                }
                case "w":
                {
                    now.rotate();now.x++;
                    if(check(now)==false)
                    {
                        System.out.printf("illegal operation!\n");
                        now.rotate();now.rotate();now.rotate();now.x--;
                        if(now.x==1)end=true;
                        break;
                    }
                    if(predict(now)==false)
                    {
                        apply(now);printmap();
                        now=nxt;nxt=cube.make();
                    }
                    else
                    {
                        apply(now);
                        printmap();
                        uapply(now);upredict(now);
                    }
                    break;
                }
                case "a":
                {
                    now.x++;now.y--;
                    if(check(now)==false)
                    {
                        System.out.printf("illegal operation!\n");
                        now.y++;now.x--;
                        if(now.x==1)end=true;
                        break;
                    }
                    if(predict(now)==false)
                    {
                        apply(now);printmap();
                        now=nxt;nxt=cube.make();
                    }
                    else
                    {
                        apply(now);
                        printmap();
                        uapply(now);upredict(now);
                    }
                    break;
                }
                case "d":
                {
                    now.x++;now.y++;
                    if(check(now)==false)
                    {
                        System.out.printf("illegal oeration!\n");
                        now.x--;now.y--;
                        if(now.x==1)end=true;
                        break;
                    }
                    if(predict(now)==false)
                    {
                        apply(now);printmap();
                        now=nxt;nxt=cube.make();
                    }
                    else
                    {
                        apply(now);
                        printmap();
                        uapply(now);upredict(now);
                    }
                    break;
                }
            }
            for(int i=1;i<=25;i++)if(mp[1][i]==1){end=true;break;}
            if(end==true)break;
            clear();
        }
        System.out.println("Game over!");
        System.out.printf("你的分数是：%d\n",score);
    }
    public static void main(String args[])
    {
        printstart();
        work();
    }
}