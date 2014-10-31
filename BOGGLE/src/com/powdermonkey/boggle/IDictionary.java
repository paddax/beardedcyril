package com.powdermonkey.boggle;

import java.io.IOException;
import java.io.InputStream;

public interface IDictionary {

	public abstract DictionaryIncrementalSearch lookup(DictionaryIncrementalSearch s);

	public abstract void loadWords(InputStream is) throws IOException;

	
}
