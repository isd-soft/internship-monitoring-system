package org.inthergroup.ims.candidate.service;


import org.inthergroup.ims.candidate.model.ResponseData;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface FileStoreService {

     ResponseData store(MultipartFile file);

     Stream<Path> loadAll();

     Resource load(String filename);
}
