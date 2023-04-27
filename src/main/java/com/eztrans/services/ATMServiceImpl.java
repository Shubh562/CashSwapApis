package com.eztrans.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eztrans.models.WithdrawTicket;
import com.eztrans.repository.WithdrawTicketRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class ATMServiceImpl implements ATMService {

	@Autowired
	TransactionService transactionservice;
	
	@Override
	public void generateQrCode(String token, OutputStream outputStream) throws IOException {
		String atmQRData = transactionservice.getQRCodeData(token);
		if (!atmQRData.equalsIgnoreCase("Invalid_Token")) {
			try {
				BitMatrix bitMatrix = new QRCodeWriter().encode(atmQRData, BarcodeFormat.QR_CODE, 200, 200);

				MatrixToImageWriter.writeToStream(bitMatrix, "jpeg", outputStream);
			} catch (WriterException e) {
				System.out.println(e.getMessage());
			}
		} else {
			// invalid token;
		}

	}

	@Override
	public ResponseEntity<?> decodeQr(MultipartFile qrCode) throws IOException {
		try {
			Result result = new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(
					new BufferedImageLuminanceSource(ImageIO.read(new ByteArrayInputStream(qrCode.getBytes()))))));
			return ResponseEntity.ok(result != null ? result.getText() : null);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}

		return ResponseEntity.badRequest().body("Invalid QR");
	}
}
