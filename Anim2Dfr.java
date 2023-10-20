// Swingによるアニメーション（JFrame版）
// Anim2Dfr.java
// 
// 2022 グラフィックスプログラミング演習
// S.Yonemoto
//
import  java.awt.*;
import  javax.swing.*;

// --------------------------------------------------
// フレームクラス(このクラスは変更しない)
// --------------------------------------------------
public  class  Anim2Dfr  extends  JFrame {
  public  Anim2Dfr() {
    setTitle(P.Title);           // ウインドウのタイトル
    setResizable(false);         // ウインドウサイズ変更不可
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // xボタンが押されたら終了する
    setBounds(0, 0, P.W, P.H);   // ウインドウの表示位置・ウインドウサイズを設定

    // ウインドウにパネル(描画用キャンバス)を貼り付ける
    Anim2D  aJPanel= new  Anim2D();
    aJPanel.setPreferredSize(new  Dimension(P.W, P.H));
    Container  cp = getContentPane();
    cp.add(aJPanel);
  }
  public  static  void  main(String[]  args) {
    Anim2Dfr  app = new  Anim2Dfr();
    app.pack();
    app.setVisible(true);
  }
}
