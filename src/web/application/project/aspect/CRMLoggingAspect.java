package web.application.project.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	//setup logger
	private Logger myLogger=Logger.getLogger(getClass().getName());
	
	//setup pointcut declaration
	@Pointcut("execution(* web.application.project.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* web.application.project.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* web.application.project.dao.*.*(..))")
	private void forDaoPackage() {}
	
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	//add @Before advice
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		//display method we are calling
		String methodeString=theJoinPoint.getSignature().toShortString();
		myLogger.info("=====> in @Before : calling method : "+methodeString);
		
		//display the arguments to the method
				
		//get the arguments
		Object[] argsObjects=theJoinPoint.getArgs();
		
		//loop thru and display
		for(Object tempArg: argsObjects) {
			myLogger.info("=====> argument: "+tempArg);
		}
		
	}
		
	//add @AfterReturning advice
	
	@AfterReturning(pointcut = "forAppFlow()",returning = "theResulObject")
	public void afterReturning(JoinPoint joinPoint,Object theResulObject) {
		
		//display method we are calling
		String methodeString=joinPoint.getSignature().toShortString();
		myLogger.info("=====> in @AfterReturning : calling method : "+methodeString);
		
		//display data returned
		myLogger.info("=====> result : "+ theResulObject);
		
	}

}























