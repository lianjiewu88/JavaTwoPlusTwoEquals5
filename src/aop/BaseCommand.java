package aop;

import java.util.logging.Level;
import com.sun.istack.internal.logging.Logger;

// Jerry 2017-05-19 9:18AM - this is a bad design
// Technical stuff ( logging ) and business logic are mixed!!
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
