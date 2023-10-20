// swing�ɂ��A�j���[�V�����i���C���j
// Anim2D.java
// 
// 2022 �O���t�B�b�N�X�v���O���~���O���K
// S.Yonemoto
//
import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;

//public
class  Anim2D  extends  JPanel  implements  KeyListener, MouseMotionListener, MouseListener, Runnable {
  int  W, H;     // ��ʂ̕��E����
  Thread  th;
  final  int  wait = 20; // �҂�����ms

  // �֘A����I�u�W�F�N�g���`
  display test_d;
  field test_f;
  player test_p;
  score test_s;
  boolean isCreate, isGameover, isStart ,canMove;
  float timeInterval;
  long startTime, nowTime;
  boolean flag;
  
  // ���̑���� -------------------------
  Image  bg;   // �w�i�摜
  Image startBG;
  ;
  
  public  Anim2D() {  // �R���X�g���N�^
    th = new  Thread(this);    th.start();
    setFocusable(true);            // �p�l�����L�[�{�[�h���󂯕t����悤�ɂ���
    addKeyListener(this);          // �L�[���X�i�[�̒ǉ�
    addMouseListener(this);        // �}�E�X���[�V�������X�i�[�̒ǉ�
    addMouseMotionListener(this);  // �}�E�X���[�V�������X�i�[�̒ǉ�
    init();  // ������
  }
  public  void  run() {
    while (true) { try { Thread.sleep(wait); } catch (InterruptedException  e) { e.printStackTrace(); }  repaint(); }
  }

  // --------------------------------------------------
  // �L�[����
  // --------------------------------------------------
  public  void  keyPressed(KeyEvent  e) {
    int  key = e.getKeyCode();  // ���͂��ꂽ�L�[
    //System.out.println(key); // �f�o�b�O�p��key��\��
    
    if(isStart) {
    	if(key==KeyEvent.VK_SPACE) {
    		isStart = false;
    	}
    	return;
    }
    if(isGameover) {
    	if(key==KeyEvent.VK_ENTER) {
    		isGameover = false;
    		isCreate = true;
    	    isStart = true;
    	    canMove = true;
    	    flag = true;
    	    test_f.init();
    	    test_p.init();
    	    test_s.init();
    	    
    	    timeInterval = 1000;
    	    nowTime = 0;
    	    startTime = System.currentTimeMillis();
    	}
    	return;
    }
    if(canMove == false) {
    	return;
    }
    
    switch (key) {
    case KeyEvent.VK_UP:    //��J�[�\���L�[
      isCreate = true;      
      break;
    case KeyEvent.VK_RIGHT: //�E�J�[�\���L�[
      ;;;      break;
    case KeyEvent.VK_DOWN:  //���J�[�\���L�[
      ;;;      break;
    case KeyEvent.VK_LEFT:  //���J�[�\���L�[
      ;;;      break;
    case  KeyEvent.VK_A:  // a�L�[
      test_p.moveLeft(test_f);      
      break;
    case  KeyEvent.VK_D: // d�L�[
      test_p.moveRight(test_f);
      break;
    case  KeyEvent.VK_K:
      test_p.rotate(test_f);
      break;
    case  KeyEvent.VK_SPACE:  //�X�y�[�X�L�[
      timeInterval = 80;
      test_s.score++;
      break;
    default:
      break;
    }
    //repaint();
  }
  public  void  keyTyped(KeyEvent e){}
  public  void  keyReleased(KeyEvent e){
	  int  key = e.getKeyCode();
	  if(key == KeyEvent.VK_SPACE) {
		  timeInterval = 1000;
		  
	  }
  }

  // --------------------------------------------------
  // �}�E�X���[�V����
  // --------------------------------------------------
  public  void  mouseDragged(MouseEvent  e) {}
  public  void  mouseMoved(MouseEvent  e) {
    // ��e.getX(), e.getY()�Ń}�E�X��(x, y)���󂯎�遚
    //System.out.println("("+e.getX()+", "+e.getY()+")");  // ���p��
  }

  // --------------------------------------------------
  // �}�E�X����
  // --------------------------------------------------
  public  void  mouseClicked(MouseEvent  e) {}
  public  void  mouseEntered(MouseEvent  e) {}
  public  void  mouseExited(MouseEvent  e)  {}
  public  void  mousePressed(MouseEvent  e) {  // �{�^����������
    // �����p�ၚ
	  /*
	  if (e.getButton()==1)       { 
		  System.out.println("L"); // ���{�^��
		  
	  }  else if (e.getButton()==3)  { 
		  System.out.println("R"); // �E�{�^��
		  
	  } 
	  */ 
  }
  public  void  mouseReleased(MouseEvent  e) {}

  
  // �����ݒ�
  public  void  init() {
    W = P.W;  H = P.H;  // ��ʂ̕��E����
    // �w�i�摜�̓ǂݍ���
    bg = new ImageIcon( getClass().getResource("img/bg.png") ).getImage();
    startBG = new ImageIcon( getClass().getResource("img/stbg.png") ).getImage();

    // ���K�v�ȃI�u�W�F�N�g�̐����A�ϐ��̏��������s���B
    test_d = new display();
    test_f = new field();
    test_p = new player();
    test_s = new score();
    isCreate = true;
    isGameover = false;
    isStart = true;
    canMove = true;
    flag = true;
    
    timeInterval = 1000;
    nowTime = 0;
    startTime = System.currentTimeMillis();
    
  }
    
  // --------------------------------------------------
  // �`�惁�C��
  // --------------------------------------------------
  public  void  paintComponent(Graphics  g) {
    super.paintComponent(g);
    // ���������������������@��������@��������������������
    // �w�i�̕`��
    
    if(isStart) {
    	g.drawImage(startBG, 0, 0, P.W, P.H, this);
    	g.setColor(new Color(0,0,0));
		Font f = new Font("���C���I" , Font.PLAIN , 30);
		g.setFont(f);
		g.drawString("�؂񂬂񂭂񂪂�΂�I�I", 50, 100);
		g.drawString("pressSPACE", W/4+70, H-50);
		f = new Font("���C���I" , Font.PLAIN , 20);
		g.setFont(f);
		g.drawString("a d s w:�ړ� \nk:��] \nSPACE:��������", 50, 150);
		
    	return;
    }
    //setBackground(new Color(90,120,210));
    //setBackground(new Color(90,120,210));    // �w�i�F�͐F
    g.drawImage(bg, 0, 0, P.W, P.H, this);  // �w�i�̕`��
    
    g.setColor(new Color(100,100,100,100));
    g.fillRect(32, 32, 32*6, 32*12);
    g.fillRect(246, 22, 84, 84);
    g.fillRect(32*12, 32*3+5, 32*5, 32);
    g.fillRect(32*10-5, 32*4+5, 32*7+10, 32*2+5);
    
    //test_d.testDraw(test_f, g);
    test_d.draw(test_f, g, this);
    test_d.drawNextBlock(test_f, g, this);
    test_d.scoreDraw(test_s, g);
    test_d.charaDraw(test_s, g, this);
    
    if(isGameover) {
    	g.setColor(new Color(0,0,0));
		Font f = new Font("���C���I" , Font.PLAIN , 30);
		g.setFont(f);
		if(test_s.score <= 100) {
			g.drawString("�u�܂��܂����ˁv(B)", W/2-100, H/2+100);
		}else if(test_s.score <= 2000) {
			g.drawString("�u�m���}�B���I�v(A)", W/2-100, H/2+100);
		}else if(test_s.score >= 2000&&test_s.maxChainNum >= 4) {
			g.drawString("�u�������I�v�����I�v(S)", W/2-100, H/2+100);
		}else if(test_s.maxChainNum >= 10) {
			g.drawString("�u��䂤�ł��ˁB�v(X)", W/2-100, H/2+100);
		}else {
			g.drawString("�u��񂳂�_���Ă݂悤�v(A+)", W/2-100, H/2+100);
		}
		g.drawString("pressENTER", W/2, H/2);
    	return;
    }
    
    //�����R������
    
    if(test_f.isDestroyed&&test_f.isSwapped) {
    	nowTime = System.currentTimeMillis();
    	if((timeInterval) < (nowTime - startTime)) {
    		if(flag) {
    			test_f.movedPos.forEach(i -> System.out.println(i[0]+":"+i[1]+","));
    			if(test_f.movedPos.size()==0||test_f.movedPos==null) {
    				test_f.isDestroyed = false;
    				test_f.isSwapped = false;
    				
    				isCreate = true;
    				canMove = true;
    				flag = true;
    				startTime = System.currentTimeMillis();
    				test_d.draw(test_f, g, this);
    				return;
    			}
        		//test_f.movedPos.forEach(i -> test_f.destroyBlockManager(i));
    			for(int i = 0; i < test_f.movedPos.size(); i++) {
    				test_f.destroyBlockManager(test_f.movedPos.get(i), test_s);
    			}
    			test_s.addSingleChainScore();
        		flag = false;
        	}else {
        		test_f.bottomBlock();
        		flag = true;
        	}
    		startTime = System.currentTimeMillis();
    	}
    	return;
    }
    
    if(isCreate) {
    	isCreate = false;
    	canMove = false;
    	test_p.rotateState = 0;
    	test_f.createOpeBlock();
    }
    
    //��b���Ƃ�drop 
    nowTime = System.currentTimeMillis();
    //System.out.println(nowTime - startTime);
    if(timeInterval < (nowTime - startTime)) {
    	canMove = false;
    	test_f.isDestroyed = false;
		test_f.isSwapped = false;
    	if(test_f.blockCheck(test_p)) {
    		test_f.destroyBlockManager(test_f.opeBlock1, test_s);
    		System.out.println("\n***********");
    		test_f.destroyBlockManager(test_f.opeBlock2, test_s);
    		System.out.println("\n");
    		test_s.addSingleChainScore();
    		test_f.clearOpeBlock();
    		test_f.bottomBlock();
    		
    		if(test_f.isDestroyed==false&&test_f.isSwapped) {
    			for(int i = 0; i < test_f.movedPos.size(); i++) {
    				test_f.destroyBlockManager(test_f.movedPos.get(i), test_s);
    			}
    			test_s.addSingleChainScore();
    			flag = false;
    			
    		}
    		if(test_f.isDestroyed&&test_f.isSwapped) {
    			startTime = System.currentTimeMillis();
    			timeInterval = 1000;
    			//test_f.movedPos.forEach(i -> System.out.println(i[0]+":"+i[1]+","));
    			//isGameover  = true;
    			flag = true;
    	    	return; 
    	    }
    		if(test_f.gameoverCheck()) {
    			isGameover = true;
    		}
    		isCreate = true;
    	}else {
    		test_f.dropOpeBlock();//�u���b�N�𗎂Ƃ�
        	test_f.setOpeBlock();
    	}
    	canMove = true;
    	startTime = System.currentTimeMillis();
    }
    
    test_f.setOpeBlock();//fieldArray�Ƀu���b�N���Z�b�g
    //opeBlock�̒��ɂ܂����W���c���Ă邩��ēxset�����(�C���ς�)
    
    
    
    
    
    
    // ���������������������@�����܂Ł@��������������������
  }
}
