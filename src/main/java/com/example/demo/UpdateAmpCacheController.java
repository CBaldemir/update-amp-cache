package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import util.KeyUtil;

import java.io.*;
import java.net.URL;
import java.time.Instant;

@RestController
public class UpdateAmpCacheController {

	@PostMapping("update-cache")
	public String test(@RequestBody UpdateCacheDto updateCacheDto) throws Exception {

		ClassLoader classLoader = UpdateAmpCacheController.class.getClassLoader();
		URL resource = classLoader.getResource("pkcs8-key.pem");
		StringBuilder file = new StringBuilder();
		String line;
		try (FileReader reader = new FileReader(resource.getFile());
				 BufferedReader br = new BufferedReader(reader)) {
			while ((line = br.readLine()) != null) {
				System.out.println(file);
				file.append(line);
			}
		}

return updateCacheDto.getCacheApiDomain() + "/update-cache/c/s" + updateCacheDto.getDomain() + "/amp?amp_action=flush&amp_ts="
			+ Instant.now().getEpochSecond() + "&amp_url_signature=" + KeyUtil.getSignature(file.toString());

  // cd src/main/resource


		//openssl genrsa 2048 > private-key.pem
		//openssl rsa -in private-key.pem -pubout >public-key.pem
		//openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in private-key.pem -out pkcs8-key.pem
		//mvn spring-boot:run


	}



}
