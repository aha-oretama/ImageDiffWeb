package aha.oretama.jp.service;

import aha.oretama.jp.model.ImageUrl;
import aha.oretama.jp.model.Mapping;
import aha.oretama.jp.template.ZipTemplate;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ImagesDownloadingService {

  @Autowired
  private ZipTemplate zipTemplate;

  public Mapping downloadImagesFromZip(ImageUrl imageUrl) {
    File tempFolder = createTempFolder();

    zipTemplate.createUnzipFiles(tempFolder + "/build", imageUrl.getBuildUrl(), imageUrl.getBuildUrlAuth());
    zipTemplate.createUnzipFiles(tempFolder + "/approved", imageUrl.getApprovedUrl(), imageUrl.getApprovedUrlAuth());

    return new Mapping(tempFolder.getPath());
  }

  private File createTempFolder(){
    File tempDirectory = new File(FileUtils.getTempDirectoryPath() + "/" + System.currentTimeMillis());
    if(!tempDirectory.mkdir()){
      throw new RuntimeException("Temp folder cannot be created.");
    };

    return tempDirectory;
  }

}
