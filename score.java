
class score {
	int score;
	int chainNum;
	int desPosLen;
	int maxChainNum;
	
	score(){
		init();
	}
	
	void init() {
		score = 0;
		chainNum = 0;
		desPosLen = 0;
		maxChainNum = 0;
	}
	
	public void addSingleChainScore() {
		if(desPosLen<=3) {desPosLen = 0;chainNum=0;return;}
		chainNum++;
		int result1 = desPosLen*10;
		int result2;
		if(chainNum==1) {
			result2 = (desPosLen-4);
		}else if(chainNum==2||chainNum==3){
			result2 = (8*(chainNum-1))+(desPosLen-4);
		}else {
			result2 = (32^(chainNum-3))+(desPosLen-4);
		}
		if(result2<=0) {result2++;}
		score += result1*result2;
		desPosLen = 0;
		if(chainNum >= maxChainNum) {maxChainNum = chainNum;}
		System.out.println(result1+"///"+result2);
		System.out.println("singleScore:"+result1*result2);
	}
}
