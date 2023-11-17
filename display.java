import  java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class display {
	private Image[] blockImgs;
	private Image[] penImgs;
	private final int BW = 32, BH = 32;
	
	display(){
		blockImgs = new Image[4];
		penImgs = new Image[2];
		loadImage();
	}
	
	private void loadImage() {
		blockImgs[0] = new ImageIcon(getClass().getResource("img/b1.png")).getImage();
		blockImgs[1] = new ImageIcon(getClass().getResource("img/b2.png")).getImage();
		blockImgs[2] = new ImageIcon(getClass().getResource("img/b3.png")).getImage();
		blockImgs[3] = new ImageIcon(getClass().getResource("img/batu.png")).getImage();
		penImgs[0] = new ImageIcon(getClass().getResource("img/p1.png")).getImage();
		penImgs[1] = new ImageIcon(getClass().getResource("img/p2.png")).getImage();
	}
	
	public void charaDraw(score s, Graphics g, JPanel p) {
		if(s.maxChainNum >= 10) {
			g.drawImage(penImgs[0], 32*6, 32*9, 300, 200, p);
		}else {
			g.drawImage(penImgs[1], 32*6, 32*9, 300, 200, p);
		}
	}
	
	public void draw(field a, Graphics g, JPanel p) {
		g.drawImage(blockImgs[3], 32*3, 32, BW, BH, p);
		for(int i = 0; i < a.fieldArray.length; i++) {
			for(int q = 0; q < a.fieldArray[0].length; q++) {
				if(a.fieldArray[i][q] == 1) {
					g.drawImage(blockImgs[0], q*BW, i*BH, BW, BH, p);
				}else if(a.fieldArray[i][q] == 2) {
					g.drawImage(blockImgs[1], q*BW, i*BH, BW, BH, p);
				}else if(a.fieldArray[i][q] == 3) {
					g.drawImage(blockImgs[2], q*BW, i*BH, BW, BH, p);
				}
			}
		}
		
	}
	
	public void scoreDraw(score s, Graphics g) {
		g.setColor(new Color(0,0,0));
		Font f = new Font("   C   I" , Font.BOLD , 40);
		g.setFont(f);
		FontMetrics fontMetrics = g.getFontMetrics();
		g.drawString("score:", BW*10, BH*5);
		g.drawString(Integer.toString(s.score),BW*17-fontMetrics.stringWidth(Integer.toString(s.score)), BH*6);
		
		f = new Font("   C   I" , Font.BOLD , 20);
		g.setFont(f);
		g.drawString(" ő A  :", BW*12, BH*4);
		g.drawString(Integer.toString(s.maxChainNum),BW*17-fontMetrics.stringWidth(Integer.toString(s.maxChainNum)), BH*4);
		
		if(s.chainNum!=0) {
			f = new Font("   C   I" , Font.PLAIN , 20);
			g.setFont(f);
			g.drawString(s.chainNum+"   !",BW*9-fontMetrics.stringWidth(Integer.toString(s.chainNum)), BH*10);
		}
	}
	
	public void drawNextBlock(field a, Graphics g, JPanel p) {
		List<int[]> tmp = new ArrayList<int[]>();
		for(int i = 0; i < 6; i++) {
			tmp.add(a.nextBlock.poll());
		}
		//tmp.forEach(t -> System.out.print(":"+t[2]+":"));
		//System.out.println("-----------");
		for(int q = 0; q < 2; q++) {
			for(int i = 0; i < 2; i++) {
				if(tmp.get(i+q*2)[2] == 1) {
					g.drawImage(blockImgs[0], 256+q*32, 32+i*32, BW, BH, p);
				}else if(tmp.get(i+q*2)[2] == 2) {
					g.drawImage(blockImgs[1], 256+q*32, 32+i*32, BW, BH, p);
				}else if(tmp.get(i+q*2)[2] == 3) {
					g.drawImage(blockImgs[2], 256+q*32, 32+i*32, BW, BH, p);
				}
			}
		}
		for(int i =0; i < tmp.size(); i++) {
			//System.out.print(tmp.get(i)[2]+" ");
			a.nextBlock.offer(tmp.get(i));
		}
	}
	
	public void testDraw(field a,Graphics g) {
		g.setColor(new Color(255,255,255));
		for(int i = 1; i < a.fieldArray.length-1; i++) {
			for(int q = 1; q < a.fieldArray[0].length-1; q++) {
				if(a.fieldArray[i][q] == 0) {
					g.drawString("0",q*BW+BW/2, i*BH+BH/2);
				}else if(a.fieldArray[i][q] == 1) {
					g.drawString("1",q*BW+BW/2, i*BH+BH/2);
				}else if(a.fieldArray[i][q] == 2) {
					g.drawString("2",q*BW+BW/2, i*BH+BH/2);
				}else if(a.fieldArray[i][q] == 3) {
					g.drawString("3",q*BW+BW/2, i*BH+BH/2);
				}
			}
		}
	}

}
