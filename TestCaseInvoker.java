/**
 * Pattern - Command Pattern - Invoker
 * @author Calvin
 *  This class Invokes a command.
 */
public class TestCaseInvoker 
{
	Command command;
	
	/**
	 *  Constructor
	 * @param command
	 */
	public TestCaseInvoker(Command command)
	{
		this.command = command;
	}
	
	/**
	 * setter method
	 * @param command
	 */
	public void setCommand(Command command)
	{
		this.command = command;
	}
	
	/**
	 *  invoker method that calls the execute command on the specific command.
	 *  The invoker invokes a command, and the command executes the appropriate action of the receiver.
	 */
	
	public void invoke() 
	{
		command.execute();
	}
}
