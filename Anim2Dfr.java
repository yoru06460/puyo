// Swing�ɂ��A�j���[�V�����iJFrame�Łj
// Anim2Dfr.java
// 
// 2022 �O���t�B�b�N�X�v���O���~���O���K
// S.Yonemoto
//
import  java.awt.*;
import  javax.swing.*;

// --------------------------------------------------
// �t���[���N���X(���̃N���X�͕ύX���Ȃ�)
// --------------------------------------------------
public  class  Anim2Dfr  extends  JFrame {
  public  Anim2Dfr() {
    setTitle(P.Title);           // �E�C���h�E�̃^�C�g��
    setResizable(false);         // �E�C���h�E�T�C�Y�ύX�s��
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // x�{�^���������ꂽ��I������
    setBounds(0, 0, P.W, P.H);   // �E�C���h�E�̕\���ʒu�E�E�C���h�E�T�C�Y��ݒ�

    // �E�C���h�E�Ƀp�l��(�`��p�L�����o�X)��\��t����
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
