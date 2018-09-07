package vn.com.vhc.consolidate.controllers;

import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.vhc.consolidate.models.Subscriber;
import vn.com.vhc.consolidate.services.SubscriberService;


@RestController
public class SubcriberController {
	SubscriberService subscriberService = new SubscriberService();

	@RequestMapping(value = "/subscriber", method = RequestMethod.GET)
	public List<Subscriber> checkUserPassword(
			@RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
			@RequestParam(value = "GPKD", required = false, defaultValue = "") String GPKD,
			@RequestParam(value = "CMT_HC", required = false, defaultValue = "") String CMT_HC,
			@RequestParam(value = "type", required = true) String type) throws NoSuchAlgorithmException, SQLException {
		return subscriberService.getSubscriber(phoneNumber, GPKD, CMT_HC, type);
	}
}
