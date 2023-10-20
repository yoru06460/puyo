import java.util.Random;
import java.util.*;

class field {
	int[][] fieldArray;
	int[] opeBlock1;
	int[] opeBlock2;
	boolean isDestroyed, isSwapped;
	List<int[]> desPos = new ArrayList<int[]>();
	List<int[]> movedPos = new ArrayList<int[]>();
	Queue<int[]> nextBlock = new ArrayDeque<>();
	
	field(){
		fieldArray = new int [14][8];//����傫������
		opeBlock1 = new int[3];//y:x:element
		opeBlock2 = new int[3];
		isDestroyed = false;
		isSwapped = false;
		for(int i = 0; i < fieldArray.length; i++) {
			for(int q = 0; q < fieldArray[0].length; q++) {
				fieldArray[i][q] = 0;
			}
		}
		for(int i = 0; i < 3; i++) {
			int[] tmp1 = {1, 3, 0};
			int[] tmp2 = {2, 3, 0};
			
			Random rand = new Random();
			tmp1[2] = rand.nextInt(3) +1; tmp2[2] = rand.nextInt(3) +1;
			//System.out.print(" "+tmp1[2]+" "+tmp2[2]);
			nextBlock.offer(tmp1); nextBlock.offer(tmp2);
		}
	}
	
	void init() {
		for(int i = 0; i < opeBlock1.length; i++) {
			opeBlock1[i] = 0;
			opeBlock2[i] = 0;
		}
		isDestroyed = false;
		isSwapped = false;
		desPos.clear();
		movedPos.clear();
		nextBlock.clear();
		for(int i = 0; i < fieldArray.length; i++) {
			for(int q = 0; q < fieldArray[0].length; q++) {
				fieldArray[i][q] = 0;
			}
		}
		for(int i = 0; i < 3; i++) {
			int[] tmp1 = {1, 3, 0};
			int[] tmp2 = {2, 3, 0};
			
			Random rand = new Random();
			tmp1[2] = rand.nextInt(3) +1; tmp2[2] = rand.nextInt(3) +1;
			//System.out.print(" "+tmp1[2]+" "+tmp2[2]);
			nextBlock.offer(tmp1); nextBlock.offer(tmp2);
		}
	}
	
	int getElement(int x,int y) {
		return fieldArray[x][y];
	}
	
	public void clearOpeBlock() {
		for(int i = 0; i < 3; i++) {
			if(i==2) {opeBlock1[i] = 0;opeBlock2[i] = 0;}
			else {opeBlock1[i] = 1;opeBlock2[i] = 1;}
		}
	}
	
	public void setOpeBlock() {
		fieldArray[opeBlock1[0]][opeBlock1[1]] = opeBlock1[2];
		fieldArray[opeBlock2[0]][opeBlock2[1]] = opeBlock2[2];
	}
	
	void createOpeBlock() {
		opeBlock1 = nextBlock.poll();
		opeBlock2 = nextBlock.poll();
		
		int[] tmp1 = {1, 3, 0};
		int[] tmp2 = {2, 3, 0};
		Random rand = new Random();
		tmp1[2] = rand.nextInt(3) +1; tmp2[2] = rand.nextInt(3) +1;
		nextBlock.offer(tmp1); nextBlock.offer(tmp2);
	}
	
	//�C�ӂ̍��W�̎���̗v�f���擾
	//�����v�f������΂��̗v�f�̍��W�𒆐S�Ƃ��Ă�����x���s
	//�Ȃ����return
	//4����s���ꂽ��destroyBlock()���s
	
	//4�����̗v�f���ׂē����v�f�Ȃ�0��
	//0�ɂ������W�𒆐S�ɍĂю��s
	
	void destroyBlockCheck(int[] tPos) {
		//tPos[0]:y, tPos[1]:x
		int tElement = fieldArray[tPos[0]][tPos[1]];
		if(tElement == 0) {return;}
		
		System.out.println(tPos[0] + ":" +tPos[1]);
		
		/*
		list��Pos�̒l�������Ă���
		�T���񐔂�3�ȏ��Element��0��
		����contains���g�p����Ƃ��܂�������
		if(desPos.contains(tPos)) {return;}
		*/ 
		
		//�������X�g�ɒT��������W�͊܂܂�Ă��邩
		for(int i = 0; i < desPos.size(); i++) {
			if(desPos.get(i)[0] == tPos[0]) {
				if(desPos.get(i)[1] == tPos[1]) {
					return;
				}
			}
		}
		desPos.add(tPos);
		
		//��
		if(fieldArray[tPos[0]-1][tPos[1]] == tElement) {
			System.out.println("��:"+(tPos[0]-1)+","+tPos[1]);
			int[] retrnPos = {tPos[0]-1,tPos[1]};
			destroyBlockCheck(retrnPos);
		}
		//��
		if(fieldArray[tPos[0]+1][tPos[1]] == tElement) {
			System.out.println("��:"+(tPos[0]+1)+","+tPos[1]);
			int[] retrnPos = {tPos[0]+1,tPos[1]};
			destroyBlockCheck(retrnPos);
		}
		//��
		if(fieldArray[tPos[0]][tPos[1]-1] == tElement) {
			System.out.println("��:"+(tPos[0])+","+(tPos[1]-1));
			int[] retrnPos = {tPos[0],tPos[1]-1};
			destroyBlockCheck(retrnPos);
		}
		//�E
		if(fieldArray[tPos[0]][tPos[1]+1] == tElement) {
			System.out.println("�E:"+(tPos[0])+","+(tPos[1]+1));
			int[] retrnPos = {tPos[0],tPos[1]+1};
			destroyBlockCheck(retrnPos);
		}
		
	}
	
	void destroyBlock(score s) {
		System.out.print("desPos is ->");
		desPos.forEach(i -> System.out.print(i[0]+":"+i[1]+","));
		
		if(desPos.size() >= 4) {
			//Pos�̗v�f��0�ɂ���
			isDestroyed = true;
			s.desPosLen += desPos.size();
			desPos.forEach(i -> fieldArray[i[0]][i[1]] = 0);
			
		}
	}
	
	public void destroyBlockManager(int[] tPos, score s) {
		destroyBlockCheck(tPos);
		destroyBlock(s);
		desPos.clear();
	}
	
	public void bottomBlock() {
		//�����珇�ɗv�f��I��
		//���̗v�f�̉��̍��W�̗v�f��0�Ȃ�X���b�v
		//���̗v�f��0�ȊO�ł���Ό��X�I�����Ă����v�f����ĊJ
		movedPos.clear();
		for(int x = 1; x < 7; x++) {
			int nowPos = 11;
			while(nowPos > 0) {
				if(fieldArray[nowPos][x]!=0&&fieldArray[nowPos+1][x]==0) { 
					isSwapped = true;
					blockSwap(nowPos, x);
				}
				nowPos--;
			}
		}
	}
	
	void blockSwap(int nowPos, int x) {
		int tmp = fieldArray[nowPos][x];
		fieldArray[nowPos][x] = 0;
		fieldArray[nowPos+1][x] = tmp;
		if(nowPos!=11&&fieldArray[nowPos+2][x]==0) {
			blockSwap(nowPos+1, x);
			return;
		}
		int[] tmp_add = {nowPos+1, x};movedPos.add(tmp_add);
	}
	
	public void dropOpeBlock() {
		if(opeBlock1[0] == 12 || opeBlock2[0] == 12) {return;}
		
		opeBlock1[0] += 1;  
		opeBlock2[0] += 1;
		fieldArray[opeBlock1[0]-1][opeBlock1[1]] = 0;
		fieldArray[opeBlock2[0]-1][opeBlock2[1]] = 0;
		setOpeBlock();
	}
	
	public boolean gameoverCheck() {
		if(fieldArray[1][3] != 0) {return true;}
		
		return false;
	}
	
	public boolean blockCheck(player p) {
		if(opeBlock1[0] == 12 || opeBlock2[0] == 12) {return true;}
		
		//drop����A���̃u���b�N�擾
		if(p.getRotateState() == 0) {
			if(getElement(opeBlock2[0]+1, opeBlock2[1]) != 0) {return true;}
		}
		else if(p.getRotateState() == 1) {
			if(getElement(opeBlock1[0]+1, opeBlock1[1]) != 0) {return true;}
			if(getElement(opeBlock2[0]+1, opeBlock2[1]) != 0) {return true;}
		}
		else if(p.getRotateState() == 2) {
			if(getElement(opeBlock1[0]+1, opeBlock1[1]) != 0) {return true;}
		}
		else {
			if(getElement(opeBlock1[0]+1, opeBlock1[1]) != 0) {return true;}
			if(getElement(opeBlock2[0]+1, opeBlock2[1]) != 0) {return true;}
		}
		
		return false;
	}
}