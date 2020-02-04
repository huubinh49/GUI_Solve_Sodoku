package solveSodoku.huubinh49;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import solveSodoku.huubinh49.tableSudoku;
public class solveSudokuUI extends JFrame{
	public static boolean CheckTrue(int Sudoku[][], int x, int y , int k)
	{
		for(int i=0; i<9;i++)
		{
			if(Sudoku[i][y]==k) return false;
		}
		for(int i=0; i<9;i++)
		{
			if(Sudoku[x][i]==k) return false;
		}
		int a=x/3, b=y/3;
		for(int i= 3*a; i<3*a+3;i++)
		{
			for(int j=3*b;j<3*b+3;j++)
			{
				if(Sudoku[i][j]==k) return false;
			}
		}
		return true;
	}

	public static void SolveSudoku(int Sudoku[][], int x, int y)
	{
		if(y==9)
		{
			if(x==8)
			{
				for(int i=0; i<9;i++)
					for(int j=0; j<9; j++)
						{
						dm.setValueAt(Sudoku[i][j], i,j);
						dm.fireTableCellUpdated(i, j);
						}
						tb.setModel(dm);
			}
			else
				SolveSudoku(Sudoku, x+1, 0);
		}
		else if(Sudoku[x][y]==0)
		{
			for(int k=1; k<10;k++)
			{
				if(CheckTrue(Sudoku, x, y, k))
					{
					Sudoku[x][y]=k;
					SolveSudoku(Sudoku,x,y+1);
					Sudoku[x][y]=0;
					};
			}
		}
		else
			SolveSudoku(Sudoku, x, y+1);
	}
	public static int n=100;
	public static JButton Giai;
	public static int [][]Sudoku= new int [n][n];
	public static DefaultTableModel dm;
	public static JTable tb;
	public solveSudokuUI()
	{
		super("Solve Sudoku");
		addControls();
		addEvents();
	}

	public void addEvents()
	{
		tb.addMouseListener(new MouseListener() {
			
			@Override
			//chuot phai
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			//Khi chuot di ra khoi table
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			//Sự kiện khi click chuột vào 1 ô :
			public void mouseClicked(MouseEvent arg0) {
		int row= tb.getSelectedRow();
		int col= tb.getSelectedColumn();
		dm.setValueAt(null, row, col);
		if(dm.isCellEditable(row, col))
		 	{String s = JOptionPane.showInputDialog("Nhập vào giá trị cho ô:");
		 	//Chuyển số đã nhập vào mảng 2 chiều để xử lí và set
		 		dm.setValueAt(s, row, col);
			}
			}
		});
		tb.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			public Boolean IsKeyNumber(KeyEvent key)
			{
				char a= key.getKeyChar();
			
				
				if(a>='0'&&a<='9')
					{
					return true;
					}
				return false;
				
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(IsKeyNumber(arg0))
				{
					int row= tb.getSelectedRow();
					int col= tb.getSelectedColumn();
							dm.setValueAt(null, row, col);
							dm.fireTableCellUpdated(row, col);
							if(dm.isCellEditable(row, col))
						 	{
								String s = JOptionPane.showInputDialog("Nhập vào giá trị cho ô:");
						 		dm.setValueAt(s, row, col);
							}
				}
			}
		});
		Giai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0; i<9;i++)
					for(int j=0; j<9;j++)
					{
						Sudoku[i][j]=Integer.parseInt(dm.getValueAt(i, j).toString());
					}
					SolveSudoku(Sudoku, 0, 0);
					}
				});
	}
	public void addControls()
	{
		Container con= getContentPane();
		JPanel main = new JPanel();
		con.add(main);
		//
		Giai= new JButton("Giải");
		main.add(Giai);
		//
		dm= new DefaultTableModel(9, 9);
		tb= new JTable();
		tb.setModel(dm);
		tb.setRowHeight(70);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tb.setFont(new Font("Serif", Font.BOLD, 30));
		for(int i=0; i<9;i++)
			{
				tb.getColumnModel().getColumn(i).setWidth(30);
				tb.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
				for(int j=0; j<9;j++)
				{
					Sudoku[i][j]=0;
					dm.setValueAt(0, i, j);
					dm.fireTableCellUpdated(i,j);
				}
			}
		// Tạo table 9x9 gồm các mảng s 
		JPanel Sudo=new JPanel();
		Sudo.add(tb);
		main.add(Sudo);
	}
	public static void showWindow(solveSudokuUI UI)
	{
		UI.setSize(new Dimension(1024,1024));
		UI.setLocationRelativeTo(null);
		UI.setVisible(true);
		UI.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String arg[])
	{
		solveSudokuUI UI= new solveSudokuUI();
		showWindow(UI);
	}
}
