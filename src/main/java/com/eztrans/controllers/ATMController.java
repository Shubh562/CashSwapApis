package com.eztrans.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eztrans.dto.request.GenerateQrRequest;
import com.eztrans.services.ATMService;

@RestController
@RequestMapping("/atm")
public class ATMController {

	@Autowired
	ATMService atmService;

	@PostMapping(value = "/generateQR", produces = MediaType.IMAGE_JPEG_VALUE)

	public void generateQR(@Valid @RequestBody GenerateQrRequest request, HttpServletResponse response)
			throws IOException {
		if (request != null) {
			try {
				atmService.generateQrCode(request.getToken(), response.getOutputStream());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			response.getOutputStream().flush();
		}
	}

	@PostMapping(value = "/decodeQR")
	public ResponseEntity<?> decodeQr(MultipartFile qrCode) throws IOException {
		return atmService.decodeQr(qrCode);
	}
}
