
public class player {
	public int rotateState;
	
	player(){
		init();
	}
	
	void init() {
		rotateState = 0;
	}
	
	public int getRotateState() {
		return rotateState;
	}
	
	public void moveLeft(field a){
		if((a.opeBlock1[1]-1) == 0 || (a.opeBlock2[1]-1) == 0) {
			return;
		}
		switch(rotateState) {
		case 0:
			if(a.fieldArray[a.opeBlock1[0]][a.opeBlock1[1]-1]!=0) {
				return;
			}
			if(a.fieldArray[a.opeBlock2[0]][a.opeBlock2[1]-1]!=0) {
				return;
			}
			break;
		case 1:
			if(a.fieldArray[a.opeBlock1[0]][a.opeBlock1[1]-1]!=0) {
				return;
			}
			break;
		case 2:
			if(a.fieldArray[a.opeBlock1[0]][a.opeBlock1[1]-1]!=0) {
				return;
			}
			if(a.fieldArray[a.opeBlock2[0]][a.opeBlock2[1]-1]!=0) {
				return;
			}
			break;
		case 3:
			if(a.fieldArray[a.opeBlock2[0]][a.opeBlock2[1]-1]!=0) {
				return;
			}
			break;
		default:
			break;
		}
		
		a.opeBlock1[1] -= 1;
		a.opeBlock2[1] -= 1;
		a.fieldArray[a.opeBlock1[0]][a.opeBlock1[1]+1] = 0;
		a.fieldArray[a.opeBlock2[0]][a.opeBlock2[1]+1] = 0;
	}
	public void moveRight(field a){
		if((a.opeBlock1[1]+1) > 6 || (a.opeBlock2[1]+1) > 6) {
			return;
		}
		switch(rotateState) {
		case 0:
			if(a.fieldArray[a.opeBlock1[0]][a.opeBlock1[1]+1]!=0) {
				return;
			}
			if(a.fieldArray[a.opeBlock2[0]][a.opeBlock2[1]+1]!=0) {
				return;
			}
			break;
		case 1:
			if(a.fieldArray[a.opeBlock2[0]][a.opeBlock2[1]+1]!=0) {
				return;
			}
			break;
		case 2:
			if(a.fieldArray[a.opeBlock1[0]][a.opeBlock1[1]+1]!=0) {
				return;
			}
			if(a.fieldArray[a.opeBlock2[0]][a.opeBlock2[1]+1]!=0) {
				return;
			}
			break;
		case 3:
			if(a.fieldArray[a.opeBlock1[0]][a.opeBlock1[1]+1]!=0) {
				return;
			}
			break;
		default:
			break;
		}
		
		a.opeBlock1[1] += 1;
		a.opeBlock2[1] += 1;
		a.fieldArray[a.opeBlock1[0]][a.opeBlock1[1]-1] = 0;
		a.fieldArray[a.opeBlock2[0]][a.opeBlock2[1]-1] = 0;
	}
	public void rotate(field a){
		switch(rotateState) {
		case 0:
			if(a.opeBlock2[1] == 6) {
				break;
			}
			a.opeBlock2[0] -= 1; a.opeBlock2[1] += 1;
			a.fieldArray[a.opeBlock2[0]+1][a.opeBlock2[1]-1] = 0;
			rotateState++;
			break;
		case 1:
			a.opeBlock2[0] -= 1; a.opeBlock2[1] -= 1;
			a.fieldArray[a.opeBlock2[0]+1][a.opeBlock2[1]+1] = 0;
			rotateState++;
			break;
		case 2:
			if(a.opeBlock2[1] == 1) {
				break;
			}
			a.opeBlock2[0] += 1; a.opeBlock2[1] -= 1;
			a.fieldArray[a.opeBlock2[0]-1][a.opeBlock2[1]+1] = 0;
			rotateState++;
			break;
		case 3:
			a.opeBlock2[0] += 1; a.opeBlock2[1] += 1;
			a.fieldArray[a.opeBlock2[0]-1][a.opeBlock2[1]-1] = 0;
			rotateState = 0;
			break;
		default:
			break;
		}
	}
}
