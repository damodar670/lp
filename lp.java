// A Java program to implement greedy algorithm for graph coloring 
import java.io.*; 
import java.util.*; 
import java.util.LinkedList; 

// This class represents an undirected graph using adjacency list 
class Graph 
{ 
	private int V; // No. of vertices 
	private LinkedList<Integer> adj[]; //Adjacency List 

	//Constructor 
	Graph(int v) 
	{ 
		V = v; 
		adj = new LinkedList[v]; 
		for (int i=0; i<v; ++i) 
			adj[i] = new LinkedList(); 
	} 

	//Function to add an edge into the graph 
	void addEdge(int v,int w) 
	{ 
		adj[v].add(w); 
		adj[w].add(v); //Graph is undirected 
	} 

	// Assigns colors (starting from 0) to all vertices and 
	// prints the assignment of colors 
	void greedyColoring() 
	{ 
		int result[] = new int[V]; 

		// Initialize all vertices as unassigned 
		Arrays.fill(result, -1); 

		// Assign the first color to first vertex 
		result[0] = 0; 

		// A temporary array to store the available colors. False 
		// value of available[cr] would mean that the color cr is 
		// assigned to one of its adjacent vertices 
		boolean available[] = new boolean[V]; 
		
		// Initially, all colors are available 
		Arrays.fill(available, true); 

		// Assign colors to remaining V-1 vertices 
		for (int u = 1; u < V; u++) 
		{ 
			// Process all adjacent vertices and flag their colors 
			// as unavailable 
			Iterator<Integer> it = adj[u].iterator() ; 
			while (it.hasNext()) 
			{ 
				int i = it.next(); 
				if (result[i] != -1) 
					available[result[i]] = false; 
			} 

			// Find the first available color 
			int cr; 
			for (cr = 0; cr < V; cr++){ 
				if (available[cr]) 
					break; 
			} 

			result[u] = cr; // Assign the found color 

			// Reset the values back to true for the next iteration 
			Arrays.fill(available, true); 
		} 

		// print the result 
		for (int u = 0; u < V; u++) 
			System.out.println("Vertex " + u + " ---> Color "
								+ result[u]); 
	} 

	// Driver method 
	public static void main(String args[]) 
	{ 
		Graph g1 = new Graph(5); 
		g1.addEdge(0, 1); 
		g1.addEdge(0, 2); 
		g1.addEdge(1, 2); 
		g1.addEdge(1, 3); 
		g1.addEdge(2, 3); 
		g1.addEdge(3, 4); 
		System.out.println("Coloring of graph 1"); 
		g1.greedyColoring(); 

		System.out.println(); 
		Graph g2 = new Graph(5); 
		g2.addEdge(0, 1); 
		g2.addEdge(0, 2); 
		g2.addEdge(1, 2); 
		g2.addEdge(1, 4); 
		g2.addEdge(2, 4); 
		g2.addEdge(4, 3); 
		System.out.println("Coloring of graph 2 "); 
		g2.greedyColoring(); 
	} 
} 
// This code is contributed by Aakash Hasija 




import java.io.*;
import java.util.*;
public class TACG {
	static int index=1;
	public static void main(String[] args) {
		
		System.out.println("this solution is for single digit arithmetic operational expression");
		TACG ob=new TACG();
		String input=" ( 5 * 3 + ( 4 + 2 % ( 2 * 8 ) ) ) ";
		System.out.println("input expresion: "+input);
		input=input.replaceAll(" ", "");
		Node root=ob.algorithm(input);
		ob.dfs(root);
	}
	void dfs(Node root) {
		if(isOperator(root.op)) {
			dfs(root.left);
			dfs(root.right);
            System.out.println("--> "+ root.name +" = " + root.left.name + " "+ root.op  + " " + root.right.name);
		}
	}
	public int getPrecedence(Character c){
        if (c=='+' || c=='-'){
            return 1;
        }
        else if (c=='*'){
            return 2;
        }
        else if (c=='/'){
            return 3;
        }
        else if (c=='%'){
            return 4;
        }
        else { 
            return 0;
		}
    }
	public Node algorithm(String input) {
		Stack<Character> ops=new Stack<>();
		Stack<Node> expns=new Stack<>();
		Character c;
		for(int i=0;i<input.length();i++) {
			c=input.charAt(i);
			if(c=='(') {
				ops.push(c);
			}
			else if(Character.isDigit(c)) {
				expns.push(new Node(c));
			}
			else if(isOperator(c)) {
				while (!ops.empty()&&getPrecedence(ops.peek()) >= getPrecedence(c)) {
                    Character operator = ops.pop();
                    Node right = expns.pop();
                    Node left = expns.pop();
                    expns.push(new Node(operator,left,right,"E"+index++));
                }
				ops.push(c);
			}
			else if(c==')') {
				while (ops.peek()!= '(') {
                    Character operator = ops.pop();
                    Node right = expns.pop();
                    Node left = expns.pop();
                    expns.push(new Node(operator,left,right,"E"+index++));
                }
				ops.pop();
			}
			else {
				System.out.println("Some where error occured!");
			}
		}
		while(!ops.empty()) {
			Character operator = ops.pop();
            Node right = expns.pop();
            Node left = expns.pop();
            expns.push(new Node(operator,left,right,"E"+index++));
		}
		return expns.pop();
	}
	public  boolean isOperator(Character c){
        if (c=='+' || c=='-' || c=='/' || c=='*'|| c=='%'){
            return true;
        }
        else{
            return false;
        }
    }
}
class Node{
	Node left;
	Node right;
	Character op;
	String name;
	public Node(Character c){
		op=c;
        this.name=c+"";
	}
	public Node(Character op,Node left,Node right,String name){
		this.op=op;
		this.left=left;
		this.right=right;
		this.name=name;
	}
}




package cd;

//REGISTER ALLOCATION - GRAPH COLORING
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;
class p12
{
static Stack<Integer> s=new Stack<Integer>();
static int m,n;
public static void main(String args[])
{
long b=System.currentTimeMillis();
Scanner sc=new Scanner(System.in);
System.out.println("Enter number of colours");
m=sc.nextInt();
System.out.println("Enter number of vertices");
n=sc.nextInt();
int g[][]=new int[n][n];
int c[]=new int[n];
int d[]=new int[n];
for(int i=0;i<n-1;i++)
{
c[i]=0;
g[i][i]=0;
System.out.println("Enter 1 if they have edge else 0");
for(int j=i+1;j<n;j++)
{
System.out.println(i+" and "+j);
g[i][j]=sc.nextInt();
g[j][i]=g[i][j];
}
}
for(int i=0;i<n;i++)
{
d[i]=0;
for(int j=0;j<n;j++)
d[i]+=g[i][j];
}
RA(g,c,d);
long e=System.currentTimeMillis();
System.out.println((e-b)*0.001);
}
public static void RA(int g[][],int c[],int d[])
{
int v[]=new int[n];
int ad[][]=new int[n][n];
for(int i=0;i<n;i++)
{
v[i]=0;
for(int j=0;j<n;j++)
ad[i][j]=g[i][j];
}
for(int k=0;k<n-1;k++)
{
int mi=-1,mx=999;
for(int i=0;i<n;i++)
{
if(d[i]<mx && d[i]>0)
{
mx=d[i];
mi=i;
}
}
s.push(mi);
v[mi]=1;
d[mi]=0;
for(int j=0;j<n;j++)
{
ad[mi][j]=0;
ad[j][mi]=0;
}
for(int i=0;i<n;i++)
{
d[i]=0;
for(int j=0;j<n;j++)
d[i]+=ad[i][j];
}
}
for(int i=0;i<n;i++)
{
if(v[i]==0)
{
s.push(i);
break;
}
}
for(int i=0;i<n;i++)
{
int j=(int)s.pop();
//System.out.println(j);
nV(j,c,g);
}
for(int i=0;i<n;i++)
System.out.println(i+" "+c[i]);
}
public static void nV(int j,int c[],int g[][])
{
int i;
c[j]=1;
while(true)
{
for(i=0;i<n;i++)
{
//System.out.println(g[i][j]+" "+c[j]+" "+c[i]);
if(g[i][j]==1  && c[j]==c[i])
break;
}
if(i==n)
return;
c[j]=c[j]%m;
c[j]++;
}
}
}



import java.io.*;
import java.util.*;
import java.lang.*;
class P08
{
static String in="";
public static void main(String args[])
{
long b=System.currentTimeMillis();
System.out.println("Enter the binary data");
Scanner sc=new Scanner(System.in);
String in=sc.next();
if(!(in.contains(".")))
in+=".0";
N n=new N(in);
System.out.println("Value is "+n.val);
System.out.println("Count is "+n.cnt);
long e=System.currentTimeMillis();
//System.out.println((e-b)*0.001);
}
}
class N
{
int cnt=0;
double val=0.0;
N(String s)
{
String v1="",v2="";
int i=0;
while((s.charAt(i)+"").compareTo(".")!=0)
{
v1+=s.charAt(i)+"";
i++;
}
L l1=new L(v1);
i++;
while(i<s.length())
{
v2+=s.charAt(i)+"";
i++;
}
L l2=new L(v2);
cnt=l1.cnt+l2.cnt;
val=l1.val+(l2.val*Math.pow(2,-1*l2.cnt));
}
}
class L
{
int cnt=0;
double val=0.0;
L(String s)
{
if(s.length()==1)
{
B b=new B(s);
val=b.val;
cnt=b.cnt;
}
else
{
String s1=s.charAt(s.length()-1)+"";
StringBuilder sb=new StringBuilder(s);
sb.deleteCharAt(s.length()-1);
s=sb.toString();
L l1=new L(s);
B b=new B(s1);
val=2*l1.val+b.val;
cnt=l1.cnt+b.cnt;
}
}
}
class B
{
int cnt=0;
double val=0.0;
B(String s)
{
cnt=1;
val=Double.parseDouble(s.charAt(0)+"");
}
}




// Task 12: Generate an optimized three address code for a given expression.
// Enter the maximum number of  expressions : 5

// Enter the input : 
// + 4 2 t1
// + a t1 t2
// - b a t3
// + a 6 t4
// * t3 t4 t5
// Optimized code is : 
// + a 6 t2
// - b a t3
// * t3 t2 t5
#include<stdio.h>
#include<string.h>
#include<ctype.h>

void input();
void output();
void change(int p,int q,char *res);
void constant();
void expression();

struct expr
{
  char op[2],op1[5],op2[5],res[5];
  int flag;
}arr[10];

int n;

int main()
{
  int ch=0;
  input();
  constant();
  expression();
  output();
}


void input()
{
  int i;
  printf("\n\nEnter the maximum number of  expressions : ");
  scanf("%d",&n);
  printf("\nEnter the input : \n");
  for(i=0;i<n;i++)
  {
    scanf("%s",arr[i].op);
    scanf("%s",arr[i].op1);
    scanf("%s",arr[i].op2);
    scanf("%s",arr[i].res);
    arr[i].flag=0;
  }
}


void constant()
{
  int i;
  int op1,op2,res;
  char op,res1[5];
  for(i=0;i<n;i++)
  {
    if(isdigit(arr[i].op1[0]) && isdigit(arr[i].op2[0])) //if both digits, store them in variables
    {
      op1=atoi(arr[i].op1);
      op2=atoi(arr[i].op2);
      op=arr[i].op[0];
      switch(op)
      {
        case '+':
          res=op1+op2;
          break;

        case '-':
          res=op1-op2;
          break;

        case '*':
          res=op1*op2;
          break;

        case '/':
          res=op1/op2;
          break;
      }
      sprintf(res1,"%d",res);                              
      arr[i].flag=1; //eliminate expr and replace any operand below that uses result of this expr

      change(i,i,res1);
    }
  }
}


void expression()
{
  int i,j;
  for(i=0;i<n;i++)
  {
    for(j=i+1;j<n;j++)
    {
      if(strcmp(arr[i].op,arr[j].op)==0) //if operators are same
      {
        if(strcmp(arr[i].op,"+")==0||strcmp(arr[i].op,"*")==0) //order doesn't matter if operators are + or *
        {
          if(strcmp(arr[i].op1,arr[j].op1)==0&&strcmp(arr[i].op2,arr[j].op2)==0 || strcmp(arr[i].op1,arr[j].op2)==0&&strcmp(arr[i].op2,arr[j].op1)==0)  
          {
            arr[j].flag=1; //does't print
            change(i,j,NULL); //change any operand below that uses result of this expression
          }
        }
        
        else                   
        {
          if(strcmp(arr[i].op1,arr[j].op1)==0&&strcmp(arr[i].op2,arr[j].op2)==0)
	          {
            arr[j].flag=1;
            change(i,j,NULL);
          }
        }
      }
    }
  }
}

void output()
{
  int i=0;
  printf("\nOptimized code is : ");
  for(i=0;i<n;i++)
  {
    if(!arr[i].flag)
    {
      printf("\n%s %s %s %s",arr[i].op,arr[i].op1,arr[i].op2,arr[i].res);
    }
  }
}


void change(int p,int q,char *res)
{
  int i;
  for(i=q+1;i<n;i++)
  {
    if(strcmp(arr[q].res,arr[i].op1)==0)
      if(res == NULL)                         //for csub
        strcpy(arr[i].op1,arr[p].res);
      else                                    //for ceval
        strcpy(arr[i].op1,res);                  
    else if(strcmp(arr[q].res,arr[i].op2)==0)
      if(res == NULL)                         //for csub
        strcpy(arr[i].op2,arr[p].res);
      else                                    //for ceval
        strcpy(arr[i].op2,res);
  }
}


