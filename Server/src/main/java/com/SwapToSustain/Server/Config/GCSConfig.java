package com.SwapToSustain.Server.Config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class GCSConfig {

    @Bean
    public Credentials gcsCredentials() throws IOException {
        return GoogleCredentials.fromStream(new FileInputStream("Server/src/main/resources/keys.json"));
    }

    @Bean
    public Storage gcsStorage(Credentials credentials) {
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }

    @Bean
    public String gcsBucketName() {
        return "swap_image_storage";
    }

}
