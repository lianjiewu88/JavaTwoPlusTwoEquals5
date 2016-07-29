package aop;

import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

public class LoggerDecorator implements Command{
	private Command cmd;
	
	public LoggerDecorator(Command cmd){
		this.cmd = cmd;
	}
	
	@Override
	public void execute() {
		Logger logger = Logger.getLogger(this.getClass());
		logger.log(Level.INFO, "start processing");
		// authorization check
		logger.log(Level.INFO, "authorization check");
		this.cmd.execute();
	}
}
