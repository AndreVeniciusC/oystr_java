package oystr.java.test.process;

import java.util.ArrayList;
import java.util.List;

import oystr.java.test.Machine;

public class MachineImpl implements Machine {
	private List<Ad> ads;
		
	public MachineImpl() {
		super();
		ads = new ArrayList<Ad>();
	}
	@Override
	public List<Ad> getAds() {
		return ads;
	}	
}
