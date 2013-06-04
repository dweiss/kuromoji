package com.atilika.kuromoji;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.atilika.kuromoji.xz.XZInputStream;

public final class XzCompressedResolver implements ResourceResolver {
	private final ResourceResolver delegate;

	public XzCompressedResolver(ResourceResolver delegate) {
		this.delegate = delegate;
	}

	@Override
	public InputStream resolve(String resourceName) throws IOException {
		try {
			InputStream is = delegate.resolve(resourceName + ".xz");
			return new BufferedInputStream(new XZInputStream(is));
		} catch (IOException e) {
			// Ignore.
		}
		return new BufferedInputStream(delegate.resolve(resourceName));
	}
}
