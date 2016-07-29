package aop;

import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

public class PerformanceTraceDecorator implements Command{

	private Command cmd;
	public PerformanceTraceDecorator(Command cmd){
		this.cmd = cmd;
	}
	
	@Override
	public void execute() {
		Logger logger = Logger.getLogger(this.getClass());
		logger.log(Level.INFO, "begin performance trace");
		this.cmd.execute();
		logger.log(Level.INFO, "end performance trace");
	}
}
