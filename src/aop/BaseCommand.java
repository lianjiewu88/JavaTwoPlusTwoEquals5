package aop;

import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

public abstract class BaseCommand {

	public void execute(){
		Logger logger = Logger.getLogger(this.getClass());
		logger.log(Level.INFO, "start processing");
		
		// authorization check
		logger.log(Level.INFO, "authorization check");
		
		logger.log(Level.INFO, "begin performance trace");
		// only this line implements real business logic
		this.doBusiness();
		logger.log(Level.INFO, "end performance trace");
	}
	
	protected abstract void doBusiness();
}
