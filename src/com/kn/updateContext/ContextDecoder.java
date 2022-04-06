package com.kn.updateContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

class ContextDecoder {

	public static void main(String[] args) throws IOException {
		byte[] contextEncoded = Files.readAllBytes(Paths.get("rsc/context_encoded.txt"));
		byte[] contextDecoded = Base64.getMimeDecoder().decode(contextEncoded);
		byte[] contextUnzipped = ungzip(contextDecoded);
		
		Files.write(Paths.get("rsc/context_decoded.xml"), contextUnzipped, StandardOpenOption.CREATE);
	}

	private static byte[] ungzip(byte[] bytes) throws IOException {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPInputStream zipIn = new GZIPInputStream(new ByteArrayInputStream(bytes));

		for (int len; (len = zipIn.read(buffer)) > 0;) {
			bos.write(buffer, 0, len);
		}

		return bos.toByteArray();
	}
	
}
