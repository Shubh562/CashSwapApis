package com.eztrans.services;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ATMService {
	public void generateQrCode(String token, OutputStream outputStream) throws IOException;
	public ResponseEntity<?> decodeQr(MultipartFile qrCode) throws IOException;
}
