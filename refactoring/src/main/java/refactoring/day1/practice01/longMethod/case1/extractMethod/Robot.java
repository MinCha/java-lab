package refactoring.day1.practice01.longMethod.case1.extractMethod;

// 로봇팔
public class Robot {
	// 컨베이어 벨트
    Machine location;
    String bin;

    public Robot() {}

    public Machine location() {
    	return location;
    }
    
    public void moveTo(Machine location) {
    	this.location = location;
    }

    public void pick() {
    	this.bin = location.take();
    }
    
    public String bin() {
    	return bin;
    }

    public void release() {
        location.put(bin);
        bin = null;
    }
}
