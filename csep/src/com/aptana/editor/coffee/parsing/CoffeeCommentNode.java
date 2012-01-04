/**
 * A modification of com.aptana.editor.coffee.parsing.ast.CoffeeCommentNode
 * without using other Aptana parsing internals
 */

package com.aptana.editor.coffee.parsing;

public class CoffeeCommentNode 
{
	private String fText;
	private int fStart;
	private int fEnd;
	
	/**
	 * CoffeeCommentNode
	 * 
	 * @param text
	 * @param start
	 * @param end
	 */
	public CoffeeCommentNode(String text, int start, int end)
	{
		fText = text;
		fStart = start;
		fEnd = end;
	}

	public CoffeeCommentNode(String text)
	{
		this(text, 0, 0);
	}

	/*
	 * (non-Javadoc)
	 * @see com.aptana.editor.css.parsing.ast.CSSNode#getText()
	 */
	public String getText()
	{
		return fText;
	}
	
	public int getStart()
	{
		return fStart;
	}
	
	public int getEnd()
	{
		return fEnd;
	}
	
	public String toString()
	{
		return "Comment:" + getStart() + ":" + getEnd() + ":" + getText();
	}
}
