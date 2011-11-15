/**
 * A modification of com.aptana.editor.coffee.parsing.ast.CoffeeCommentNode
 * without using other Aptana parsing internals
 */

package com.aptana.editor.coffee.parsing;

public class CoffeeCommentNode 
{
	private String fText;
	private int start;
	private int end;

	/**
	 * CoffeeCommentNode
	 * 
	 * @param text
	 * @param start
	 * @param end
	 */
	public CoffeeCommentNode(String text, int start, int end)
	{
		this.start = start;
		this.end = end;
		fText = text;
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
}
