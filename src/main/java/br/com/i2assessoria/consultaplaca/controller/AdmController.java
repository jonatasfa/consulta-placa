package br.com.i2assessoria.consultaplaca.controller;

import br.com.i2assessoria.consultaplaca.entity.Usuario;
import br.com.i2assessoria.consultaplaca.payload.UploadFileResponse;
import br.com.i2assessoria.consultaplaca.service.FileStorageService;
import br.com.i2assessoria.consultaplaca.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("v1")
public class AdmController {

    private static final Logger logger = LoggerFactory.getLogger(AdmController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("adm/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("adm/downloadFile")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource();

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("adm/addUser")
    public ResponseEntity<Resource> newEmployee(@RequestBody Usuario newUsuario) {

        usuarioService.save(newUsuario);

        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("adm/restartPassword/{id}")
    public ResponseEntity<Resource> restartPassword(@PathVariable("id") Long id, HttpServletRequest request) {

        usuarioService.restartPassword(id);

        return ResponseEntity
                .ok()
                .build();
    }


}
