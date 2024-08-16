package com.kurtulussahin.java.ibtechtasks.tasks.cmd;

import java.lang.reflect.*;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.CommandDao;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Command;

public class CommandExecuter {
	public static Bag execute(String commandString, Bag bag) throws Exception {

		CommandDao commandDao = new CommandDao();
		Command command = commandDao.getCommand(commandString);

		if (command == null) {
			throw new CommandStringNotFound(commandString);
		} else {
			printCommantInfo(command);
		}
		
		Class<?> c = Class.forName("com.kurtulussahin.java.ibtechtasks.tasks.operation." + command.getClassName());
		Object obj = c.getDeclaredConstructor().newInstance();
		Method method;
		Bag dbBag;
		if (!bag.getMap().isEmpty()) {
			method = c.getDeclaredMethod(command.getMethodName(), Bag.class);
			dbBag = (Bag) method.invoke(obj, bag);

		} else {
			method = c.getDeclaredMethod(command.getMethodName());
			dbBag = (Bag) method.invoke(obj);
		}
		return dbBag;
	}

	private static void printCommantInfo(Command command) {
		if (command == null) {
			System.out.println("Command is null");

		} else {
			System.out.println("-> Command Information: " + "Name: " + command.getCommandName() + ", Class: "
					+ command.getClassName() + ", Method: " + command.getMethodName());
		}
	}

	private static class CommandStringNotFound extends RuntimeException {
		public CommandStringNotFound(String commandString) {
			super(commandString);
		}
	}
}
