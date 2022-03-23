package org.inthergroup.ims.candidate.service;


import org.inthergroup.ims.candidate.controller.StorageProperties;
import org.inthergroup.ims.candidate.model.ResponseData;
import org.inthergroup.ims.exceptions.StorageException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;

@Service
public interface FileStoreService {

     ResponseData store(MultipartFile file);

     Stream<Path> loadAll();

     Resource load(String filename);
}
