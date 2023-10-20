// swingによるアニメーション（メイン）
// Anim2D.java
// 
// 2022 グラフィックスプログラミング演習
// S.Yonemoto
//
import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;

//public
class  Anim2D  extends  JPanel  implements  KeyListener, MouseMotionListener, MouseListener, Runnable {
  int  W, H;     // 画面の幅・高さ
  Thread  th;
  final  int  wait = 20; // 待ち時間ms

  // 関連するオブジェクトを定義
  display test_d;
  field test_f;
  player test_p;
  score test_s;
  boolean isCreate, isGameover, isStart ,canMove;
  float timeInterval;
  long startTime, nowTime;
  boolean flag;
  
  // その他情報 -------------------------
  Image  bg;   // 背景画像
  Image startBG;
  ;
  
  public  Anim2D() {  // コンストラクタ
    th = new  Thread(this);    th.start();
    setFocusable(true);            // パネルがキーボードを受け付けるようにする
    addKeyListener(this);          // キーリスナーの追加
    addMouseListener(this);        // マウスモーションリスナーの追加
    addMouseMotionListener(this);  // マウスモーションリスナーの追加
    init();  // 初期化
  }
  public  void  run() {
    while (true) { try { Thread.sleep(wait); } catch (InterruptedException  e) { e.printStackTrace(); }  repaint(); }
  }

  // --------------------------------------------------
  // キー入力
  // --------------------------------------------------
  public  void  keyPressed(KeyEvent  e) {
    int  key = e.getKeyCode();  // 入力されたキー
    //System.out.println(key); // デバッグ用にkeyを表示
    
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
    case KeyEvent.VK_UP:    //上カーソルキー
      isCreate = true;      
      break;
    case KeyEvent.VK_RIGHT: //右カーソルキー
      ;;;      break;
    case KeyEvent.VK_DOWN:  //下カーソルキー
      ;;;      break;
    case KeyEvent.VK_LEFT:  //左カーソルキー
      ;;;      break;
    case  KeyEvent.VK_A:  // aキー
      test_p.moveLeft(test_f);      
      break;
    case  KeyEvent.VK_D: // dキー
      test_p.moveRight(test_f);
      break;
    case  KeyEvent.VK_K:
      test_p.rotate(test_f);
      break;
    case  KeyEvent.VK_SPACE:  //スペースキー
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
  // マウスモーション
  // --------------------------------------------------
  public  void  mouseDragged(MouseEvent  e) {}
  public  void  mouseMoved(MouseEvent  e) {
    // ★e.getX(), e.getY()でマウスの(x, y)を受け取る★
    //System.out.println("("+e.getX()+", "+e.getY()+")");  // 利用例
  }

  // --------------------------------------------------
  // マウス入力
  // --------------------------------------------------
  public  void  mouseClicked(MouseEvent  e) {}
  public  void  mouseEntered(MouseEvent  e) {}
  public  void  mouseExited(MouseEvent  e)  {}
  public  void  mousePressed(MouseEvent  e) {  // ボタンを押した
    // ★利用例★
	  /*
	  if (e.getButton()==1)       { 
		  System.out.println("L"); // 左ボタン
		  
	  }  else if (e.getButton()==3)  { 
		  System.out.println("R"); // 右ボタン
		  
	  } 
	  */ 
  }
  public  void  mouseReleased(MouseEvent  e) {}

  
  // 初期設定
  public  void  init() {
    W = P.W;  H = P.H;  // 画面の幅・高さ
    // 背景画像の読み込み
    bg = new ImageIcon( getClass().getResource("img/bg.png") ).getImage();
    startBG = new ImageIcon( getClass().getResource("img/stbg.png") ).getImage();

    // ★必要なオブジェクトの生成、変数の初期化を行う。
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
  // 描画メイン
  // --------------------------------------------------
  public  void  paintComponent(Graphics  g) {
    super.paintComponent(g);
    // ★★★★★★★★★★　ここから　★★★★★★★★★★
    // 背景の描画
    
    if(isStart) {
    	g.drawImage(startBG, 0, 0, P.W, P.H, this);
    	g.setColor(new Color(0,0,0));
		Font f = new Font("メイリオ" , Font.PLAIN , 30);
		g.setFont(f);
		g.drawString("ぺんぎんくんがんばれ！！", 50, 100);
		g.drawString("pressSPACE", W/4+70, H-50);
		f = new Font("メイリオ" , Font.PLAIN , 20);
		g.setFont(f);
		g.drawString("a d s w:移動 \nk:回転 \nSPACE:高速落下", 50, 150);
		
    	return;
    }
    //setBackground(new Color(90,120,210));
    //setBackground(new Color(90,120,210));    // 背景色は青色
    g.drawImage(bg, 0, 0, P.W, P.H, this);  // 背景の描画
    
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
		Font f = new Font("メイリオ" , Font.PLAIN , 30);
		g.setFont(f);
		if(test_s.score <= 100) {
			g.drawString("「まだまだだね」(B)", W/2-100, H/2+100);
		}else if(test_s.score <= 2000) {
			g.drawString("「ノルマ達成！」(A)", W/2-100, H/2+100);
		}else if(test_s.score >= 2000&&test_s.maxChainNum >= 4) {
			g.drawString("「すごい！プロ級！」(S)", W/2-100, H/2+100);
		}else if(test_s.maxChainNum >= 10) {
			g.drawString("「よゆうですね。」(X)", W/2-100, H/2+100);
		}else {
			g.drawString("「れんさを狙ってみよう」(A+)", W/2-100, H/2+100);
		}
		g.drawString("pressENTER", W/2, H/2);
    	return;
    }
    
    //落ちコン処理
    
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
    
    //一秒ごとにdrop 
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
    		test_f.dropOpeBlock();//ブロックを落とす
        	test_f.setOpeBlock();
    	}
    	canMove = true;
    	startTime = System.currentTimeMillis();
    }
    
    test_f.setOpeBlock();//fieldArrayにブロックをセット
    //opeBlockの中にまだ座標が残ってるから再度setされる(修正済み)
    
    
    
    
    
    
    // ★★★★★★★★★★　ここまで　★★★★★★★★★★
  }
}
