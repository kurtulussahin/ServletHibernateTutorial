package com.kurtulussahin.java.ibtechtasks.tasks.cmd;

import java.lang.reflect.*;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.CommandDao;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Command;

public class CommandExecuter {
	public static Bag execute(String commandString, Bag bag) throws Exception {

		Command command = createCommandByCommandString(commandString);
		Class<?> c = Class.forName("com.kurtulussahin.java.ibtechtasks.tasks.operation." + command.getClassName());
		Object obj = c.getDeclaredConstructor().newInstance();
		Method method = getDeclaredMethod(bag, command, c);
		 
		Bag dbBag = (Bag) method.invoke(obj, bag);
		
		return dbBag;
	}

	private static Method getDeclaredMethod(Bag bag, Command command, Class<?> c) throws NoSuchMethodException {
		Method method;
		if (!bag.getMap().isEmpty()) {
			method = c.getDeclaredMethod(command.getMethodName(), Bag.class);
		} else {
			method = c.getDeclaredMethod(command.getMethodName());
		}
		return method;
	}

	private static Command createCommandByCommandString(String commandString) {
		CommandDao commandDao = new CommandDao();
		Command command = commandDao.getCommand(commandString);

		if (command == null) {
			throw new CommandStringNotFound(commandString);
		} else {
			printCommantInfo(command);
		}
		return command;
	}

	private static void printCommantInfo(Command command) {
		
			System.out.println("-> Command Information: " + "Name: " + command.getCommandName() + ", Class: "
					+ command.getClassName() + ", Method: " + command.getMethodName());
	}

	private static class CommandStringNotFound extends RuntimeException {
		public CommandStringNotFound(String commandString) {
			super(commandString);
		}
	}
}
