package com.kn.updateContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

class ContextEncoder {

	public static void main(String[] args) throws IOException {
		byte[] contextDecoded = Files.readAllBytes(Paths.get("rsc/context_decoded.xml"));
		byte[] contextZipped = gzip(contextDecoded);
		byte[] contextEncoded = Base64.getMimeEncoder().encode(contextZipped);

		Files.write(Paths.get("rsc/context_encoded.txt"), contextEncoded, StandardOpenOption.CREATE);
	}

	private static byte[] gzip(byte[] bytes) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(baos);
		gzip.write(bytes);
		gzip.close();
		
		return baos.toByteArray();
	}
	
}
