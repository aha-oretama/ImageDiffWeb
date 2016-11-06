package aha.oretama.jp.template;

import aha.oretama.jp.model.Authentication;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class ZipTemplate {

  private RestTemplate restTemplate;

  public ZipTemplate() {
    restTemplate = new RestTemplate();
  }

  @Async
  public void createUnzipFiles(String baseDir, URI uri, Authentication auth){
    byte[] content;
    if(auth == null || auth.getUserId() == null || auth.getPassword() == null) {
      content = getZip(uri);
    }else{
      content = getZip(uri, auth.getUserId(), auth.getPassword());
    }

    try {
      unzip(baseDir,content);
    } catch (IOException e) {
      throw new RuntimeException("Cannot unzip download zip files.", e);
    }
  }

  private byte[] getZip(URI uri) {
    RequestEntity requestEntity = RequestEntity.get(uri).build();

    ResponseEntity<byte[]> responseEntity = restTemplate.exchange(requestEntity, byte[].class);

    return responseEntity.getBody();
  }

  private byte[] getZip(URI uri, String userId, String password) {

    String plainCredentials = userId + ":" + password;
    String base64Credentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes(StandardCharsets.UTF_8));

    RequestEntity requestEntity =
        RequestEntity.get(uri).header("Authorization", "Basic " + base64Credentials).build();

    ResponseEntity<byte[]> responseEntity = restTemplate.exchange(requestEntity, byte[].class);

    return responseEntity.getBody();
  }

  private void unzip(String baseDir,byte[] content) throws IOException {
    File base = new File(baseDir);
    if(!base.exists()) {
      base.mkdirs();
    }

    ZipArchiveInputStream archive = new ZipArchiveInputStream(new ByteArrayInputStream(content));
    ZipArchiveEntry entry;

    while ((entry = archive.getNextZipEntry()) != null) {
      // ディレクトリは必要ないため、除く
      if(entry.isDirectory()){
        continue;
      }

      // ディレクトリの階層構造は除き、ディレクトリ直下に配置
      File file = new File(baseDir + "/" + Paths.get(entry.getName()).getFileName());

      OutputStream outputStream = new FileOutputStream(file);
      IOUtils.copy(archive, outputStream);
      outputStream.close();
    }
  }
}
