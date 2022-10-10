package utils;

import java.util.Stack;

import javax.swing.JFrame;

public class StackFrame {
	private static Stack<JFrame> stack = new Stack<>();

	public static void push(JFrame jFrame) {
		stack.push(jFrame);
	}

	public static JFrame pop() {
		return stack.pop();
	}

	public static boolean empty() {
		return stack.empty();
	}

	public static JFrame peek() {
		return stack.peek();
	}
}
