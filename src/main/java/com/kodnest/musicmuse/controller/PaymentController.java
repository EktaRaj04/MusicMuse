package com.kodnest.musicmuse.controller;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.annotation.GetExchange;
import com.kodnest.musicmuse.entity.User;
import com.kodnest.musicmuse.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {
	@Autowired
	UserService userservice;

	@GetMapping("/pay")
	public String pay() {
		return "pay";
	}

	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session) {

		int  amount = 149;
		Order order=null;
		try {
			RazorpayClient razorpay=new RazorpayClient("rzp_test_WCZUG5R6NkNbwj", "bjGVYsEvyOVvevTyyGWbTJ8S");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			order = razorpay.orders.create(orderRequest);



		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		finally {
			return order.toString();
		}
	}


	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId,
			@RequestParam String signature) {
		try {
			// Initialize Razorpay client with your API key and secret
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_WCZUG5R6NkNbwj", 
					"bjGVYsEvyOVvevTyyGWbTJ8S");
			
			// Create a signature verification data string
			String verificationData = orderId + "|" + paymentId;

			// Use Razorpay's utility function to verify the signature
			boolean isValidSignature = Utils.verifySignature(verificationData, signature, 
					"bjGVYsEvyOVvevTyyGWbTJ8S");

			return isValidSignature;
		} catch (RazorpayException e) {
			e.printStackTrace();
			return false;
		} 
	}


	@GetMapping("/payment-success")
	public String paymentSuccess(HttpSession session) {
		String mail =  (String) session.getAttribute("email");
		User user = userservice.getUser(mail);
		user.setPremium(true);
		
		userservice.updateUser(user);

		return "customerhome";
	}

	@GetMapping("/payment-failure")
	public String paymentFailure() {

		return "customerhome";
	}
}
