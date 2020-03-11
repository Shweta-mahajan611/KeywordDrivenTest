package com.keyworddriven.tests;

import org.testng.annotations.Test;

import com.keyworddriven.engine.KeywordEngineTest;

public class FbLoginTest
{
	public KeywordEngineTest keywordengine;

	@Test
	public void loginTest() 
	{
		keywordengine = new KeywordEngineTest();
		try 
		{
			keywordengine.startExecution("FbLogin");
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
